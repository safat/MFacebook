package util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shakhawat.hossain
 * Date: 4/29/14
 * Time: 2:12 PM
 * To change this template use File | Settings | File Templates.
 */
public final class QueryExecutor {


    private QueryExecutor() {

    }

    public static <E> ArrayList<E> executeSelectQuery(String query, ResultSetProcessor<E> resultSetProcessor, Object... params) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ArrayList<E> results = new ArrayList<E>();

        try {

            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(query);

            mapObjectsIntoPreparedStatement(preparedStatement, params);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                results.add(resultSetProcessor.processResultSet(resultSet));
            }

            closeConnection(connection, resultSet, preparedStatement);

        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return results;
    }

    public static boolean executeUpdateQuery(String query, Object... params) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(query);

            mapObjectsIntoPreparedStatement(preparedStatement, params);

            int affectedRow = preparedStatement.executeUpdate();

            closeConnection(connection, null, preparedStatement);


        } catch (SQLException e) {

            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return false;
        }

        return true;
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

    private static void mapObjectsIntoPreparedStatement(PreparedStatement preparedStatement, Object... params) {
        int itemNo = 1;

        try {
            for (Object parameter : params) {
                if (parameter instanceof String) {
                    preparedStatement.setString(itemNo, (String) parameter);
                } else if (parameter instanceof Integer) {
                    preparedStatement.setInt(itemNo, (Integer) parameter);
                } else if (parameter instanceof Long) {
                    preparedStatement.setLong(itemNo, (Long) parameter);
                } else if (parameter instanceof Float) {
                    preparedStatement.setFloat(itemNo, (Float) parameter);
                } else if (parameter instanceof Double) {
                    preparedStatement.setDouble(itemNo, (Double) parameter);
                } else if (parameter instanceof Date) {
                    preparedStatement.setDate(itemNo, (Date) parameter);
                }

                itemNo++;
            }
        } catch (SQLException exp) {
        }
    }
}

