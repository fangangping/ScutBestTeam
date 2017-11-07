package entity;

import java.util.List;

public class Moment {
    private String result;


    private List<Tweet> tweets;


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    @Override
    public String toString() {
        return "Moment{" +
                "result='" + result + '\'' +
                ", tweets=" + tweets +
                '}';
    }
}
