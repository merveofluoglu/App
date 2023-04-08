package dao.Review;

import dao.AbstractDAO;
import resource.Reviews;
import utils.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetReviewsByPostIdDao extends AbstractDAO {

    private static final String STATEMENT = "SELECT * FROM reviews r WHERE r.post_id = ?";

    public GetReviewsByPostIdDao(Connection con) { super(con); }

    public List<Reviews> getReviewsByPostId(long _id) throws SQLException, ResourceNotFoundException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Reviews> reviews = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, _id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                reviews.add(new Reviews(
                        rs.getLong("review_id"),
                        rs.getLong("user_id"),
                        rs.getLong("seller_id"),
                        rs.getLong("post_id"),
                        rs.getDouble("point_scale"),
                        rs.getString("description"),
                        rs.getTimestamp("create_date"),
                        rs.getBoolean("is_deleted"))
                );
            }
        } finally {
            if (rs != null) { rs.close(); }
            if (pstmt != null) { pstmt.close(); }
            con.close();
        }
        return reviews;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
