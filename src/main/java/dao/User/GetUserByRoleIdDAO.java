package dao.User;

import dao.AbstractDAO;
import resource.User;
import utils.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetUserByRoleIdDAO extends AbstractDAO {

    private static final String STATEMENT = "SELECT * FROM user WHERE role_id = ?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public GetUserByRoleIdDAO(Connection con) {
        super(con);
    }

    public List<User> GetUserByRoleIdDAO(Long role_id) throws SQLException, ResourceNotFoundException {

        PreparedStatement _pstmt = null;
        ResultSet _rs = null;
        List<User> _user = new ArrayList<>();

        try {

            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setLong(1, role_id);
            _rs = _pstmt.executeQuery();

            while (_rs.next()) {
                _user.add(
                        new User(
                                _rs.getLong("user_id"),
                                _rs.getString("name"),
                                _rs.getString("surname"),
                                _rs.getString("email"),
                                _rs.getString("password"),
                                _rs.getLong("role_id"),
                                _rs.getTimestamp("creation_date"),
                                _rs.getTimestamp("update_date"),
                                _rs.getBytes("pp_path")
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

        return _user;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
