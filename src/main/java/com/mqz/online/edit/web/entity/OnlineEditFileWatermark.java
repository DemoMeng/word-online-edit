package com.mqz.online.edit.web.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 在线编辑文件水印表
 * </p>
 *
 * @author mqz
 * @since 2020-12-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OnlineEditFileWatermark extends Model<OnlineEditFileWatermark> {

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
     * 类型 0为无水印； 1为文字水印
     */
    private Integer type;

    /**
     * 文字水印的文字，当type为1时此字段必选
     */
    private String value;

    /**
     * 水印的透明度，非必选，有默认值
     */
    private String fillstyle;

    /**
     * 水印的字体，非必选，有默认值
     */
    private String font;

    /**
     * 水印的旋转度，非必选，有默认值
     */
    private BigDecimal rotate;

    /**
     * 水印水平间距，非必选，有默认值
     */
    private Integer horizontal;

    /**
     * 水印水垂直距，非必选，有默认值
     */
    private Integer vertical;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
