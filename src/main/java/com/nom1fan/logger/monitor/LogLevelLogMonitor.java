package com.nom1fan.logger.monitor;

import com.nom1fan.logger.LogAlert;
import com.nom1fan.logger.LogLevel;
import com.nom1fan.logger.config.MonitorConfiguration;

import java.beans.PropertyChangeEvent;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class LogLevelLogMonitor implements LogMonitor {

    private static final Map<LogLevel, AtomicLong> logLevelThresholdMap;
    private static final Map<LogLevel, AtomicLong> logLevelCountMap = new ConcurrentHashMap<>();

    static {
        logLevelThresholdMap = MonitorConfiguration.getLevelThresholdMap();
    }

    private final MonitorService monitorService;

    public LogLevelLogMonitor(MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LogAlert logAlert = (LogAlert) evt.getNewValue();
        LogLevel logLevel = logAlert.getAlertObject();

        long logLevelCount = logLevelCountMap.getOrDefault(logLevel, new AtomicLong(0)).addAndGet(1);
        long logLevelThreshold = logLevelThresholdMap.get(logLevel).longValue();
        if (logLevelThreshold == logLevelCount) {
            logLevelCountMap.put(logLevel, new AtomicLong(0));
            logAlert.setThreshold(logLevelThreshold);
            monitorService.sendAlert(logAlert);
        } else {
            logLevelCountMap.put(logLevel, new AtomicLong(logLevelCount));
        }
    }
}
