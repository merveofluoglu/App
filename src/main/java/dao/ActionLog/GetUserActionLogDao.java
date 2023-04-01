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

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<ActionLog> userLogs = new ArrayList<>();

        try {

            pstmt = con.prepareStatement(STATEMENT);
            rs = pstmt.executeQuery();

            if(!rs.isBeforeFirst()) {
                throw new SQLException("No User Logs to Show!");
            }

            while (rs.next()) {
                userLogs.add( new ActionLog(
                        rs.getLong("action_id"),
                        rs.getBoolean("is_user_act"),
                        rs.getBoolean("is_system_act"),
                        rs.getString("description"),
                        rs.getTimestamp("action_date"),
                        rs.getLong("user_id")
                ));
            }

        } finally {
            if(pstmt != null) {
                pstmt.close();
            }
            con.close();
        }

        return userLogs;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
