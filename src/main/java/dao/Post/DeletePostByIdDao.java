package dao.Post;

import dao.AbstractDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletePostByIdDao extends AbstractDAO {

    private static final String STATEMENT = "UPDATE post SET is_deleted = true WHERE post_id = ?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    protected DeletePostByIdDao(Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

    }

    public int deletePost(long id) throws SQLException {

        PreparedStatement pstmt = null;
        int affectedRows = 0;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, id);

            affectedRows = pstmt.executeUpdate();

            if(affectedRows != 1) {
                throw new SQLException("Delete Failed");
            }
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            con.close();
        }

        return affectedRows;
    }
}
