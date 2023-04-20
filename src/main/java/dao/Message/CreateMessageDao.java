package dao.Message;

import dao.AbstractDAO;
import resource.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateMessageDao extends AbstractDAO {

    private static final String STATEMENT = "INSERT INTO message ( messageId, creatorId, recipientId," +
            "parentMessageId, subject, messageBody, isRead, creationDate, expirationDate )" +
            " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public CreateMessageDao(Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

    }

    public String createMessage(Message _message) throws SQLException {

        PreparedStatement _pstmt = null;
        int _rs;

        try {

            _pstmt = con.prepareStatement(STATEMENT);

            _pstmt.setLong(1, _message.getMessageId());
            _pstmt.setLong(2, _message.getCreatorId());
            _pstmt.setLong(3, _message.getRecipientId());
            _pstmt.setLong(4, _message.getParentMessageId());
            _pstmt.setString(5, _message.getSubject());
            _pstmt.setString(6, _message.getMessageBody());
            _pstmt.setBoolean(7, _message.getIsRead());
            _pstmt.setTimestamp(8, _message.getCreationDate());
            _pstmt.setTimestamp(9, _message.getExpirationDate());
            _rs = _pstmt.executeUpdate();

            if (_rs != 1) {
                throw new SQLException("Creation failed!");
            }
        } finally {
            if (_pstmt != null) {
                _pstmt.close();
            }
            con.close();
        }

        return "Message Created Successfully!";
    }
}
