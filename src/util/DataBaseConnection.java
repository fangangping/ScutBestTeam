package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DataBaseConnection {
    private final String DBDRIVER = "com.mysql.jdbc.Driver";
    private final String DBURL = "jdbc:mysql://47.94.194.219:3306/scutbestteam";
    private final String DBUSER = "huangtianshen";
    private final String PASSWORD = "Huangtianshen123,./";
    private Connection conn = null;

    public DataBaseConnection(){
        try{
            Class.forName(DBDRIVER);
            this.conn = (com.mysql.jdbc.Connection) DriverManager.getConnection(DBURL, DBUSER, PASSWORD);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Fail to connect database.");
        }
    }

    public Connection getConnection(){
        return conn;
    }

    public PreparedStatement preparedStatement(String sql_statement){
        com.mysql.jdbc.PreparedStatement preparedStatement = null;
        try {
            preparedStatement =
                    (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql_statement);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Fail to prepared statement.");
        }
        return preparedStatement;
    }

    public void close(){
        try{
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Fail to close connection of database.");
        }
    }
}
