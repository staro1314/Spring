package com.example.spring.design.Single;

/**
 * @author: Staro
 * @date: 2019/11/23 13:45
 * @Description:
 */
public class SingleObject {

    private static SingleObject singleObject = new SingleObject();

    public static SingleObject getInstant() {
        return singleObject;
    }

    public void execute(){
        System.out.println("singleObject");
    }
}
