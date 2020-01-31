package com.nom1fan.logger.appender;

import com.nom1fan.logger.LogLevel;

public class ConsoleAppender implements LogAppender {

    private static final String DEFAULT_PATTERN = "[%s] [%s] [%s] [%s]";

    @Override
    public void append(String threadName, String name, LogLevel logLevel, String msg) {
        String logMsg = String.format(DEFAULT_PATTERN, threadName, name, logLevel.toString(), msg);
        System.out.println(logMsg);
    }
}
