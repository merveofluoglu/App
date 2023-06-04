package dao.Post;

import dao.AbstractDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RejectBuyRequestDao extends AbstractDAO {
    private static final String STATEMENT = "UPDATE post SET status = 'Available' AND customer_id = 0 WHERE post_id = ?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public RejectBuyRequestDao(Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

    }

    public int rejectBuyRequest(long _id) throws SQLException {

        PreparedStatement _pstmt = null;
        int _affectedRows = 0;

        try {
            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setObject(1, _id);

            _affectedRows = _pstmt.executeUpdate();

            if(_affectedRows != 1) {
                throw new SQLException("Buy reject failed");
            }
        } finally {
            if (_pstmt != null) {
                _pstmt.close();
            }
            con.close();
        }

        return _affectedRows;
    }
}
