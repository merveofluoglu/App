package servlet;

import dao.Permission.CreatePermissionDao;
import dao.Permission.DeletePermissionByIdDao;
import dao.Permission.GetPermissionByIdDao;
import dao.Permission.UpdatePermissionByIdDao;
import dao.Permission.GetAllPermissionsDao;

import dao.Post.DeletePostByIdDao;
import dao.RolePermission.DeleteRolePermissionByIdDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;
import resource.Permission;
import utils.ErrorCode;
import utils.ResourceNotFoundException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.Long.parseLong;

@WebServlet(name = "PermissionServlet", value = "/PermissionServlet")
public class PermissionServlet extends AbstractServlet{

    //There are permission operation permission for the regular user.
    //Hence, this servlet should be modified accordingly by restricting only for the super-admin

    @Override
    protected void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
        String _op = _request.getRequestURI().split("/", 5)[3].replace("/", "");
        //String sessionRole = _request.getSession().getAttribute("role").toString();
        if (_op.contentEquals("allpermissions") ) {
            getAllPermissionsOp(_request, _response);
        }else{
            getPermissionDetailsOp(_request, _response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {

        String _op = _request.getRequestURI().split("/", 5)[3];
        System.out.println(_op);
        //String sessionRole = _request.getSession().getAttribute("role").toString();
        switch (_op) {
            case "add" :
                addPermission(_request, _response);
                break;

            case "update" :
                updatePermission(_request, _response);
                break;
            case "delete" :
                deletePermission(_request, _response);
                break;
            default :
                writeError(_response, ErrorCode.OPERATION_UNKNOWN);
        }
    }


    private void deletePermission(HttpServletRequest _request, HttpServletResponse _response) throws IOException {
        try {
            System.out.println(_request);
            long _permission_id = Long.parseLong(_request.getParameter("permission_id"));
            JSONObject _result = new JSONObject();
            System.out.println(_permission_id);
            _result.put("affectedRow", new DeletePermissionByIdDao(getConnection()).DeletePermissionById(_permission_id));
            _response.getWriter().write(_result.toString());
        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }
    }

    private void updatePermission(HttpServletRequest _request, HttpServletResponse _response) {

        Permission _permission = new Permission();
        Long _permissionId = Long.parseLong(_request.getParameter("permission_id"));

        try {
            _permission.setName(_request.getParameter("name"));

            JSONObject _result = new JSONObject();

            _result.put("data", new UpdatePermissionByIdDao(getConnection()).UpdatePermissionById(_permission, _permissionId));

            _response.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }

    }

    private void addPermission(HttpServletRequest _request, HttpServletResponse _response) {

        Permission _permission = null;

        try {
            _permission.setName(_request.getParameter("name"));


            JSONObject _result = new JSONObject();

            _result.put("data", new CreatePermissionDao(getConnection()).createPermission(_permission));

            _response.getWriter().write(_result.toString());

            //After jsp files prepared, request dispatcher will be implemented!!

        } catch (Exception _e) {
            throw new RuntimeException(_e);
        }

    }

    private void getPermissionDetailsOp (HttpServletRequest _request, HttpServletResponse _response) {

        // This method returns a permission.
        try {
            long _id = parseLong(_request.getParameter("permission_id"));
            JSONObject _result = new JSONObject();
            _result.put("data", new GetPermissionByIdDao(getConnection()).getPermissionById(_id));
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

    private void getAllPermissionsOp (HttpServletRequest _request, HttpServletResponse _response) {

        // This method returns a json array with the roles.
        ArrayList<Permission> _permissions;
        try {
            JSONObject _result = new JSONObject();
            _result.put("data",new GetAllPermissionsDao(getConnection()).getAllPermissions());
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
