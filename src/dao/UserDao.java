package dao;

import util.DatabaseConnector;
import domain.Post;
import domain.User;
import util.QueryExecutor;
import util.ResultSetProcessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shakhawat.hossain
 * Date: 4/27/14
 * Time: 9:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserDao {

    public ArrayList<User> getFriendList(String userId) {

        String query = "select user_id, first_name, last_name , email, gender, birthdate from user where user.is_active='yes' and user_id in (select friend_id from friendship where user_id= ? )";

        ArrayList<User> friendList = QueryExecutor.executeSelectQuery(query, new ResultSetProcessor<User>() {

            @Override
            public User processResultSet(ResultSet resultSet) throws SQLException {
                String id = resultSet.getString("user_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String gender = resultSet.getString("gender");
                String birthdate = resultSet.getDate("birthdate").toString();

                User user = new User(id, firstName, lastName, email, gender, birthdate);

                return user;
            }
        }, userId);

        return friendList;
    }


    public User getUserDetails(String userId) {

        String query = "select user_id, first_name, last_name , email, gender, birthdate from user where user_id= ? and is_active='yes'\n";

        List<User> userList = QueryExecutor.executeSelectQuery(query, new ResultSetProcessor<User>() {

            @Override
            public User processResultSet(ResultSet resultSet) throws SQLException {

                String id = resultSet.getString("user_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String gender = resultSet.getString("gender");
                String birthdate = resultSet.getDate("birthdate").toString();

                User user = new User(id, firstName, lastName, email, gender, birthdate);

                return user;

            }
        }, userId);

        return userList.size() != 0 ? userList.get(0) : null;
    }


    public boolean updateUserDetails(User user) {

        String query = "UPDATE user SET first_name = ?, last_name  = ?, email = ?  where user_id = ? ";

        return QueryExecutor.executeUpdateQuery(query, user.getFirstName(), user.getLastName(), user.getEmailAddress(), user.getId());

    }

    public boolean deactivateUser(String userId) {

        String query = "UPDATE user SET is_active = 'no' where user_id = ? ";

        return QueryExecutor.executeUpdateQuery(query, userId);

    }


    public boolean activateUser(String userId) {

        String query = "UPDATE user SET is_active = 'yes' where user_id = ? ";

        return QueryExecutor.executeUpdateQuery(query, userId);

    }

    public ArrayList<User> getMutualFriendList(String friendA, String friendB) {

        String query = "select user.user_id, user.first_name, user.last_name , email, gender, birthdate from user where user.is_active='yes' and user_id in (select friend_id from friendship where user_id=? and  friend_id in (select friend_id from friendship where user_id=?) )";

        ArrayList<User> userList = QueryExecutor.executeSelectQuery(query, new ResultSetProcessor<User>() {

            @Override
            public User processResultSet(ResultSet resultSet) throws SQLException {

                String id = resultSet.getString("user_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String gender = resultSet.getString("gender");
                String birthdate = resultSet.getDate("birthdate").toString();

                User user = new User(id, firstName, lastName, email, gender, birthdate);

                return user;

            }
        }, friendA, friendB);

        return userList;

    }


    public boolean addUser(User user) {

        String query = "INSERT INTO user (`user_id`, `password`, `first_name`, `last_name`, `email`, `gender`, `birthdate`, `is_active`) VALUES (?, ?, ?, ?, ?, ? , ?, ?)";

        return QueryExecutor.executeUpdateQuery(query, user.getId(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmailAddress(), user.getGender(), user.getDateOfBirth(), "yes");

    }

    public boolean addPost(Post post) {

        String query = "INSERT INTO post (`user_id`, `post_text`, `post_link`, `photo_video_link`) VALUES (?, ?, ?, ?)";

        return QueryExecutor.executeUpdateQuery(query, post.getPostedBy(), post.getText(), post.getLink(), post.getMediaUrl());

    }
}
