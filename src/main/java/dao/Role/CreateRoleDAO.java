package dao.Role;
import dao.AbstractDAO;
import resource.Role;
import resource.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class CreateRoleDAO extends AbstractDAO{

    private static final String STATEMENT = "INSERT INTO role (" +
            "role_id, name"+
            "VALUES (?, ?)";
    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    protected CreateRoleDAO(Connection con) {
        super(con);
    }

    public String CreateRoleDAO(Role role) throws SQLException {

        PreparedStatement _pstmt = null;
        int _rs;

        try {

            _pstmt = con.prepareStatement(STATEMENT);

            _pstmt.setLong(1, role.getRole_id());
            _pstmt.setString(2, role.getName());
            _rs = _pstmt.executeUpdate();

            if (_rs != 1) {
                throw new SQLException("User Creation failed!");
            }
        } finally {
            if (_pstmt != null) {
                _pstmt.close();
            }
            con.close();
        }

        return "Role Created Successfully!";
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
