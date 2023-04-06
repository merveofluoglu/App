package dao.Review;

import dao.AbstractDAO;
import resource.Reviews;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GetReviewsByPostId extends AbstractDAO<List<Reviews>> {

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */

    private static final String STATEMENT = "SELECT * FROM reviews r WHERE r.review_id = ?";

    protected GetReviewsByPostId(Connection con) { super(con); }

    @Override
    protected void doAccess() throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Reviews> reviews = new ArrayList<Reviews>();

        try {
            pstmt = con.prepareStatement(STATEMENT);
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

            LOGGER.info("Review(s) successfully listed.");
        } finally {
            if (rs != null) { rs.close(); }
            if (pstmt != null) { pstmt.close(); }
        }
        outputParam = reviews;
    }
}
