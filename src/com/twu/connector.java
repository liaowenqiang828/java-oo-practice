package com.twu;

import java.sql.*;

public class connector {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/source_data";
    private static final String NAME = "root";
    private static final String PASSWORD = "liaowenqiang";

    public static void main(String[] args) throws SQLException {
        connector connector = new connector();
        Connection connection = connector.getConnect();
        Statement statement = connector.getStatement(connection);
        ResultSet resultSet = connector.executeSQL(statement);
    }

    public Connection getConnect() throws SQLException {
        try {
            // 加载驱动
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/source_data?characterEncoding=utf8&useSSL=true" +
                    "user=root&password=liaowenqiang");
            System.out.println("数据库连接成功");
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
        }
        return connection;
    }

    public Statement getStatement(Connection connection) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("创建statement失败");
        }
        return statement;
    }

    public ResultSet executeSQL(Statement statement) {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery("select * from hot_search");
        } catch (SQLException e) {
            System.out.println("查询失败");
        }
        return rs;
    }
}
