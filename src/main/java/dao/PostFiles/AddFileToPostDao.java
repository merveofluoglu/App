package dao.PostFiles;

import resource.PostFiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddFileToPostDao {
    private static final String STATEMENT = "INSERT INTO postfiles (post_id, file_type, file_size, file_path) VALUES (?, ?, ?, ?)";

    private final Connection con;

    public AddFileToPostDao(Connection con) { this.con = con; }

    public String AddFile(long post_id, String file_type, double file_size, String file_path) throws SQLException {
        System.out.println("We are in AddFileToPostDao: ");
        PreparedStatement pstmt = null;
        int rs;
        PostFiles pf = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setLong(1, post_id);
            pstmt.setString(2, file_type);
            pstmt.setDouble(3, file_size);
            pstmt.setString(4, file_path);

            rs = pstmt.executeUpdate();

            if(rs != 1) { throw new SQLException("Addition failed."); }
        } finally {
            if(pstmt != null) { pstmt.close(); }
            con.close();
        }
        return  "successful";
    }
}
