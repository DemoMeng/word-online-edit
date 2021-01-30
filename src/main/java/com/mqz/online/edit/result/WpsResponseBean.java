package com.mqz.online.edit.result;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author mqz
 * @description 通用返回类
 * @abount https://github.com/DemoMeng
 * @since 2020/11/10
 */
@Data
@Accessors(chain = true)
public class WpsResponseBean implements Serializable {

    /**
     * @param body
     * @return
     */
    private static ResponseEntity<Object> getEntity(Object body) {
        List<String> contentType = new ArrayList<>();
        contentType.add("application/json;charset=utf-8");
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Content-Type", contentType);
        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }

    /**
     * 成功
     * @return
     */
    public static ResponseEntity<Object> SUCCESS(){
        Map<String,Object> body = new ConcurrentHashMap<>();
        body.put(WpsResponseEnums.CODE.getKey(),WpsResponseEnums.CODE.getValue());
        body.put(WpsResponseEnums.MESSAGE.getKey(),WpsResponseEnums.MESSAGE.getValue());
        body.put(WpsResponseEnums.STATUS.getKey(),WpsResponseEnums.STATUS.getValue());
        return getEntity(body);
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static ResponseEntity<Object> SUCCESS(Map<String, Object> data){
        Map<String, Object> result = new ConcurrentHashMap<>();
        result.put(WpsResponseEnums.CODE.getKey(),WpsResponseEnums.CODE.getValue());
        result.put(WpsResponseEnums.MESSAGE.getKey(),WpsResponseEnums.MESSAGE.getValue());
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }
        result.put(WpsResponseEnums.STATUS.getKey(),WpsResponseEnums.STATUS.getValue());
        return getEntity(result);
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static ResponseEntity<Object> SUCCESS(Object data){
        Map<String, Object> result = new ConcurrentHashMap<>(4);
        result.put(WpsResponseEnums.CODE.getKey(),WpsResponseEnums.CODE.getValue());
        result.put(WpsResponseEnums.MESSAGE.getKey(),WpsResponseEnums.MESSAGE.getValue());
        result.put(WpsResponseEnums.STATUS.getKey(),WpsResponseEnums.STATUS.getValue());
        result.put(WpsResponseEnums.DATA.getKey(),data);
        return getEntity(result);
    }

    /**
     * 异常
     * @param msg
     * @return
     */
    public static ResponseEntity<Object> ERROR(String msg){
        Map<String, Object> result = new ConcurrentHashMap<>(4);
        result.put(WpsResponseEnums.CODE.getKey(),WpsResponseEnums.CODE.getValue());
        result.put(WpsResponseEnums.MESSAGE.getKey(),msg);
        result.put(WpsResponseEnums.STATUS.getKey(),WpsResponseEnums.STATUS.getValue());
        return getEntity(result);
    }

}
