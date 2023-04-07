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

public class GetPostFilesByPostIdDao extends AbstractDAO {

    private static final String STATEMENT = "SELECT * FROM postfiles p WHERE p.post_id = ?";

    public GetPostFilesByPostIdDao(Connection con) { super(con); }

    public List<PostFiles> getPostFilesByPostId(long _id) throws SQLException, ResourceNotFoundException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<PostFiles> postFiles = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, _id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                postFiles.add(new PostFiles(
                        rs.getLong("file_id"),
                        rs.getLong("post_id"),
                        rs.getString("file_type"),
                        rs.getDouble("file_size"),
                        rs.getString("file_path"))
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












