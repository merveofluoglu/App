package dao.Favourites;


import dao.AbstractDAO;
import resource.Favourites;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddFavouriteDao extends AbstractDAO {

    private static final String STATEMENT = "INSERT INTO favourites (user_id, post_id )" +
            " VALUES(?, ?)";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public AddFavouriteDao(Connection con) {
        super(con);
    }

    public String addFavourite(Favourites _fav) throws SQLException {

        PreparedStatement _pstmt = null;

        int _rs = 0;

        try {

            _pstmt = con.prepareStatement(STATEMENT);

            _pstmt.setLong(1, _fav.getUserId());
            _pstmt.setLong(2, _fav.getPostId());

            _rs = _pstmt.executeUpdate();

            if (_rs != 1) {
                throw new SQLException("Creation failed!");
            }

        } finally {
            if (_pstmt != null) {
                _pstmt.close();
            }
            con.close();
        }

        return "Added to Favourites Successfully";
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
