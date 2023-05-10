package dao.PostFiles;

import dao.AbstractDAO;
import resource.PostFiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateFilesByPostIdDao extends AbstractDAO {

    private static final String STATEMENT =
            "UPDATE post_files SET file_id = ?, file = ?, is_deleted = ?, file_media_type = ? WHERE post_id = ?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public UpdateFilesByPostIdDao(Connection con) { super(con); }

    public int updateFilesByPostId(PostFiles pf, long post_id) throws SQLException {
        PreparedStatement pstmt = null;
        int affectedRows = 0;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setLong(1, pf.getFileId());
            pstmt.setBytes(2, pf.getFile());
            pstmt.setBoolean(3, pf.isDeleted());
            pstmt.setString(4, pf.getFileMediaType());
            pstmt.setLong(5, post_id);

            affectedRows = pstmt.executeUpdate();

            if (affectedRows != 1) { throw new SQLException("Update Failed!"); }

        } finally {
            if (pstmt != null) { pstmt.close(); }
            con.close();
        }
        return affectedRows;
    }

    @Override
    protected void doAccess() throws SQLException {

    }
}
