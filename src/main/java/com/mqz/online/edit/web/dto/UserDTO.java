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
public class UserDTO {

    private String id = "-1";
    private String name = " ";
    private String permission = "write";
    private String avatar_url = "";

}
