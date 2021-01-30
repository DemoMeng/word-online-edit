package com.mqz.online.edit.web.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author mqz
 * @since
 * @description
 * @abount https://github.com/DemoMeng
 */
@Data
@Accessors(chain = true)
public class UserAclBO {

    //重命名权限，1为打开该权限，0为关闭该权限，默认为0
    private int rename = 1;
    //历史版本权限，1为打开该权限，0为关闭该权限,默认为1
    private int history = 1;
    //复制
    private int copy = 1;
    //导出PDF
    private int export = 1;
    //打印
    private int print = 1;



}
