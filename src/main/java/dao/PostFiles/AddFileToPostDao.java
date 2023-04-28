package dao.PostFiles;

import dao.AbstractDAO;
import resource.PostFiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddFileToPostDao extends AbstractDAO {
    private static final String STATEMENT = "INSERT INTO post_files " +
            "(file_id, post_id, file, is_deleted, file_media_type) VALUES (nextval('postfiles_seq'), ?, ?, ?, ?)";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public AddFileToPostDao(Connection con) { super(con); }

    public String addFileToPost(PostFiles _postFiles) throws SQLException {
        PreparedStatement pstmt = null;
        int rs;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setLong(1, _postFiles.getPost_id());
            pstmt.setBytes(2, _postFiles.getFile());
            pstmt.setBoolean(3, _postFiles.isIs_deleted());
            pstmt.setString(4, _postFiles.getFile_media_type());

            rs = pstmt.executeUpdate();

            if (rs != 1) { throw new SQLException("Addition failed!"); }
        } finally {
            if (pstmt != null) { pstmt.close(); }
            con.close();
        }
        return "File Added Successfully!";
    }

    @Override
    protected void doAccess() throws SQLException {

    }
}
