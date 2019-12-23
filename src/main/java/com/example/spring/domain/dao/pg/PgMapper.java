package com.example.spring.domain.dao.pg;


import com.example.spring.domain.bean.Company;

import java.util.List;

/**
 * @author: Staro
 * @date: 2019/4/1914:42
 * @Description:
 */
public interface PgMapper {
    List<Company> getList();
}
