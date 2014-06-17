package util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: shakhawat.hossain
 * Date: 4/29/14
 * Time: 10:00 AM
 * To change this template use File | Settings | File Templates.
 */
public final class ConnectionPool {
    private static String CONNECTION_URL;
    private static String DRIVER_CLASS;
    private static String USER_NAME;
    private static String PASSWORD;

    private static DataSource dataSource;

    static {
        setDatabaseProperties();
     }

    private ConnectionPool(){

    }

    private static void setDatabaseProperties() {

        Properties prop = PropertyReader.getProperty("properties/connection_info.properties");
        CONNECTION_URL = prop.getProperty("connectionUrl");
        DRIVER_CLASS = prop.getProperty("driverClass");
        USER_NAME = prop.getProperty("userName");
        PASSWORD = prop.getProperty("password");

    }

    public static Connection getConnection() {

        Connection connection = null;

        try {

            dataSource = getDataSource();
            connection = dataSource.getConnection();

        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (PropertyVetoException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return connection;
    }

    private static DataSource getDataSource() throws PropertyVetoException {

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(DRIVER_CLASS);
        comboPooledDataSource.setJdbcUrl(CONNECTION_URL);
        comboPooledDataSource.setUser(USER_NAME);
        comboPooledDataSource.setPassword(PASSWORD);

        comboPooledDataSource.setMinPoolSize(5);
        comboPooledDataSource.setAcquireIncrement(5);
        comboPooledDataSource.setMaxPoolSize(20);
        comboPooledDataSource.setAcquireRetryAttempts(10);

        return comboPooledDataSource;
    }


}