package com.example.spring.domain.service.serviceImpl;

import com.example.spring.domain.config.rabbitmq.sender.FirstProduct;
import com.example.spring.domain.returnMsg.ReturnBody;
import com.example.spring.domain.returnMsg.codeMsg.ReturnMsgImpl;
import com.example.spring.domain.returnMsg.excetion.GlobalException;
import com.example.spring.domain.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Staro
 * @date: 2019/3/1612:37
 * @Description:
 */
@Service
public class ProductServcieIml implements ProductService {

    @Autowired
    private FirstProduct productor;

    @Override
    public ReturnBody sendMsgToRabbitMQ(String content) throws GlobalException {
        productor.senMessage(content);
        return new ReturnBody(ReturnMsgImpl.SUCCESS);
    }
}
