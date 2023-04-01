package dao.Review;

import dao.AbstractDAO;
import resource.Reviews;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddReviewDao extends AbstractDAO {

    private static final String STATEMENT = "INSERT INTO reviews (user_id, seller_id, " +
            "post_id, point_scale, description, create_date, is_deleted) VALUES (?, ?, ?, ?, ?, ?, ?)";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    protected AddReviewDao(Connection con) {
        super(con);
    }

    public String addReview(Reviews review) throws SQLException {

        PreparedStatement _pstmt = null;
        int _rs = 0;

        try {

            _pstmt = con.prepareStatement(STATEMENT);

            _pstmt.setLong(1, review.getUser_id());
            _pstmt.setLong(2, review.getSeller_id());
            _pstmt.setLong(3, review.getPost_id());
            _pstmt.setDouble(4, review.getPoint_scale());
            _pstmt.setString(5, review.getDescription());
            _pstmt.setTimestamp(6, review.getCreate_date());
            _pstmt.setBoolean(7, review.isIs_deleted());

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
