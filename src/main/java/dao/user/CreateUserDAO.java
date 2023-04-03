package dao.User;

import dao.AbstractDAO;
import resource.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateUserDAO extends AbstractDAO{
    private static final String STATEMENT = "INSERT INTO user (" +
            "user_id, name, surname, email, password, role_id, creation_date, update_date, pp_path,"+
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    /**
     * The employee to be stored into the database
     */
    private final User user;

    /**
     * Creates a new object for storing an user into the database.
     *
     * @param con
     *            the connection to the database.
     * @param user
     *            the employee to be stored into the database.
     */

    protected CreateUserDAO(final Connection con, final User user) {
        super(con);

        if (user == null) {
            LOGGER.error("The user cannot be null.");
            throw new NullPointerException("The user cannot be null.");
        }

        this.user = user;
    }

    @Override
    protected void doAccess() throws Exception {

    }

    public String createUser(User user) throws SQLException {

        PreparedStatement _pstmt = null;
        int _rs;

        try {

            _pstmt = con.prepareStatement(STATEMENT);

            _pstmt.setLong(1, user.getUserID());
            _pstmt.setString(2, user.getName());
            _pstmt.setString(3, user.getSurname());
            _pstmt.setString(4, user.getEmail());
            _pstmt.setString(5, user.getPassword());
            _pstmt.setLong(6, 1);
            _pstmt.setTimestamp(7, user.getCreation_date());
            _pstmt.setTimestamp(8, user.getUpdate_date());
            _pstmt.setBytes(9, user.getProfile_photo());
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
