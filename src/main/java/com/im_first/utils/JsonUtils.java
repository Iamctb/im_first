package com.im_first.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

//类转换
public class JsonUtils {
    //定义 jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //将对象转换成json字符串
    public static String objectToJson(Object data){
        try{
            String string = MAPPER.writeValueAsString(data);
            return string;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //将json结果转化为对象
    public static <T> T jsonToPojo(String jsonData,Class<T> beanType){
        try{
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //将json 数据转换成pojo 对象list
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType){
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try{
            List<T> list = MAPPER.readValue(jsonData,javaType);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
