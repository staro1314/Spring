package com.example.spring.domain.controller;

import com.example.spring.domain.bean.UserBean;
import com.example.spring.domain.returnMsg.ReturnBody;
import com.example.spring.domain.returnMsg.excetion.GlobalException;
import com.example.spring.domain.service.service.TestServcie;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Staro
 * @date: 2019/3/1316:17
 * @Description:
 */
@RestController
@RequestMapping("demo")
@Api(tags = "测试Demo")
public class TestController {
    private final TestServcie testServcie;

    @Autowired
    public TestController(TestServcie testServcie) {
        this.testServcie = testServcie;
    }

    /*******************************************测试druid连接*******************************************/
    @RequestMapping(value = "test", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "测试数据库连接")
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "请求失败")})
    public ReturnBody<List<UserBean>> testConnect() throws GlobalException {
        return testServcie.testConnect();
    }

    /*********************************************用户*********************************************/

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "添加用户")
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "请求失败")})
    public ReturnBody addUser(@ApiParam(name = "用户信息", required = true)
                              @RequestBody UserBean userBean) throws GlobalException {
        return testServcie.addUser(userBean);
    }

    @RequestMapping(value = "delete/{userID}", method = RequestMethod.DELETE)
    @ApiOperation(httpMethod = "DELETE", value = "删除用户")
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "请求失败")})
    public ReturnBody deleteUser(@PathVariable String userID) throws GlobalException {
        return testServcie.deleteUser(userID);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    @ApiOperation(httpMethod = "PUT", value = "更新用户信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "请求失败")})
    public ReturnBody updateUser(@RequestBody UserBean userBean) throws GlobalException {
        return testServcie.updateUser(userBean);
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "获取用户列表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "请求失败")})
    public ReturnBody<List<UserBean>> getUserList(@ApiParam("用户名") @RequestParam(required = false) String name) throws GlobalException {
        return testServcie.getUserList(name);
    }

    @RequestMapping(value = "search/user", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "根据条件查询用户信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "请求失败")})
    public ReturnBody selectUser(String userId, String name, String sex, String addTime) throws GlobalException {
        return testServcie.selectUser(userId,name,sex,addTime);
    }
}
