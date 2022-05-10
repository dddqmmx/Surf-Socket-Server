package com.dd.surf.util;

import java.sql.*;

public class BaseDao {

    private String url = "jdbc:mysql://localhost:3306/surf?serverTimezone=Asia/Shanghai&characterEncoding=utf8";
    private String userName = "root";
    private String pwd = "114514";

    private Connection connection = null;
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;

    public Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            if (connection == null){
                connection = DriverManager.getConnection(url,userName,pwd);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public ResultSet executeQuery(String sql,Object[] obj){
        connection = this.getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (obj!=null){
                for (int i=0; i<obj.length; i++){
                    preparedStatement.setObject(i+1,obj[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public int executeUpdate(String sql,Object[] obj){
        int rows = 0;
        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (obj!=null){
                for (int i = 0; i<obj.length; i++){
                    preparedStatement.setObject(i+1,obj[i]);
                }
            }
            rows=preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public void closeAll(){
        if (resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement!=null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
