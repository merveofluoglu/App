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
import dao.Post.GetPostByIdDao;
import dao.Post.UpdatePostByIdDao;
import dao.PostFiles.GetPostFileByPostIdDao;
import dao.PostFiles.GetPostFilesByIdDao;
import dao.Review.DeleteReviewDao;
import dao.Review.GetReviewsByUserIdDao;
import dao.User.UploadProfilePhotoDAO;
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
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

import static java.lang.Long.parseLong;
import static org.postgresql.core.Oid.JSON;

/**
 * It's a servlet that handles all the requests that are sent to the server with the path `/user`
 */
@MultipartConfig(fileSizeThreshold=1024*1024*10,
        maxFileSize=1024*1024*10, maxRequestSize=1024*1024*5*5)
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
            try {
                logoutOperations(_request, _response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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
                registrationOperations(_request,_response);
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
            /*
            case "updatephoto":
                updatephoto(_request,_response);
                break;
            */
            case "upload" :
                uploadUserPP(_request, _response);
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
                _result.put("affectedRow", new DeleteReviewDao(getConnection()).deleteReview(_reviews.get(i).getReviewId()));
            }

            List<Message> _cretorMessages = new ArrayList<>();

            _cretorMessages = new GetMessagesByCreatorIdDao(getConnection()).getMessagesByCreatorId(_userId);

            for(int i=0; i<_cretorMessages.size();i++){
                _result.put("affectedRow", new DeleteMessageByIdDao(getConnection()).deleteMessageById(_cretorMessages.get(i).getMessageId()));
            }

            int _deletedPosts = new DeletePostByUserIdDao(getConnection()).deletePosts(_userId);

            List<Message> _recipientMessages = new ArrayList<>();

            _recipientMessages = new GetMessagesByRecipientIdDao(getConnection()).getMessagesByRecipientId(_userId);

            for(int i=0; i<_recipientMessages.size();i++){
                _result.put("affectedRow", new DeleteMessageByIdDao(getConnection()).deleteMessageById(_recipientMessages.get(i).getMessageId()));
            }

            _result.put("affectedRow", new DeleteUserByUseridDAO(getConnection()).DeleteUserByUseridDAO(_userId));

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
        HttpSession session = _request.getSession();
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
                if (_user.getPpPath() != null) {
                    String encoded = Base64.getEncoder().encodeToString(_user.getPpPath());
                    _user.setBase64(encoded);
                    _user.setFileMediaType(_user.getFileMediaType());
                }
                session.setAttribute("userId", _user.getUserId());
                session.setAttribute("password", _user.getPassword());
                session.setAttribute("user",_user);
                boolean roleCheck = _user.getRoleId() == 0;
                if (roleCheck){
                    session.setAttribute("role", "admin");
                }

                else{
                    session.setAttribute("role", "user");

                }
                _response.sendRedirect(_request.getContextPath() + "/jsp/main-page/mainpage.jsp");
            } else {
                _response.setStatus(errorCode.getHTTPCode());
            }
            new AddActionLogDao(getConnection()).addActionLog(new ActionLog(true, false, "User logged in!", new Timestamp(System.currentTimeMillis()), (Long) session.getAttribute("userId")));

        } catch (ResourceNotFoundException e) {
            errorCode = ErrorCode.WRONG_CREDENTIALS;
            _response.setStatus(errorCode.getHTTPCode());
            writeError(_response, errorCode);
        } catch (Exception e) {
            writeError(_response, ErrorCode.INTERNAL_ERROR);
        }
    }
    private void registrationOperations(HttpServletRequest _request, HttpServletResponse _response) throws IOException {
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
                    newUser.setRoleId(1L);
                    newUser.setCreationDate(new Timestamp(System.currentTimeMillis()));
                    newUser.setUpdateDate(new Timestamp(System.currentTimeMillis()));
                    newUser.setDeleted(false);
                    newUser.setPpPath(null);
                    JSONObject _result = new JSONObject();
                    _result.put("data", new CreateUserDAO(getConnection()).createUser(newUser));
                    new AddActionLogDao(getConnection()).addActionLog(new ActionLog(false, true, "New user registered!", new Timestamp(System.currentTimeMillis()), 0));
                    _response.getWriter().write(_result.toString());
                    _response.sendRedirect(_request.getContextPath() + "/jsp/login.jsp");
                }
                else{
                    checkuser = _user.get(_user.size()-1);
                    if (checkuser.isDeleted() == true){
                        if (checkuser.getPassword() != password){
                            newUser.setName(_request.getParameter("name"));
                            newUser.setSurname(_request.getParameter("surname"));
                            newUser.setEmail(_request.getParameter("email"));
                            newUser.setPassword(_request.getParameter("password"));
                            newUser.setRoleId(1L);
                            newUser.setCreationDate(new Timestamp(System.currentTimeMillis()));
                            newUser.setUpdateDate(new Timestamp(System.currentTimeMillis()));
                            newUser.setDeleted(false);
                            newUser.setPpPath(null);
                            JSONObject _result = new JSONObject();
                            _result.put("data", new CreateUserDAO(getConnection()).createUser(newUser));
                            new AddActionLogDao(getConnection()).addActionLog(new ActionLog(false, true, "New user registered!", new Timestamp(System.currentTimeMillis()), 0));
                            _response.getWriter().write(_result.toString());
                            _response.sendRedirect(_request.getContextPath() + "/jsp/login.jsp");
                        }
                        else{
                            checkuser.setDeleted(false);
                            JSONObject _result = new JSONObject();
                            _result.put("data", new UpdateUserByIdDAO(getConnection()).UpdateUserByIdDAO(checkuser.getUserId(),checkuser));
                            new AddActionLogDao(getConnection()).addActionLog(new ActionLog(false, true, "New user registered!", new Timestamp(System.currentTimeMillis()), 0));
                            _response.getWriter().write(_result.toString());
                            _response.sendRedirect(_request.getContextPath() + "/jsp/login.jsp");
                        }

                    }
                }

                //registrationCheck = true;
            }
        } catch (Exception e) {
            writeError(_response, ErrorCode.INTERNAL_ERROR);
        }
    }
    private void logoutOperations(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {

        try {

            HttpSession _session = req.getSession();

            long _userId = (long) _session.getAttribute("userId");
            /*
            String _user = String.valueOf(_userId);
            String _role = (String) _session.getAttribute("role");
            ActionLog _actionlog = new ActionLog();

            if (_role == "admin"){
                _actionlog.setDescription("Admin logged out!");
                _actionlog.setIs_user_act(true);
                _actionlog.setIs_system_act(false);
                _actionlog.setAction_date(new Timestamp(System.currentTimeMillis()));
                _actionlog.setUserId(_userId);
            }
            else{
                _actionlog.setDescription("User #"+ _user +" logged out!");
                _actionlog.setIs_user_act(true);
                _actionlog.setIs_system_act(false);
                _actionlog.setAction_date(new Timestamp(System.currentTimeMillis()));
                _actionlog.setUserId(_userId);
            }
            JSONObject res = new JSONObject();
            resp.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = resp.getWriter();

            res.put("data", new AddActionLogDao(getConnection()).addActionLog(_actionlog));
            resp.getWriter().write(res.toString());

             */
            new AddActionLogDao(getConnection()).addActionLog(new ActionLog(true, false, "User logged out!", new Timestamp(System.currentTimeMillis()), _userId));

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
            long _userId = (long) _session.getAttribute("userId");
            JSONObject res = new JSONObject();

            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);

            res.put("data", new ChangePasswordDao(getConnection()).ChangePasswordDao(_userId, _request.getParameter("password")));
            new AddActionLogDao(getConnection()).addActionLog(new ActionLog(true, false, "User changed password!", new Timestamp(System.currentTimeMillis()), _userId));

            _response.getWriter().write(res.toString());
            _response.sendRedirect(_request.getContextPath() + "/jsp/login.jsp");
        } catch (Exception e) {
            System.out.println("internal error");
            writeError(_response, ErrorCode.INTERNAL_ERROR);
        }
    }
    /*
    private void updatephoto(HttpServletRequest _request, HttpServletResponse _response) throws IOException {
        try {
            HttpSession _session = _request.getSession();
            long _userId = (long) _session.getAttribute("userId");
            String password = _request.getParameter("password");
            byte[] ppPath = _request.getParameter("file").getBytes();
            _response.setContentType("application/json");
            JSONObject res = new JSONObject();
            _response.setStatus(HttpServletResponse.SC_OK);

            res.put("data", new UpdateProfilePhotoByUserIdDao(getConnection()).UpdateProfilePhotoByUserIdDao(_userId,ppPath));
            new AddActionLogDao(getConnection()).addActionLog(new ActionLog(true, false, "User changed profile photo!", new Timestamp(System.currentTimeMillis()), _userId));

            _response.getWriter().write(res.toString());
            _response.sendRedirect(_request.getContextPath() + "/jsp/profile.jsp");

        } catch (Exception e) {
            System.out.println("internal error");
            writeError(_response, ErrorCode.INTERNAL_ERROR);
        }
    }
    */

    private void uploadUserPP(HttpServletRequest _request, HttpServletResponse _response) {

        User _userPP = null;
        JSONObject _result = new JSONObject();
        String encoded = "";
        String fileMediaType = "";
        try {
            _userPP = parseRequest(_request);
           _result.put("affectedRows", new UpdateProfilePhotoByUserIdDao(getConnection()).UpdateProfilePhotoByUserIdDao(_userPP));
            encoded = Base64.getEncoder().encodeToString(_userPP.getPpPath());
            fileMediaType = _userPP.getFileMediaType();
            HttpSession _session = _request.getSession();
            User _temp = (User) _session.getAttribute("user");
            _temp.setBase64(encoded);
            _temp.setFileMediaType(fileMediaType);
            _session.setAttribute("user", _temp);
            _response.getWriter().write(_temp.toString());
            _response.sendRedirect(_request.getContextPath() + "/jsp/profile.jsp");
        } catch (SQLException | ServletException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    private User parseRequest(HttpServletRequest _request) throws ServletException, IOException {
        HttpSession _session = _request.getSession();
        long userId = (long) _session.getAttribute("userId");
        byte[] ppPath = null;

        for (Part p : _request.getParts()) {
            switch (p.getName()) {
                case "ppPath":

                    try (InputStream is = p.getInputStream()) {
                        ppPath = is.readAllBytes();
                    }
                    break;
            }
        }
        return new User(userId, ppPath);
    }


    private void updateOperations(HttpServletRequest _request, HttpServletResponse _response) throws IOException {
        try {
            User _user = new User();
            HttpSession _session = _request.getSession();
            long _userId = Long.parseLong(_request.getParameter("userId"));
            String name = _request.getParameter("name");
            String surname = _request.getParameter("surname");
            String encoded = "";

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
                User temp = new GetUserByUseridDAO(getConnection()).GetUserByUseridDAO(_userId);
                if(temp.getPpPath() != null){
                    _user.setName(_request.getParameter("name"));
                    _user.setSurname(_request.getParameter("surname"));
                    _user.setEmail(temp.getEmail());
                    _user.setPpPath(temp.getPpPath());
                    _user.setPassword(temp.getPassword());
                    _user.setCreationDate(temp.getCreationDate());
                    _user.setRoleId(temp.getRoleId());
                    _user.setDeleted(temp.isDeleted());
                    encoded = Base64.getEncoder().encodeToString(temp.getPpPath());
                    _user.setBase64(encoded);
                    _user.setFileMediaType(temp.getFileMediaType());
                    _user.setUpdateDate(new Timestamp(System.currentTimeMillis()));
                    JSONObject _result = new JSONObject();
                    _result.put("data", new UpdateUserByIdDAO(getConnection()).UpdateUserByIdDAO(_userId, _user));
                    new AddActionLogDao(getConnection()).addActionLog(new ActionLog(true, false, "User have been updated!", new Timestamp(System.currentTimeMillis()), _userId));
                    _request.getSession().setAttribute("user", _user);
                    _response.getWriter().write(_result.toString());
                    _response.sendRedirect(_request.getContextPath() + "/jsp/profile.jsp");
                }
                else{
                    _user.setName(_request.getParameter("name"));
                    _user.setSurname(_request.getParameter("surname"));
                    _user.setEmail(temp.getEmail());
                    _user.setBase64("");
                    _user.setFileMediaType("");
                    _user.setPpPath(new byte[0]);
                    _user.setPassword(temp.getPassword());
                    _user.setCreationDate(temp.getCreationDate());
                    _user.setRoleId(temp.getRoleId());
                    _user.setDeleted(temp.isDeleted());
                    _user.setUpdateDate(new Timestamp(System.currentTimeMillis()));
                    JSONObject _result = new JSONObject();
                    _result.put("data", new UpdateUserByIdDAO(getConnection()).UpdateUserByIdDAO(_userId, _user));
                    new AddActionLogDao(getConnection()).addActionLog(new ActionLog(true, false, "User have been updated!", new Timestamp(System.currentTimeMillis()), _userId));
                    _request.getSession().setAttribute("user", _user);

                    _response.getWriter().write(_result.toString());
                    _response.sendRedirect(_request.getContextPath() + "/jsp/profile.jsp");
                }

            }
        } catch (Exception e) {
            System.out.println("internal error");
            writeError(_response, ErrorCode.INTERNAL_ERROR);
        }
    }

    private void getUserDetailsByIdOp (HttpServletRequest _request, HttpServletResponse _response) {

        // This method returns a permission.
        try {
            long _userId = parseLong(_request.getParameter("userId"));
            JSONObject _result = new JSONObject();
            _result.put("data", new GetUserByUseridDAO(getConnection()).GetUserByUseridDAO(_userId));
            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);
            new AddActionLogDao(getConnection()).addActionLog(new ActionLog(true, false, "User details fetched!", new Timestamp(System.currentTimeMillis()), _userId));
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
            long _userId = (long) _session.getAttribute("userId");
            JSONObject _result = new JSONObject();

            User users = new GetUserByUseridDAO(getConnection()).GetUserByUseridDAO(_userId);
            String encoded = Base64.getEncoder().encodeToString(users.getPpPath());
            users.setBase64(encoded);
            users.setFileMediaType(users.getFileMediaType());

            _result.put("data", users);
            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);
            _response.getWriter().write(_result.toString());
            _session.setAttribute("data",_result.getJSONObject("data"));
        new AddActionLogDao(getConnection()).addActionLog(new ActionLog(true, false, "User profile fetched!", new Timestamp(System.currentTimeMillis()), _userId));
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
            HttpSession _session = _request.getSession();

            Timestamp _creation_date = Timestamp.valueOf(_request.getParameter("creationDate"));

            JSONObject _result = new JSONObject();

            _result.put("data",new GetUserByCreationDateDAO(getConnection()).GetUserByCreationDateDAO(_creation_date));

            new AddActionLogDao(getConnection()).addActionLog(new ActionLog(true, false, "User profile fetched!", new Timestamp(System.currentTimeMillis()), (Long) _session.getAttribute("userId")));

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
            long _roleId = Long.parseLong(_request.getParameter("roleId"));
            JSONObject _result = new JSONObject();
            _result.put("data",new GetUserByRoleIdDAO(getConnection()).GetUserByRoleIdDAO(_roleId));
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
            HttpSession _session = _request.getSession();

            JSONObject _result = new JSONObject();

            _result.put("data",new GetAllUsersDAO(getConnection()).getAllUsers());

            new AddActionLogDao(getConnection()).addActionLog(new ActionLog(true, false, "All users have been fetched!", new Timestamp(System.currentTimeMillis()), (Long) _session.getAttribute("userId")));

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
            Timestamp _updateDate = Timestamp.valueOf(_request.getParameter("updateDate"));
            JSONObject _result = new JSONObject();
            _result.put("data",new GetUserByUpdateDateDAO(getConnection()).GetUserByUpdateDateDAO(_updateDate));
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
