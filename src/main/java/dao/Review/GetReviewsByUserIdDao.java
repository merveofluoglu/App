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
import java.util.Queue;

public class GetReviewsByUserIdDao extends AbstractDAO {

    private static final String STATEMENT = "SELECT * FROM reviews WHERE user_id = ?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public GetReviewsByUserIdDao(Connection con) {
        super(con);
    }

    public List<Reviews> getReviewsByUserId(long _user_id) throws SQLException, ResourceNotFoundException {

        PreparedStatement _pstmt = null;
        ResultSet _rs = null;
        List<Reviews> _reviews = new ArrayList<Reviews>();

        try {

            _pstmt = con.prepareStatement(STATEMENT);

            _pstmt.setObject(1, _user_id);

            _rs = _pstmt.executeQuery();

            if (!_rs.isBeforeFirst()) {
                return _reviews;
            }

            while (_rs.next()) {
                _reviews.add( new Reviews(
                        _rs.getLong("review_id"),
                        _rs.getLong("user_id"),
                        _rs.getLong("seller_id"),
                        _rs.getLong("post_id"),
                        _rs.getDouble("point_scale"),
                        _rs.getString("description"),
                        _rs.getTimestamp("create_date"),
                        _rs.getBoolean("is_deleted")
                ));
            }

        } finally {
            if (_pstmt != null) {
                _pstmt.close();
            }
            con.close();
        }

        return _reviews;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
