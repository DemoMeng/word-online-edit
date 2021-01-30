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
 * 在线编辑文件版本表
 * </p>
 *
 * @author mqz
 * @since 2020-12-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OnlineEditFileVersion extends Model<OnlineEditFileVersion> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文件id
     */
    private String fileId;

    /**
     * 名称
     */
    private String name;

    /**
     * 版本
     */
    private Integer version;

    /**
     * 下载地址
     */
    private String downloadUrl;

    /**
     * 文件大小
     */
    private Integer size;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 修改者
     */
    private String modifier;

    /**
     * 修改时间
     */
    private Long modifyTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
