package com.example.spring.design.chainOfResponsibilityPattern.chain;

/**
 * @author: Staro
 * @date: 2019/12/7 14:45
 * @Description:
 */
public class ErrorLogger extends AbstractLogger {
    public ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}
