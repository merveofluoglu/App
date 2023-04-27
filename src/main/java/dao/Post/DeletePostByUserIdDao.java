package dao.Post;

import dao.AbstractDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletePostByUserIdDao extends AbstractDAO {

    private static final String STATEMENT = "UPDATE post SET is_deleted = true WHERE user_id = ?";


    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public DeletePostByUserIdDao(Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

    }

    public int deletePosts(long _id) throws SQLException {

        PreparedStatement _pstmt = null;
        int _affectedRows = 0;

        try {
            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setObject(1, _id);

            _affectedRows = _pstmt.executeUpdate();

            if(_affectedRows != 1) {
                return _affectedRows;
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
