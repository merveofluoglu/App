package dao.Category;

import dao.AbstractDAO;
import resource.Category;
import utils.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GetCategoryByIdDao extends AbstractDAO {
    private static final String STATEMENT = "SELECT * FROM Category WHERE Category.Category_id = ?";

    public GetCategoryByIdDao(Connection con) {
        super(con);
    }

    public Category getCategoryById(long _id) throws SQLException, ResourceNotFoundException {

        PreparedStatement _pstmt = null;
        ResultSet _rs =null;
        Category _Category = null;

        try {
            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setObject(1, _id);
            _rs= _pstmt.executeQuery();
            if(!_rs.isBeforeFirst()) {
                throw new ResourceNotFoundException("Couldn't found such Category!");
            }
            if(_rs.next()) {
                _Category = new Category(
                        _rs.getLong("Category_id"),
                        _rs.getString("Category_name")
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

        return _Category;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
