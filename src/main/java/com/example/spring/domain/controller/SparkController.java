package com.example.spring.domain.controller;

import com.example.spring.domain.bean.SparkBean;
import com.example.spring.domain.returnMsg.ReturnBody;
import com.example.spring.domain.returnMsg.excetion.GlobalException;
import com.example.spring.domain.service.service.SparkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Staro
 * @date: 2019/8/23 10:52
 * @Description:
 */
@Api("sparkDemo")
@RequestMapping("spark")
@RestController
public class SparkController {
    private final SparkService sparkService;

    @Autowired
    public SparkController(SparkService sparkService) {
        this.sparkService = sparkService;
    }

    @RequestMapping(value = "test", method = RequestMethod.POST)
    @ApiOperation(value = "spark测试接口", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "请求资源不存在")
    })
    public ReturnBody sparkTest(@RequestBody SparkBean sparkBean) throws GlobalException {
        return sparkService.sparkTest(sparkBean);
    }
}
