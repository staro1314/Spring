package com.example.spring.design.filterPattern.model;

import lombok.Data;

/**
 * @author: Staro
 * @date: 2019/12/5 11:35
 * @Description:
 */
@Data
public class Person {
    private String name;
    private String gender;
    private String maritalStatus;

    public Person(String name, String gender, String maritalStatus) {
        this.name = name;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
    }
}
