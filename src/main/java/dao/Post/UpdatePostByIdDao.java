package dao.Post;

import dao.AbstractDAO;
import resource.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdatePostByIdDao extends AbstractDAO {

    private static final String STATEMENT =
            "UPDATE post SET name = ?, description = ?, customer_id = ?, price = ?, status = ?, is_deleted = ?, is_sold = ?," +
                    "sold_date = ?, update_date = ? WHERE post_id = ?";

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
            _pstmt.setLong(3, _post.getCustomer_id());
            _pstmt.setDouble(4, _post.getPrice());
            _pstmt.setString(5, _post.getStatus());
            _pstmt.setBoolean(6, _post.isIs_deleted());
            _pstmt.setBoolean(7, _post.isIs_sold());
            _pstmt.setTimestamp(8, _post.getSold_date());
            _pstmt.setTimestamp(9, _post.getUpdate_date());
            _pstmt.setLong(10, _postId);

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
