package com.example.spring.domain.service.service;


import com.example.spring.domain.returnMsg.ReturnBody;
import com.example.spring.domain.returnMsg.excetion.GlobalException;

/**
 * @author: Staro
 * @date: 2019/3/1612:37
 * @Description:
 */
public interface ProductService {
    ReturnBody sendMsgToRabbitMQ(String content)throws GlobalException;
}
