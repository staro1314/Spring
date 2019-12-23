package com.example.spring.domain.jpaRepo;

import com.example.spring.domain.bean.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: Staro
 * @date: 2019/7/512:00
 * @Description:
 */
public interface UserRepository extends JpaRepository<UserJpa,Long> {
    List<UserJpa> findByNameLike(String name);
}

