package dao.Review;

import dao.AbstractDAO;
import resource.Reviews;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddReviewDao extends AbstractDAO {

    private static final String STATEMENT = "INSERT INTO reviews (review_id, user_id, seller_id, " +
            "post_id, point_scale, description, create_date, is_deleted) VALUES (nextval('review_seq'), ?, ?, ?, ?, ?, ?, ?)";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public AddReviewDao(Connection con) {
        super(con);
    }

    public String addReview(Reviews review) throws SQLException {

        PreparedStatement _pstmt = null;
        int _rs = 0;

        try {

            _pstmt = con.prepareStatement(STATEMENT);

            _pstmt.setLong(1, review.getUserId());
            _pstmt.setLong(2, review.getSellerId());
            _pstmt.setLong(3, review.getPostId());
            _pstmt.setDouble(4, review.getPointScale());
            _pstmt.setString(5, review.getDescription());
            _pstmt.setTimestamp(6, review.getCreateDate());
            _pstmt.setBoolean(7, review.isDeleted());

            _rs = _pstmt.executeUpdate();

            if (_rs != 1) {
                throw new SQLException("Error occurred while adding review");
            }

        } finally {
            if (_pstmt != null) {
                _pstmt.close();
            }
            con.close();
        }

        return "Review added successfully!";
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
