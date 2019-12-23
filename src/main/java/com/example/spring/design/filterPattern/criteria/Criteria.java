package com.example.spring.design.filterPattern.criteria;


import com.example.spring.design.filterPattern.model.Person;

import java.util.List;

/**
 * @author: Staro
 * @date: 2019/12/5 11:34
 * @Description:
 */
public interface Criteria {
    public List<Person> meetCriteria(List<Person> personList);
}
