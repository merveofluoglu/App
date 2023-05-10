package servlet;

import dao.Permission.GetAllPermissionsDao;
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
        String _op = _request.getRequestURI().split("/", 5)[3].replace("/", "");
        //String sessionRole = _request.getSession().getAttribute("role").toString();
        if (_op.contentEquals("getallrolepermissions")) {
            getAllRolePermissionsOp(_request, _response);
        }
        else {

        }
    }

    @Override
    protected void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {

        String _op = _request.getRequestURI().split("/", 4)[3];
        System.out.println(_op);
        //String sessionRole = _request.getSession().getAttribute("role").toString();
        switch (_op) {
            case "add" :
                addRolePermission(_request, _response);
                break;

            case "update" :
                updateRolePermission(_request, _response);
                break;

            case "delete" :
                deleteRolePermission(_request, _response);
                break;

            default :
                writeError(_response, ErrorCode.OPERATION_UNKNOWN);
        }
    }


    private void updateRolePermission(HttpServletRequest _request, HttpServletResponse _response) {

        RolePermission _rolePermission = null;

        Long _rolePermissionId = Long.parseLong(_request.getParameter("rolePermissionId"));

        try {
            _rolePermission.setRoleId(Long.valueOf(_request.getParameter("roleId")));
            _rolePermission.setPermissionId(Long.valueOf(_request.getParameter("permissionId")));

            JSONObject _result = new JSONObject();

            _result.put("data", new UpdateRolePermissionByIdDao(getConnection()).updateRolePermissionById(_rolePermission, _rolePermissionId));

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
            Long _rolePermissionId = Long.parseLong(_request.getParameter("rolePermissionId"));
            JSONObject _result = new JSONObject();

            _result.put("data", new DeleteRolePermissionByIdDao(getConnection()).deleteRolePermission(_rolePermissionId));

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

        RolePermission _rolePermission = null;

        try {
            _rolePermission.setRoleId(Long.valueOf(_request.getParameter("roleId")));
            _rolePermission.setPermissionId(Long.valueOf(_request.getParameter("permissionId")));


            JSONObject _result = new JSONObject();

            _result.put("data", new CreateRolePermissionDao(getConnection()).createRolePermission(_rolePermission));

            _response.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (Exception _e) {
            throw new RuntimeException(_e);
        }

    }

    private void getAllRolePermissionsOp (HttpServletRequest _request, HttpServletResponse _response) throws IOException {

        try {
            JSONObject _result = new JSONObject();
            _result.put("data", new GetAllRolePermissionsDao(getConnection()).getAllRolePermissions());

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
