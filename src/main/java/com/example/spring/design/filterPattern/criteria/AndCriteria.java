package com.example.spring.design.filterPattern.criteria;


import com.example.spring.design.filterPattern.model.Person;

import java.util.List;

/**
 * @author: Staro
 * @date: 2019/12/5 11:55
 * @Description:
 */
public class AndCriteria implements Criteria {
    private Criteria criteria;
    private Criteria otherCriteria;

    public AndCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Person> meetCriteria(List<Person> personList) {
        List<Person> people = criteria.meetCriteria(personList);
        return otherCriteria.meetCriteria(people);
    }
}
