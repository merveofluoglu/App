package dao.Category;

import dao.AbstractDAO;
import resource.Category;
import utils.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GetCategoryByNameDao extends AbstractDAO {

    private static final String STATEMENT = "SELECT * FROM category WHERE category_name ILIKE ?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public GetCategoryByNameDao(Connection con) {
        super(con);
    }

    public List<Category> getCategoriesByName(String _name) throws SQLException, ResourceNotFoundException {

        PreparedStatement _pstmt = null;
        ResultSet _rs = null;
        List<Category> _category = new ArrayList<>();

        try {

            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setString(1, "%" + _name + "%");;
            _rs = _pstmt.executeQuery();

            while (_rs.next()) {
                _category.add(
                        new Category(
                                _rs.getLong("category_id"),
                                _rs.getString("category_name")
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

        return _category;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
