package com.twu;

import java.sql.SQLException;
import java.util.ArrayList;

public interface HotSearch {
    void checkHotSearch() throws SQLException;
    void addHotSearch();
}
