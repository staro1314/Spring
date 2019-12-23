package com.example.spring.domain.service.serviceImpl;

import com.example.spring.domain.bean.UserBean;
import com.example.spring.domain.dao.mysql.MysqlMapper;
import com.example.spring.domain.returnMsg.ReturnBody;
import com.example.spring.domain.returnMsg.codeMsg.ReturnMsgImpl;
import com.example.spring.domain.returnMsg.excetion.GlobalException;
import com.example.spring.domain.service.service.TestServcie;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Staro
 * @date: 2019/3/1316:19
 * @Description:
 */
@Service
public class TestServiceImpl implements TestServcie {

    @Resource
    private MysqlMapper testMapper;

//    @Resource
//    private RedisTemplate<String,Object> redisTemplate;

    /**
     * @Cacheable 可以标记在一个方法上，也可以标记在一个类上。
     * 当标记在一个方法上时表示该方法是支持缓存的，当标记在一个类上时则表示该类所有的方法都是支持缓存的.
     * value属性指定Cache名称
     * key属性
     * 自定义key
     * methodName  当前方法名                #root.methodName
     * method      当前方法                  #root.method.name
     * target      当前被调用的对象           #root.target
     * targetClass 当前被调用的对象的class     #root.targetClass
     * args        当前方法参数组成的数组       #root.args[0]
     * caches      当前被调用的方法使用的Cache  #root.caches[0].name
     * 默认策略
     * 默认的key生成策略是通过KeyGenerator生成的，其默认策略如下：
     * 如果方法没有参数，则使用0作为key。
     * 如果只有一个参数的话则使用该参数作为key。
     * 如果参数多余一个的话则使用所有参数的hashCode作为key。
     * <p>
     * condition属性指定发生的条件
     * @CachePut 也可以声明一个方法支持缓存功能, (每次都会执行方法 ， 并将结果存入指定的缓存中)
     * @CacheEvict 是用来标注在需要清除缓存元素的方法或类上的
     * @Caching 注解可以在一个方法或者类上同时指定多个Spring Cache相关的注解
     * 其拥有三个属性：cacheable、put和evict，分别用于指定@Cacheable、@CachePut和@CacheEvict
     * 例如：@Caching(cacheable = @Cacheable("users"), evict = { @CacheEvict("cache2"),@CacheEvict(value = "cache3",
     * allEntries = true) })
     */
    @Cacheable(value = "test", keyGenerator = "myKeyGenerator", unless = "#result eq null")
    @Override
    public ReturnBody<List<UserBean>> testConnect() throws GlobalException {
        List<UserBean> userBeans = null;
        try {
            userBeans = testMapper.selectUser(null, null, null, null);
        } catch (Exception e) {
            throw new GlobalException(ReturnMsgImpl.SQL_EXCU_ERROR);
        }
        return new ReturnBody<>(ReturnMsgImpl.SUCCESS, userBeans);
    }

    /**
     * @CacheEvict 是用来标注在需要清除缓存元素的方法或类上的
     * allEntries 是boolean类型，表示是否需要清除缓存中的所有元素
     */
    @CacheEvict(value = "users", allEntries = true)
    @Override
    public ReturnBody addUser(UserBean userBean) throws GlobalException {
        try {
            int flag = testMapper.addUser(userBean);
            if (flag == 0)
                throw new GlobalException(ReturnMsgImpl.USER_ADD_FAIL);
        } catch (Exception e) {
            throw new GlobalException(ReturnMsgImpl.SQL_EXCU_ERROR);
        }
        return new ReturnBody(ReturnMsgImpl.SUCCESS);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public ReturnBody deleteUser(String userID) throws GlobalException {
        try {
            int flag = testMapper.deleteUserById(userID);
            if (flag == 0)
                throw new GlobalException(ReturnMsgImpl.USER_DELETE_FAIL);
        } catch (Exception e) {
            throw new GlobalException(ReturnMsgImpl.SQL_EXCU_ERROR);
        }
        return new ReturnBody(ReturnMsgImpl.SUCCESS);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public ReturnBody updateUser(UserBean userBean) throws GlobalException {
        try {
            int flag = testMapper.updateUser(userBean);
            if (flag == 0)
                throw new GlobalException(ReturnMsgImpl.USER_UPDATE_FAIL);
        } catch (Exception e) {
            throw new GlobalException(ReturnMsgImpl.SQL_EXCU_ERROR);
        }
        return new ReturnBody(ReturnMsgImpl.SUCCESS);
    }

    @Cacheable(value = "users", unless = "#result eq null")
    @Override
    public ReturnBody<List<UserBean>> getUserList(String name) throws GlobalException {
        List<UserBean> userBeans = null;
        if (StringUtils.isEmpty(name))
            name = null;
        try {
            userBeans = testMapper.getUserList(name);
        } catch (Exception e) {
            throw new GlobalException(ReturnMsgImpl.SQL_EXCU_ERROR);
        }
        return new ReturnBody<>(ReturnMsgImpl.SUCCESS, userBeans);
    }

    @Override
    public ReturnBody selectUser(String userId, String name, String sex, String addTime) throws GlobalException {
        if (StringUtils.isEmpty(userId))
            userId = null;
        if (StringUtils.isEmpty(name))
            name = null;
        if (StringUtils.isEmpty(sex))
            sex = null;
        if (StringUtils.isEmpty(addTime))
            addTime = null;
        List<UserBean> userBeans = null;
        Map<String, Object> map = new HashMap<>();
        try {
            userBeans = testMapper.selectUser(userId, name, sex, addTime);
            map.put("userList", userBeans);
        } catch (Exception e) {
            throw new GlobalException(ReturnMsgImpl.SQL_EXCU_ERROR);
        }

        return new ReturnBody<>(ReturnMsgImpl.SUCCESS, map);
    }

}
