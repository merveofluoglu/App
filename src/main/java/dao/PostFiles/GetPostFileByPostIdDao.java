package dao.PostFiles;

import dao.AbstractDAO;
import resource.PostFiles;
import utils.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetPostFileByPostIdDao extends AbstractDAO {

    private static final String STATEMENT =
            "SELECT * FROM post_files pf WHERE pf.post_id = ? AND is_deleted = false";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public GetPostFileByPostIdDao(Connection con) { super(con); }

    public PostFiles getPostFileByPostId(long _id) throws SQLException, ResourceNotFoundException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PostFiles postFile = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, _id);
            rs= pstmt.executeQuery();

            if(!rs.isBeforeFirst()) {
                throw new ResourceNotFoundException("Couldn't found such post file!");
            }

            while (rs.next()) {
                postFile = new PostFiles(
                        rs.getLong("file_id"),
                        rs.getLong("post_id"),
                        rs.getBytes("file"),
                        rs.getBoolean("is_deleted"),
                        rs.getString("file_media_type")
                );
            }
        } finally {
            if (rs != null) { rs.close(); }
            if (pstmt != null) { pstmt.close(); }
            con.close();
        }
        return postFile;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
