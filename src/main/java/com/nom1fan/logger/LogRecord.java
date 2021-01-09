package com.nom1fan.logger;

import com.nom1fan.logger.appender.LogAppender;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogRecord {

    private LogAppender logAppender;
    private String logThreadName;
    private String loggerName;
    private LogLevel logLevel;
    private String msg;

    void append() {
        logAppender.append(this);
    }
}
