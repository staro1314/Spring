package com.example.spring.domain.service.serviceImpl;

import com.example.spring.domain.bean.Company;
import com.example.spring.domain.dao.pg.PgMapper;
import com.example.spring.domain.returnMsg.ReturnBody;
import com.example.spring.domain.returnMsg.codeMsg.ReturnMsgImpl;
import com.example.spring.domain.returnMsg.excetion.GlobalException;
import com.example.spring.domain.service.service.PgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Staro
 * @date: 2019/4/1914:42
 * @Description:
 */
@Service
public class PgServiceImpl implements PgService {
    @Resource
    private PgMapper pgMapper;

    @Override
    public ReturnBody<List<Company>> getList() throws GlobalException {
        List<Company> company = pgMapper.getList();
        return new ReturnBody<>(ReturnMsgImpl.SUCCESS,company);
    }
}
