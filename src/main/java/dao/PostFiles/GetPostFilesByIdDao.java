package dao.PostFiles;

import dao.AbstractDAO;
import resource.PostFiles;
import utils.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetPostFilesByIdDao extends AbstractDAO {

    private static final String STATEMENT = "SELECT * FROM post_files";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public GetPostFilesByIdDao(Connection con) { super(con); }

    public List<PostFiles> getPostFilesById() throws SQLException, ResourceNotFoundException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<PostFiles> postFiles = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT);
            rs = pstmt.executeQuery();

            if(!rs.isBeforeFirst()) {
                return postFiles;
            }

            while (rs.next()) {
                postFiles.add(new PostFiles(
                        rs.getLong("file_id"),
                        rs.getLong("post_id"),
                        rs.getBytes("file"),
                        rs.getBoolean("is_deleted"),
                        rs.getString("file_media_type"))
                );
            }
        } finally {
            if (rs != null) { rs.close(); }
            if (pstmt != null) { pstmt.close(); }
            con.close();
        }
        return postFiles;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}












