package dao.User;

import dao.AbstractDAO;
import resource.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateUserByIdDAO extends AbstractDAO{

    private static final String STATEMENT = "UPDATE user SET name = ?, surname = ?, email = ?, password = ?, role_id = ?, creation_date = ?, update_date = ?," +
            "pp_path = ? WHERE user_id=?";
    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    public UpdateUserByIdDAO(Connection con) {
        super(con);
    }

    public User UpdateUserByIdDAO(Long user_id,User user) throws SQLException {

        PreparedStatement _pstmt = null;
        int _affectedRows = 0;

        try {

            _pstmt = con.prepareStatement(STATEMENT);
            _pstmt.setString(1, user.getName());
            _pstmt.setString(2, user.getSurname());
            _pstmt.setString(3, user.getEmail());
            _pstmt.setString(4, user.getPassword());
            _pstmt.setLong(5, user.getRole_id());
            _pstmt.setTimestamp(6, user.getCreation_date());
            _pstmt.setTimestamp(7, user.getUpdate_date());
            _pstmt.setBytes(8, user.getProfile_photo());
            _pstmt.setLong(9, user_id);
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

        return user;
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
