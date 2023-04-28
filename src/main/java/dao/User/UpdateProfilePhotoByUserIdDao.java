package dao.User;

import dao.AbstractDAO;
import resource.User;
import servlet.AbstractServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateProfilePhotoByUserIdDao extends AbstractDAO {
    private static final String STATEMENT = "UPDATE users SET pp_path = ?" +
            " WHERE user_id = ?";
    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public UpdateProfilePhotoByUserIdDao(Connection con) {
        super(con);
    }

    public int UpdateProfilePhotoByUserIdDao(long user_id, byte[] pp_path) throws SQLException {

        PreparedStatement _pstmt = null;
        int _affectedRows = 0;

        try {

            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setBytes(1, pp_path);
            _pstmt.setLong(2, user_id);
            _affectedRows = _pstmt.executeUpdate();

            if (_affectedRows != 1) {
                throw new SQLException("Update Failed!");
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
