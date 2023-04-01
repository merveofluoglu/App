package dao.Post;

import dao.AbstractDAO;
import resource.Post;
import utils.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetPostsByCategoryIdDao extends AbstractDAO {

    private static final String STATEMENT = "SELECT * FROM post WHERE category_id = ? AND is_deleted = false";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    protected GetPostsByCategoryIdDao(Connection con) {
        super(con);
    }

    public List<Post> getPostsByCategoryId(long id) throws SQLException, ResourceNotFoundException {

        PreparedStatement _pstmt = null;
        ResultSet _rs = null;
        List<Post> _posts = new ArrayList<>();

        try {

            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setObject(1, id);
            _rs = _pstmt.executeQuery();

            while (_rs.next()) {
                _posts.add(
                        new Post(
                                _rs.getLong("post_id"),
                                _rs.getString("name"),
                                _rs.getString("description"),
                                _rs.getLong("user_id"),
                                _rs.getLong("customer_id"),
                                _rs.getDouble("price"),
                                _rs.getString("status"),
                                _rs.getTimestamp("start_date"),
                                _rs.getTimestamp("end_date"),
                                _rs.getBoolean("is_deleted"),
                                _rs.getBoolean("is_sold"),
                                _rs.getTimestamp("sold_date"),
                                _rs.getTimestamp("update_date"),
                                _rs.getLong("category_id"),
                                _rs.getLong("subcategory_id")
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

        return _posts;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
