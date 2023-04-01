package dao.PostFiles;

import resource.PostFiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateFilesByPostIdDao {

    private static final String STATEMENT = "UPDATE post_files SET file_id = ?, post_id = ?, file_type = ?, file_size = ?, file_path = ? WHERE post_id = ?";

    private final Connection con;

    public UpdateFilesByPostIdDao(Connection con) {
        this.con = con;
    }

    public int updatePostFiles(long post_id, String file_type, double file_size, String file_path) throws SQLException {
        PreparedStatement pstmt = null;
        int affectedRows = 0;
        PostFiles pf = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setLong(1, post_id);
            pstmt.setString(2, file_type);
            pstmt.setDouble(3, file_size);
            pstmt.setString(4, file_path);

            affectedRows = pstmt.executeUpdate();

            if(affectedRows != 1) { throw new SQLException("Update failed."); }
        } finally {
            if(pstmt != null) { pstmt.close(); }
            con.close();
        }
        return affectedRows;
    }


    private long file_id;

    private long post_id;

    private String file_type;

    private double file_size;

    private String file_path;
}
