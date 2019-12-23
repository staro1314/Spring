package com.example.spring.domain.service.service;


import com.example.spring.domain.bean.Company;
import com.example.spring.domain.returnMsg.ReturnBody;
import com.example.spring.domain.returnMsg.excetion.GlobalException;

import java.util.List;

/**
 * @author: Staro
 * @date: 2019/4/1914:42
 * @Description:
 */
public interface PgService {
    ReturnBody<List<Company>> getList()throws GlobalException;
}
