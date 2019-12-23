package com.example.spring.design.chainOfResponsibilityPattern.chain;

/**
 * @author: Staro
 * @date: 2019/12/7 14:44
 * @Description:
 */
public class ConsoleLogger extends AbstractLogger {
    public ConsoleLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}
