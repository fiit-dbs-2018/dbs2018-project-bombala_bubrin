package project.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Post {

    private int id;
    private int type;
    private String text;
    private int eventId;
    private String imageURL;
    private String eventName;
    private int opinion;
    private int likeCount;

    public Post(ResultSet result) throws SQLException {


        id = result.getInt("id");
        type = result.getInt("type");
        text = result.getString("text");
        eventId = result.getInt("event_id");
        imageURL = result.getString("image_url");
        eventName = result.getString("name");
        opinion = result.getInt("opinion");
        likeCount = result.getInt("like_count");

    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public int getEventId() {
        return eventId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getEventName() {
        return eventName;
    }

    public int getOpinion() {
        return opinion;
    }

    public int getLikeCount(){
        return likeCount;
    }
}
