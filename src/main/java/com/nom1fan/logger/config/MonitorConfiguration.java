package com.nom1fan.logger.config;

import com.nom1fan.logger.LogLevel;
import com.nom1fan.logger.monitor.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public final class MonitorConfiguration {

    private static final String DEFAULT_MONITOR_SERVICE_ENDPOINT = "http://monitor.service.com:80";
    private static final String MONITOR_SERVICE_KEY = "monitor.service.endpoint";
    private static final Map<LogLevel, AtomicLong> LogLevelThresholdMap = new ConcurrentHashMap<>();
    private static final Properties properties;

    static {
        InputStream propertiesStream = LoggingConfiguration.class.getResourceAsStream("/application.properties");
        properties = new Properties();
        try {
            properties.load(propertiesStream);
            LogLevelThresholdMap.put(LogLevel.ERROR, new AtomicLong(getLogLevelErrorThreshold()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static long getLogLevelErrorThreshold() {
        return Long.parseLong(properties.getProperty("log.level.error.threshold"));
    }

    public static List<LogMonitor> getLogMonitors() {
        List<LogMonitor> logMonitors = new ArrayList<>();
        logMonitors.add(getErrorLogLevelLogMonitor());
        return logMonitors;
    }

    public static String getMonitorServiceEndpoint() throws IOException {


        if (!properties.contains(MONITOR_SERVICE_KEY)) {
            return DEFAULT_MONITOR_SERVICE_ENDPOINT;
        }

        return properties.getProperty(MONITOR_SERVICE_KEY);
    }

    public static Map<LogLevel, AtomicLong> getLevelThresholdMap() {
        return LogLevelThresholdMap;
    }

    private static LogMonitor getErrorLogLevelLogMonitor() {
        return new LogLevelLogMonitor(getMonitorService());
    }

    private static MonitorService getMonitorService() {
        return new MonitorServiceImpl(getMonitorClient());
    }

    private static MonitorClient getMonitorClient() {
        return new MonitorClientImpl(new RestTemplate());
    }
}
