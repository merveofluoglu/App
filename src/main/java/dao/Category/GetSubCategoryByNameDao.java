package dao.Category;

import dao.AbstractDAO;
import resource.Category;
import resource.SubCategory;
import utils.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetSubCategoryByNameDao extends AbstractDAO {

    private static final String STATEMENT = "SELECT * FROM sub_category WHERE subcategory_name ILIKE ?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public GetSubCategoryByNameDao(Connection con) {
        super(con);
    }

    public List<SubCategory> getSubCategoriesByName(String _name) throws SQLException, ResourceNotFoundException {

        PreparedStatement _pstmt = null;
        ResultSet _rs = null;
        List<SubCategory> _subCategory = new ArrayList<>();

        try {

            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setString(1, "%" + _name + "%");
            ;
            _rs = _pstmt.executeQuery();

            while (_rs.next()) {
                _subCategory.add(
                        new SubCategory(
                                _rs.getLong("subcategory_id"),
                                _rs.getString("subcategory_name"),
                                _rs.getLong("category_id")
                        )
                );
            }

        } finally {
            if (_rs != null) {
                _rs.close();
            }
            if (_pstmt != null) {
                _pstmt.close();
            }
            con.close();
        }
        return _subCategory;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
