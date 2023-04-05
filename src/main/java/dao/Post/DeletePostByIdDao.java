package dao.Post;

import dao.AbstractDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletePostByIdDao extends AbstractDAO {

    private static final String STATEMENT = "UPDATE post SET is_deleted = true WHERE post_id = ?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    protected DeletePostByIdDao(Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

    }

    public int deletePost(long _id) throws SQLException {

        PreparedStatement _pstmt = null;
        int _affectedRows = 0;

        try {
            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setObject(1, _id);

            _affectedRows = _pstmt.executeUpdate();

            if(_affectedRows != 1) {
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
