package dao.RolePermission;

import dao.AbstractDAO;
import resource.Post;
import resource.Role;
import resource.RolePermission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateRolePermissionByIdDao extends AbstractDAO {

    private static final String STATEMENT =
            "UPDATE role_permission SET role_id = ?, permission_id = ? WHERE role_permission_id = ?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public UpdateRolePermissionByIdDao(Connection con) {
        super(con);
    }

    public RolePermission updateRolePermissionById(RolePermission rolePermission, Long _role_permission_id) throws SQLException {

        PreparedStatement _pstmt = null;
        int _affectedRows = 0;

        try {

            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setLong(1, rolePermission.getRole_id());
            _pstmt.setLong(2, rolePermission.getPermission_id());
            _pstmt.setLong(3, rolePermission.getRole_permission_id());


            _affectedRows = _pstmt.executeUpdate();

            if (_affectedRows != 1) {
                throw new SQLException("Role Permission Update Failed!");
            }

        } finally {
            if (_pstmt != null) {
                _pstmt.close();
            }
            con.close();
        }

        return rolePermission;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
