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
@ConfigurationProperties(prefix = "oss")
public class OSSProperties {

    private String fileUrlPrefix;
    private String bucketName;
    private String diskName;
    private String regionId;
    private String endpoint;
    private String accessKey;
    private String accessSecret;

}
