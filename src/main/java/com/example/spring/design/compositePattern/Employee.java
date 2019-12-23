package com.example.spring.design.compositePattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Staro
 * @date: 2019/12/5 15:15
 * @Description:
 */
public class Employee {
    private String name;
    private String dept;
    private long salary;
    private List<Employee> subordinates;

    public Employee(String name, String dept, long salary) {
        this.name = name;
        this.dept = dept;
        this.salary = salary;
        this.subordinates = new ArrayList<>();
    }

    public void add(Employee employee){
        subordinates.add(employee);
    }

    public void remove(Employee employee){
        subordinates.remove(employee);
    }

    public List<Employee> getSubordinates(){
        return subordinates;
    }

    @Override
    public String toString() {
        return ("Employee :[ Name : "+ name
                +", dept : "+ dept + ", salary :"
                + salary+" ]");
    }
}
