package servlet;

import dao.Permission.*;

import dao.Post.DeletePostByIdDao;
import dao.RolePermission.CreateRolePermissionDao;
import dao.RolePermission.DeleteRolePermissionByIdDao;
import dao.RolePermission.GetRolePermissionByPermissionIdDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;
import resource.Permission;
import resource.RolePermission;
import utils.ErrorCode;
import utils.ResourceNotFoundException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                try {
                    deletePermission(_request, _response);
                } catch (ResourceNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            default :
                writeError(_response, ErrorCode.OPERATION_UNKNOWN);
        }
    }


    private void deletePermission(HttpServletRequest _request, HttpServletResponse _response) throws IOException, ResourceNotFoundException {
        try {
            long _permission_id = Long.parseLong(_request.getRequestURI().split("/", 5)[4]);
            JSONObject _result = new JSONObject();
            List<RolePermission> _role_permissions = new ArrayList<>();
            _role_permissions = new GetRolePermissionByPermissionIdDao(getConnection()).getRolePermissionByPermissionId(_permission_id);
            for(int i=0; i< _role_permissions.size(); i++){
                _result.put("affectedRow", new DeleteRolePermissionByIdDao(getConnection()).deleteRolePermission(_role_permissions.get(i).getRole_permission_id()));

            }


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
        long _permissionId = Long.parseLong(_request.getParameter("permission_id"));

        try {
            _permission.setPermission_id(_permissionId);
            _permission.setName(_request.getParameter("name"));

            JSONObject _result = new JSONObject();

            _result.put("data", new UpdatePermissionByIdDao(getConnection()).UpdatePermissionById(_permission, _permissionId));

            _response.getWriter().write(_result.toString());

        } catch (SQLException _e) {
            throw new RuntimeException(_e);
        } catch (IOException _e) {
            throw new RuntimeException(_e);
        }

    }

    private void addPermission(HttpServletRequest _request, HttpServletResponse _response) {

        Permission _permission = new Permission();
        RolePermission _role_permission = new RolePermission();
        Permission _came_Permission = new Permission();
        try {
            String name = _request.getParameter("name");
            boolean role_id = Boolean.parseBoolean(_request.getParameter("role"));
            _permission.setName(name);

            JSONObject _result = new JSONObject();
            _result.put("data", new CreatePermissionDao(getConnection()).createPermission(_permission));
            _came_Permission = new GetPermissionByNameDao(getConnection()).getPermissionByName(name);
            _role_permission.setRole_id(1L);
            _role_permission.setPermission_id(_came_Permission.getPermission_id());
            _result.put("data", new CreateRolePermissionDao(getConnection()).createRolePermission(_role_permission));
            _role_permission.setRole_id(0L);
            _result.put("data", new CreateRolePermissionDao(getConnection()).createRolePermission(_role_permission));
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
