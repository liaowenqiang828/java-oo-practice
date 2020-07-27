package com.twu;

import org.omg.Messaging.SyncScopeHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.stream.StreamSupport;

public class Connector {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/source_data";
    private static final String NAME = "root";
    private static final String PASSWORD = "liaowenqiang";

//    public static void main(String[] args) throws SQLException {
//        connector connector = new connector();
//        Connection connection = connector.getConnect();
//        Statement statement = connector.getStatement(connection);
//        ResultSet resultSet = connector.executeSQL(statement);
//
//        ArrayList<HotSearchItem> hotSearchList = new ArrayList<>();
//
//        while (resultSet.next()) {
//            hotSearchList.add(new HotSearchItem(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
//            }
//
//        hotSearchList.stream().forEach(item -> System.out.println(item.toString()));
////        try {
////            Class.forName("com.mysql.jdbc.Driver");
////        } catch (ClassNotFoundException e) {
////            e.printStackTrace();
////        }
////
////        String sql = "select * from hot_search";
////
////        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/source_data?characterEncoding=utf8&useSSL=false",
////                "root", "liaowenqiang");
////             Statement statement = connection.createStatement())
////        {
////            ResultSet resultSet = statement.executeQuery(sql);
////            while (resultSet.next()) {
////                System.out.println(resultSet.getString(2));
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
//    }
    public ArrayList<HotSearchItem> getSourceDataArrayList() throws SQLException {
        ArrayList<HotSearchItem> hotSearchList = new ArrayList<>();

        Connection connection = getConnect();
        Statement statement = getStatement(connection);
        ResultSet resultSet = executeSQL(statement);

        while (resultSet.next()) {
            hotSearchList.add(new HotSearchItem(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
        }
        return hotSearchList;
    }

    public void updateDataAfterVoteOrPurchase(String sql) throws SQLException {
        Connection connection = getConnect();
        Statement statement = getStatement(connection);
        updateSQL(statement, sql);
    }

    public boolean getSuperHotSearchByDescription(String sql) throws SQLException {
        boolean superHotSearch = false;
        ResultSet resultSet = null;
        try (Connection connection = getConnect();
             Statement statement = getStatement(connection))
        {
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                superHotSearch = resultSet.getBoolean(4);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return superHotSearch;
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
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/source_data?characterEncoding=utf8&useSSL=false",
                    "root", "liaowenqiang");
            System.out.println("数据库连接成功");
        } catch (SQLException e) {
            e.printStackTrace();
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

    public void updateSQL(Statement statement, String sql) {
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
