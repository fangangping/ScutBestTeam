package Dao;

import entity.User;
import util.DataBaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDao {


    public List<User>  getUserFriend(String userName){
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        String sql = "SELECT * FROM  sys_app_uesr_relationship WHERE first_user = ? UNION SELECT  * FROM sys_app_uesr_relationship WHERE seoncd_user = ?";
        List<User> userList = null;
        try {
            PreparedStatement preparedStatement = dataBaseConnection.preparedStatement(sql);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            userList = getUserList(resultSet,userName);
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            dataBaseConnection.close();
        }
        return userList;
    }


    public String addNewFriend(String user1, String user2){
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        String statusCode = "500";
        String sql = "INSERT INTO sys_app_uesr_relationship VALUES (?,?)";
        System.out.println("user1 = "+user1 + " user2" + user2);
        try {
            PreparedStatement preparedStatement = dataBaseConnection.preparedStatement(sql);
            preparedStatement.setString(1,user1);
            preparedStatement.setString(2,user2);
            preparedStatement.executeUpdate();
            statusCode = "200";
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            dataBaseConnection.close();
        }
        return statusCode;
    }


    public List<User> getUserDeatial(String[] userNmaeList){
        DataBaseConnection dataBaseConnection =  new DataBaseConnection();
        String sql = "SELECT * FROM sys_app_user WHERE USERNAME = ?";
        List<User> userList = new LinkedList<>();
        try{
            for(String userName:userNmaeList) {
                PreparedStatement preparedStatement = dataBaseConnection.preparedStatement(sql);
                preparedStatement.setString(1,userName);
                ResultSet resultSet = preparedStatement.executeQuery();
                User user = new User();
                if(resultSet.next()) {
                    user.setUserId(resultSet.getString("USERNAME"));
                    user.setName(resultSet.getString("NAME"));
                    user.setAvatarUrl("/" + user.getName() + ".icon");
                    userList.add(user);
                }
                System.out.println(user);
                resultSet.close();
                preparedStatement.close();
                System.out.println(userName);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            dataBaseConnection.close();
        }
        return userList;
    }


    public String delteFriend(String user1, String user2){
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        String statusCode = "500";
        try{
            String sql = "DELETE FROM sys_app_uesr_relationship WHERE (first_user = ? and seoncd_user = ?)  or (first_user = ? and seoncd_user = ?)";
            PreparedStatement preparedStatement = dataBaseConnection.preparedStatement(sql);
            preparedStatement.setString(1,user1);
            preparedStatement.setString(2,user2);
            preparedStatement.setString(3,user2);
            preparedStatement.setString(4,user1);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            statusCode = "200";
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            dataBaseConnection.close();
        }
        return statusCode;
    }


    private String getName(String userName){
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        String sql = "SELECT * from sys_app_user WHERE USERNAME = ?";
        String name = null;
        System.out.println("userName"+userName);
        try {
            PreparedStatement preparedStatement = dataBaseConnection.preparedStatement(sql);
            preparedStatement.setString(1,userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                name = resultSet.getString("NAME");
            }
            System.out.println("name="+ name);
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            dataBaseConnection.close();
        }
        return name;
    }

    private  List<User> getUserList(ResultSet resultSet,String currUserName) throws SQLException{
        List<User> userList = new LinkedList<>();
        while(resultSet.next()){
            String firstUser = resultSet.getString("first_user");
            String secondUser = resultSet.getString("seoncd_user");
            User user = new User();
            if(firstUser.equals(currUserName)) {
                user.setUserId(secondUser);
                user.setName(getName(secondUser));
            }else{
                user.setUserId(firstUser);
                user.setName(getName(firstUser));
            }
            user.setAvatarUrl("/" + user.getName() + ".icon");
            System.out.println(user);
            userList.add(user);
        }
        return userList;
    }

    private  List<User> getUserList(ResultSet resultSet) throws SQLException{
        List<User> userList = new LinkedList<>();
        while(resultSet.next()){
            String username = resultSet.getString("USERNAME");
            String name = resultSet.getString("NAME");
            User user = new User();
            user.setUserId(username);
            user.setName(name);
            user.setAvatarUrl("/" + user.getName() + ".icon");
            System.out.println(user);
            userList.add(user);
        }
        return userList;
    }





    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        System.out.println(userDao.delteFriend("1","2"));
    }

}