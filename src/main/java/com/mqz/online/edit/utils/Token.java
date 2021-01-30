package com.mqz.online.edit.utils;

import lombok.Data;

/**
 * @author mqz
 * @since 
 * @description
 * @abount https://github.com/DemoMeng
 */
@Data
public class Token {

    private int expires_in;
    private String token;
    private String wpsUrl;

}
