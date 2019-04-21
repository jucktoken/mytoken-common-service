package com.juck.mytoken.common.service;

import com.github.pagehelper.PageInfo;
import com.juck.mytoken.common.domain.base.BaseDto;

public interface BaseService<T extends BaseDto> {

    int insert(T t,String createBy);
    int delete(T t);
    int update(T t,String updateBy);
    int count(T t);
    T selectOne(T t);

    PageInfo<T> selectPage(int pageNum,int pageSize,T t);
}
