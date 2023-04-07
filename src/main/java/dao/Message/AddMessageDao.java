package dao.Message;

import dao.AbstractDAO;
import resource.Message;
import resource.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddMessageDao extends AbstractDAO {

    private static final String STATEMENT = "INSERT INTO message ( message_id, creator_id, recipient_id," +
            "parent_message_id, subject, message_body, is_read, creation_date, expiration_date )" +
            " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    protected AddMessageDao(Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

    }

    public String addMessage(Message _message) throws SQLException {

        PreparedStatement _pstmt = null;
        int _rs;

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
