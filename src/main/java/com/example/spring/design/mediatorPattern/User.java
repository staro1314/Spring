package com.example.spring.design.mediatorPattern;

import lombok.Data;

/**
 * @author: Staro
 * @date: 2019/12/14 17:03
 * @Description:
 */
@Data
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public void sendMessage(String message) {
        ChatRoom.showMessage(this, message);
    }
}
