package dao.Category;

import dao.AbstractDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteSubCategoryDao extends AbstractDAO {
    private static final String STATEMENT = "INSERT INTO subcategory (subcategory_name) VALUES (?)";
    private Connection con;

    public DeleteSubCategoryDao(Connection con) {
        super(con);
        this.con = con;
    }
    //Declare and set up a connection in constructor

    @Override
    protected void doAccess() throws Exception {}

    public int DeleteSubCategory(long id) throws SQLException {
        PreparedStatement pstmt = null;
        int affectedRows = 0;

        try {
            //Prepare and set the statement object to be executed
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, id);
            affectedRows = pstmt.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Subcategory Deleting Failed");
                // return the row count that are manipulated: in this case 1 row deleted
                }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
                if (pstmt != null) {
                    pstmt.close();
                }
                con.close();
            }
            return affectedRows;
        }
}



