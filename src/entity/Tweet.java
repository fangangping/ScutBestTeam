package entity;

import java.sql.Timestamp;

public class Tweet{

    private String friend_id;
    private String tweets_id;
    private String comment_num;
    private String upvote_num;
    private String upvote_status;
    private String tweet_content;
    private String tweet_img = "";
    private Timestamp tweet_time;

    public String getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(String friend_id) {
        this.friend_id = friend_id;
    }

    public String getTweets_id() {
        return tweets_id;
    }

    public void setTweets_id(String tweets_id) {
        this.tweets_id = tweets_id;
    }

    public String getComment_num() {
        return comment_num;
    }

    public void setComment_num(String comment_num) {
        this.comment_num = comment_num;
    }

    public String getUpvote_num() {
        return upvote_num;
    }

    public void setUpvote_num(String upvote_num) {
        this.upvote_num = upvote_num;
    }

    public String getUpvote_status() {
        return upvote_status;
    }

    public void setUpvote_status(String upvote_status) {
        this.upvote_status = upvote_status;
    }

    public String getTweet_content() {
        return tweet_content;
    }

    public void setTweet_content(String tweet_content) {
        this.tweet_content = tweet_content;
    }

    public String getTweet_img() {
        return tweet_img;
    }

    public void setTweet_img(String tweet_img) {
        this.tweet_img = tweet_img;
    }

    public Timestamp getTweet_time() {
        return tweet_time;
    }

    public void setTweet_time(Timestamp tweet_time) {
        this.tweet_time = tweet_time;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "friend_id='" + friend_id + '\'' +
                ", tweets_id='" + tweets_id + '\'' +
                ", comment_num='" + comment_num + '\'' +
                ", upvote_num='" + upvote_num + '\'' +
                ", upvote_status='" + upvote_status + '\'' +
                ", tweet_content='" + tweet_content + '\'' +
                ", tweet_img='" + tweet_img + '\'' +
                ", tweet_time=" + tweet_time +
                '}';
    }
}
