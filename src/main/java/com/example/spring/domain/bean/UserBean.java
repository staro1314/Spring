package com.example.spring.domain.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: Staro
 * @date: 2019/3/1317:25
 * @Description:
 */
@Data
@ApiModel(value = "用户信息")
public class UserBean implements Serializable {
    @ApiModelProperty(value = "用户名id",required = true)
    private String id;
    @ApiModelProperty(value = "用户名",required = true)
    private String username;
    @ApiModelProperty(value = "用户性别",required = true)
    private String sex;
    @ApiModelProperty(value = "薪水",required = true)
    private int salary;
}
