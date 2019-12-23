package com.example.spring.domain.controller;

import com.example.spring.domain.bean.Company;
import com.example.spring.domain.returnMsg.ReturnBody;
import com.example.spring.domain.returnMsg.excetion.GlobalException;
import com.example.spring.domain.service.service.PgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Staro
 * @date: 2019/4/1914:41
 * @Description:
 */
@Api(tags = "pg数据库")
@RequestMapping("pg")
@RestController
public class PgController {
    private final PgService pgService;

    @Autowired
    public PgController(PgService pgService) {
        this.pgService = pgService;
    }

    @RequestMapping(value = "getList", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "pg测试")
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "请求失败")})
    public ReturnBody<List<Company>> getList() throws GlobalException {
        return pgService.getList();
    }
}
