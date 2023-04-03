package dao.Role;

import dao.AbstractDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteRoleByIdDAO extends AbstractDAO {
    private static final String STATEMENT = "DELETE FROM role WHERE role_id=?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    protected DeleteRoleByIdDAO(Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

    }

    public int DeleteRoleByIdDAO(Long role_id) throws SQLException {

        PreparedStatement _pstmt = null;
        int _affectedRows = 0;

        try {
            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setLong(1, role_id);

            _affectedRows = _pstmt.executeUpdate();

            if (_affectedRows != 1) {
                throw new SQLException("Delete Failed");
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
