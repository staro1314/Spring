package com.example.spring.domain.service.service;

import com.example.spring.domain.bean.UserBean;
import com.example.spring.domain.returnMsg.ReturnBody;
import com.example.spring.domain.returnMsg.excetion.GlobalException;

import java.util.List;

/**
 * @author: Staro
 * @date: 2019/3/1316:18
 * @Description:
 */
public interface TestServcie {
    ReturnBody<List<UserBean>> testConnect() throws GlobalException;

    ReturnBody addUser(UserBean userBean) throws GlobalException;

    ReturnBody deleteUser(String userID) throws GlobalException;

    ReturnBody updateUser(UserBean userBean) throws GlobalException;

    ReturnBody<List<UserBean>> getUserList(String name) throws GlobalException;

    ReturnBody selectUser(String userId, String name, String sex, String addTime)throws GlobalException;
}
