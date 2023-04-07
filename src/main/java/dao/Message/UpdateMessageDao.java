package dao.Message;

import dao.AbstractDAO;
import resource.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateMessageDao extends AbstractDAO {

    private static final String STATEMENT = "UPDATE post SET message_id = ?, creator_id = ?, recipient_id = ?, " +
            "parent_message_id = ?, subject = ?, message_body = ?, is_read = ?, creation_date = ?, expiration_date = ?," +
            "WHERE message_id = ?";


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

            _pstmt.setLong(1, _message.getMessage_id());
            _pstmt.setLong(2, _message.getCreator_id());
            _pstmt.setLong(3, _message.getRecipient_id());
            _pstmt.setLong(4, _message.getParent_message_id());
            _pstmt.setString(5, _message.getSubject());
            _pstmt.setString(6, _message.getMessage_body());
            _pstmt.setBoolean(7, _message.isIs_read());
            _pstmt.setTimestamp(8, _message.getCreation_date());
            _pstmt.setTimestamp(9, _message.getExpiration_date());
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
