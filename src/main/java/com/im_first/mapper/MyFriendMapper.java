package com.im_first.mapper;

import com.im_first.pojo.MyFriend;
import tk.mybatis.mapper.entity.Example;


public interface MyFriendMapper {
    int deleteByPrimaryKey(String id);

    int insert(MyFriend record);

    int insertSelective(MyFriend record);

    MyFriend selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MyFriend record);

    int updateByPrimaryKey(MyFriend record);

    MyFriend selectOneByExample(Example mfe);

    MyFriend selectOneByExample(String myUserId, String myFriendsUserId);

}