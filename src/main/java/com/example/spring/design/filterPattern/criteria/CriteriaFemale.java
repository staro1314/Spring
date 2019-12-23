package com.example.spring.design.filterPattern.criteria;


import com.example.spring.design.filterPattern.model.Person;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Staro
 * @date: 2019/12/5 11:40
 * @Description:
 */
public class CriteriaFemale implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> personList) {
        return personList
                .stream()
                .filter(bean -> "FEMALE".equalsIgnoreCase(bean.getGender()))
                .collect(Collectors.toList());
    }
}
