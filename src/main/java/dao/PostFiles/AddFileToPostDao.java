package dao.PostFiles;

import dao.AbstractDAO;
import resource.PostFiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddFileToPostDao extends AbstractDAO {
    private static final String STATEMENT = "INSERT INTO postfiles " +
            "(post_id, file_type, file_size, file_path) VALUES (?, ?, ?, ?)";

    public AddFileToPostDao(Connection con) { super(con); }

    public String addFileToPost(PostFiles pf) throws SQLException {
        PreparedStatement pstmt = null;
        int rs;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setLong(1, pf.getPost_id());
            pstmt.setString(2, pf.getFile_type());
            pstmt.setDouble(3, pf.getFile_size());
            pstmt.setString(4, pf.getFile_path());

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
