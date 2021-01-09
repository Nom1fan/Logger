package com.nom1fan.logger.appender;

import com.nom1fan.logger.LogRecord;

import java.text.SimpleDateFormat;

public interface LogAppender {

    String DEFAULT_PATTERN = "[%s] [%s] [%s] [%s] [%s]";

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    void append(LogRecord logRecord);
}
