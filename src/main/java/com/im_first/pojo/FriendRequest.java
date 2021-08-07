package com.im_first.pojo;

import java.util.Date;

public class FriendRequest {
    private String id;

    private String sendUserId;

    private String acceptUserId;

    private Date requestDataTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getAcceptUserId() {
        return acceptUserId;
    }

    public void setAcceptUserId(String acceptUserId) {
        this.acceptUserId = acceptUserId;
    }

    public Date getRequestDataTime() {
        return requestDataTime;
    }

    public void setRequestDataTime(Date requestDataTime) {
        this.requestDataTime = requestDataTime;
    }
}