package dao.RolePermission;

import dao.AbstractDAO;
import resource.Permission;
import resource.Post;
import resource.PostFiles;
import resource.RolePermission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateRolePermissionDao extends AbstractDAO {

    private static final String STATEMENT = "INSERT INTO role_permission (" +
            "role_permission_id, role_id, permission_id) VALUES (nextval('rolepermission_seq'),?, ?)";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public CreateRolePermissionDao(Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

    }

    public String createRolePermission(RolePermission _rolepermission) throws SQLException {

        PreparedStatement _pstmt = null;
        int _rs;

        try {

            _pstmt = con.prepareStatement(STATEMENT);

            _pstmt.setLong(1, _rolepermission.getRole_id());
            _pstmt.setLong(2, _rolepermission.getPermission_id());

            _rs = _pstmt.executeUpdate();

            if (_rs != 1) {
                throw new SQLException("Role Permission Creation failed!");
            }
        } finally {
            if (_pstmt != null) {
                _pstmt.close();
            }
            con.close();
        }

        return "Role Permission Created Successfully!";
    }
}
