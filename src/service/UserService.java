package service;

import dao.PostCommentDao;
import dao.UserDao;
import domain.Post;
import domain.User;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: shakhawat.hossain
 * Date: 4/27/14
 * Time: 10:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserService {

    public static void main(String[] args) {
        // showUserDetails("imran.azad");
        // updateUserDetails();
        //showUserDetails("imran.azad");
        //  deactivateUser();
        // activateUsers();
        // showFriendList();
        //   showMutualFriendList();
    }

    public static ArrayList<User> getMutualFriendList(String userIdA, String userIdB) {
        UserDao userDao = new UserDao();
        return userDao.getMutualFriendList(userIdA, userIdB);
    }

    public static ArrayList<User> getFriendList(String id) {
        UserDao userDao = new UserDao();
        return userDao.getFriendList(id);
    }


    private static void printUserList(ArrayList<User> frndList) {
        for (User user : frndList)
            System.out.println(user.getFirstName() + " " + user.getLastName());
    }

    public static boolean deactivateUser(String userId) {
        UserDao userDao = new UserDao();
        return userDao.deactivateUser(userId);
    }

    public static boolean activateUser(String userId) {
        UserDao userDao = new UserDao();
        return userDao.activateUser(userId);
    }

    public static User getUserDetails(String id) {
        UserDao userDao = new UserDao();
        return userDao.getUserDetails(id);
    }

    public static boolean updateUserDetails(User user) {
        UserDao userDao = new UserDao();
        return userDao.updateUserDetails(user);
    }

    public static ArrayList<Post> getTimeLine(String userId) {
        PostCommentDao postCommentDao = new PostCommentDao();
        return postCommentDao.getTimeLine(userId);
    }

    public static boolean addUser(User user) {
        UserDao userDao = new UserDao();
        return userDao.addUser(user);
    }

    public static boolean addPost(Post post) {
        UserDao userDao = new UserDao();
        return userDao.addPost(post);

    }
}
