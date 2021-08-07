package com.im_first.controller;

import com.im_first.bo.UserBO;
import com.im_first.enums.SearchFriendsStatusEnum;
import com.im_first.pojo.MyFriend;
import com.im_first.pojo.User;
import com.im_first.services.MyFriendServices;
import com.im_first.services.UserServices;
import com.im_first.utils.FastDFSClient;
import com.im_first.utils.FileUtils;
import com.im_first.utils.JSONResult;
import com.im_first.utils.MD5Utils;
import com.im_first.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServices userServices;

    @Autowired
    FastDFSClient fastDFSClient;

    @Autowired
    MyFriendServices myFriendServices;

    //登录和注册
    @PostMapping("/registerOrLogin")
    public JSONResult registerOrLogin(@RequestBody User user){
        User userResult = userServices.queryUserNameIsExit(user.getUsername());
        if(userResult != null){
            if(!userResult.getPassword().equals(MD5Utils.getPwd(user.getPassword()))){
                return JSONResult.errorMsg("密码错误");
            }
            user.setId(userResult.getId());

        }else{
            user.setNickname("ctb");
            user.setFaceImage("");
            user.setFaceImageBig("");
            user.setQrcode("");
            user.setCid("");
            user.setPassword(MD5Utils.getPwd(user.getPassword()));
            user = userServices.insert(user);
        }

        UserVo userVo = new UserVo();

        BeanUtils.copyProperties(user,userVo);
        JSONResult jsonResult = JSONResult.ok(userVo);

        return jsonResult;
    }

    //用户头像上传
    @PostMapping("/uploadFaceBase64")
    public JSONResult uploadFaceBase64(@RequestBody UserBO userBO) throws Exception {
        String base64Data = userBO.getFaceData();
        String userFacePath = "D:\\"+userBO.getUserId()+"userFaceBase64.png";
        //调用FileUtils 类中的方法将base64字符串转换为文件对象
        FileUtils.base64ToFile(userFacePath,base64Data);        //这个类有些问题，怎样将base64转换成图片？？？
        MultipartFile multipartFile = FileUtils.fileToMultipart(userFacePath);
        //获取fastDFS上传图片的路径
        String url = fastDFSClient.uploadBase64(multipartFile);
        System.out.println(url);

//        String thump = "small.";
//        String[] arr = url.split("\\.");
//        String thumpImgUrl = arr[0]+thump+arr[1];

        //更新用户数据
        User user = new User();
        user.setId(userBO.getUserId());
        user.setFaceImage(url);
        user.setFaceImageBig(url);
        User result = userServices.updateUserInfo(user);
        return  JSONResult.ok(result);
    }

    /**
     * 搜索好友
     * @param myUserId
     * @param friendUserName
     * @return
     */
    @PostMapping("/searchFriend")
    public JSONResult searchFriend(String myUserId,String friendUserName){
        /**
         * 前置条件
         * 1.搜索的用户如果不存在，返回【无此用户】
         * 2.如果是本人，返回【不能添加自己】
         * 3.已经是朋友，返回【该用户已经是好友】
         */

        User user = userServices.queryUserNameIsExit(friendUserName);
        if(user == null){
            return JSONResult.errorMsg("无此用户");
        }

        Integer status = userServices.preconditionSearchFriend(myUserId,user.getId());
        if(status == SearchFriendsStatusEnum.SUCCESS.status){
            //User user = userServices.queryUserNameIsExit(friendUserName);
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user,userVo);
            return JSONResult.ok(userVo);
        }else {
            String msg = SearchFriendsStatusEnum.getMsgByKey(status);
            return JSONResult.errorMsg(msg);
        }
    }

    @PostMapping("/getUser")
    public User getUserById(String id){
        User user=userServices.getUserById(id);
        return user;
    }

    @PostMapping("/getFriend")
    public MyFriend getFriendById(String id){
        return myFriendServices.selectByPrimaryKey(id);
    }


}
