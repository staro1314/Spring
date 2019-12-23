package com.example.spring.design.builder;


import com.example.spring.design.builder.build.AsusBuilder;
import com.example.spring.design.builder.build.MacBookBuilder;
import com.example.spring.design.builder.entry.Computer;

/**
 * @author: Staro
 * @date: 2019/11/20 18:04
 * @Description: 建造者
 */
public class BuilderMain {

    public static void main(String[] args) {
        Computer computer=new MacBookBuilder()
                .buildHost("没有主机")
                .buildMonitor("三星显示器")
                .buildSysOS()
                .build();
        System.out.println(computer);

        computer=new AsusBuilder()
                .buildHost("没有主机")
                .buildMonitor("三星显示器")
                .buildSysOS()
                .build();
        System.out.println(computer);
    }
}
