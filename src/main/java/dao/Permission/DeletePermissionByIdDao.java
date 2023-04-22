package dao.Permission;
import dao.AbstractDAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletePermissionByIdDao extends AbstractDAO{

    private static final String STATEMENT = "UPDATE permission SET is_deleted = true WHERE permission_id=?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public DeletePermissionByIdDao(Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

    }

    public int DeletePermissionById(long permission_id) throws SQLException {

        PreparedStatement _pstmt = null;
        int _affectedRows = 0;

        try {
            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setObject(1, permission_id);

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

        return _affectedRows;
    }
}
