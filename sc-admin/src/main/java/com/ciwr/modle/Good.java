package com.ciwr.modle;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName("basic_good_info")
public class Good implements Serializable {

    /**
     * 商品编号
     */
    @TableId
    @TableField(value = "BGI_ID")
    private Long id;

    /**
     * 商品名称
     */
    @TableField(value = "BGI_NAME")
    private String name;

    /**
     * 商品单位
     */
    @TableField(value = "BGI_UNIT")
    private String unit;

    /**
     * 商品单价
     */
    @TableField(value = "BGI_PRICE")
    private BigDecimal price;

}
