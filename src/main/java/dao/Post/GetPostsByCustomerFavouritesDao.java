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
    public GetPostsByCustomerFavouritesDao(Connection con) {
        super(con);
    }

    public List<Post> getPostsByCustomerFavourites(long _id) throws SQLException, ResourceNotFoundException {

        PreparedStatement _pstmt = null;
        ResultSet _rs = null;
        List<Post> _posts = new ArrayList<>();
        Long _postId = null;

        GetPostByIdDao getPosts = null;

        try {

            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setObject(1,_id);
            _rs = _pstmt.executeQuery();

            if (!_rs.isBeforeFirst()) {
                throw new ResourceNotFoundException("No Favourites Yet!");
            }

            if (_rs.next()) {
                _postId = _rs.getLong("post_id");

                _posts.add(getPosts.getPostById(_postId));
            }

        } finally {
            if (_rs != null) {
                _rs.close();
            }
            if (_pstmt != null) {
                _pstmt.close();
            }
            con.close();
        }

        return _posts;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
