package com.mqz.online.edit.config.wps;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mqz
 * @since
 * @description
 * @abount https://github.com/DemoMeng
 */
public class Request {

    public static String getHeaderParam(HttpServletRequest request, String key) {
        return request.getHeader(key);
    }

    public static String getQueryParam(HttpServletRequest request, String key) {
        return request.getParameter(key);
    }

}
