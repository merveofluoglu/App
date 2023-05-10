package dao.ActionLog;

import dao.AbstractDAO;
import resource.ActionLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddActionLogDao extends AbstractDAO {

    private static final String STATEMENT = "INSERT INTO action_log ( is_user_act, is_system_act, description, action_date, user_id ) " +
            "VALUES (?, ?, ?, ?, ?)";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public AddActionLogDao(Connection con) {
        super(con);
    }

    public String addActionLog(ActionLog _actionLog) throws SQLException
    {
        PreparedStatement _pstmt = null;

        int _rs = 0;

        try {

            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setBoolean(1, _actionLog.isUserAct());
            _pstmt.setBoolean(2, _actionLog.isSystemAct());
            _pstmt.setString(3, _actionLog.getDescription());
            _pstmt.setTimestamp(4, _actionLog.getActionDate());
            _pstmt.setLong(5, _actionLog.getUserId());

            _rs = _pstmt.executeUpdate();

            if (_rs != 1) {
                throw new SQLException("Error occurred while adding log!");
            }

        } finally {
            if (_pstmt != null) {
                _pstmt.close();
            }
            con.close();
        }

        return "Log added successfully!";
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
