package com.example.spring.design.filterPattern.criteria;


import com.example.spring.design.filterPattern.model.Person;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Staro
 * @date: 2019/12/5 11:42
 * @Description:
 */
public class CriteriaSingle implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> personList) {
        return personList
                .stream()
                .filter(bean -> "SINGLE".equalsIgnoreCase(bean.getMaritalStatus()))
                .collect(Collectors.toList());
    }
}
