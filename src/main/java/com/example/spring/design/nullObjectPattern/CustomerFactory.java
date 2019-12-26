package com.example.spring.design.nullObjectPattern;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: Staro
 * @date: 2019/12/26 11:24
 * @Description:
 */
public class CustomerFactory {
    private static final String[] names = {"Rob", "Joe", "Julie"};

    static AbstractCustomer getCustomer(String name) {
        List<String> collect = Arrays.stream(names)
                .filter(name1 -> Objects.equals(name, name1))
                .collect(Collectors.toList());
        if (collect.size()>0)
            return new RealCustomer(name);
        else
            return new NullCustomer(name);
    }
}
