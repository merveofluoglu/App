package dao.ActionLog;

import dao.AbstractDAO;
import resource.ActionLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetUserActionLogDao extends AbstractDAO {

    private static final String STATEMENT = "SELECT * FROM action_log WHERE is_user_act = true";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    protected GetUserActionLogDao(Connection con) {
        super(con);
    }

    public List<ActionLog> getUserLog() throws SQLException {

        PreparedStatement _pstmt = null;
        ResultSet _rs = null;

        List<ActionLog> _userLogs = new ArrayList<>();

        try {

            _pstmt = con.prepareStatement(STATEMENT);
            _rs = _pstmt.executeQuery();

            if(!_rs.isBeforeFirst()) {
                throw new SQLException("No User Logs to Show!");
            }

            while (_rs.next()) {
                _userLogs.add( new ActionLog(
                        _rs.getLong("action_id"),
                        _rs.getBoolean("is_user_act"),
                        _rs.getBoolean("is_system_act"),
                        _rs.getString("description"),
                        _rs.getTimestamp("action_date"),
                        _rs.getLong("user_id")
                ));
            }

        } finally {
            if(_pstmt != null) {
                _pstmt.close();
            }
            con.close();
        }

        return _userLogs;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
