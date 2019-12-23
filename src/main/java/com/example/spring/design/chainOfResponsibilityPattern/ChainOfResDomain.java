package com.example.spring.design.chainOfResponsibilityPattern;


import com.example.spring.design.chainOfResponsibilityPattern.chain.AbstractLogger;
import com.example.spring.design.chainOfResponsibilityPattern.chain.ConsoleLogger;
import com.example.spring.design.chainOfResponsibilityPattern.chain.ErrorLogger;
import com.example.spring.design.chainOfResponsibilityPattern.chain.FileLogger;

/**
 * @author: Staro
 * @date: 2019/12/7 14:47
 * @Description: 责任链
 */
public class ChainOfResDomain {

    private static AbstractLogger getChainOfLoggers() {
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger consoleLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger fileLogger = new ConsoleLogger(AbstractLogger.INFO);
        errorLogger.setNextLogger(consoleLogger);
        consoleLogger.setNextLogger(fileLogger);
        return errorLogger;
    }

    public static void main(String[] args) {
        AbstractLogger chainOfLoggers = getChainOfLoggers();
        chainOfLoggers.logMessage(AbstractLogger.INFO, "This is an information.");
        chainOfLoggers.logMessage(AbstractLogger.DEBUG, "This is a debug level information.");
        chainOfLoggers.logMessage(AbstractLogger.ERROR, "This is an error information.");
    }
}
