package com.nom1fan.logger;

import com.nom1fan.logger.config.LoggingConfiguration;
import com.nom1fan.logger.config.MonitorConfiguration;
import com.nom1fan.logger.monitor.LogMonitor;
import org.dom4j.DocumentException;

import java.util.List;
import java.util.Map;

public final class LoggerFactory {

    private static final Map<String, Logger> name2LoggerMap;
    private static final LogAppender defaultLogAppender;
    private static final List<LogMonitor> logMonitors;

    static {
        try {
            name2LoggerMap = LoggingConfiguration.getLoggers();
            defaultLogAppender = LoggingConfiguration.getDefaultAppender();
            logMonitors = MonitorConfiguration.getLogMonitors();
        } catch (DocumentException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to instantiate loggers by configuration");
        }
    }

    private LoggerFactory() {
    }

    public static Logger getLogger(Class<?> aClass) {
        return getLogger(aClass.getName());
    }

    public static Logger getLogger(String name) {
        if (name2LoggerMap.containsKey(name)) {
            return name2LoggerMap.get(name);
        }

        Logger log = new SimpleLogger(name, defaultLogAppender, logMonitors);
        name2LoggerMap.put(name, log);

        return log;
    }
}
