package com.mqz.online.edit.utils.upload;

import lombok.Data;

/**
 * @author mqz
 * @since 
 * @description
 * @abount https://github.com/DemoMeng
 */
@Data
public class ResFileDTO {

    private String fileUrl;
    private String fileName;
    private String cFileName;
    private String fileType;
    private long fileSize;
    private String md5key;

}
