package dao.Post;

import dao.AbstractDAO;
import resource.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdatePostByIdDao extends AbstractDAO {

    private static final String STATEMENT =
            "UPDATE post SET name = ?, description = ?, price = ?," +
                    "update_date = ? WHERE post_id = ?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public UpdatePostByIdDao(Connection con) {
        super(con);
    }

    public int updatePostById(Post _post, long _postId) throws SQLException {

        PreparedStatement _pstmt = null;
        int _affectedRows = 0;

        try {

            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setString(1, _post.getName());
            _pstmt.setString(2, _post.getDescription());
            _pstmt.setDouble(3, _post.getPrice());
            _pstmt.setTimestamp(4, _post.getUpdateDate());
            _pstmt.setLong(5, _postId);

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
