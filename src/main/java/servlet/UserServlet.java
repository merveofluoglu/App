package servlet;

import dao.ActionLog.AddActionLogDao;
import dao.Message.DeleteMessageByIdDao;
import dao.Message.GetMessageByIdDao;
import dao.Message.GetMessagesByCreatorIdDao;
import dao.Message.GetMessagesByRecipientIdDao;
import dao.Permission.GetAllPermissionsDao;
import dao.Permission.GetPermissionByIdDao;
import dao.Post.DeletePostByIdDao;
import dao.Post.DeletePostByUserIdDao;
import dao.Post.UpdatePostByIdDao;
import dao.Review.DeleteReviewDao;
import dao.Review.GetReviewsByUserIdDao;
import dao.User.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;
import resource.*;
import utils.ErrorCode;
import utils.ResourceNotFoundException;
import utils.RestrictedActionException;
import utils.Validator;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.lang.Long.parseLong;
import static org.postgresql.core.Oid.JSON;

/**
 * It's a servlet that handles all the requests that are sent to the server with the path `/user`
 */
@MultipartConfig(fileSizeThreshold = 128 * 3 * 1024,
        maxFileSize = 1024 * 1024,
        maxRequestSize = 1024 * 1024 * 5)
@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends AbstractServlet{

    @Override
    protected void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
        String operation = _request.getRequestURI().split("/", 4)[3].replace("/", "");
        //String sessionRole = _request.getSession().getAttribute("role").toString();
        if (operation.contentEquals("userById")) {
            getUserDetailsByIdOp(_request, _response);
        }
        else if (operation.contentEquals("userByCreationDate")) {
            getUserDetailsByCreationDateOp(_request, _response);
        }
        else if (operation.contentEquals("userByNameSurname")) {
            getUserByNameAndSurnameOp(_request, _response);
        }
        else if (operation.contentEquals("userByUpdateDate")) {
            getUserByUpdateDateOp(_request, _response);
        }
        else if (operation.contentEquals("getAll")) {
            getAllUsersOp(_request, _response);
        }
        else if (operation.contentEquals("getProfile")) {
            getProfileData(_request, _response);
        }
        else{
            logoutOperations(_request, _response);
        }
    }

    @Override
    public void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
        //parse URI
        String op = _request.getRequestURI().split("/", 5)[3];
        switch (op) {
            // the requested operation is login
            case "login" :
                loginOperations(_request,_response);
                break;
            case "register" :
                registerationOperations(_request,_response);
                break;
            case "update" :
                updateOperations(_request,_response);
                break;
            case "delete":
                deleteUser(_request,_response);
                break;
            case "changepassword":
                changePassword(_request,_response);
                break;
            case "updatephoto":
                updatephoto(_request,_response);
                break;

            // the requested operation is unknown
            default :
                writeError(_response, ErrorCode.OPERATION_UNKNOWN);
                break;
        }
    }


    private void deleteUser(HttpServletRequest _request, HttpServletResponse _response) throws IOException {
        try {
            long _userId = Long.parseLong(_request.getRequestURI().split("/", 5)[4]);

            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);

            JSONObject _result = new JSONObject();

            List<Reviews> _reviews = new ArrayList<>();

            _reviews = new GetReviewsByUserIdDao(getConnection()).getReviewsByUserId(_userId);

            for(int i=0; i<_reviews.size();i++){
                _result.put("affectedRow", new DeleteReviewDao(getConnection()).deleteReview(_reviews.get(i).getReview_id()));
            }

            List<Message> _cretorMessages = new ArrayList<>();

            _cretorMessages = new GetMessagesByCreatorIdDao(getConnection()).getMessagesByCreatorId(_userId);

            for(int i=0; i<_cretorMessages.size();i++){
                _result.put("affectedRow", new DeleteMessageByIdDao(getConnection()).deleteMessageById(_cretorMessages.get(i).getMessage_id()));
            }

            int _deletedPosts = new DeletePostByUserIdDao(getConnection()).deletePosts(_userId);

            List<Message> _recipientMessages = new ArrayList<>();

            _recipientMessages = new GetMessagesByRecipientIdDao(getConnection()).getMessagesByRecipientId(_userId);

            for(int i=0; i<_recipientMessages.size();i++){
                _result.put("affectedRow", new DeleteMessageByIdDao(getConnection()).deleteMessageById(_recipientMessages.get(i).getMessage_id()));
            }

            _result.put("affectedRow", new DeleteUserByUseridDAO(getConnection()).DeleteUserByUseridDAO(_userId));

            _response.getWriter().write(_result.toString());

            _response.getWriter().write(_result.toString());
        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loginOperations(HttpServletRequest _request, HttpServletResponse _response) throws IOException, ServletException {

        boolean logAccess = false;
        ErrorCode errorCode = null;
        User _user = new User();
        try {
            //take from the request, the parameters (email and password)
            String email = _request.getParameter("email");
            String password = _request.getParameter("password");

            if (email.isEmpty() || email.isBlank() ) {
                errorCode = ErrorCode.EMAIL_MISSING;
                _response.setStatus(errorCode.getHTTPCode());
                writeError(_response, ErrorCode.OPERATION_UNKNOWN);

            }
            if (password.isEmpty() || password.isBlank()) {
                errorCode = ErrorCode.PASSWORD_MISSING;
                _response.setStatus(errorCode.getHTTPCode());
                writeError(_response, ErrorCode.OPERATION_UNKNOWN);
            }
            if (!Validator.isValidEmail(email)) {
                errorCode = ErrorCode.INVALID_EMAIL_ADDRESS;
                _response.setStatus(errorCode.getHTTPCode());
                writeError(_response, ErrorCode.OPERATION_UNKNOWN);

            } else if (!Validator.isValidPassword(password)) {
                errorCode = ErrorCode.INVALID_PASSWORD;
                _response.setStatus(errorCode.getHTTPCode());
                writeError(_response, ErrorCode.OPERATION_UNKNOWN);

            } else {
                JSONObject _result = new JSONObject();
                _user = new GetUserByEmailAndPasswordDAO(getConnection()).getUserByEmailandPassword(email, password).get(0);
                logAccess = true;
            }

            if (logAccess) {
                HttpSession session = _request.getSession();
                session.setAttribute("user_id", _user.getUserID());
                session.setAttribute("user",_user);
                boolean roleCheck = _user.getRole_id() == 0;
                if (roleCheck){
                    session.setAttribute("role", "admin");
                }

                else{
                    session.setAttribute("role", "user");

                }
                _response.sendRedirect(_request.getContextPath() + "/jsp/main.jsp");
            } else {
                _response.setStatus(errorCode.getHTTPCode());
            }

        } catch (ResourceNotFoundException e) {
            errorCode = ErrorCode.WRONG_CREDENTIALS;
            _response.setStatus(errorCode.getHTTPCode());
            writeError(_response, errorCode);
        } catch (Exception e) {
            writeError(_response, ErrorCode.INTERNAL_ERROR);
        }
    }
    private void registerationOperations(HttpServletRequest _request, HttpServletResponse _response) throws IOException {
        ErrorCode errorCode = null;
        User newUser = new User();
        User checkuser = new User();
        User newmailuser = new User();
        try {
            String name = _request.getParameter("name");
            String surname = _request.getParameter("surname");
            String email = _request.getParameter("email");
            String password = _request.getParameter("password");

            boolean registrationCheck = true;
            /*
            if (_request.getSession().getAttribute("role").equals("admin")) {
                registrationCheck = false;
                throw new RestrictedActionException("Only user is authorised to do this action");
            }*/
            if (!Validator.isAlphabetical(name) || !Validator.isAlphabetical(surname)) {
                registrationCheck = false;
                errorCode = ErrorCode.INVALID_USER_INFO;
                _response.setStatus(errorCode.getHTTPCode());
                writeError(_response, errorCode);


            } else if (!Validator.isValidEmail(email)) {
                registrationCheck = false;
                errorCode = ErrorCode.INVALID_EMAIL_ADDRESS;
                _response.setStatus(errorCode.getHTTPCode());
                writeError(_response, errorCode);
            } else if (!Validator.isValidPassword(password)) {
                registrationCheck = false;
                errorCode = ErrorCode.INVALID_PASSWORD;
                _response.setStatus(errorCode.getHTTPCode());
                writeError(_response, errorCode);
            } else {
                var _user = new GetUserbyEmailDAO(getConnection()).GetUserbyEmailDAO(email);
                if (_user.size() == 0){
                    newUser.setName(_request.getParameter("name"));
                    newUser.setSurname(_request.getParameter("surname"));
                    newUser.setEmail(_request.getParameter("email"));
                    newUser.setPassword(_request.getParameter("password"));
                    newUser.setRole_id(1L);
                    newUser.setCreation_date(new Timestamp(System.currentTimeMillis()));
                    newUser.setUpdate_date(new Timestamp(System.currentTimeMillis()));
                    newUser.setIs_deleted(false);
                    newUser.setProfile_photo(null);
                    JSONObject _result = new JSONObject();
                    _result.put("data", new CreateUserDAO(getConnection()).createUser(newUser));
                    _response.getWriter().write(_result.toString());
                }
                else{
                    checkuser = _user.get(_user.size()-1);
                    if (checkuser.getIsDeleted() == true){
                        if (checkuser.getPassword() != password){
                            newUser.setName(_request.getParameter("name"));
                            newUser.setSurname(_request.getParameter("surname"));
                            newUser.setEmail(_request.getParameter("email"));
                            newUser.setPassword(_request.getParameter("password"));
                            newUser.setRole_id(1L);
                            newUser.setCreation_date(new Timestamp(System.currentTimeMillis()));
                            newUser.setUpdate_date(new Timestamp(System.currentTimeMillis()));
                            newUser.setIs_deleted(false);
                            newUser.setProfile_photo(null);
                            JSONObject _result = new JSONObject();
                            _result.put("data", new CreateUserDAO(getConnection()).createUser(newUser));
                            _response.getWriter().write(_result.toString());
                        }
                        else{
                            checkuser.setIs_deleted(false);
                            JSONObject _result = new JSONObject();
                            _result.put("data", new UpdateUserByIdDAO(getConnection()).UpdateUserByIdDAO(checkuser.getUserID(),checkuser));
                            _response.getWriter().write(_result.toString());
                        }

                    }
                }

                //registrationCheck = true;
            }
        } catch (Exception e) {
            writeError(_response, ErrorCode.INTERNAL_ERROR);
        }
    }
    private void logoutOperations(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        try {
            HttpSession _session = req.getSession();

            long _userId = (long) _session.getAttribute("user_id");
            /*
            String _user = String.valueOf(_userId);
            String _role = (String) _session.getAttribute("role");
            ActionLog _actionlog = new ActionLog();

            if (_role == "admin"){
                _actionlog.setDescription("Admin logged out!");
                _actionlog.setIs_user_act(true);
                _actionlog.setIs_system_act(false);
                _actionlog.setAction_date(new Timestamp(System.currentTimeMillis()));
                _actionlog.setUser_id(_userId);
            }
            else{
                _actionlog.setDescription("User #"+ _user +" logged out!");
                _actionlog.setIs_user_act(true);
                _actionlog.setIs_system_act(false);
                _actionlog.setAction_date(new Timestamp(System.currentTimeMillis()));
                _actionlog.setUser_id(_userId);
            }
            JSONObject res = new JSONObject();
            resp.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = resp.getWriter();

            res.put("data", new AddActionLogDao(getConnection()).addActionLog(_actionlog));
            resp.getWriter().write(res.toString());

             */
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
        //resp.sendRedirect("/jsp/login.jsp");
        //req.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req,resp);
    }
    private void changePassword(HttpServletRequest _request, HttpServletResponse _response) throws IOException {
        try {
            HttpSession _session = _request.getSession();
            long _userId = (long) _session.getAttribute("user_id");
            String password = _request.getParameter("password");
            _response.setContentType("application/json");
            JSONObject res = new JSONObject();
            _response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = _response.getWriter();

            res.put("data", new ChangePasswordDao(getConnection()).ChangePasswordDao(_userId,password));
            _response.getWriter().write(res.toString());
            _response.sendRedirect(_request.getContextPath() + "/jsp/login.jsp");
        } catch (Exception e) {
            System.out.println("internal error");
            writeError(_response, ErrorCode.INTERNAL_ERROR);
        }
    }

    private void updatephoto(HttpServletRequest _request, HttpServletResponse _response) throws IOException {
        try {
            HttpSession _session = _request.getSession();
            long _userId = (long) _session.getAttribute("user_id");
            String password = _request.getParameter("password");
            byte[] pp_path = _request.getParameter("file").getBytes();
            _response.setContentType("application/json");
            JSONObject res = new JSONObject();
            _response.setStatus(HttpServletResponse.SC_OK);

            res.put("data", new UpdateProfilePhotoByUserIdDao(getConnection()).UpdateProfilePhotoByUserIdDao(_userId,pp_path));
            _response.getWriter().write(res.toString());
            _response.sendRedirect(_request.getContextPath() + "/jsp/profile.jsp");

        } catch (Exception e) {
            System.out.println("internal error");
            writeError(_response, ErrorCode.INTERNAL_ERROR);
        }
    }



    private void updateOperations(HttpServletRequest _request, HttpServletResponse _response) throws IOException {
        try {
            User _user = new User();
            HttpSession _session = _request.getSession();
            long _user_id = (long) _session.getAttribute("user_id");
            String name = _request.getParameter("name");
            String surname = _request.getParameter("surname");
            String email = _request.getParameter("email");

            JSONObject error = new JSONObject();
            JSONObject message = new JSONObject();
            _response.setContentType( "application/json");
            PrintWriter out = _response.getWriter();

            int errorCount = 0;

            if (!Validator.isAlphabetical(name)) {
                errorCount++;
                System.out.println(errorCount);
            }
            if (!Validator.isAlphabetical(surname)) {
                errorCount++;
                System.out.println(errorCount);
            }
            if (!Validator.isValidEmail(email)) {
                errorCount++;
                System.out.println(errorCount);
            }
            if(errorCount >= 1){
                _response.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
                out.print(error);
                out.flush();
                out.close();
            }
            else {
                _response.setContentType("application/json");
                JSONObject res = new JSONObject();
                _response.setStatus(HttpServletResponse.SC_OK);
                User temp = new GetUserByUseridDAO(getConnection()).GetUserByUseridDAO(_user_id);
                _user.setName(_request.getParameter("name"));
                _user.setSurname(_request.getParameter("surname"));
                _user.setEmail(_request.getParameter("email"));
                _user.setUpdate_date(new Timestamp(System.currentTimeMillis()));
                JSONObject _result = new JSONObject();
                _result.put("data", new UpdateUserByIdDAO(getConnection()).UpdateUserByIdDAO(_user_id, _user));
                _response.getWriter().write(_result.toString());
                _response.sendRedirect(_request.getContextPath() + "/jsp/profile.jsp");
            }
        } catch (Exception e) {
            System.out.println("internal error");
            writeError(_response, ErrorCode.INTERNAL_ERROR);
        }
    }

    private void getUserDetailsByIdOp (HttpServletRequest _request, HttpServletResponse _response) {

        // This method returns a permission.
        try {
            long _user_id = parseLong(_request.getParameter("user_id"));
            JSONObject _result = new JSONObject();
            _result.put("data", new GetUserByUseridDAO(getConnection()).GetUserByUseridDAO(_user_id));
            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);
            _response.getWriter().write(_result.toString());

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }

    private void getProfileData (HttpServletRequest _request, HttpServletResponse _response) {
        HttpSession _session = _request.getSession();
        // This method returns a permission.
        try {
            long _user_id = (long) _session.getAttribute("user_id");
            JSONObject _result = new JSONObject();
            _result.put("data", new GetUserByUseridDAO(getConnection()).GetUserByUseridDAO(_user_id));
            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);
            _response.getWriter().write(_result.toString());
            _session.setAttribute("data",_result.getJSONObject("data"));
            _request.getRequestDispatcher("jsp/profile.jsp").forward(_request, _response);
        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    private void getUserDetailsByCreationDateOp (HttpServletRequest _request, HttpServletResponse _response) {

        // This method returns a json array with the roles.
        ArrayList<User> _users;
        try {
            Timestamp _creation_date = Timestamp.valueOf(_request.getParameter("role_id"));
            JSONObject _result = new JSONObject();
            _result.put("data",new GetUserByCreationDateDAO(getConnection()).GetUserByCreationDateDAO(_creation_date));
            _response.setStatus(HttpServletResponse.SC_OK);
            _response.setContentType("application/json");
            _response.getWriter().write(_result.toString());

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }

    private void getUserByRoleIdOp (HttpServletRequest _request, HttpServletResponse _response) {

        ArrayList<User> _users;
        try {
            long _role_id = Long.parseLong(_request.getParameter("role_id"));
            JSONObject _result = new JSONObject();
            _result.put("data",new GetUserByRoleIdDAO(getConnection()).GetUserByRoleIdDAO(_role_id));
            _response.setStatus(HttpServletResponse.SC_OK);
            _response.setContentType("application/json");
            _response.getWriter().write(_result.toString());

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }

    private void getAllUsersOp (HttpServletRequest _request, HttpServletResponse _response) {

        try {
            JSONObject _result = new JSONObject();
            _result.put("data",new GetAllUsersDAO(getConnection()).getAllUsers());
            _response.getWriter().write(_result.toString());

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }

    private void getUserByEmailOp (HttpServletRequest _request, HttpServletResponse _response) {

        try {
            String _email = _request.getParameter("email");
            JSONObject _result = new JSONObject();
            _result.put("data",new GetUserbyEmailDAO(getConnection()).GetUserbyEmailDAO(_email));
            _response.setStatus(HttpServletResponse.SC_OK);
            _response.setContentType("application/json");
            _response.getWriter().write(_result.toString());

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }

    private void getUserByNameOp (HttpServletRequest _request, HttpServletResponse _response) {

        try {
            String _name = _request.getParameter("name");
            JSONObject _result = new JSONObject();
            _result.put("data",new GetUserbyNameDAO(getConnection()).getUserByName(_name));
            _response.setStatus(HttpServletResponse.SC_OK);
            _response.setContentType("application/json");
            _response.getWriter().write(_result.toString());

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }

    private void getUserByNameAndSurnameOp (HttpServletRequest _request, HttpServletResponse _response) {

        try {
            String _name = _request.getParameter("name");
            String _surname = _request.getParameter("surname");
            JSONObject _result = new JSONObject();
            _result.put("data",new GetUserByNameAndSurnameDAO(getConnection()).getUserByNameAndSurname(_name,_surname));
            _response.setStatus(HttpServletResponse.SC_OK);
            _response.setContentType("application/json");
            _response.getWriter().write(_result.toString());

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }

    private void getUserByUpdateDateOp (HttpServletRequest _request, HttpServletResponse _response) {

        // This method returns a json array with the roles.
        ArrayList<User> _users;
        try {
            Timestamp _update_date = Timestamp.valueOf(_request.getParameter("update_date"));
            JSONObject _result = new JSONObject();
            _result.put("data",new GetUserByUpdateDateDAO(getConnection()).GetUserByUpdateDateDAO(_update_date));
            _response.setStatus(HttpServletResponse.SC_OK);
            _response.setContentType("application/json");
            _response.getWriter().write(_result.toString());

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }
}
