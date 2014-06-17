package dao;

import util.DatabaseConnector;
import domain.Comment;
import domain.Post;
import domain.User;
import util.QueryExecutor;
import util.ResultSetProcessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: shakhawat.hossain
 * Date: 4/28/14
 * Time: 9:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class PostCommentDao {

    public ArrayList<Post> getTimeLine(String userId) {

        String query = "select CONCAT(user.first_name,' ', user.last_name) as name, post.post_id,  post.post_text, post.post_link, post.photo_video_link, post.post_date from post natural join user where post.is_history = 'no' and post.user_id in (select user.user_id from user where user.is_active='yes' and user_id in (select friend_id from friendship where user_id=? )\n" +
                "union\n" +
                "select ?)\n" +
                "order by post.post_date desc ";


        ArrayList<Post> timeLine = QueryExecutor.executeSelectQuery(query, new ResultSetProcessor<Post>() {

            @Override
            public Post processResultSet(ResultSet resultSet) throws SQLException {

                String postedBy = resultSet.getString("name");
                String postId = resultSet.getString("post_id");
                String postText = resultSet.getString("post_text");
                String postUrl = resultSet.getString("post_link");
                String mediaUrl = resultSet.getString("photo_video_link");
                String date = resultSet.getString("post_date");

                ArrayList<Comment> comments = getComments(postId);

                Post post = new Post();

                post.setId(postId);
                post.setText(postText);
                post.setPostedBy(postedBy);
                post.setLink(postUrl);
                post.setMediaUrl(mediaUrl);
                post.setDate(date);
                post.setComments(comments);

                return post;
            }
        }, userId, userId);

        return timeLine;
    }

    public ArrayList<Comment> getComments(String postId) {

        String query = "SELECT CONCAT(user.first_name,' ', user.last_name) as name, user.user_id,  text, photo_link, date FROM comment inner join user on comment.commenter_id = user.user_id WHERE comment.post_id = ? and comment.is_history='no' and user.is_active='yes'\n";

        ArrayList<Comment> comments = QueryExecutor.executeSelectQuery(query, new ResultSetProcessor<Comment>() {

            @Override
            public Comment processResultSet(ResultSet resultSet) throws SQLException {
                String commentedBy = resultSet.getString("name");
                String userId = resultSet.getString("user_id");
                String text = resultSet.getString("text");
                String photoUrl = resultSet.getString("photo_link");
                String date = resultSet.getString("date");

                Comment comment = new Comment();
                comment.setCommentedBy(commentedBy);
                comment.setUserId(userId);
                comment.setText(text);
                comment.setPhotoUrl(photoUrl);
                comment.setDate(date);

                return comment;
            }
        }, postId);

        return comments;
    }

}
