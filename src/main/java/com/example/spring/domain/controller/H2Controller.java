package com.example.spring.domain.controller;

import com.example.spring.domain.returnMsg.ReturnBody;
import com.example.spring.domain.returnMsg.excetion.GlobalException;
import com.example.spring.domain.service.service.H2Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Staro
 * @date: 2019/4/1717:20
 * @Description:
 */
@Api(tags = "h2")
@RestController
@RequestMapping("h2")
public class H2Controller {
    private final H2Service h2Service;

    @Autowired
    public H2Controller(H2Service h2Service) {
        this.h2Service = h2Service;
    }

    @RequestMapping(value = "createTable", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "h2测试")
    public ReturnBody createTable() throws GlobalException {

        return h2Service.createTable();
    }

}
