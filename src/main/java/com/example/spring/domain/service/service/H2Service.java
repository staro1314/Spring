package com.example.spring.domain.service.service;


import com.example.spring.domain.returnMsg.ReturnBody;
import com.example.spring.domain.returnMsg.excetion.GlobalException;

/**
 * @author: Staro
 * @date: 2019/4/1717:21
 * @Description:
 */
public interface H2Service {
    ReturnBody createTable()throws GlobalException;
}
