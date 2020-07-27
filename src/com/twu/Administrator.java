package com.twu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Administrator extends User {
    public Administrator(ArrayList<HotSearchItem> hotSearchList) {
        super(hotSearchList);
    }

    public void addSuperHotSearch() throws SQLException {
        System.out.println("请输入要添加的超级热搜事件名称:");
        Scanner scanner = new Scanner(System.in);
        String description = scanner.next();

        System.out.println("请输入添加超级热搜的热度值：");
        Scanner scanner1 = new Scanner(System.in);
        int hotDegree = Integer.parseInt(scanner1.next());

        Connector connector = new Connector();
        String sql;

        String sqlFormat = "insert into hot_search values (null, \'%s\', %d, true)";
        sql = String.format(sqlFormat, description, hotDegree);

        connector.updateDataAfterVoteOrPurchase(sql);
    }
}
