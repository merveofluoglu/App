package dao.Role;

import dao.AbstractDAO;
import resource.Role;
import resource.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateRoleDAO extends AbstractDAO {

    private static final String STATEMENT = "UPDATE role SET name = ? WHERE role_id=?";

    @Override
    protected void doAccess() throws Exception {

    }
    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public UpdateRoleDAO(Connection con) {
        super(con);
    }

    public int updateRoleById(Role role, long role_id) throws SQLException {

        PreparedStatement _pstmt = null;
        int _affectedRows = 0;

        try {

            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setString(1, role.getName());
            _pstmt.setLong(2, role_id);
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


}
