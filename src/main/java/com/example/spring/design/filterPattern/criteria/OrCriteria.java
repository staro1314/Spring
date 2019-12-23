package com.example.spring.design.filterPattern.criteria;

import com.example.spring.design.filterPattern.model.Person;
import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author: Staro
 * @date: 2019/12/5 11:57
 * @Description:
 */
public class OrCriteria implements Criteria{
    private Criteria criteria;
    private Criteria otherCriteria;

    public OrCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Person> meetCriteria(List<Person> personList) {
        List<Person> peoples = criteria.meetCriteria(personList);
        List<Person> meetCriteria = otherCriteria.meetCriteria(personList);
        List<Person> union = ListUtils.union(peoples, meetCriteria);
        HashSet<Person> set=new HashSet<>(union);
        return new ArrayList<>(set);
    }
}
