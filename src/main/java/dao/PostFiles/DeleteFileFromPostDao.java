package dao.PostFiles;

import resource.PostFiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteFileFromPostDao {
    private static final String STATEMENT = "DELETE FROM post_files WHERE file_id = ? RETURNING *";

    private final Connection con;

    public DeleteFileFromPostDao(Connection con) { this.con = con; }

    public PostFiles deleteFileFromPost(long file_id, long post_id, String file_type, double file_size, String file_path) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PostFiles pf = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setLong(1, file_id);
            pstmt.setLong(2, post_id);
            pstmt.setString(3, file_type);
            pstmt.setDouble(4, file_size);
            pstmt.setString(5, file_path);

            rs = pstmt.executeQuery();
            while(rs.next()) {
                pf = new PostFiles(
                        rs.getLong("file_id"),
                        rs.getLong("post_id"),
                        rs.getString("file_type"),
                        rs.getDouble("file_size"),
                        rs.getString("file_path")
                );
            }
        } finally {
            if(rs != null) { rs.close(); }
            if(pstmt != null) { pstmt.close(); }
            con.close();
        }
        return pf;
    }
}
