package dao.Review;

import dao.AbstractDAO;
import resource.PostFiles;
import resource.Reviews;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteReviewDao extends AbstractDAO<Reviews> {

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */

    private static final String STATEMENT = "DELETE FROM reviews WHERE review_id = ? RETURNING *";

    private long review_id;

    public DeleteReviewDao(Connection con, long review_id) {
        super(con);
        this.review_id = review_id;
    }

    @Override
    protected void doAccess() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Reviews review = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setLong(1, review_id);

            rs = pstmt.executeQuery();

            while(rs.next()) {
                review = new Reviews(
                        rs.getLong("review_id"),
                        rs.getLong("user_id"),
                        rs.getLong("seller_id"),
                        rs.getLong("post_id"),
                        rs.getDouble("point_scale"),
                        rs.getString("description"),
                        rs.getTimestamp("create_date"),
                        rs.getBoolean("is_deleted")
                );
                LOGGER.info("Review %d successfully deleted from the database.", review.getReview_id());
            }
        } finally {
            if(rs != null) { rs.close(); }
            if(pstmt != null) { pstmt.close(); }
        }
        outputParam = review;
    }
}
