package dao.User;

import dao.AbstractDAO;
import resource.User;
import utils.ResourceNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetUserByCreationDateDAO extends AbstractDAO {

    private static final String STATEMENT = "SELECT * FROM user WHERE creation_date = ?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    protected GetUserByCreationDateDAO(Connection con) {
        super(con);
    }

    public List<User> GetUserByCreationDateDAO(Timestamp creation_date) throws SQLException, ResourceNotFoundException {

        PreparedStatement _pstmt = null;
        ResultSet _rs = null;
        List<User> _user = new ArrayList<>();

        try {

            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setTimestamp(1, creation_date);
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
