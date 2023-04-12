package dao.Role;
import dao.AbstractDAO;
import resource.Role;
import utils.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class GetAllRolesDAO extends AbstractDAO{

    private static final String STATEMENT = "SELECT * FROM role";

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public GetAllRolesDAO(Connection con) {
        super(con);
    }

    public List<Role> GetAllRoles () throws SQLException, ResourceNotFoundException {

        PreparedStatement _pstmt = null;
        ResultSet _rs = null;
        List<Role> _roles = new ArrayList<>();

        try {
            _pstmt = con.prepareStatement(STATEMENT);
            _rs = _pstmt.executeQuery();

            if(!_rs.isBeforeFirst()) {
                throw new ResourceNotFoundException("There are no roles!");
            }

            while (_rs.next()) {
                _roles.add(
                        new Role(
                                _rs.getLong("role_id"),
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

        return _roles;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
