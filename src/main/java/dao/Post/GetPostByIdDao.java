package dao.Post;

import dao.AbstractDAO;
import resource.Post;
import utils.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GetPostByIdDao extends AbstractDAO {

    private static final String STATEMENT = "SELECT * FROM post WHERE post.post_id = ? AND is_deleted = false";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public GetPostByIdDao(Connection con) {
        super(con);
    }

    public Post getPostById(long id) throws SQLException, ResourceNotFoundException {

        PreparedStatement _pstmt = null;
        ResultSet _rs =null;
        Post _post = null;

        try {

            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setObject(1, id);
            _rs= _pstmt.executeQuery();

            if(!_rs.isBeforeFirst()) {
                throw new ResourceNotFoundException("Couldn't found such post!");
            }

            if(_rs.next()) {
                _post = new Post(
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

        return _post;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
