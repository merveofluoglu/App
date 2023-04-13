package dao.RolePermission;

import dao.AbstractDAO;
import resource.Role;
import resource.RolePermission;
import utils.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteRolePermissionByIdDao extends AbstractDAO {

    private static final String STATEMENT = "DELETE FROM role_permission WHERE role_permission_id=?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public DeleteRolePermissionByIdDao(Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

    }

    public RolePermission deleteRolePermission(Long role_permission_id) throws SQLException, ResourceNotFoundException {

        PreparedStatement _pstmt = null;
        ResultSet _rs = null;
        RolePermission _role_permission = null;

        try {

            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setObject(1, role_permission_id);
            _rs = _pstmt.executeQuery();
            if (!_rs.isBeforeFirst() ) {
                throw new ResourceNotFoundException("There is no such role-permission!");
            }
            if (_rs.next()) {
                _role_permission = new RolePermission(_rs.getLong("role_permission_id"),
                        _rs.getLong("role_id"),
                        _rs.getLong("permission_id")
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

        return _role_permission;
    }
}
