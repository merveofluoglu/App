package dao.PostFiles;

import dao.AbstractDAO;
import resource.PostFiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteFileFromPostDao extends AbstractDAO<PostFiles> {
    private static final String STATEMENT = "DELETE FROM post_files WHERE file_id = ? RETURNING *";

    private long file_id;

    public DeleteFileFromPostDao(Connection con, long file_id) {
        super(con);
        this.file_id = file_id;
    }

    @Override
    protected void doAccess() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        PostFiles pf = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setLong(1, file_id);

            rs = pstmt.executeQuery();

            while(rs.next()) {
                pf = new PostFiles(
                        rs.getLong("file_id"),
                        rs.getLong("post_id"),
                        rs.getString("file_type"),
                        rs.getDouble("file_size"),
                        rs.getString("file_path")
                );
                LOGGER.info("Post file %d successfully deleted from the database.", pf.getFile_id());
            }
        } finally {
            if(rs != null) { rs.close(); }
            if(pstmt != null) { pstmt.close(); }
        }
        outputParam = pf;
    }
}
