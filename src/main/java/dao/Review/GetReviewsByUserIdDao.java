package dao.Review;

import dao.AbstractDAO;

import java.sql.Connection;

public class GetReviewsByUserIdDao extends AbstractDAO {

    /**
     * Creates a new DAO object.
     *
     * @param con the connection to be used for accessing the database.
     */
    protected GetReviewsByUserIdDao(Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

    }
}
