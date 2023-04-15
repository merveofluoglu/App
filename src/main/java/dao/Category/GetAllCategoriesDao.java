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

public class GetAllCategoriesDao  extends AbstractDAO {
    private static final String STATEMENT = "SELECT * FROM Category";
    public GetAllCategoriesDao(Connection con) {
        super(con);
    }
    public List<Category> getAllCategories () throws SQLException, ResourceNotFoundException {
        PreparedStatement _pstmt = null;
        ResultSet _rs = null;
        List<Category> _Categories = new ArrayList<>();
        try {
            _pstmt = con.prepareStatement(STATEMENT);
            _rs = _pstmt.executeQuery();

            if(!_rs.isBeforeFirst()) {
                throw new ResourceNotFoundException("There are no Category!");
            }
            while (_rs.next()) {
                _Categories.add(
                        new Category(
                                _rs.getLong("Category_id"),
                                _rs.getString("Category_name")
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
        return _Categories;
    }
    @Override
    protected void doAccess() throws Exception {}

}
