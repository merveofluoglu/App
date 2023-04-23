package dao.User;

import dao.AbstractDAO;
import resource.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateUserDAO extends AbstractDAO{
    private static final String STATEMENT = "INSERT INTO users("+
            "user_id, name, surname, email, password,role_id, creation_date, update_date, pp_path,"+
            "is_deleted) VALUES (nextval('user_seq'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    /**
     * The employee to be stored into the database
     */

    /**
     * Creates a new object for storing an user into the database.
     *
     * @param con
     *            the connection to the database.
     */

    public CreateUserDAO(final Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

    }

    public String createUser(User user) throws SQLException {

        PreparedStatement _pstmt = null;
        int _rs;

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
            _pstmt.setBoolean(9,user.getIsDeleted());
            _rs = _pstmt.executeUpdate();

            if (_rs != 1) {
                throw new SQLException("User Creation failed!");
            }
        } finally {
            if (_pstmt != null) {
                _pstmt.close();
            }
            con.close();
        }

        return "User Created Successfully!";
    }

}
