package Dao;

import com.google.gson.Gson;
import entity.Moment;
import entity.ResultInfo;
import entity.Tweet;
import util.DataBaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MomentDao {


    public Moment getMoments(String userId){
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        List<Tweet> tweetList = new LinkedList<>();
        try {
            String sql1 = "SELECT * FROM app_tweet";
            PreparedStatement preparedStatement1 = dataBaseConnection.preparedStatement(sql1);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            while(resultSet1.next()){
                Tweet tweet = new Tweet();

                String tweet_id = resultSet1.getString("tweet_id");
                String friend_id = resultSet1.getString("friend_id");
//                Date tweet_time = resultSet1.getDate("tweet_time");
                String content = resultSet1.getString("tweet_content");
                tweet.setTweets_id(tweet_id);
                tweet.setFriend_id(friend_id);
                tweet.setTweet_time(resultSet1.getTimestamp("tweet_time"));
                tweet.setTweet_content(content);

                String sql3 = "SELECT count(*) from app_comment WHERE tweet_id = ?";
                PreparedStatement preparedStatement3 = dataBaseConnection.preparedStatement(sql3);
                preparedStatement3.setString(1,tweet_id);
                ResultSet resultSet3 = preparedStatement3.executeQuery();
                if(resultSet3.next()){
                    tweet.setComment_num(""+resultSet3.getInt(1));
                }
                resultSet3.close();
                preparedStatement3.close();

                String sql4 = "SELECT count(*) from app_upvote WHERE tweet_id = ?";
                PreparedStatement preparedStatement4 = dataBaseConnection.preparedStatement(sql4);
                preparedStatement4.setString(1,tweet_id);
                ResultSet resultSet4 = preparedStatement4.executeQuery();
                if(resultSet4.next()){
                    tweet.setUpvote_num(""+resultSet4.getInt(1));
                }
                resultSet4.close();
                preparedStatement4.close();


                String sql2 = "SELECT count(*) FROM app_tweet intersect WHERE friend_id = ? AND  tweet_id = ?";
                PreparedStatement preparedStatement2 = dataBaseConnection.preparedStatement(sql2);
                preparedStatement2.setString(1,userId);
                preparedStatement2.setString(2,tweet_id);
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                if(resultSet2.next()){
                    int count = resultSet2.getInt(1);
                    if(count > 0){
                        tweet.setUpvote_status("1");
                    }else{
                        tweet.setUpvote_status("0");
                    }
                }
                tweetList.add(tweet);
                resultSet2.close();
                preparedStatement2.close();
            }
            resultSet1.close();
            preparedStatement1.close();

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            dataBaseConnection.close();
        }
        Moment moment = new Moment();
        moment.setResult("success");
        moment.setTweets(tweetList);
        return moment;
    }

    public static void main(String[] args) {
        MomentDao momentDao = new MomentDao();
        Gson gson = new Gson();
        System.out.println(gson.toJson(momentDao.getMoments("d911d63edb4a4757bcad7f6fee5c6420")));
    }

    public ResultInfo addMoments(String tweet_id,String userName ,Date now,String tweet_content){
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        List<Tweet> tweetList = new LinkedList<>();
        ResultInfo resultInfo=new ResultInfo();
        resultInfo.setResult("failure");
        try {
            String sql2 = "insert into app_tweet(tweet_id,friend_id,tweet_time,tweet_content)  VALUES (?,?,?,?) ";
            PreparedStatement preparedStatement2 = dataBaseConnection.preparedStatement(sql2);
            preparedStatement2.setString(1,tweet_id);
            preparedStatement2.setString(2,userName);
            preparedStatement2.setDate(3,new java.sql.Date(now.getTime()));
            preparedStatement2.setString(4,tweet_content);
            preparedStatement2.executeUpdate();
            resultInfo.setResult("success");
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            dataBaseConnection.close();
        }
        return resultInfo;
    }
}
