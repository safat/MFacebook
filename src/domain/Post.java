package domain;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: shakhawat.hossain
 * Date: 4/28/14
 * Time: 9:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class Post {
    private String id;
    private String postedBy;
    private String text;
    private String link;
    private String mediaUrl;
    private ArrayList<Comment> comments;
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }


    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "Post{" +
                "\nid='" + id + '\'' +
                "\npostedBy='" + postedBy + '\'' +
                "\ntext='" + text + '\'' +
                "\nlink='" + link + '\'' +
                "\nmediaUrl='" + mediaUrl + '\'' +
                "\ndate='" + date + '\'' +

                '}';
    }
}
