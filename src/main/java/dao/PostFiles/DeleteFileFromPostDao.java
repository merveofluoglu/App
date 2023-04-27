package dao.PostFiles;

import dao.AbstractDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteFileFromPostDao extends AbstractDAO {
    private static final String STATEMENT = "UPDATE post_files SET is_deleted = true WHERE file_id = ?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public DeleteFileFromPostDao(Connection con) { super(con); }

    public int deleteFileFromPost(long _id) throws SQLException {
        PreparedStatement pstmt = null;
        int affectedRows = 0;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, _id);

            affectedRows = pstmt.executeUpdate();

            if(affectedRows != 1) { throw new SQLException("Delete Failed"); }
        } finally {
            if (pstmt != null) { pstmt.close(); }
            con.close();
        }

        return affectedRows;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
