package com.example.spring.design.builder.build;

import com.example.spring.design.builder.entry.Computer;
import com.example.spring.design.builder.entry.MacBook;

/**
 * @author: Staro
 * @date: 2019/11/20 17:59
 * @Description:
 */
public class MacBookBuilder extends Builder {
    Computer macBook = new MacBook();

    @Override
    public Builder buildHost(String host) {
        macBook.setHost(host);
        return this;
    }

    @Override
    public Builder buildMonitor(String monitor) {
        macBook.setMonitor(monitor);
        return this;
    }

    @Override
    public Builder buildSysOS() {
        macBook.setSysOS();
        return this;
    }

    @Override
    public Computer build() {
        return macBook;
    }
}
