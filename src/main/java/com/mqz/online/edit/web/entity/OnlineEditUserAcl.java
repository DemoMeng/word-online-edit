package com.mqz.online.edit.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 在线编辑文件用户关联表
 * </p>
 *
 * @author mqz
 * @since 2020-12-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OnlineEditUserAcl extends Model<OnlineEditUserAcl> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 文件id
     */
    private String fileId;

    /**
     * 用户操作权限，write：可编辑，read：预览
     */
    private String permission;

    /**
     * 重命名权限，1为打开该权限，0为关闭该权限，默认为0
     */
    private Integer reName;

    /**
     * 历史版本权限，1为打开该权限，0为关闭该权限,默认为1
     */
    private Integer history;

    /**
     * 复制，1为打开该权限，0为关闭该权限,默认为1
     */
    private Integer copy;

    /**
     * 导出pdf，1为打开该权限，0为关闭该权限,默认为1
     */
    private Integer export;

    /**
     * 打印，1为打开该权限，0为关闭该权限,默认为1
     */
    private Integer print;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
