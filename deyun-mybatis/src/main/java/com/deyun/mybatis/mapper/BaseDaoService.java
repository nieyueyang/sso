package com.deyun.mybatis.mapper;

import com.deyun.common.domain.QueryParameter;

import java.util.List;
import java.util.Map;

/**
 * @Author: nieyy
 * @Date: 2019/3/15 0:00
 * @Version 1.0
 * @Describe: 数据库通用操作接口服务
 */

public interface BaseDaoService<T> {

    List<T> queryForList(T t) throws Exception;

    int insert(T t) throws Exception;

    int insertBatch(List <T> list) throws Exception;

    int update(T t, QueryParameter queryParameter) throws Exception;

    int delete(String tableName, QueryParameter queryParameter);

    int deleteBatch(String tableName, List <String> list);

}
