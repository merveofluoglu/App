package dao.Category;

import dao.AbstractDAO;
import resource.SubCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateSubCategoryByIdDao extends AbstractDAO {
    private static final String STATEMENT = "UPDATE SubCategory Set subcategory_name = ?";

    public UpdateSubCategoryByIdDao(Connection con) {
        super(con);
        this.con = con;
    }

    public int updateSubCategoryById(SubCategory subcategory) throws SQLException {
        PreparedStatement pstmt = null;
        int affectedRows = 0;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, subcategory.getSubCategory_name());

            affectedRows = pstmt.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Subcategory Updating Failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
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


