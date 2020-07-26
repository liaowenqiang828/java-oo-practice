package com.twu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class NormalUser extends User {
    ArrayList<HotSearchItem> hotSearchList;
    public NormalUser(ArrayList<HotSearchItem> hotSearchList) {
        this.hotSearchList = hotSearchList;
    }

    @Override
    public ArrayList<String> checkHotSearch() {
        return super.checkHotSearch();
    }

    @Override
    public ArrayList<String> addHotSearch() {
        return super.addHotSearch();
    }

    public void voteHotSearch() throws SQLException {
        connector connector = new connector();
        String sql;

        System.out.println("请输入要进行投票热搜事件名称:");
        Scanner scanner = new Scanner(System.in);
        String hotSearchName = scanner.next();

        System.out.println("请输入你带投票数目：");
        Scanner scanner1 = new Scanner(System.in);
        int hotDegree = Integer.parseInt(scanner1.next());

        String sqlFormat = "update hot_search set hot_degree=hot_degree+%d where hot_search.description=%s";
        sql = String.format(sqlFormat, hotDegree, hotSearchName);

        connector.updateDataAfterVoteOrPurchase(sql);
    }

    public void purchaseHotSearch() {
        System.out.println("请输入购买的热搜事件名称:");
        Scanner scanner = new Scanner(System.in);
        String hotSearchName = scanner.next();

        System.out.println("请输入需要购买的热度值：");
        Scanner scanner1 = new Scanner(System.in);
        int hotDegree = Integer.parseInt(scanner1.next());


    }
}
