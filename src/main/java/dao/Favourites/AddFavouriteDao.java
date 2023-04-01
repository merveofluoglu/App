package dao.Favourites;


import dao.AbstractDAO;
import resource.Favourites;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddFavouriteDao extends AbstractDAO {

    private static final String STATEMENT = "INSERT INTO favourites ( user_id, post_id )" +
            " VALUES(?, ?)";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    protected AddFavouriteDao(Connection con) {
        super(con);
    }

    public String addFavourite(Favourites fav) throws SQLException {

        PreparedStatement pstmt = null;

        int rs = 0;

        try {

            pstmt = con.prepareStatement(STATEMENT);

            pstmt.setLong(1, fav.getUser_id());
            pstmt.setLong(2, fav.getPost_id());

            rs = pstmt.executeUpdate();

            if (rs != 1) {
                throw new SQLException("Creation failed!");
            }

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            con.close();
        }

        return "Added to Favourites Successfully";
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
