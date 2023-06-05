package dao.Message;

import dao.AbstractDAO;
import dao.User.GetUserByUseridDAO;
import resource.Message;
import resource.User;
import utils.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetUserChatsDao extends AbstractDAO {

    private static final String STATEMENT = "SELECT * FROM message WHERE creator_id=? or recipient_id=?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public GetUserChatsDao(Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

    }

    public List<Message> getChatIds(long _creatorId) throws SQLException, ResourceNotFoundException {
        PreparedStatement _pstmt = null;
        ResultSet _rs = null;
        List<Message> _messages = new ArrayList<>();


        try {
            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setObject(1, _creatorId);
            _pstmt.setObject(2, _creatorId);
            _rs = _pstmt.executeQuery();

            while (_rs.next()) {
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
                                _rs.getTimestamp("expiration_date")
                        )
                );
            }
        }finally {
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