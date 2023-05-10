package dao.User;
import dao.AbstractDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class DeleteUserByUseridDAO extends AbstractDAO{

    private static final String STATEMENT = "UPDATE users SET is_deleted = true WHERE user_id = ?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public DeleteUserByUseridDAO(Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

    }

    public int DeleteUserByUseridDAO(long _userId) throws SQLException {

        PreparedStatement _pstmt = null;
        int _affectedRows = 0;

        try {
            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setObject(1, _userId);

            _affectedRows = _pstmt.executeUpdate();

            if (_affectedRows != 1) {
                throw new SQLException("Delete Failed");
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
