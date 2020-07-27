package com.twu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class User implements HotSearch{
    protected ArrayList<HotSearchItem> hotSearchList;

    public User(ArrayList<HotSearchItem> hotSearchList) {
        this.hotSearchList = hotSearchList;
    }

    @Override
    public void checkHotSearch() throws SQLException {
//        connector connector = new connector();
//        ArrayList<HotSearchItem> hotSearchList = connector.getSourceDataArrayList();
//        System.out.println("排序前： ");
//        for (HotSearchItem item:hotSearchList) {
//            System.out.println(item.toString());
//        }
//
//        System.out.println("----------------------------------");
//        System.out.println("排序后： ");
        Collections.sort(hotSearchList);
        for (HotSearchItem item:hotSearchList) {
            System.out.printf("%-5d%-40s%-10d\n", hotSearchList.indexOf(item) + 1, item.getDescription(), item.getHotDegree());
        }
    }

    @Override
    public void addHotSearch() throws SQLException {
        System.out.println("请输入要添加的热搜事件名称:");
        Scanner scanner = new Scanner(System.in);
        String description = scanner.next();

        System.out.println("请输入添加热搜的热度值：");
        Scanner scanner1 = new Scanner(System.in);
        int hotDegree = Integer.parseInt(scanner1.next());

        Connector connector = new Connector();
        String sql;

        String sqlFormat = "insert into hot_search values (null, \'%s\', %d)";
        sql = String.format(sqlFormat, description, hotDegree);

        connector.updateDataAfterVoteOrPurchase(sql);
    }
}
