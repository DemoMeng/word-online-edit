package com.mqz.online.edit.config.wps;

/**
 * @author mqz
 * @since
 * @description url枚举
 * @abount https://github.com/DemoMeng
 */
public enum PassUrl {

    v1_3rd_user, v1_3rd_onnotify, v1_3rd_file, v1_api_file;

    public static boolean checkCode(String code) {
        for (PassUrl mode : PassUrl.values()) {
            if (code.contains(mode.toString())) {
                return true;
            }
        }
        return false;
    }


}
