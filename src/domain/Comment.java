package domain;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: shakhawat.hossain
 * Date: 4/28/14
 * Time: 9:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class Comment {
    private String userId;
    private String text;
    private String commentedBy;
    private String date;

    private String photoUrl;
    private ArrayList<String> likedBy;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCommentedBy() {
        return commentedBy;
    }

    public void setCommentedBy(String commentedBy) {
        this.commentedBy = commentedBy;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<String> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(ArrayList<String> likedBy) {
        this.likedBy = likedBy;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "userId='" + userId + '\'' +
                ", text='" + text + '\'' +
                ", commentedBy='" + commentedBy + '\'' +
                ", date='" + date + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", likedBy=" + likedBy +
                '}';
    }
}
