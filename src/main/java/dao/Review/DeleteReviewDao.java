package dao.Review;

import dao.AbstractDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteReviewDao extends AbstractDAO {

    private static final String STATEMENT = "DELETE FROM reviews WHERE review_id = ? RETURNING *";

    public DeleteReviewDao(Connection con) { super(con); }

    public int deleteReview(long _id) throws SQLException {
        PreparedStatement pstmt = null;
        int affectedRows = 0;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, _id);

            affectedRows = pstmt.executeUpdate();

            if(affectedRows != 1) { throw new SQLException("Delete Failed"); }
        } finally {
            if (pstmt != null) { pstmt.close(); }
            con.close();
        }

        return affectedRows;
    }

    @Override
    protected void doAccess() throws SQLException {

    }
}
