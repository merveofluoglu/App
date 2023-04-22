package dao.Permission;
import dao.AbstractDAO;
import resource.Permission;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreatePermissionDao extends AbstractDAO{

    private static final String STATEMENT = "INSERT INTO permission (" +
            "name"+
            "is_deleted"+
            "VALUES (?,?)";


    /**
     * Creates a new object for storing an user into the database.
     *
     * @param con
     *            the connection to the database.
     *
     */

    public CreatePermissionDao(final Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

    }

    public String createPermission(Permission _permission) throws SQLException {

        PreparedStatement _pstmt = null;
        int _rs;

        try {

            _pstmt = con.prepareStatement(STATEMENT);

            _pstmt.setString(1, _permission.getName());
            _pstmt.setBoolean(2, false);
            _rs = _pstmt.executeUpdate();

            if (_rs != 1) {
                throw new SQLException("Permission Creation failed!");
            }
        } finally {
            if (_pstmt != null) {
                _pstmt.close();
            }
            con.close();
        }

        return "Permission Created Successfully!";
    }
}
