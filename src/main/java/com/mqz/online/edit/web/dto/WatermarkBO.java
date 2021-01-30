package com.mqz.online.edit.web.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class WatermarkBO {

    private Integer type = 0;
    private String value = "";
    private String fillstyle = "rgba( 192, 192, 192, 0.6 )";
    private String font = "bold 20px Serif";
    private BigDecimal rotate = new BigDecimal("0");
    private Integer horizontal = 50;
    private Integer vertical = 50;

}
