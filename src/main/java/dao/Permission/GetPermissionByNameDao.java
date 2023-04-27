package dao.Permission;
import dao.AbstractDAO;
import resource.Permission;
import utils.ResourceNotFoundException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetPermissionByNameDao extends AbstractDAO{

    private static final String STATEMENT = "SELECT * FROM permission WHERE name = ?";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public GetPermissionByNameDao(Connection con) {
        super(con);
    }

    public Permission getPermissionByName(String name) throws SQLException, ResourceNotFoundException {

        PreparedStatement _pstmt = null;
        ResultSet _rs =null;
        Permission _permission = null;

        try {

            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setObject(1, name);
            _rs= _pstmt.executeQuery();

            if(!_rs.isBeforeFirst()) {
                throw new ResourceNotFoundException("Couldn't found such permission!");
            }

            if(_rs.next()) {
                _permission = new Permission(
                        _rs.getLong("permission_id"),
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

        return _permission;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
