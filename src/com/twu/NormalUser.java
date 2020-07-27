package com.twu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class NormalUser extends User {
    private int votes;
    private ArrayList<HotSearchItem> hotSearchList;
    public NormalUser(int votes, ArrayList<HotSearchItem> hotSearchList) {
        super(hotSearchList);
        this.votes = votes;
    }

    public void voteHotSearch() throws SQLException {
        Connector connector = new Connector();
        String sql;

        System.out.println("请输入要进行投票热搜事件名称:");
        Scanner scanner = new Scanner(System.in);
        String description = scanner.next();

        System.out.printf("当前可投票数为： %d\n", this.votes);
        System.out.println("请输入你的投票数目：");
        Scanner scanner1 = new Scanner(System.in);
        int hotDegree = Integer.parseInt(scanner1.next());

        String sqlFormat = "update hot_search set hot_degree=hot_degree+%d where description=\'%s\'";
        sql = String.format(sqlFormat, hotDegree, description);

        connector.updateDataAfterVoteOrPurchase(sql);
        this.votes = this.votes - hotDegree;
    }

    public void purchaseHotSearch() throws SQLException {
        System.out.println("请输入购买的热搜事件名称:");
        Scanner scanner = new Scanner(System.in);
        String description = scanner.next();

        System.out.println("请输入需要购买的热度值：");
        Scanner scanner1 = new Scanner(System.in);
        int hotDegree = Integer.parseInt(scanner1.next());

        Connector connector = new Connector();
        String sql;

        String sqlFormat = "insert into hot_search values (null, \'%s\', %d)";
        sql = String.format(sqlFormat, description, hotDegree);

        connector.updateDataAfterVoteOrPurchase(sql);
    }
}
