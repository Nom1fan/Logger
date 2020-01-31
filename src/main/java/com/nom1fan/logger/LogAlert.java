package com.nom1fan.logger;

import com.nom1fan.logger.monitor.Alert;

public class LogAlert implements Alert<LogLevel> {

    private final LogLevel logLevel;
    private Long threshold;

    public LogAlert(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public LogLevel getAlertObject() {
        return logLevel;
    }

    public void setThreshold(Long threshold) {
        this.threshold = threshold;
    }

    @Override
    public String toString() {
        return "Log level: " + logLevel + " threshold: " + threshold + " has been reached";
    }
}
