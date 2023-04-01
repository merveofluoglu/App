package dao.Review;

import dao.AbstractDAO;

import java.sql.Connection;

public class DeleteReviewDao extends AbstractDAO {

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    protected DeleteReviewDao(Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
