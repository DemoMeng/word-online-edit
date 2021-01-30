package com.mqz.online.edit.config.file;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * @author mqz
 * @since 2020年12月11日15:18:19
 * @description
 * @abount https://github.com/DemoMeng
 */
@Component
@Data
public class FileConfig {

    @Value("${upload.maxFileSize}")
    private Integer maxFileSize;
    @Value("${upload.maxRequestSize}")
    private Integer maxRequestSize;
    @Value("${upload.path}")
    private String path;

}
