package com.juck.mytoken.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.juck.mytoken.common.domain.base.BaseDto;
import com.juck.mytoken.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.MyMapper;

import java.util.Date;

@Service
@Transactional(readOnly =true)
public abstract class BaseServiceImpl<T extends BaseDto,D extends MyMapper<T>> implements BaseService<T> {

   @Autowired
   public D dao;
    @Override
    @Transactional(readOnly =false)
    public int insert(T t,String createBy) {
        t.setUpdateBy(createBy);
        t.setCreateDate(new Date());
        return dao.insert(t);
    }

    @Override
    @Transactional(readOnly =false)
    public int delete(T t) {
        return dao.delete(t);
    }

    @Override
    @Transactional(readOnly =false)
    public int update(T t,String updateBy) {
        t.setUpdateBy(updateBy);
        t.setUpdateDate(new Date());
        return dao.updateByPrimaryKey(t);
    }

    @Override
    public int count(T t) {
        return dao.selectCount(t);
    }

    @Override
    public T selectOne(T t) {
        return dao.selectOne(t);
    }

    @Override
    public PageInfo<T> selectPage(int pageNum, int pageSize,T t) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(pageNum,pageSize);

        PageInfo<T> pageInfo = new PageInfo<>(dao.select(t));

        return pageInfo;
    }
}
