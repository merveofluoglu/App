package dao.RolePermission;

import dao.AbstractDAO;
import resource.Post;
import resource.RolePermission;
import utils.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetRolePermissionByIdDao extends AbstractDAO {

    private static final String STATEMENT = "SELECT * FROM role_permission WHERE role_permission = ?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public GetRolePermissionByIdDao(Connection con) {
        super(con);
    }

    public RolePermission getRolePermissionById(long _role_permission_id) throws SQLException, ResourceNotFoundException {

        PreparedStatement _pstmt = null;
        ResultSet _rs =null;
        RolePermission _rolePermission = null;

        try {

            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setLong(1, _role_permission_id);
            _rs= _pstmt.executeQuery();

            if(!_rs.isBeforeFirst()) {
                throw new ResourceNotFoundException("Couldn't found such post!");
            }

            if(_rs.next()) {
                _rolePermission = new RolePermission(
                        _rs.getLong("role_permission_id"),
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

        return _rolePermission;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
