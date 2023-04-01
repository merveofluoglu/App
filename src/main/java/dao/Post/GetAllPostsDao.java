package dao.Post;

import resource.Post;
import utils.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAllPostsDao {

    private static final String STATEMENT = "SELECT * FROM post WHERE is_deleted = false";

    private final Connection con;

    public GetAllPostsDao(Connection con) {
        this.con = con;
    }

    public List<Post> getAllPosts () throws SQLException, ResourceNotFoundException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Post> posts = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT);
            rs = pstmt.executeQuery();

            if(!rs.isBeforeFirst()) {
                throw new ResourceNotFoundException("There are no post!");
            }

            while (rs.next()) {
                posts.add(
                        new Post(
                                rs.getLong("post_id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getLong("user_id"),
                                rs.getLong("customer_id"),
                                rs.getDouble("price"),
                                rs.getString("status"),
                                rs.getTimestamp("start_date"),
                                rs.getTimestamp("end_date"),
                                rs.getBoolean("is_deleted"),
                                rs.getBoolean("is_sold"),
                                rs.getTimestamp("sold_date"),
                                rs.getTimestamp("update_date"),
                                rs.getLong("category_id"),
                                rs.getLong("subcategory_id")
                        )
                );
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            con.close();
        }

        return posts;
    }

}
