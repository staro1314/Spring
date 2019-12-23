package com.example.spring.design.compositePattern;

import java.util.List;

/**
 * @author: Staro
 * @date: 2019/12/5 15:19
 * @Description: 组合模式
 */
public class CompositeDomain {
    public static void main(String[] args) {
        Employee CEO = new Employee("John", "CEO", 300000);
        Employee headSales = new Employee("Robert", "Header Sales", 20000);
        Employee headMarkets = new Employee("Michel", "Header Marketing", 20000);
        Employee salesExecutive1 = new Employee("Richard", "Sales", 10000);
        Employee salesExecutive2 = new Employee("Rob", "Sales", 10000);
        Employee clerk1 = new Employee("Laura", "Marketing", 10000);
        Employee clerk2 = new Employee("Sob", "Marketing", 10000);
        CEO.add(headMarkets);
        CEO.add(headSales);
        headSales.add(salesExecutive1);
        headSales.add(salesExecutive2);
        headMarkets.add(clerk1);
        headMarkets.add(clerk2);
        print(CEO);
    }

    private static void print(Employee employee) {
        List<Employee> subordinates = employee.getSubordinates();
        System.out.println(employee);
        if (subordinates.size() > 0) {
            subordinates.forEach(CompositeDomain::print);
        }
    }
}
