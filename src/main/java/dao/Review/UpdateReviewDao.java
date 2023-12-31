package dao.Review;

import dao.AbstractDAO;
import resource.Reviews;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateReviewDao extends AbstractDAO {

    private static final String STATEMENT =
            "UPDATE reviews SET user_id = ?, seller_id = ?, post_id = ?, " +
            "point_scale = ?, description = ?, create_date = ?, is_deleted = ? WHERE review_id = ?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public UpdateReviewDao(Connection con) {
        super(con);
    }

    public int updateReview(Reviews _review, long _reviewId) throws SQLException {

        PreparedStatement _pstmt = null;
        int _affectedRows = 0;

        try {

            _pstmt = con.prepareStatement(STATEMENT);

            _pstmt.setLong(1, _review.getUserId());
            _pstmt.setLong(2, _review.getSellerId());
            _pstmt.setLong(3, _review.getPostId());
            _pstmt.setDouble(4, _review.getPointScale());
            _pstmt.setString(5, _review.getDescription());
            _pstmt.setTimestamp(6, _review.getCreateDate());
            _pstmt.setBoolean(7, _review.isDeleted());
            _pstmt.setLong(8, _reviewId);

            _affectedRows = _pstmt.executeUpdate();

            if(_affectedRows != 1) {
                throw new SQLException("Error occurred while updating _review!");
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
