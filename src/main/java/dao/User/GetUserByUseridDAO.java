package dao.User;

import dao.AbstractDAO;
import resource.Post;
import resource.User;
import utils.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class GetUserByUseridDAO extends AbstractDAO{
    private static final String STATEMENT = "SELECT * FROM users WHERE users.user_id = ?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public GetUserByUseridDAO(Connection con) {
        super(con);
    }

    public User GetUserByUseridDAO(Long user_id) throws SQLException, ResourceNotFoundException {

        PreparedStatement _pstmt = null;
        ResultSet _rs =null;
        User _user = null;

        try {

            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setObject(1, user_id);
            _rs= _pstmt.executeQuery();

            if(!_rs.isBeforeFirst()) {
                throw new ResourceNotFoundException("Couldn't found such user!");
            }

            if(_rs.next()) {
                _user = new User(
                        _rs.getLong("user_id"),
                        _rs.getString("name"),
                        _rs.getString("surname"),
                        _rs.getString("email"),
                        _rs.getString("password"),
                        _rs.getLong("role_id"),
                        _rs.getTimestamp("creation_date"),
                        _rs.getTimestamp("update_date"),
                        _rs.getBytes("pp_path"),
                        _rs.getBoolean("is_deleted")
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

        return _user;
    }

    @Override
    protected void doAccess() throws Exception {

    }

}
