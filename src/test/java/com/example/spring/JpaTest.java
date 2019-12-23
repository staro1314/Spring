package com.example.spring;

import com.example.spring.domain.bean.UserJpa;
import com.example.spring.domain.jpaRepo.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author: Staro
 * @date: 2019/7/514:29
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JpaTest {
    @Resource
    private UserRepository userRepository;

    @Test
    public void save() {
        for (int i = 0; i < 10; i++) {
            UserJpa userJpa=new UserJpa("jap"+i,i%2==0?"男":"女");
            userRepository.save(userJpa);
        }
    }
}
