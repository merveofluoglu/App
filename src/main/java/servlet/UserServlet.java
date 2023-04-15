package servlet;

import dao.ActionLog.AddActionLogDao;
import dao.Permission.GetAllPermissionsDao;
import dao.Permission.GetPermissionByIdDao;
import dao.Post.UpdatePostByIdDao;
import dao.User.CreateUserDAO;
import dao.User.DeleteUserByUseridDAO;
import dao.User.GetAllUsersDAO;
import dao.User.GetUserByCreationDateDAO;
import dao.User.GetUserByEmailAndPasswordDAO;
import dao.User.GetUserbyEmailDAO;
import dao.User.GetUserByNameAndSurnameDAO;
import dao.User.GetUserbyNameDAO;
import dao.User.GetUserByNameSurnameEmailDAO;
import dao.User.GetUserByRoleIdDAO;
import dao.User.GetUserbySurnameDAO;
import dao.User.GetUserByUpdateDateDAO;
import dao.User.GetUserByUseridDAO;
import dao.User.UpdateUserByIdDAO;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;
import resource.ActionLog;
import resource.Permission;
import resource.Post;
import resource.User;
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
import java.util.Properties;

import static java.lang.Long.parseLong;

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
        String sessionRole = _request.getSession().getAttribute("role").toString();
        if (operation.contentEquals("userById")) {
            getUserDetailsByIdOp(_request, _response);
        }
        else if (operation.contentEquals("userByCreationDate")) {
            getUserDetailsByCreationDateOp(_request, _response);
        }
        else if (operation.contentEquals("users")) {
            getAllUsersOp(_request, _response);
        }
        else if (operation.contentEquals("userByRoleId")) {
            getUserByRoleIdOp(_request, _response);
        }
        else if (operation.contentEquals("userByEmail")) {
            getUserByEmailOp(_request, _response);
        }
        else if (operation.contentEquals("userByName")) {
            getUserByNameOp(_request, _response);
        }
        else if (operation.contentEquals("userByNameSurname")) {
            getUserByNameAndSurnameOp(_request, _response);
        }
        else if (operation.contentEquals("userByUpdateDate")) {
            getUserByUpdateDateOp(_request, _response);
        }
        else if (operation.contentEquals("logout")) {
            logoutOperations(_request, _response);
        }
        else{
            writeError(_response, ErrorCode.OPERATION_UNKNOWN);
        }
    }

    @Override
    public void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
        //parse URI
        String op = _request.getRequestURI().split("/", 4)[3].replace("/", "");
        switch (op) {
            // the requested operation is login
            case "protected/login" :
                loginOperations(_request,_response);
            case "protected/register" :
                registerationOperations(_request,_response);
            case "update" :
                updateOperations(_request,_response);

            // the requested operation is unknown
            default :
                writeError(_response, ErrorCode.OPERATION_UNKNOWN);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest _request, HttpServletResponse _response) throws  IOException {
        String op = _request.getRequestURI().split("/", 4)[3];

        if ("/protected/delete".equals(op)) {
            deleteUser(_request, _response);
        } else {
            writeError(_response, ErrorCode.OPERATION_UNKNOWN);
        }
    }

    private void deleteUser(HttpServletRequest _request, HttpServletResponse _response) throws IOException {
        try {
            long _user_id = Long.parseLong(_request.getParameter("user_id"));
            if (!Validator.isValidGuid(String.valueOf(_user_id))) {
                ErrorCode errorCode = ErrorCode.INVALID_GUID;
                _response.setStatus(errorCode.getHTTPCode());
                writeError(_response, errorCode);
            } else {
                JSONObject _result = new JSONObject();

                _result.put("data", new DeleteUserByUseridDAO(getConnection()).DeleteUserByUseridDAO(_user_id));
                _response.setStatus(HttpServletResponse.SC_OK);
                _response.setContentType("application/json");
                _response.getWriter().write(_result.toString());
            }
        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }

    private void loginOperations(HttpServletRequest _request, HttpServletResponse _response) throws IOException, ServletException {

        boolean logAccess = false;
        ErrorCode errorCode = null;

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


            User _user_logged_in = null;
            if (!Validator.isValidEmail(email)) {
                errorCode = ErrorCode.INVALID_EMAIL_ADDRESS;
                _response.setStatus(errorCode.getHTTPCode());
                writeError(_response, ErrorCode.OPERATION_UNKNOWN);

            } else if (!Validator.isValidPassword(password)) {
                errorCode = ErrorCode.INVALID_PASSWORD;
                _response.setStatus(errorCode.getHTTPCode());
                writeError(_response, ErrorCode.OPERATION_UNKNOWN);

            } else {
                _user_logged_in = new GetUserByEmailAndPasswordDAO(getConnection()).getUserByEmailandPassword(email, password).get(0);
                logAccess = true;
            }


            if (logAccess) {

                HttpSession session = _request.getSession();
                session.setAttribute("user_id", _user_logged_in.getUserID());
                boolean roleCheck = _user_logged_in.getRole_id() == 0;
                if (roleCheck){
                    session.setAttribute("role", "admin");
                }

                else{
                    session.setAttribute("role", "user");
                }
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
        try {
            String name = _request.getParameter("name");
            String surname = _request.getParameter("surname");
            String email = _request.getParameter("email");
            String password = _request.getParameter("password");
            Long role_id = Long.valueOf(_request.getParameter("role_id"));
            Timestamp creation_date = Timestamp.valueOf(_request.getParameter("creation_date"));
            Timestamp update_date = Timestamp.valueOf(_request.getParameter("update_date"));
            byte[] pp_path = _request.getParameter("pp_path").getBytes();


            boolean registrationCheck = true;
            if (_request.getSession().getAttribute("role").equals("admin")) {
                registrationCheck = false;
                throw new RestrictedActionException("Only user is authorised to do this action");
            }
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
                _response.setContentType("application/json");
                JSONObject _result = new JSONObject();
                _response.setStatus(HttpServletResponse.SC_OK);
                User newUser = null;
                newUser.setName(name);
                newUser.setSurname(surname);
                newUser.setEmail(email);
                newUser.setCreation_date(new Timestamp(System.currentTimeMillis()));
                newUser.setUpdate_date(new Timestamp(System.currentTimeMillis()));
                User user = new CreateUserDAO(getConnection(),newUser).createUser(newUser);
                _result.put("data", user.toString());
                _response.getWriter().write(_result.toString());
                registrationCheck = true;
            }
        } catch (RestrictedActionException e) {
            writeError(_response, ErrorCode.ADMIN_ONLY_ACTION);
        } catch (Exception e) {
            writeError(_response, ErrorCode.INTERNAL_ERROR);
        }
    }
    private void logoutOperations(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            boolean user = req.getSession().getAttribute("role") == "user";
            ActionLog actionlog = null;
            actionlog.setUser_id((Long) req.getSession().getAttribute("user_id"));
            if (user){
                actionlog.setDescription("User logged out!");
                actionlog.setIs_user_act(true);
                actionlog.setIs_system_act(false);
            }
            else{
                actionlog.setDescription("Admin logged out!");
                actionlog.setIs_user_act(false);
                actionlog.setIs_system_act(true);
            }
            //AddActionLogDao.addActionLog(getConnection(), actionlog);
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }




    private void updateOperations(HttpServletRequest _request, HttpServletResponse _response) throws IOException {
        try {
            boolean userRole = _request.getSession().getAttribute("role") == "user";
            boolean imageCheck = Boolean.parseBoolean(_request.getParameter("pp_path"));
            System.out.println(imageCheck);

            Long user_id = Long.valueOf(_request.getSession().getAttribute("user_id").toString());
            String name = _request.getParameter("name");
            String surname = _request.getParameter("surname");
            String email = _request.getParameter("email");
            Long role_id = Long.valueOf(_request.getParameter("role_id"));
            Timestamp creation_date = Timestamp.valueOf(_request.getParameter("creation_date"));
            Timestamp update_date = Timestamp.valueOf(_request.getParameter("update_date"));
            byte[] pp_path = _request.getParameter("pp_path").getBytes();


            User user = new GetUserByUseridDAO(getConnection())
                    .GetUserByUseridDAO(user_id);

            JSONObject error = new JSONObject();
            JSONObject message = new JSONObject();
            _response.setContentType( "application/json");
            PrintWriter out = _response.getWriter();

            int errorCount = 0;

            if(imageCheck){
                byte[] userImage = DatatypeConverter.parseBase64Binary(
                        _request.getParameter("pp_path").split(",")[1]);
                if (userImage.length > 128 * 3 * 1024) {
                    errorCount++;
                    System.out.println(errorCount);
                }
            }
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
                if(imageCheck) {
                    byte[] userImage = DatatypeConverter.parseBase64Binary(
                            _request.getParameter("userImage").split(",")[1]);
                    User newUser = new User(user_id,
                            name.equals(user.getName()) ? user.getName() : name,
                            surname.equals(user.getSurname()) ? user.getSurname() : surname,
                            email.equals(user.getEmail()) ? user.getEmail() : email,
                            Long.valueOf(role_id.equals(user.getRole_id()) ? user.getRole_id() : role_id),
                            creation_date.equals(user.getCreation_date()) ? user.getCreation_date() : creation_date,
                            update_date.equals(user.getUpdate_date()) ? user.getUpdate_date(): update_date,
                            userImage.equals(user.getProfile_photo()) ? user.getProfile_photo():pp_path);

                    User userUpdated = new UpdateUserByIdDAO(getConnection()).
                            UpdateUserByIdDAO(user_id, newUser);
                }else{
                    User newUser = new User(user_id,
                            name.equals(user.getName()) ? user.getName() : name,
                            surname.equals(user.getSurname()) ? user.getSurname() : surname,
                            email.equals(user.getEmail()) ? user.getEmail() : email,
                            Long.valueOf(role_id.equals(user.getRole_id()) ? user.getRole_id() : role_id),
                            creation_date.equals(user.getCreation_date()) ? user.getCreation_date() : creation_date,
                            update_date.equals(user.getUpdate_date()) ? user.getUpdate_date(): update_date,
                            null);
                    User userUpdated = new UpdateUserByIdDAO(getConnection())
                            .UpdateUserByIdDAO(user_id,newUser);
                }

                out.print(res.put("msg","success!"));
                out.flush();
                out.close();
            }
        } catch (ResourceNotFoundException e) {
            writeError(_response, ErrorCode.NO_SUCH_RESOURCE);
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

        ArrayList<User> _users;
        try {
            JSONObject _result = new JSONObject();
            _result.put("data",new GetAllUsersDAO(getConnection()).getAllUsers());
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
