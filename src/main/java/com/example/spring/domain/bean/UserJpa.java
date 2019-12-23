package com.example.spring.domain.bean;

import lombok.Data;

import javax.persistence.*;

/**
 * @author: Staro
 * @date: 2019/7/511:48
 * @Description:
 */
@Data
@Entity
@Table(name = "userJap")
public class UserJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String sex;

    public UserJpa() {
    }

    public UserJpa(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }
}
