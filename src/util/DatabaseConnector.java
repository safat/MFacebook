package util;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: shakhawat.hossain
 * Date: 4/27/14
 * Time: 9:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseConnector {

    public static Connection geConnection() {
        return ConnectionPool.getConnection();
    }

    public static void closeConnection(Connection connection, ResultSet resultSet, PreparedStatement preparedStatement) {

        try {
            if (connection != null)
                connection.close();

            if (resultSet != null)
                resultSet.close();

            if (preparedStatement != null)
                preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
