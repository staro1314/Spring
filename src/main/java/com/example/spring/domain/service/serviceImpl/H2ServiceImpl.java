package com.example.spring.domain.service.serviceImpl;

import com.example.spring.domain.dao.h2.H2Mapper;
import com.example.spring.domain.returnMsg.ReturnBody;
import com.example.spring.domain.returnMsg.codeMsg.ReturnMsgImpl;
import com.example.spring.domain.returnMsg.excetion.GlobalException;
import com.example.spring.domain.service.service.H2Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Staro
 * @date: 2019/4/1717:21
 * @Description:
 */
@Service
public class H2ServiceImpl implements H2Service {
    @Resource
    private H2Mapper h2Mapper;

    @Override
    public ReturnBody createTable() throws GlobalException {
        List<String> list = h2Mapper.getTableList();
        return new ReturnBody<>(ReturnMsgImpl.SUCCESS,list);
    }
}
