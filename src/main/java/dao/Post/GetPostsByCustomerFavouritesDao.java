package dao.Post;

import dao.AbstractDAO;
import resource.Post;
import utils.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetPostsByCustomerFavouritesDao extends AbstractDAO {

    private static final String STATEMENT = "SELECT post_id FROM favourites WHERE user_id = ?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    protected GetPostsByCustomerFavouritesDao(Connection con) {
        super(con);
    }

    public List<Post> getPostsByCustomerFavourites(long id) throws SQLException, ResourceNotFoundException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Post> posts = new ArrayList<>();
        Long postId = null;

        GetPostByIdDao getPosts = null;

        try {

            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1,id);
            rs = pstmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                throw new ResourceNotFoundException("No Favourites Yet!");
            }

            if (rs.next()) {
                postId = rs.getLong("post_id");

                posts.add(getPosts.getPostById(postId));
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

        return posts;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
