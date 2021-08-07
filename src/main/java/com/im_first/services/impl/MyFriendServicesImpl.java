package com.im_first.services.impl;

import com.im_first.mapper.MyFriendMapper;
import com.im_first.pojo.MyFriend;
import com.im_first.services.MyFriendServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyFriendServicesImpl implements MyFriendServices {
    @Autowired
    private MyFriendMapper myFriendMapper;

    @Override
    public MyFriend selectByPrimaryKey(String id) {
        return myFriendMapper.selectByPrimaryKey(id);
    }
}
