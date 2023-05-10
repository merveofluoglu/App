package servlet;

import dao.Role.CreateRoleDAO;
import dao.Role.DeleteRoleByIdDAO;
import dao.Role.GetAllRolesDAO;
import dao.Role.UpdateRoleDAO;
import dao.RolePermission.DeleteRolePermissionByIdDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;
import resource.Role;
import utils.ErrorCode;
import utils.ResourceNotFoundException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.Long.parseLong;

@WebServlet(name = "RoleServlet", value = "/RoleServlet")
public class RoleServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
        String _op = _request.getRequestURI().split("/", 4)[3].replace("/", "");
        String sessionRole = _request.getSession().getAttribute("role").toString();
        if (_op.contentEquals("allRoles") && sessionRole =="admin") {
            getRolesDetailsOp(_request, _response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {

        String _op = _request.getRequestURI().split("/", 4)[3];
        System.out.println(_op);
        String sessionRole = _request.getSession().getAttribute("role").toString();

        switch (_op) {
            case "protected/add" :
                if(sessionRole == "admin"){
                    addRole(_request, _response);
                    break;
                }
            case "update" :
                if(sessionRole == "admin"){
                    updateRole(_request, _response);
                    break;
                }

            default :
                writeError(_response, ErrorCode.OPERATION_UNKNOWN);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest _request, HttpServletResponse _response) throws  IOException {
        String op = _request.getRequestURI().split("/", 4)[3];
        String sessionRole = _request.getSession().getAttribute("role").toString();
        if ("/protected/deleteRole".equals(op)) {
            if(sessionRole == "admin"){
                deleteRole(_request, _response);
            }
        } else {
            writeError(_response, ErrorCode.OPERATION_UNKNOWN);
        }
    }

    private void updateRole(HttpServletRequest _request, HttpServletResponse _response) {

        Role _role = null;

        long _roleId = Long.parseLong(_request.getParameter("roleId"));

        HttpSession _session = _request.getSession();

        try {

            if(_session.getAttribute("role") != "admin") {
                throw new Exception("You don't have access to this area!");
            }
            _role.setName(_request.getParameter("name"));

            JSONObject _result = new JSONObject();

            _result.put("data", new UpdateRoleDAO(getConnection()).updateRoleById(_role, _roleId));

            _response.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    private void deleteRole(HttpServletRequest _request, HttpServletResponse _response) throws IOException {
        HttpSession _session = _request.getSession();

        try {

            if(_session.getAttribute("role") != "admin") {
                throw new Exception("You don't have access to this area!");
            }

            Long _roleId = Long.parseLong(_request.getParameter("roleId"));
            JSONObject _result = new JSONObject();

            _result.put("data", new DeleteRoleByIdDAO(getConnection()).deleteRoleById(_roleId));

            _response.setStatus(HttpServletResponse.SC_OK);
            _response.setContentType("application/json");
            _response.getWriter().write(_result.toString());

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void addRole(HttpServletRequest _request, HttpServletResponse _response) {

        Role _role = null;

        HttpSession _session = _request.getSession();

        try {

            if(_session.getAttribute("role") != "admin") {
                throw new Exception("You don't have access to this area!");
            }

            _role.setName(_request.getParameter("name"));


            JSONObject _result = new JSONObject();

            _result.put("data", new CreateRoleDAO(getConnection()).createRole(_role));

            _response.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (Exception _e) {
            throw new RuntimeException(_e.getMessage());
        }

    }

    private void getRolesDetailsOp (HttpServletRequest _request, HttpServletResponse _response) throws IOException {

        // This method returns a json array with the roles.
        ArrayList<Role> roles;

        try {
            roles = (ArrayList<Role>) new GetAllRolesDAO(getConnection()).GetAllRoles();
        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        JSONObject resJSON = new JSONObject();
        resJSON.put("roles-list", roles);
        _response.setStatus(HttpServletResponse.SC_OK);
        _response.setContentType("application/json");
        _response.getWriter().write((new JSONObject()).put("data", resJSON).toString());
    }
}
