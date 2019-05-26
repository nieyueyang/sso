package com.deyun.common.domain;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @Author: nieyy
 * @Date: 2019/5/25 17:35
 * @Version 1.0
 * @Description:
 */
public class QueryParameter extends HashMap<String, Object> implements Serializable {

    public final static String MAP_KEY_SORT = "create_date";

    private Object[] params;
    private String orderBy;

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
        this.remove(MAP_KEY_SORT);
    }

}
