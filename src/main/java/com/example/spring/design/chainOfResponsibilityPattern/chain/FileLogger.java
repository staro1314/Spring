package com.example.spring.design.chainOfResponsibilityPattern.chain;

/**
 * @author: Staro
 * @date: 2019/12/7 14:46
 * @Description:
 */
public class FileLogger extends AbstractLogger {
    public FileLogger(int level) {
        this.level=level;
    }
    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}
