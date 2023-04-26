package servlet;

import dao.RolePermission.CreateRolePermissionDao;
import dao.RolePermission.UpdateRolePermissionByIdDao;
import dao.RolePermission.DeleteRolePermissionByIdDao;
import dao.RolePermission.GetRolePermissionByIdDao;
import dao.RolePermission.GetAllRolePermissionsDao;

import dao.ActionLog.AddActionLogDao;

import dao.User.DeleteUserByUseridDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;
import resource.Post;
import resource.Role;
import resource.RolePermission;
import utils.ErrorCode;
import utils.ResourceNotFoundException;
import utils.Validator;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import static java.lang.Long.parseLong;

@WebServlet(name = "RolePermissionServlet", value = "/RolePermissionServlet")
public class RolePermissionServlet extends AbstractServlet{

    @Override
    protected void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
        String _op = _request.getRequestURI().split("/", 4)[3].replace("/", "");
        String sessionRole = _request.getSession().getAttribute("role").toString();
        if (_op.contentEquals("details") && sessionRole == "admin") {
            getRolePermissionDetailsOp(_request, _response);
        }
        else if (_op.contentEquals("allRolePermissions") && sessionRole == "admin") {
            getAllRolePermissionsOp(_request, _response);
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
                    addRolePermission(_request, _response);
                    break;
                }

            case "update" :
                if(sessionRole == "admin"){
                    updateRolePermission(_request, _response);
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

        if ("/protected/deleteRolePermission".equals(op)) {
            if(sessionRole == "admin"){
                deleteRolePermission(_request, _response);
            }
        } else {
            writeError(_response, ErrorCode.OPERATION_UNKNOWN);
        }
    }

    private void updateRolePermission(HttpServletRequest _request, HttpServletResponse _response) {

        RolePermission _role_permission = null;

        Long _role_permission_id = Long.parseLong(_request.getParameter("role_permission_id"));

        try {
            _role_permission.setRole_id(Long.valueOf(_request.getParameter("role_id")));
            _role_permission.setPermission_id(Long.valueOf(_request.getParameter("permission_id")));

            JSONObject _result = new JSONObject();

            _result.put("data", new UpdateRolePermissionByIdDao(getConnection()).updateRolePermissionById(_role_permission, _role_permission_id));

            _response.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }

    }

    private void deleteRolePermission(HttpServletRequest _request, HttpServletResponse _response) throws IOException {
        try {
            Long _role_permission_id = Long.parseLong(_request.getParameter("role_permission_id"));
            JSONObject _result = new JSONObject();

            _result.put("data", new DeleteRolePermissionByIdDao(getConnection()).deleteRolePermission(_role_permission_id));
            _response.setStatus(HttpServletResponse.SC_OK);
            _response.setContentType("application/json");
            _response.getWriter().write(_result.toString());
        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void addRolePermission(HttpServletRequest _request, HttpServletResponse _response) {

        RolePermission _role_permission = null;

        try {
            _role_permission.setRole_id(Long.valueOf(_request.getParameter("role_id")));
            _role_permission.setPermission_id(Long.valueOf(_request.getParameter("permission_id")));


            JSONObject _result = new JSONObject();

            _result.put("data", new CreateRolePermissionDao(getConnection()).createRolePermission(_role_permission));

            _response.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (Exception _e) {
            throw new RuntimeException(_e);
        }

    }

    private void getAllRolePermissionsOp (HttpServletRequest _request, HttpServletResponse _response) throws IOException {

        try {
            Long _role_permission_id = parseLong(_request.getParameter("role_permission_id"));
            _response.setContentType("application/json");
            _response.setStatus(HttpServletResponse.SC_OK);

            JSONObject _result = new JSONObject();
            _result.put("data", new GetRolePermissionByIdDao(getConnection()).getRolePermissionById(_role_permission_id));

            _response.getWriter().write(_result.toString());

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }

    private void getRolePermissionDetailsOp (HttpServletRequest _request, HttpServletResponse _response) throws IOException {

        // This method returns a json array with the role-permissions.
        ArrayList<RolePermission> _role_permissions;

        try {
            _role_permissions = (ArrayList<RolePermission>) new GetAllRolePermissionsDao(getConnection()).getAllRolePermissions();
        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        JSONObject resJSON = new JSONObject();
        resJSON.put("role-permissions-list", _role_permissions);
        _response.setStatus(HttpServletResponse.SC_OK);
        _response.setContentType("application/json");
        _response.getWriter().write((new JSONObject()).put("data", resJSON).toString());
    }
}