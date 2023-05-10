package dao.Category;
import resource.Category;
import dao.AbstractDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateCategoryDao extends AbstractDAO {
    // SQL Query to be executed : CREATE categoryobject in database.

    private static  final String STATEMENT = "INSERT INTO category (category_id, category_name) VALUES (nextval('category_seq'), ?) ";

    public CreateCategoryDao(Connection con) {super(con);}
    //Declare and set up a connection in constructor
    @Override
    protected void doAccess() throws Exception {}

    public String CreateCategory(Category category) throws SQLException {
        PreparedStatement pstmt = null;
        int rs;
        try {
            //Prepare and set the statement object to be executed
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, category.getCategoryName());

            rs = pstmt.executeUpdate();
            if (rs != 1) {
                throw new SQLException("Category Creation Failed!");
            }
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            con.close();
        }
        return "Category Creation Successfully!";
    }
}