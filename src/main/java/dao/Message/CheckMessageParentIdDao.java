package dao.Message;

import dao.AbstractDAO;
import resource.Message;
import utils.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CheckMessageParentIdDao extends AbstractDAO {
    private static final String STATEMENT = "SELECT * FROM message WHERE creator_id=? OR recipient_id=?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public CheckMessageParentIdDao(Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

    }


    public List<Message> getAllMessagesByUserId(long user_id) throws SQLException, ResourceNotFoundException {

        PreparedStatement _pstmt = null;
        ResultSet _rs = null;
        List<Message> _messages = new ArrayList<>();

        try {
            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setObject(1, user_id);
            _pstmt.setObject(2, user_id);
            _rs = _pstmt.executeQuery();

            if(!_rs.isBeforeFirst()) {
                throw new ResourceNotFoundException("There are no messages!");
            }

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
