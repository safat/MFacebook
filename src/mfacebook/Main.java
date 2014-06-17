package mfacebook;

import domain.Comment;
import domain.Post;
import domain.User;
import service.UserService;
import util.PropertyReader;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: shakhawat.hossain
 * Date: 4/27/14
 * Time: 3:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        while (true) {

            System.out.println("#1 show user details");
            System.out.println("#2 update user details");
            System.out.println("#3 show mutual friendlist of");
            System.out.println("#4 show friendlist of");
            System.out.println("#5 deactivate user");
            System.out.println("#6 activate user");
            System.out.println("#7 show homepage of a user");
            System.out.println("#8 post a status/photo/video");
            System.out.println("#9 Add a user");

            int choice = 0;

            try {

                choice = Integer.parseInt(input.nextLine());

            } catch (NoSuchElementException exception) {
                System.out.println("No command found");
                System.exit(1);
            }


            switch (choice) {

                case 1:
                    String userId = inputUserId();

                    User user = UserService.getUserDetails(userId);

                    if (user == null)
                        System.out.println("USER NOT FOUND");
                    else
                        System.out.println(user.toString());

                    break;

                case 2:
                    System.out.println("Updating details from 'update_properties' file  ");


                    Properties prop = PropertyReader.getProperty("properties/update_user.properties");

                    User userTobeUpdated = new User();
                    userTobeUpdated.setId(prop.getProperty("userId"));
                    userTobeUpdated.setFirstName(prop.getProperty("firstName"));
                    userTobeUpdated.setLastName(prop.getProperty("lastName"));
                    userTobeUpdated.setEmailAddress(prop.getProperty("email"));

                    boolean isUpdated = UserService.updateUserDetails(userTobeUpdated);

                    if (isUpdated)
                        System.out.println("Update operation successfull");
                    else
                        System.out.println("Update failed . Please try later");

                    break;

                case 3:
                    System.out.println("showing mutual friendlist for the users specified in 'mutual_friends_of.properties' file");

                    prop = PropertyReader.getProperty("properties/mutual_friends_of.properties");

                    String friendA = prop.getProperty("friendA");
                    String friendB = prop.getProperty("friendB");

                    ArrayList<User> mutualFriendList = UserService.getMutualFriendList(friendA, friendB);

                    System.out.println("Mutual friends of : " + friendA + " and " + friendB + "");

                    if (mutualFriendList != null && mutualFriendList.size() == 0)
                        System.out.println("No mutual friends");
                    else
                        printUserList(mutualFriendList);

                    break;

                case 4:
                    userId = inputUserId();

                    ArrayList<User> friendList = UserService.getFriendList(userId);

                    printUserList(friendList);

                    break;

                case 5:
                    userId = inputUserId();

                    if (UserService.deactivateUser(userId)) {
                        System.out.println("User deactivated");
                    } else {
                        System.out.println("Operation failed");
                    }
                    break;

                case 6:
                    userId = inputUserId();

                    if (UserService.activateUser(userId)) {
                        System.out.println("User activated");
                    } else {
                        System.out.println("Operation failed");
                    }
                    break;

                case 7:
                    userId = inputUserId();

                    ArrayList<Post> timeLine = UserService.getTimeLine(userId);
                    printPostList(timeLine);
                    break;

                case 8:
                    System.out.println("Giving a post from 'new_post.properties' ");

                    prop = PropertyReader.getProperty("properties/new_post.properties");

                    Post post = new Post();

                    post.setText(prop.getProperty("postText"));
                    post.setPostedBy(prop.getProperty("userId"));
                    post.setLink(prop.getProperty("postLink"));
                    post.setMediaUrl(prop.getProperty("mediaUrl"));

                    boolean isPostSuccessfull = UserService.addPost(post);

                    if (isPostSuccessfull) {
                        System.out.println("post successfull");
                    } else {
                        System.out.println("Operation failed");
                    }

                    break;

                case 9:
                    System.out.println("Adding new user from  new_user.properties");

                    prop = PropertyReader.getProperty("properties/new_user.properties");

                    user = new User();

                    user.setId(prop.getProperty("userId"));
                    user.setFirstName(prop.getProperty("firstName"));
                    user.setLastName(prop.getProperty("firstName"));
                    user.setPassword(prop.getProperty("password"));
                    user.setEmailAddress(prop.getProperty("email"));
                    user.setGender(prop.getProperty("gender"));
                    user.setDateOfBirth(prop.getProperty("birthdate"));

                    boolean successfull = UserService.addUser(user);

                    if (successfull)
                        System.out.println("User successfully added");
                    else
                        System.out.println("User can't be added right now. Please try later");

                    break;
            }

        }

    }

    private static void printPostList(ArrayList<Post> timeLine) {
        System.out.println();

        for (int i = 0; i < timeLine.size(); i++) {
            System.out.println("\n"+timeLine.get(i).toString());

            for (Comment comment : timeLine.get(i).getComments()) {
                System.out.println("-->" + comment.toString());
            }

        }
        System.out.println("\n");
    }

    private static String inputUserId() {
        Scanner input = new Scanner(System.in);
        System.out.println("User ID : ");

        return input.nextLine();
    }

    private static void printUserList(ArrayList<User> frndList) {
        for (User user : frndList)
            System.out.println("-->" + user.getFirstName() + " " + user.getLastName());
    }
}
