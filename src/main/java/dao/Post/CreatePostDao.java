package dao.Post;

import dao.AbstractDAO;
import resource.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreatePostDao extends AbstractDAO {

    private static final String STATEMENT = "INSERT INTO post (" +
            "post_id, name, description, user_id, customer_id, price, status, start_date, end_date, is_deleted," +
            "is_sold, sold_date, update_date, category_id, subcategory_id) VALUES (nextval('post_seq'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING post_id";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public CreatePostDao(Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

    }

    public Long createPost(Post _post) throws SQLException {

        PreparedStatement _pstmt = null;
        ResultSet _rs;
        long _id = 0;

        try {

            _pstmt = con.prepareStatement(STATEMENT);

            _pstmt.setString(1, _post.getName());
            _pstmt.setString(2, _post.getDescription());
            _pstmt.setLong(3, _post.getUserId());
            _pstmt.setLong(4, _post.getCustomerId());
            _pstmt.setDouble(5, _post.getPrice());
            _pstmt.setString(6, _post.getStatus());
            _pstmt.setTimestamp(7, _post.getStartDate());
            _pstmt.setTimestamp(8, _post.getEndDate());
            _pstmt.setBoolean(9, _post.isDeleted());
            _pstmt.setBoolean(10, _post.isSold());
            _pstmt.setTimestamp(11, _post.getSoldDate());
            _pstmt.setTimestamp(12, _post.getUpdateDate());
            _pstmt.setLong(13, _post.getCategoryId());
            _pstmt.setLong(14, _post.getSubcategoryId());

            _rs = _pstmt.executeQuery();

            _rs.next();

            _id = _rs.getLong(1);

            if (_rs == null) {
                throw new SQLException("Creation failed!");
            }
        } finally {
            if (_pstmt != null) {
                _pstmt.close();
            }
            con.close();
        }

        return _id;
    }
}
