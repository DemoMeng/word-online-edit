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
 * 在线编辑文件表
 * </p>
 *
 * @author mqz
 * @since 2020-12-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OnlineEditFile extends Model<OnlineEditFile> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id,字符串长度小于40（建议使用uuid避免读取到重复的文档）
     */
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 文件名(必须带文件后缀)
     */
    private String name;

    /**
     * 版本
     */
    private Integer version;

    /**
     * 文件大小,单位为B(文件真实大小，否则会出现异常)
     */
    private Integer size;

    /**
     * 创建者,字符串长度小于40
     */
    private String creator;

    /**
     * 创建时间,时间戳，单位为秒
     */
    private Long createTime;

    /**
     * 修改者,字符串长度小于40
     */
    private String modifier;

    /**
     * 修改时间,时间戳，单位为秒
     */
    private Long modifyTime;

    /**
     * 文件路径
     */
    private String downloadUrl;

    /**
     * 删除标识
     */
    private String deleted;

    /**
     * 是否可删除
     */
    private String canDelete;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
