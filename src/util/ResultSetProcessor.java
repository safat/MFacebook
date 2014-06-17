package util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: shakhawat.hossain
 * Date: 4/29/14
 * Time: 2:27 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ResultSetProcessor<E> {

    public E processResultSet(ResultSet resultSet) throws SQLException;
}
