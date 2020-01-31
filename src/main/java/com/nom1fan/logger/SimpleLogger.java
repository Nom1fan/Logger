package com.nom1fan.logger;

import com.nom1fan.logger.appender.LogAppender;
import com.nom1fan.logger.monitor.LogMonitor;

import java.beans.PropertyChangeEvent;
import java.util.Arrays;
import java.util.List;

public class SimpleLogger implements Logger {

    private final LogAppender logAppender;
    private final List<LogMonitor> logMonitors;
    private final String name;

    public SimpleLogger(String name, LogAppender logAppender, List<LogMonitor> logMonitors) {
        this.logAppender = logAppender;
        this.logMonitors = logMonitors;
        this.name = name;
    }

    public void info(String msg) {
        append(LogLevel.INFO, msg);
    }

    public void debug(String msg) {
        append(LogLevel.DEBUG, msg);
    }

    public void warn(String msg) {
        append(LogLevel.WARN, msg);
    }

    public void error(String msg) {
        append(LogLevel.ERROR, msg);
        LogAlert logAlert = new LogAlert(LogLevel.ERROR);
        logMonitors.forEach(logMonitor -> logMonitor.propertyChange(new PropertyChangeEvent(this, "Error Alert", null, logAlert)));
    }

    public void error(String msg, Throwable throwable) {
        append(LogLevel.ERROR, msg + ". Exception stacktrace:" + Arrays.toString(throwable.getStackTrace()));
        LogAlert logAlert = new LogAlert(LogLevel.ERROR);
        logMonitors.forEach(logMonitor -> logMonitor.propertyChange(new PropertyChangeEvent(this, "Error Alert", null, logAlert)));
    }

    private void append(LogLevel logLevel, String msg) {
        logAppender.append(Thread.currentThread().getName(), name, logLevel, msg);
    }
}
