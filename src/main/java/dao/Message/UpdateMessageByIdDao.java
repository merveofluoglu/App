package dao.Message;

import dao.AbstractDAO;
import resource.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateMessageByIdDao extends AbstractDAO {

    private static final String STATEMENT = "UPDATE message SET creator_id = ?, recipient_id = ?, " +
            "parent_message_id = ?, subject = ?, message_body = ? WHERE message_id = ?";


    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public UpdateMessageByIdDao(Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

    }

    public int updateMessageById(Message _message, long _message_id) throws SQLException {
        PreparedStatement _pstmt = null;
        int _affectedRows = 0;

        try {

            _pstmt = con.prepareStatement(STATEMENT);

            _pstmt.setLong(1, _message.getCreator_id());
            _pstmt.setLong(2, _message.getRecipient_id());
            _pstmt.setLong(3, _message.getParent_message_id());
            _pstmt.setString(4, _message.getSubject());
            _pstmt.setString(5, _message.getMessage_body());
            _pstmt.setLong(6, _message_id);

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

        return _affectedRows;
    }
}
