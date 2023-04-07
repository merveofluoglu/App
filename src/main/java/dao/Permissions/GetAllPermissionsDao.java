package dao.Permissions;
import dao.AbstractDAO;
import resource.Permission;
import resource.User;
import utils.ResourceNotFoundException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAllPermissionsDao extends AbstractDAO{

    private static final String STATEMENT = "SELECT * FROM permission";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public GetAllPermissionsDao(Connection con) {
        super(con);
    }

    public List<Permission> getAllPermissions () throws SQLException, ResourceNotFoundException {

        PreparedStatement _pstmt = null;
        ResultSet _rs = null;
        List<Permission> _permissions = new ArrayList<>();

        try {
            _pstmt = con.prepareStatement(STATEMENT);
            _rs = _pstmt.executeQuery();

            if(!_rs.isBeforeFirst()) {
                throw new ResourceNotFoundException("There are no permissions!");
            }

            while (_rs.next()) {
                _permissions.add(
                        new Permission(
                                _rs.getLong("permission_id"),
                                _rs.getString("name")
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

        return _permissions;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
