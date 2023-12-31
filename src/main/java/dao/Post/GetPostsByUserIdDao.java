package dao.Post;

import dao.AbstractDAO;
import utils.ResourceNotFoundException;
import resource.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetPostsByUserIdDao extends AbstractDAO {

    private static final String STATEMENT = "SELECT * FROM post WHERE user_id = ? AND is_deleted = false";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public GetPostsByUserIdDao(Connection con) {
        super(con);
    }

    public List<Post> getPostsByUserId(long _id) throws SQLException, ResourceNotFoundException {

        PreparedStatement _pstmt = null;
        ResultSet _rs = null;
        List<Post> _posts = new ArrayList<>();
        
        try {

            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setObject(1, _id);
            _rs = _pstmt.executeQuery();

            if (!_rs.isBeforeFirst()) {
                throw new ResourceNotFoundException("No Posts Yet!");
            }

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
