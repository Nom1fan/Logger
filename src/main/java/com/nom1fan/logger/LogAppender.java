package com.nom1fan.logger;

public interface LogAppender {

    void append(String threadName, String name, LogLevel logLevel, String msg);
}
