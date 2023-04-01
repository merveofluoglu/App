package dao.Post;

import dao.AbstractDAO;
import resource.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreatePostDao extends AbstractDAO {

    private static final String STATEMENT = "INSERT INTO post (" +
            "name, description, user_id, customer_id, price, status, start_date, end_date, is_deleted," +
            "is_sold, update_date, category_id, subcategory_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    protected CreatePostDao(Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

    }

    public String createPost(Post post) throws SQLException {

        PreparedStatement _pstmt = null;
        int _rs;

        try {

            _pstmt = con.prepareStatement(STATEMENT);

            _pstmt.setString(1, post.getName());
            _pstmt.setString(2, post.getDescription());
            _pstmt.setLong(3, post.getUser_id());
            _pstmt.setLong(4, post.getCustomer_id());
            _pstmt.setDouble(5, post.getPrice());
            _pstmt.setString(6, post.getStatus());
            _pstmt.setTimestamp(7, post.getStart_date());
            _pstmt.setTimestamp(8, post.getEnd_date());
            _pstmt.setBoolean(9, post.isIs_deleted());
            _pstmt.setBoolean(10, post.isIs_sold());
            _pstmt.setTimestamp(11, post.getSold_date());
            _pstmt.setTimestamp(12, post.getUpdate_date());
            _pstmt.setLong(13, post.getCategory_id());
            _pstmt.setLong(14, post.getSubcategory_id());

            _rs = _pstmt.executeUpdate();

            if (_rs != 1) {
                throw new SQLException("Creation failed!");
            }
        } finally {
            if (_pstmt != null) {
                _pstmt.close();
            }
            con.close();
        }

        return "Post Created Successfully!";
    }
}
