package com.example.spring.domain.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: Staro
 * @date: 2019/4/1914:52
 * @Description:
 */
@Data
@ApiModel("公司信息")
public class Company {
    @ApiModelProperty(name = "id")
    private int id;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("年龄")
    private int age;
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("薪水")
    private float salary;
}
