package com.mqz.online.edit.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author mqz
 * @since 
 * @description
 * @abount https://github.com/DemoMeng
 */
@Data
@Component
@ConfigurationProperties(prefix = "wps.convert")
public class ConvertProperties {

    /** 转换地址 */
    private String convert;
    /**  文件查询地址 */
    private String query;
    private String appid;
    private String appsecret;

}
