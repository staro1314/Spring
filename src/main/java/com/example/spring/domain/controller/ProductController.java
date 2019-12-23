package com.example.spring.domain.controller;

import com.example.spring.domain.returnMsg.ReturnBody;
import com.example.spring.domain.returnMsg.excetion.GlobalException;
import com.example.spring.domain.service.service.ProductService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Staro
 * @date: 2019/3/1612:28
 * @Description:
 */
@Api(tags = "rabbitMQ")
@RequestMapping("rabbitmq")
@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "send", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "生产者发送消息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "请求失败")})
    public ReturnBody sendMsgToRabbitMQ(@ApiParam(value = "消息", required = true)@RequestParam String content) throws GlobalException {
        return productService.sendMsgToRabbitMQ(content);
    }
}
