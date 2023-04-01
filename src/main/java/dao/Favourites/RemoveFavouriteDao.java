package dao.Favourites;

import dao.AbstractDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveFavouriteDao extends AbstractDAO {

    private static final String STATEMENT = "DELETE FROM favourites WHERE post_id = ?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    protected RemoveFavouriteDao(Connection con) {
        super(con);
    }

    public int removeFavourite(long post_id) throws SQLException {

        PreparedStatement pstmt = null;
        int affectedRows = 0;

        try {
            pstmt = con.prepareStatement(STATEMENT);

            pstmt.setLong(1, post_id);

            affectedRows = pstmt.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Remove Failed!");
            }

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            con.close();
        }

        return affectedRows;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
