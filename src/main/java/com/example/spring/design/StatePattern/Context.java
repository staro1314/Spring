package com.example.spring.design.StatePattern;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: Staro
 * @date: 2019/12/26 10:16
 * @Description:
 */
@Setter
@Getter
public class Context {
    private State state;

    public Context() {
        this.state = null;
    }
}
