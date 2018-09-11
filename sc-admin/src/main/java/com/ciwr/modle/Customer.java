package com.ciwr.modle;

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
public class Customer implements Serializable {

    @Id
    private String id;

    /**
     * 客户名称
     */
    public String firstName;
    /**
     * 客户姓氏
     */
    public String lastName;
}
