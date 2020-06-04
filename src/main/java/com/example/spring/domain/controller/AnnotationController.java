package com.example.spring.domain.controller;

import com.example.spring.domain.returnMsg.ReturnBody;
import com.example.spring.domain.returnMsg.codeMsg.ReturnMsgImpl;
import com.example.spring.domain.service.service.AnnotationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Staro
 * @date: 2020/5/27 16:39
 * @Description:
 */
@Api(tags = "自定义注解")
@RestController
@RequestMapping("annotation")
public class AnnotationController {

    private final AnnotationService annotationService;

    @Autowired
    public AnnotationController(AnnotationService annotationService) {
        this.annotationService = annotationService;
    }

    @RequestMapping(value = "custom", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "自定义token验证")
    public ReturnBody verificationToken(){

        return new ReturnBody<>(ReturnMsgImpl.SUCCESS,annotationService.getToken());
    }
}
