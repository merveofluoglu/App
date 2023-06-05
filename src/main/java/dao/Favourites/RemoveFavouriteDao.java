package dao.Favourites;

import dao.AbstractDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveFavouriteDao extends AbstractDAO {

    private static final String STATEMENT = "DELETE FROM favourites WHERE user_id = ? AND post_id = ?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public RemoveFavouriteDao(Connection con) {
        super(con);
    }

    public int removeFavourite(long _postId, long _userId) throws SQLException {

        PreparedStatement _pstmt = null;
        int _affectedRows = 0;

        try {
            _pstmt = con.prepareStatement(STATEMENT);

            _pstmt.setLong(1, _userId);
            _pstmt.setLong(2, _postId);

            _affectedRows = _pstmt.executeUpdate();

            if (_affectedRows != 1) {
                throw new SQLException("Remove Failed!");
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
