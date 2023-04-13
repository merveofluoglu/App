package dao.Category;
import resource.Category;
import dao.AbstractDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateCategoryDao extends AbstractDAO{
    private static final String STATEMENT =  "UPDATE Category Set category_name = ? WHERE category_id = ?";

    public UpdateCategoryDao(Connection con) {super(con);}
    //Declare and set up a connection in constructor
    public int UpdateCategoryByIdDao(Category category, long _CategoryId) throws SQLException {
        PreparedStatement pstmt = null;
        int affectedRows = 0;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, category.getCategory_name());
            pstmt.setLong(2, _CategoryId);

            affectedRows = pstmt.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Category Updating Failed!");
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
    protected void doAccess() throws Exception { }
}
