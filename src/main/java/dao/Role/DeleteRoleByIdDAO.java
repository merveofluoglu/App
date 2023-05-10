package dao.Role;

import dao.AbstractDAO;
import resource.Role;
import utils.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteRoleByIdDAO extends AbstractDAO {
    private static final String STATEMENT = "DELETE FROM role WHERE role_id=?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public DeleteRoleByIdDAO(Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

    }

    public Role deleteRoleById(Long _roleId) throws SQLException, ResourceNotFoundException {

        PreparedStatement _pstmt = null;
        ResultSet _rs = null;
        Role _role = null;

        try {

            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setObject(1, _roleId);
            _rs = _pstmt.executeQuery();
            if (!_rs.isBeforeFirst() ) {
                throw new ResourceNotFoundException("There is no such user!");
            }
            if (_rs.next()) {
                _role = new Role(_rs.getLong("role_id"),
                        _rs.getString("name")
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

        return _role;
    }
}
