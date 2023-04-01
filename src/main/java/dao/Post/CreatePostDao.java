package dao.Post;

import resource.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreatePostDao {

    private static final String STATEMENT = "INSERT INTO post (" +
            "name, description, user_id, customer_id, price, status, start_date, end_date, is_deleted," +
            "is_sold, update_date, category_id, subcategory_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private final Connection con;

    public CreatePostDao(Connection con) {
        this.con = con;
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

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            con.close();
        }

        return "Post Created Successfully!";
    }
}
