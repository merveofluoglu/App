package dao.User;

import dao.AbstractDAO;
import resource.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UploadProfilePhotoDAO extends AbstractDAO {

    private static final String STATEMENT = "SELECT pp_path FROM users WHERE user_id = ?";

    private final long user_id;

    public UploadProfilePhotoDAO(Connection con, final long user_id) {
        super(con);
        this.user_id = user_id;
    }

    @Override
    public final void doAccess() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        User userPP = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setLong(1, user_id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                userPP = new User(
                        rs.getLong("user_id"),
                        rs.getBytes("pp_path"));


                LOGGER.info("File for profile photo successfully loaded.");
            } else {
                LOGGER.warn("Profile photo not found.");
                throw new SQLException(String.format("Profile photo not found."), "NOT_FOUND");
            }


        } finally {
            if (rs != null) { rs.close(); }

            if (pstmt != null) { pstmt.close(); }
        }
        this.outputParam = userPP;
    }

}
