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
        Connector connectorSelect = new Connector();
        Connector connectorUpdate = new Connector();
        String selectSql;
        String updateSql;

        System.out.println("请输入要进行投票热搜事件名称:");
        Scanner scanner = new Scanner(System.in);
        String description = scanner.next();

        System.out.printf("当前可投票数为： %d\n", this.votes);
        System.out.println("请输入你的投票数目：");
        Scanner scanner1 = new Scanner(System.in);
        int hotDegree = Integer.parseInt(scanner1.next());

        String sqlFormatForSelect = "select * from hot_search where description=\'%s\'";
        selectSql = String.format(sqlFormatForSelect, description);
        boolean isSuperHotSearch = connectorSelect.getSuperHotSearchByDescription(selectSql);

        if (isSuperHotSearch) hotDegree = hotDegree * 2;

        String sqlFormatForUpdate = "update hot_search set hot_degree=hot_degree+%d where description=\'%s\'";
        updateSql = String.format(sqlFormatForUpdate, hotDegree, description);

        connectorUpdate.updateDataAfterVoteOrPurchase(updateSql);
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
