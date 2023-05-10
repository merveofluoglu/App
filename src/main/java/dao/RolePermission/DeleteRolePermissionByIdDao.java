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

    private static final String STATEMENT = "DELETE FROM role_permission WHERE  role_permission_id=?";

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

    public String deleteRolePermission(long _rolePermissionId) throws SQLException, ResourceNotFoundException {

        PreparedStatement _pstmt = null;
        int _affectedRows = 0;

        try {
            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setObject(1, _rolePermissionId);

            _affectedRows = _pstmt.executeUpdate();

            if (_affectedRows != 1) {
                throw new SQLException("Deletion Failed");
            }
        } finally {
            if (_pstmt != null) {
                _pstmt.close();
            }
            con.close();
        }


        return ("Role Permission Assignment Deleted Successfully!");
    }
}
