package dao.Message;

import dao.AbstractDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utils.ResourceNotFoundException;

public class GetMessageByIdDao extends AbstractDAO {

    private static final String STATEMENT = "SELECT * FROM message WHERE message.messageId = ?";

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

    public String getMessageById(long messageId) throws SQLException {
        PreparedStatement _pstmt = null;
        int _affectedRows;

        try {

            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setLong(1, messageId);

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
