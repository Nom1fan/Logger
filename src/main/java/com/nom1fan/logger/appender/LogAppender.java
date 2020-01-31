package com.nom1fan.logger.appender;

import com.nom1fan.logger.LogLevel;

public interface LogAppender {

    void append(String threadName, String name, LogLevel logLevel, String msg);
}
