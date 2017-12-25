package dev.ska.volleyrecycler;

/**
 * Created by skadevz on 25/12/17.
 */

public class NewsFeeds {

    private String imgURL, feedName, content;
    private int rating;

    public NewsFeeds(String name, String content, String imgurl, int rating) {
        this.feedName = name;
        this.content = content;
        this.imgURL = imgurl;
        this.rating = rating;
    }

    public String getImgURL() {
        return imgURL;
    }

    public String getFeedName() {
        return feedName;
    }

    public String getContent() {
        return content;
    }

    public int getRating() {
        return rating;
    }
}
