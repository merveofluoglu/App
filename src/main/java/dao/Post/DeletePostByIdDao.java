package dao.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletePostByIdDao {

    private static final String STATEMENT = "UPDATE post SET is_deleted = true WHERE post_id = ?";

    private final Connection con;

    public DeletePostByIdDao(Connection con) {
        this.con = con;
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
