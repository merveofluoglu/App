package dao.PostFiles;

import dao.AbstractDAO;
import resource.PostFiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetPostFilesByPostIdDao extends AbstractDAO<List<PostFiles>> {

    private static final String STATEMENT = "SELECT * FROM posts p WHERE p.post_id = ?";

    public GetPostFilesByPostIdDao(Connection con) { super(con); }

    @Override
    protected void doAccess() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<PostFiles> postFiles = new ArrayList<PostFiles>();

        try {
            pstmt = con.prepareStatement(STATEMENT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                postFiles.add(new PostFiles(
                        rs.getLong("file_id"),
                        rs.getLong("post_id"),
                        rs.getString("file_type"),
                        rs.getDouble("file_size"),
                        rs.getString("file_path"))
                );
            }

            LOGGER.info("Post file(s) successfully listed.");
        } finally {
            if (rs != null) { rs.close(); }
            if (pstmt != null) { pstmt.close(); }
        }
        outputParam = postFiles;
    }
}












