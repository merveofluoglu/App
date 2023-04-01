package dao.Post;

import dao.AbstractDAO;
import resource.Post;
import utils.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GetPostByIdDao extends AbstractDAO {

    private static final String STATEMENT = "SELECT * FROM post WHERE post.post_id = ? AND is_deleted = false";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    protected GetPostByIdDao(Connection con) {
        super(con);
    }

    public Post getPostById(long id) throws SQLException, ResourceNotFoundException {

        PreparedStatement pstmt = null;
        ResultSet rs =null;
        Post post = null;

        try {

            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, id);
            rs= pstmt.executeQuery();

            if(!rs.isBeforeFirst()) {
                throw new ResourceNotFoundException("Couldn't found such post!");
            }

            if(rs.next()) {
                post = new Post(
                        rs.getLong("post_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getLong("user_id"),
                        rs.getLong("customer_id"),
                        rs.getDouble("price"),
                        rs.getString("status"),
                        rs.getTimestamp("start_date"),
                        rs.getTimestamp("end_date"),
                        rs.getBoolean("is_deleted"),
                        rs.getBoolean("is_sold"),
                        rs.getTimestamp("sold_date"),
                        rs.getTimestamp("update_date"),
                        rs.getLong("category_id"),
                        rs.getLong("subcategory_id")
                );
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            con.close();
        }

        return post;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
