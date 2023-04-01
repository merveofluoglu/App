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
    protected AddActionLogDao(Connection con) {
        super(con);
    }

    public String addActionLog(ActionLog actionLog) throws SQLException
    {
        PreparedStatement pstmt = null;

        int rs = 0;

        try {

            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setBoolean(1, actionLog.isIs_user_act());
            pstmt.setBoolean(2, actionLog.isIs_system_act());
            pstmt.setString(3, actionLog.getDescription());
            pstmt.setTimestamp(4, actionLog.getAction_date());
            pstmt.setLong(5, actionLog.getUser_id());

            rs = pstmt.executeUpdate();

            if (rs != 1) {
                throw new SQLException("Error occured while adding log!");
            }

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            con.close();
        }

        return "Log added successfully";
    }
    @Override
    protected void doAccess() throws Exception {

    }
}
