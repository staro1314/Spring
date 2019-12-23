package com.example.spring.domain.service.service;


import com.example.spring.domain.bean.SparkBean;
import com.example.spring.domain.returnMsg.ReturnBody;
import com.example.spring.domain.returnMsg.excetion.GlobalException;

/**
 * @author: Staro
 * @date: 2019/7/115:44
 * @Description:
 */
public interface SparkService {
    ReturnBody sparkTest(SparkBean sparkBean)throws GlobalException;
}
