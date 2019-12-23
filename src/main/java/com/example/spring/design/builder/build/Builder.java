package com.example.spring.design.builder.build;


import com.example.spring.design.builder.entry.Computer;

/**
 * @author: Staro
 * @date: 2019/11/20 17:57
 * @Description:
 */
public abstract class Builder {
    public abstract Builder buildHost(String host);
    public abstract Builder buildMonitor(String monitor);
    public abstract Builder buildSysOS();
    public abstract Computer build();
}
