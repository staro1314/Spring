package com.example.spring.domain.dao.mysql;

import com.example.spring.domain.bean.UserBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: Staro
 * @date: 2019/3/1316:19
 * @Description:
 */
public interface MysqlMapper {
    List<UserBean> selectUser(@Param("userId") String userId,
                              @Param("name") String name,
                              @Param("sex") String sex,
                              @Param("addTime") String addTime);

    int addUser(@Param("userBean") UserBean userBean);

    int deleteUserById(@Param("userID") String userID);

    int updateUser(@Param("userBean") UserBean userBean);

    List<UserBean> getUserList(@Param("name") String name);
}
