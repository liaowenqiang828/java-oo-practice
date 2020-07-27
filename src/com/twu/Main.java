package com.twu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        Connector connector = new Connector();

        ArrayList<HotSearchItem> hotSearchList = connector.getSourceDataArrayList();
//        hotSearchList.stream().forEach(hotSearchItem -> System.out.println(hotSearchItem.toString()));

        System.out.println("请按照格式输入用户角色代码：");
        System.out.println("普通用户请输入数字：0， 管理员用户请输入数字：1， 按Enter键结束。");
        Scanner scanner = new Scanner(System.in);
        String code = scanner.next();

        if (!code.equals("0") || !code.equals("1")) {
            System.out.println("命令输入格式错误，请重新正确输入： ");
        }

        if (code.equals("0")) {
            System.out.println("进入普通用户操作：");
            NormalUser normalUser = new NormalUser(10, hotSearchList);

            System.out.println("请输入你要进行的操作的代码，按Enter键结束");
            System.out.println("查看热搜排行榜，请输入： check");
            System.out.println("给热搜事件投票，请输入： vote");
            System.out.println("购买热搜，请输入： purchase");
            System.out.println("添加热搜，请输入： add");

            Scanner scanner1 = new Scanner(System.in);
            String commond1 = scanner1.next();

            switch (commond1) {
                case "check":
                    normalUser.checkHotSearch();
                    break;
                case "vote":
                    normalUser.voteHotSearch();
                    break;
                case "purchase":
                    normalUser.purchaseHotSearch();
                    break;
                case "add":
                    normalUser.addHotSearch();
                    break;
            }
        } else {
            System.out.println("进入管理员用户操作：");
            Administrator administrator = new Administrator(10, hotSearchList);

            System.out.println("请输入你要进行的操作的代码，按Enter键结束");
            System.out.println("查看热搜排行榜，请输入： check");
            System.out.println("添加热搜，请输入： add");
            System.out.println("添加超级热搜，请输入： add super");

            Scanner scanner1 = new Scanner(System.in);
            String commond1 = scanner1.next();

            switch (commond1) {
                case "check":
                    administrator.checkHotSearch();
                    break;
                case "add":
                    administrator.addHotSearch();
                    break;
                case "add super":
                    administrator.addSperHotSearch();
                    break;
            }


        }
    }
}
