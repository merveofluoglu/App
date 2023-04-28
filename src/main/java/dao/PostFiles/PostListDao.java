package dao.PostFiles;

import dao.AbstractDAO;
import resource.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostListDao extends AbstractDAO {

    private static final String STATEMENT = "SELECT name FROM post ORDER BY name";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public PostListDao(Connection con) { super(con); }

    public List<Post> postList() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Post> postList = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("post_id");
                String name = rs.getString("name");
                Post post = new Post(id, name);

                postList.add(post);
            }
            return postList;
        } finally {
            if (rs != null) { rs.close(); }
            if (pstmt != null) { pstmt.close(); }
            con.close();
        }
    }


    @Override
    protected void doAccess() throws Exception {

    }
}
