package dao.RolePermission;

import dao.AbstractDAO;
import resource.Post;
import resource.Role;
import resource.RolePermission;
import utils.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAllRolePermissionsDao extends AbstractDAO {

    private static final String STATEMENT = "SELECT * FROM role_permission";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public GetAllRolePermissionsDao(Connection con) {
        super(con);
    }

    public List<RolePermission> getAllRolePermissions () throws SQLException, ResourceNotFoundException {

        PreparedStatement _pstmt = null;
        ResultSet _rs = null;
        List<RolePermission> _role_permissions = new ArrayList<>();

        try {
            _pstmt = con.prepareStatement(STATEMENT);
            _rs = _pstmt.executeQuery();

            if(!_rs.isBeforeFirst()) {
                throw new ResourceNotFoundException("There are no Role Permissions!");
            }

            while (_rs.next()) {
                _role_permissions.add(
                        new RolePermission(
                                _rs.getLong("role_permission_id"),
                                _rs.getLong("role_id"),
                                _rs.getLong("permission_id")
                        )
                );
            }
        } finally {
            if (_rs != null) {
                _rs.close();
            }
            if (_pstmt != null) {
                _pstmt.close();
            }
            con.close();
        }

        return _role_permissions;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
