package com.deyun.common.domain;

/**
 * @Author: nieyy
 * @Date: 2019/5/25 17:40
 * @Version 1.0
 * @Description:
 */
public class PageParameter extends QueryParameter {
    private static final int PAGE_SIZE = 20;

    protected int pageNum = 1; //页码
    protected int pageSize = PAGE_SIZE; // 每页显示的数量

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

}
