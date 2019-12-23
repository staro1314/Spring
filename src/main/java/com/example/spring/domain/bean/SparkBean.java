package com.example.spring.domain.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: Staro
 * @date: 2019/8/23 11:16
 * @Description:
 */
@Data
@ApiModel("spark请求参数")
public class SparkBean {
    @ApiModelProperty("数据库URL")
    private String url;
    @ApiModelProperty("数据库用户名")
    private String username;
    @ApiModelProperty("数据库密码")
    private String password;
    @ApiModelProperty("执行的SQL语句")
    private String execSql;
    @ApiModelProperty("查询类型")
    private String type;
    @ApiModelProperty("查询条数")
    private Integer limit;
}
