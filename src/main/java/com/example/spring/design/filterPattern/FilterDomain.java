package com.example.spring.design.filterPattern;


import com.example.spring.design.filterPattern.criteria.*;
import com.example.spring.design.filterPattern.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Staro
 * @date: 2019/12/5 14:18
 * @Description: 过滤器模式
 */
public class FilterDomain {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<Person>();

        persons.add(new Person("Robert","Male", "Single"));
        persons.add(new Person("John","Male", "Married"));
        persons.add(new Person("Laura","Female", "Married"));
        persons.add(new Person("Diana","Female", "Single"));
        persons.add(new Person("Mike","Male", "Single"));
        persons.add(new Person("Bobby","Male", "Single"));

        Criteria male = new CriteriaMale();
        Criteria female = new CriteriaFemale();
        Criteria single = new CriteriaSingle();
        Criteria singleMale = new AndCriteria(single, male);
        Criteria singleOrFemale = new OrCriteria(single, female);

        System.out.println("Males: ");
        male.meetCriteria(persons).forEach(person-> System.out.println("Person : [ Name : " + person.getName()
                +", Gender : " + person.getGender()
                +", Marital Status : " + person.getMaritalStatus()
                +" ]"));

        System.out.println("\nFemales: ");
        female.meetCriteria(persons).forEach(person-> System.out.println("Person : [ Name : " + person.getName()
                +", Gender : " + person.getGender()
                +", Marital Status : " + person.getMaritalStatus()
                +" ]"));

        System.out.println("\nSingle Males: ");
        singleMale.meetCriteria(persons).forEach(person-> System.out.println("Person : [ Name : " + person.getName()
                +", Gender : " + person.getGender()
                +", Marital Status : " + person.getMaritalStatus()
                +" ]"));

        System.out.println("\nSingle Or Females: ");
        singleOrFemale.meetCriteria(persons).forEach(person-> System.out.println("Person : [ Name : " + person.getName()
                +", Gender : " + person.getGender()
                +", Marital Status : " + person.getMaritalStatus()
                +" ]"));
    }
}
