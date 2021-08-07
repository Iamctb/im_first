package com.im_first.services.impl;


import com.im_first.enums.SearchFriendsStatusEnum;
import com.im_first.mapper.MyFriendMapper;
import com.im_first.mapper.UserMapper;
import com.im_first.pojo.MyFriend;
import com.im_first.pojo.User;
import com.im_first.services.UserServices;

import com.im_first.utils.FastDFSClient;
import com.im_first.utils.FileUtils;
import com.im_first.utils.QRCodeUtils;
import n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class UserServicesImpl implements UserServices {

    //注入mapper
    @Autowired
    UserMapper userMapper;

    @Autowired
    MyFriendMapper myFriendMapper;

    @Autowired
    Sid sid;

    @Autowired
    QRCodeUtils qrCodeUtils;

    @Autowired
    FastDFSClient fastDFSClient;

    @Override
    public User getUserById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public User queryUserNameIsExit(String username){
        User user = userMapper.queryUserNameIsExit(username);
        return user;
    }

    @Override
    public User insert(User user) {
        user.setId(sid.nextShort());
        //为每一个用户生成一个二维码
        String qrCodePath = "D://user"+user.getId()+"qrcode.png";
        //创建二维码对象信息
        qrCodeUtils.createQRCode(qrCodePath,"bird_qrcode"+user.getUsername());
        MultipartFile qrcodeFile = FileUtils.fileToMultipart(qrCodePath);
        String qrCodeURL = "";
        try {
            qrCodeURL = fastDFSClient.uploadQRCode(qrcodeFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setQrcode(qrCodeURL);
        userMapper.insert(user);
        return user;
    }

    @Override
    public User updateUserInfo(User user) {
        userMapper.updateByPrimaryKeySelective(user);
        User result = userMapper.selectByPrimaryKey(user.getId());
        return result;
    }

    //添加好友的前置条件
    @Override
    public Integer preconditionSearchFriend(String myUserId, String myFriendsUserId) {
//        User user = queryUserNameIsExit(myFriendsUserId);
//        //1.搜索的用户如果不存在，返回【无此用户】
//        if(user == null){
//            return SearchFriendsStatusEnum.USER_NOT_EXIST.status;
//        }
        //2.不能添加自己
        if(myUserId.equals(myFriendsUserId)){
            return SearchFriendsStatusEnum.NOT_YOURSELF.status;
        }
        //3.不能添加已经存在的好友
        //示例查询
//        Example myFriend = new Example(MyFriend.class);
//        Example.Criteria criteria = myFriend.createCriteria();
//        criteria.andEqualTo("myUserId",myUserId);
//        criteria.andEqualTo("myFriendsUserId",user.getId());
        MyFriend myF = myFriendMapper.selectOneByExample(myUserId,myFriendsUserId);
        if(myF != null){
            return SearchFriendsStatusEnum.ALREADY_FRIENDS.status;
        }
        return SearchFriendsStatusEnum.SUCCESS.status;

    }

}
