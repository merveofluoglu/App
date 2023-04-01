package dao.Post;

import dao.AbstractDAO;
import resource.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdatePostByIdDao extends AbstractDAO {

    private static final String STATEMENT =
            "UPDATE post SET name = ?, description = ?, customer_id = ?, price = ?, status = ?, is_deleted = ?, is_sold = ?," +
                    "sold_date = ?, update_date = ?, category_id = ?, subcategory_id = ?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    protected UpdatePostByIdDao(Connection con) {
        super(con);
    }

    public int updatePostById(Post post) throws SQLException {

        PreparedStatement pstmt = null;
        int affectedRows = 0;

        try {

            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, post.getName());
            pstmt.setString(2, post.getDescription());
            pstmt.setLong(3, post.getCustomer_id());
            pstmt.setDouble(4, post.getPrice());
            pstmt.setString(5, post.getStatus());
            pstmt.setBoolean(6, post.isIs_deleted());
            pstmt.setBoolean(7, post.isIs_sold());
            pstmt.setTimestamp(8, post.getSold_date());
            pstmt.setTimestamp(9, post.getUpdate_date());
            pstmt.setLong(10, post.getCategory_id());
            pstmt.setLong(11, post.getSubcategory_id());

            affectedRows = pstmt.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Update Failed!");
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
