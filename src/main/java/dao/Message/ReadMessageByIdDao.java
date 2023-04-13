package dao.Message;
import dao.AbstractDAO;
import resource.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReadMessageByIdDao extends AbstractDAO {

    private static final String STATEMENT = "UPDATE message SET isRead = true WHERE messageId = ?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public ReadMessageByIdDao(Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

    }

    public int readMessage(Message _message) throws SQLException {

        PreparedStatement _pstmt = null;
        int _affectedRows = 0;

        try {
            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setObject(1, _message.getMessageId());

            _affectedRows = _pstmt.executeUpdate();

            if(_affectedRows != 1) {
                throw new SQLException("Message Read Failed");
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
