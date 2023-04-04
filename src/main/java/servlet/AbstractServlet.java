package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class AbstractServlet extends HttpServlet {


    protected static final Logger LOGGER = LogManager.getLogger(AbstractServlet.class, StringFormatterMessageFactory.INSTANCE);

    private DataSource _ds;

    public void init(ServletConfig _config) throws ServletException {
        InitialContext _context;

        try {

            _context = new InitialContext();
            _ds = (DataSource) _context.lookup("java:/comp/env/jdbc/damacaNaN");

            LOGGER.info("Connection pool to the database pool successfully acquired.");
        } catch (NamingException _e) {
            _ds = null;

            LOGGER.error("Unable to acquire the connection pool to the database.", _e);

            throw new ServletException("Unable to acquire the connection pool to the database", _e);
        }
    }

    public void destroy() {
        _ds = null;
        LOGGER.info("Connection pool to the database pool successfully released.");
    }


    /**
     * Returns a {@link  Connection} for accessing the database.
     *
     * @return a {@link Connection} for accessing the database
     *
     * @throws SQLException if anything goes wrong in obtaining the connection.
     */
    protected final Connection getConnection() throws SQLException {
        try {
            return _ds.getConnection();
        } catch (final SQLException _e) {
            LOGGER.error("Unable to acquire the connection from the pool.", _e);
            throw _e;
        }
    }

}
