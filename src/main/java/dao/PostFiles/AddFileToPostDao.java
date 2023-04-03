package dao.PostFiles;

import dao.AbstractDAO;
import resource.PostFiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddFileToPostDao extends AbstractDAO<PostFiles> {
    private static final String STATEMENT = "INSERT INTO postfiles (post_id, file_type, file_size, file_path) VALUES (?, ?, ?, ?)";

    private PostFiles postFiles;

    protected AddFileToPostDao(Connection con, PostFiles postFiles) {
        super(con);

        if (postFiles == null) {
            LOGGER.error("The post file cannot be null.");
            throw new NullPointerException("The post file cannot be null.");
        }

        this.postFiles = postFiles;
    }

    @Override
    protected void doAccess() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        PostFiles pf = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setLong(1, pf.getFile_id());
            pstmt.setLong(2, pf.getPost_id());
            pstmt.setString(3, pf.getFile_type());
            pstmt.setDouble(4, pf.getFile_size());
            pstmt.setString(5, pf.getFile_path());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                pf = new PostFiles(
                        rs.getLong("file_id"),
                        rs.getLong("post_id"),
                        rs.getString("file_type"),
                        rs.getDouble("file_size"),
                        rs.getString("file_path")
                );

                LOGGER.info("Post file %d successfully stored in the database.", pf.getFile_id());
            }
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        outputParam = pf;
    }
}
