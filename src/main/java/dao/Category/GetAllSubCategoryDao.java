package dao.Category;

import dao.AbstractDAO;
import org.apache.maven.model.Resource;
import resource.SubCategory;
import utils.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAllSubCategoryDao extends AbstractDAO {
    private static final String STATEMENT = "SELECT * FROM SubCategory";

    protected GetAllSubCategoryDao(Connection con) {
        super(con);
    }

    public List<SubCategory> getAllSubCategories () throws SQLException, ResourceNotFoundException {
        PreparedStatement _pstmt = null;
        ResultSet _rs = null;
        List<SubCategory> _SubCategories = new ArrayList<>();

        try {
            _pstmt = con.prepareStatement(STATEMENT);
            _rs = _pstmt.executeQuery();

            if (!_rs.isBeforeFirst()) {
                throw new ResourceNotFoundException("There are no SubCategory!");
            }

            while (_rs.next()) {
                _SubCategories.add(
                        new SubCategory(
                                _rs.getLong("SubCategory_id"),
                                _rs.getString("SubCategory_name")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
