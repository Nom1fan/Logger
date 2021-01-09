package com.nom1fan.logger;

import com.nom1fan.logger.appender.LogAppender;
import com.nom1fan.logger.config.LoggerConfig;
import com.nom1fan.logger.config.LoggingConfiguration;
import com.nom1fan.logger.config.MonitorConfiguration;
import com.nom1fan.logger.monitor.LogMonitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class LoggerFactory {

    private static final Map<String, Logger> loggerMap;
    private static final Map<String, LoggerConfig> name2LoggerConfigMap;
    private static final LogAppender defaultLogAppender;
    private static final List<LogMonitor> logMonitors;
    private static final LoggingQueue loggingQueue;

    static {
        loggerMap = new HashMap<>();
        name2LoggerConfigMap = LoggingConfiguration.getLoggersConfig();
        defaultLogAppender = AppenderFactory.getDefaultAppender();
        logMonitors = MonitorConfiguration.getLogMonitors();
        loggingQueue = new LoggingQueue();
    }

    private LoggerFactory() {
    }

    public static Logger getLogger(Class<?> aClass) {
        return getLogger(aClass.getName());
    }

    public static Logger getLogger(String name) {

        if (loggerMap.containsKey(name)){
            return loggerMap.get(name);
        }
        if (name2LoggerConfigMap.containsKey(name)) {
            LogAppender logAppender = name2LoggerConfigMap.get(name).getLogAppender();
            Logger logger = new SimpleLogger(name, logAppender, MonitorConfiguration.getLogMonitors(), loggingQueue);
            loggerMap.put(name, logger);
            return logger;
        }
        Logger logger = new SimpleLogger(name, defaultLogAppender, logMonitors, loggingQueue);
        loggerMap.put(name, logger);
        return logger;
    }
}
