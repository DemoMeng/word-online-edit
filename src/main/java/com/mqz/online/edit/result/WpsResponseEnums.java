package com.mqz.online.edit.result;

import org.springframework.http.HttpStatus;

/**
 * @author mqz
 * @description wps通用返回key枚举
 * @abount https://github.com/DemoMeng
 * @since 2020/11/10
 */

public enum WpsResponseEnums {

    MESSAGE("msg","ok"),
    STATUS("status",200),
    CODE("code", HttpStatus.OK.value()),
    DATA("data","data"),
    ;

    private String key;
    private Object value;

    WpsResponseEnums(String key, Object value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
