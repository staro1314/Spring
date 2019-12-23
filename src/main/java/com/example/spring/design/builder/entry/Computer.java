package com.example.spring.design.builder.entry;

import lombok.Setter;
import lombok.ToString;

/**
 * @author: Staro
 * @date: 2019/11/20 17:48
 * @Description:
 */
@ToString
public abstract class Computer {
    @Setter
    private String host;
    @Setter
    private String monitor;
    public String sysOS;
    public abstract void setSysOS();
}
