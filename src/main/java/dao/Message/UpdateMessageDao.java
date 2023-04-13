package dao.Message;

import dao.AbstractDAO;
import resource.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateMessageDao extends AbstractDAO {

    private static final String STATEMENT = "UPDATE post SET messageId = ?, creatorId = ?, recipientId = ?, " +
            "parentMessageId = ?, subject = ?, messageBody = ?, isRead = ?, creationDate = ?, expirationDate = ?," +
            "WHERE messageId = ?";


    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    protected UpdateMessageDao(Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

    }

    public String updateMessage(Message _message, long _message_id) throws SQLException {
        PreparedStatement _pstmt = null;
        int _affectedRows = 0;

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
            _pstmt.setLong(10, _message_id);

            _affectedRows = _pstmt.executeUpdate();

            if (_affectedRows != 1) {
                throw new SQLException("Update Failed!");
            }
        } finally {
            if (_pstmt != null) {
                _pstmt.close();
            }
            con.close();
        }

        return "Message Updated Successfully!";
    }
}
