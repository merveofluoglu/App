package dao.Post;

import dao.AbstractDAO;
import resource.Favourites;
import resource.Post;
import utils.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetPostsByCustomerFavouritesDao extends AbstractDAO {

    private static final String STATEMENT = "SELECT * FROM favourites WHERE user_id = ?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public GetPostsByCustomerFavouritesDao(Connection con) {
        super(con);
    }

    public List<Favourites> getPostsByCustomerFavourites(long _id) throws SQLException, ResourceNotFoundException {

        PreparedStatement _pstmt = null;
        ResultSet _rs = null;
        List<Favourites> _favourites = new ArrayList<>();

        //GetPostByIdDao getPosts = null;

        try {

            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setObject(1,_id);
            _rs = _pstmt.executeQuery();

            if (!_rs.isBeforeFirst()) {
                return _favourites;
            }

            while (_rs.next()) {
                _favourites.add(
                        new Favourites(
                                _rs.getLong("favourite_id"),
                                _rs.getLong("post_id"),
                                _rs.getLong("user_id")
                        )
                );
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

        return _favourites;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
