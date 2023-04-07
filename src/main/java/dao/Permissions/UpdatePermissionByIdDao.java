package dao.Permissions;

import dao.AbstractDAO;
import resource.Permission;
import resource.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdatePermissionByIdDao extends AbstractDAO {

    private static final String STATEMENT = "UPDATE permission SET name = ?" +
            "WHERE permission_id =?";
    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public UpdatePermissionByIdDao(Connection con) {
        super(con);
    }

    public int UpdatePermissionById(Permission permission, Long permission_id) throws SQLException {

        PreparedStatement _pstmt = null;
        int _affectedRows = 0;

        try {

            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setString(1, permission.getName());
            _pstmt.setLong(2, permission_id);
            _affectedRows = _pstmt.executeUpdate();

            if (_affectedRows != 1) {
                throw new SQLException("Permission Update Failed!");
            }

        } finally {
            if (_pstmt != null) {
                _pstmt.close();
            }
            con.close();
        }

        return _affectedRows;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
