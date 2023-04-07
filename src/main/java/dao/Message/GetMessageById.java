package dao.Message;
import resource.Message;

import dao.AbstractDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetMessageById extends AbstractDAO {

    private static final String STATEMENT = "SELECT * FROM message WHERE message.message_id = ?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    protected GetMessageById(Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

    }

    public String getMessage(long _message_id) throws SQLException {
        PreparedStatement _pstmt = null;
        int _affectedRows = 0;

        try {

            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setLong(1, _message_id);

            _affectedRows = _pstmt.executeUpdate();

            if (_affectedRows != 1) {
                throw new SQLException("Getting message by ID Failed!");
            }
        } finally {
            if (_pstmt != null) {
                _pstmt.close();
            }
            con.close();
        }

        return "Message Got by ID Successfully!";
    }
}
