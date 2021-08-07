package com.im_first.services;

import com.im_first.enums.SearchFriendsStatusEnum;
import com.im_first.pojo.User;

public interface UserServices {
    User getUserById(String id);

    //更具用户名查找对象
    User queryUserNameIsExit(String name);

    User insert(User user);

    //修改用户
    User updateUserInfo(User user);

    //搜索好友的前置条件接口
    Integer preconditionSearchFriend(String myUserId, String friendUserName);

}
