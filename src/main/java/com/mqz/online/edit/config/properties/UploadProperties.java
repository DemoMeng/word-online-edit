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
@ConfigurationProperties(prefix = "upload")
public class UploadProperties {

    private String fileLocation;

}
