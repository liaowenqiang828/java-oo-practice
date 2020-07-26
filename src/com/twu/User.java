package com.twu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class User implements HotSearch{
    @Override
    public void checkHotSearch() throws SQLException {
        connector connector = new connector();
        ArrayList<HotSearchItem> hotSearchList = connector.getSourceDataArrayList();

        System.out.println("排序前： ");
        for (HotSearchItem item:hotSearchList) {
            System.out.println(item.toString());
        }

        System.out.println("----------------------------------");
        System.out.println("排序后： ");
        Collections.sort(hotSearchList);
        for (HotSearchItem item:hotSearchList) {
            System.out.println(item.toString());
        }
    }

    @Override
    public void addHotSearch() {

    }
}
