package dao.PostFiles;

import resource.PostFiles;
import utils.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class GetPostFilesByPostIdDao {
    // SQL Query to be executed : Get post files from the database with the post id

    private static final String STATEMENT = "SELECT f.file_id, f.post_id, f.file_type, f.file_size, f.file_path FROM posts p WHERE p.post_id = ?";

    //Declare and set up a connection in constructor

    private final Connection connection;

    public GetPostFilesByPostIdDao(Connection connection) { this.connection = connection; }

    public PostFiles getPostFilesByPostId(long post_id) throws SQLException, ResourceNotFoundException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        PostFiles postFiles = null;

        try {
            //Prepare and set the statement object to be executed
            preparedStatement = connection.prepareStatement(STATEMENT);
            preparedStatement.setObject(1, post_id);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()) {
                throw new ResourceNotFoundException("No such post file found");
            }

            if(resultSet.next()) {
                postFiles = new PostFiles(resultSet.getObject("file_id", long.class),
                    resultSet.getLong("file_id"),
                    resultSet.getString("file_type"),
                    resultSet.getDouble("file_size"),
                    resultSet.getString("file_path")
                );
            }
        } finally {
            if(resultSet != null) { resultSet.close(); }
            if(preparedStatement != null) { preparedStatement.close(); }
            connection.close();
        }
        return postFiles;
    }
}












