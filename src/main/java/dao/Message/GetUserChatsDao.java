package dao.Message;

import dao.AbstractDAO;
import dao.User.GetUserByUseridDAO;
import resource.User;
import utils.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetUserChatsDao extends AbstractDAO {

    private static final String STATEMENT = "SELECT DISTINCT recipient_id FROM message WHERE creator_id=?";

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

    public List<Long> getChatIds(long _creatorId) throws SQLException, ResourceNotFoundException {
        PreparedStatement _pstmt = null;
        ResultSet _rs = null;
        List<Long> _recipients = new ArrayList<>();

        try {
            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setObject(1, _creatorId);
            _rs = _pstmt.executeQuery();

            while (_rs.next()) {
                _recipients.add(_rs.getLong("recipient_id"));
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
        return _recipients;
    }

}