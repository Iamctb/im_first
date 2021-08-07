package com.im_first.utils;

//响应数据结构
// 200: 表示成功
// 500: 表示错误
// 501: bean验证错误。不管多少错误都以map形式返回
// 502: 拦截器拦截到用户token出错
// 555: 一场抛出信息

public class JSONResult {
    //响应状态
    private Integer status;

    //响应消息
    private String msg;

    //响应数据
    private Object data;

    private String ok;

    public JSONResult() {

    }


    public JSONResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public JSONResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;//data = {"id":"dsssd","title":"dsddsds"};
    }


    public static JSONResult build(Integer status,String msg,Object data){
        return new JSONResult(status,msg,data);
    }

    public static JSONResult  ok(Object data){
        return new JSONResult(data);
    }

    public static JSONResult errorMsg(String msg){
        return new JSONResult(500,msg,null);
    }

    public static JSONResult errorMap(Object data) {
        return new JSONResult(501, "error", data);
    }

    public static JSONResult errorTokenMsg(String msg) {
        return new JSONResult(502, msg, null);
    }

    public static JSONResult errorException(String msg) {
        return new JSONResult(555, msg, null);
    }






    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

}
