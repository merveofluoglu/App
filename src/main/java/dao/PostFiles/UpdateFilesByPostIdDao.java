package dao.PostFiles;

import dao.AbstractDAO;
import resource.PostFiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateFilesByPostIdDao extends AbstractDAO {

    private static final String STATEMENT = "UPDATE post_files SET file_id = ?, file_type = ?, file_size = ?, file_path = ? WHERE post_id = ?";

    public UpdateFilesByPostIdDao(Connection con) { super(con); }

    public int updateFilesByPostId(PostFiles pf, long postId) throws SQLException {
        PreparedStatement pstmt = null;
        int affectedRows = 0;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setLong(1, pf.getFile_id());
            pstmt.setString(2, pf.getFile_type());
            pstmt.setDouble(3, pf.getFile_size());
            pstmt.setString(4, pf.getFile_path());
            pstmt.setLong(5, postId);

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
