package com.nom1fan.logger.config;

import com.nom1fan.logger.appender.LogAppender;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class LoggingConfiguration {

    private LoggingConfiguration() {
    }

    public static Map<String, LoggerConfig> getLoggersConfig() {
        try {
            Map<String, LoggerConfig> name2LoggerConfigMap = new HashMap<>();
            InputStream resource = LoggingConfiguration.class.getResourceAsStream("/logging.xml");
            SAXReader saxReader = new SAXReader();
            Document loggingConfigXml = saxReader.read(resource);
            List<Node> loggers = loggingConfigXml.selectNodes("/configuration/logger");
            for (Node loggerNode : loggers) {
                String name = ((DefaultElement) loggerNode).attribute("name").getValue();
                DefaultElement appenderNode = (DefaultElement) loggerNode.selectNodes("logAppender").get(0);
                Class<LogAppender> appenderClass = (Class<LogAppender>) Class.forName(appenderNode.getText());
                LogAppender logAppender = appenderClass.getConstructor().newInstance();
                name2LoggerConfigMap.put(name, new LoggerConfig(logAppender));
            }
            return name2LoggerConfigMap;
        } catch (ClassNotFoundException | DocumentException | NoSuchMethodException
                | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to instantiate loggers by configuration", e);
        }
    }
}
