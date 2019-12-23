package com.example.spring.design.builder.build;


import com.example.spring.design.builder.entry.Asus;
import com.example.spring.design.builder.entry.Computer;

/**
 * @author: Staro
 * @date: 2019/11/20 18:09
 * @Description:
 */
public class AsusBuilder extends Builder {
    private Computer computer = new Asus();

    @Override
    public Builder buildHost(String host) {
        computer.setHost(host);
        return this;
    }

    @Override
    public Builder buildMonitor(String monitor) {
        computer.setMonitor(monitor);
        return this;
    }

    @Override
    public Builder buildSysOS() {
        computer.setSysOS();
        return this;
    }

    @Override
    public Computer build() {
        return computer;
    }
}
