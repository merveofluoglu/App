package dao.Role;

import dao.AbstractDAO;
import resource.Role;
import resource.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateRoleDAO extends AbstractDAO {

    private static final String STATEMENT = "UPDATE role SET role_id = ?, name = ?";
    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    protected UpdateRoleDAO(Connection con) {
        super(con);
    }

    public int UpdateRoleDAO(Role role) throws SQLException {

        PreparedStatement _pstmt = null;
        int _affectedRows = 0;

        try {

            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setLong(1, role.getRole_id());
            _pstmt.setString(2, role.getName());

            _affectedRows = _pstmt.executeUpdate();

            if (_affectedRows != 1) {
                throw new SQLException("Update Failed!");
            }

        } finally {
            if (_pstmt != null) {
                _pstmt.close();
            }
            con.close();
        }

        return _affectedRows;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
