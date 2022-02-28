package ibf2021.day4_demo.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Post {
    private String poster;
    private String comment;
    private String imageType;
    private byte[] image;
    private int postId;

    public Post() {
    }

    public Post(String poster, String comment, String imageType, byte[] image) {
        this.poster = poster;
        this.comment = comment;
        this.imageType = imageType;
        this.image = image;
    }

    public int getPostId() {
        return this.postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPoster() {
        return this.poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImageType() {
        return this.imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public byte[] getImage() {
        return this.image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public static Post populate(ResultSet rs) throws SQLException {
        final Post post = new Post();
        post.setPostId(rs.getInt("post_id"));
        post.setComment(rs.getString("comment"));
        post.setImageType(rs.getString("mediatype"));
        post.setImage(rs.getBytes("photo"));
        post.setPoster(rs.getString("poster"));
        return post;
    }

}
