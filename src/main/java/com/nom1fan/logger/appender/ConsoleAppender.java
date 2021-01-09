package com.nom1fan.logger.appender;

import com.nom1fan.logger.LogRecord;

import java.util.Date;

public class ConsoleAppender implements LogAppender {

    @Override
    public void append(LogRecord logRecord) {
        String logMsg = String.format(DEFAULT_PATTERN, sdf.format(new Date()), logRecord.getLogThreadName(),
                logRecord.getLoggerName(), logRecord.getLogLevel().toString(), logRecord.getMsg());
        System.out.println(logMsg);
    }
}
