package com.im_first.mapper;

import com.im_first.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //更具用户名查找指定对象
    User queryUserNameIsExit(String username);
}