package dao.PostFiles;

import dao.AbstractDAO;
import resource.PostFiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoadPostFileDao extends AbstractDAO {
    private static final String STATEMENT =
            "SELECT file, file_media_type FROM post_files WHERE file_id = ?";

    private final long file_id;

    public LoadPostFileDao(Connection con, final long file_id) {
        super(con);
        this.file_id = file_id;
    }

    @Override
    public final void doAccess() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        PostFiles pf = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setLong(1, file_id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                pf = new PostFiles(
                        Long.MIN_VALUE,
                        Long.MIN_VALUE,
                        rs.getBytes("file"),
                        false,
                        rs.getString("file_media_type"));

              LOGGER.info("File for post successfully loaded.");
              } else {
              LOGGER.warn("Post not found.");
              throw new SQLException(String.format("Post not found."), "NOT_FOUND");
              }


            } finally {
            if (rs != null) { rs.close(); }

            if (pstmt != null) { pstmt.close(); }
        }
        this.outputParam = pf;
    }

}
