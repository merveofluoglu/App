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

        PreparedStatement pstmt = null;
        int rs;

        try {

            pstmt = con.prepareStatement(STATEMENT);

            pstmt.setString(1, post.getName());
            pstmt.setString(2, post.getDescription());
            pstmt.setLong(3, post.getUser_id());
            pstmt.setLong(4, post.getCustomer_id());
            pstmt.setDouble(5, post.getPrice());
            pstmt.setString(6, post.getStatus());
            pstmt.setTimestamp(7, post.getStart_date());
            pstmt.setTimestamp(8, post.getEnd_date());
            pstmt.setBoolean(9, post.isIs_deleted());
            pstmt.setBoolean(10, post.isIs_sold());
            pstmt.setTimestamp(11, post.getSold_date());
            pstmt.setTimestamp(12, post.getUpdate_date());
            pstmt.setLong(13, post.getCategory_id());
            pstmt.setLong(14, post.getSubcategory_id());

            rs = pstmt.executeUpdate();

            if (rs != 1) {
                throw new SQLException("Creation failed!");
            }
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            con.close();
        }

        return "Post Created Successfully!";
    }
}
