package dao.Message;

import dao.AbstractDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import resource.Message;
import utils.ResourceNotFoundException;

public class GetMessageByIdDao extends AbstractDAO {

    private static final String STATEMENT = "SELECT * FROM message WHERE message.message_id = ?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public GetMessageByIdDao(Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

    }

    public List<Message> getMessageById(long message_id) throws SQLException {
        PreparedStatement _pstmt = null;
        ResultSet _rs = null;
        List<Message> _messages = new ArrayList<>();


        try {

            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setObject(1, message_id);

            _rs = _pstmt.executeQuery();


            if(_rs.next()) {
                _messages.add(
                        new Message(
                        _rs.getLong("message_id"),
                        _rs.getLong("creator_id"),
                        _rs.getLong("recipient_id"),
                        _rs.getLong("parent_message_id"),
                        _rs.getString("subject"),
                        _rs.getString("message_body"),
                        _rs.getBoolean("is_read"),
                        _rs.getTimestamp("creation_date"),
                        _rs.getTimestamp("expiration_date"))
                );
            }

        } finally {
            if (_rs != null) {
                _rs.close();
            }
            if (_pstmt != null) {
                _pstmt.close();
            }
            con.close();
        }
        return _messages;
    }
}
