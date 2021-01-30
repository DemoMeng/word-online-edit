package com.mqz.online.edit.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author mqz
 * @since
 * @description
 * @abount https://github.com/DemoMeng
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Accessors(chain = true)
public class FileDTO {

    private String id;
    private String name;
    private int version;
    private double size;
    private String creator;
    private String modifier;
    private long create_time;
    private long modify_time;
    private String download_url;

    private UserAclBO user_acl;
    private WatermarkBO watermark;

}

