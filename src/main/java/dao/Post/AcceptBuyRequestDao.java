package dao.Post;

import dao.AbstractDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AcceptBuyRequestDao extends AbstractDAO {

    private static final String STATEMENT = "UPDATE post SET status = 'Sold', is_sold = true, sold_date=? WHERE post_id = ?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public AcceptBuyRequestDao(Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

    }

    public int acceptBuyRequest(long _id, Timestamp _time) throws SQLException {

        PreparedStatement _pstmt = null;
        int _affectedRows = 0;

        try {
            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setObject(1, _time);
            _pstmt.setObject(2, _id);


            _affectedRows = _pstmt.executeUpdate();

            if(_affectedRows != 1) {
                throw new SQLException("Buy Failed");
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
