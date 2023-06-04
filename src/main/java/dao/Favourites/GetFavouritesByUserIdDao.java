package dao.Favourites;

import dao.AbstractDAO;
import resource.Favourites;
import utils.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetFavouritesByUserIdDao extends AbstractDAO {

    private static final String STATEMENT = "SELECT * FROM favourites WHERE user_id = ?";
    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */

    public GetFavouritesByUserIdDao(Connection con) {
        super(con);
    }

    public List<Favourites> getFavouritesByUserIdDao (long _userId) throws SQLException, ResourceNotFoundException {

        PreparedStatement _pstmt = null;
        ResultSet _rs = null;
        List<Favourites> _favs = new ArrayList<>();

        try {
            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setObject(1, _userId);
            _rs= _pstmt.executeQuery();

            if(!_rs.isBeforeFirst()) {
                return _favs;
            }

            while (_rs.next()) {
                _favs.add(
                        new Favourites(
                                _rs.getLong("user_id"),
                                _rs.getLong("post_id")
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

        return _favs;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
