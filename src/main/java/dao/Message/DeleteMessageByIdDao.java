package dao.Message;

import dao.AbstractDAO;
import resource.Message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DeleteMessageByIdDao extends AbstractDAO {

    private static final String STATEMENT = "DELETE FROM message WHERE message_id = ?";

    public DeleteMessageByIdDao(Connection con) { super(con); }

    public int deleteMessageById(long _id) throws SQLException {
        PreparedStatement pstmt = null;
        int affectedRows = 0;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setLong(1, _id);

            affectedRows = pstmt.executeUpdate();

            if(affectedRows != 1) { throw new SQLException("Delete Failed"); }
        } finally {
            if (pstmt != null) { pstmt.close(); }
            con.close();
        }

        return affectedRows;
    }

    @Override
    protected void doAccess() throws SQLException {

    }
}
