package com.ciwr.modle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created with IDEA
 * Author:catHome
 * Description: 客户pojo
 * Time:Create on 2018/9/11 16:34
 */
@Data
@ApiModel(value = "客户pojo")
public class Customer implements Serializable {

    @Id
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 客户名称
     */
    @ApiModelProperty("客户名称")
    public String firstName;
    /**
     * 客户姓氏
     */
    @ApiModelProperty(value = "客户姓氏")
    public String lastName;
}
