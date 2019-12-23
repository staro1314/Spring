package com.example.spring.design.mediatorPattern;

/**
 * @author: Staro
 * @date: 2019/12/14 17:06
 * @Description:
 */
public class MediatorDomain {
    public static void main(String[] args) {
        User robert=new User("robert");
        User john=new User("john");
        robert.sendMessage("Hi john");
        john.sendMessage("Hi robert");
    }
}
