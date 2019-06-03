package com.deyun.common.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: nieyy
 * @Date: 2019/5/25 17:40
 * @Version 1.0
 * @Description:
 */
public class PageParameter2 {
    private static final int PAGE_SIZE = 10;

    protected int pageNum = 1; //页码
    protected int pageSize = PAGE_SIZE; // 每页显示的数量
    protected String orderBy = "";
    private HashMap<String, Object> hbasMap;


    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public HashMap <String, Object> getHbasMap() {
        return hbasMap;
    }

    public void setHbasMap(HashMap <String, Object> hbasMap) {
        this.hbasMap = hbasMap;
    }
}
