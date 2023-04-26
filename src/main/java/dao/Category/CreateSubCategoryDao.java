package dao.Category;

import dao.AbstractDAO;
import resource.SubCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateSubCategoryDao extends AbstractDAO{
    // SQL Query to be executed : CREATE subcategoryobject in database.

    private static final String STATEMENT = "INSERT INTO sub_category (subcategory_id, subcategory_name, category_id) VALUES (nextval('subcategory_seq'), ?, ?)";
    private Connection con;

    public CreateSubCategoryDao(Connection con) {
        super(con);
        this.con = con;
    }
    // Declare and set up a connection in constructor

    @Override
    protected void doAccess() throws Exception {}

    public String CreateSubCategory(SubCategory subcategory) throws SQLException {
        PreparedStatement pstmt = null;
        int rs;
        try {
            // Prepare and set the statement object to be executed
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, subcategory.getSubcategory_name());
            pstmt.setLong(2, subcategory.getCategory_id());

            rs = pstmt.executeUpdate();
            if (rs != 1 ) {
                throw new SQLException("Subcategory Creation Failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            con.close();
        }
        return "Subcategory Creation Successfully!";
    }
}
