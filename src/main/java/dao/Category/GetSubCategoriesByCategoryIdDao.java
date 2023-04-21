package dao.Category;

import dao.AbstractDAO;
import resource.SubCategory;
import utils.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GetSubCategoriesByCategoryIdDao extends AbstractDAO{
    private static final String STATEMENT = "SELECT * FROM sub_category WHERE category_id = ?";
    public GetSubCategoriesByCategoryIdDao(Connection con) {
        super(con);
    }
    public List<SubCategory> GetSubCategoriesByCategoryId(long _id) throws SQLException, ResourceNotFoundException {
        PreparedStatement _pstmt = null;
        ResultSet _rs = null;
        List<SubCategory> _SubCategories = new ArrayList<>();
        try {
            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setObject(1, _id);
            _rs = _pstmt.executeQuery();
            while (_rs.next()) {
                _SubCategories.add(
                        new SubCategory(
                                _rs.getLong("SubCategory_id"),
                                _rs.getString("SubCategory_name"),
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
        return _SubCategories;
    }
    @Override
    protected void doAccess() throws Exception {
    }
}

