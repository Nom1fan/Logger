package com.nom1fan.logger.config;

import com.nom1fan.logger.ConsoleAppender;
import com.nom1fan.logger.LogAppender;
import com.nom1fan.logger.Logger;
import com.nom1fan.logger.SimpleLogger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class LoggingConfiguration {

    private LoggingConfiguration() {
    }

    public static Map<String, Logger> getLoggers() throws DocumentException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Map<String, Logger> name2LoggerMap = new HashMap<>();
        InputStream resource = LoggingConfiguration.class.getResourceAsStream("/logging.xml");
        SAXReader saxReader = new SAXReader();

        Document loggingConfigXml = saxReader.read(resource);
        List<Node> loggers = loggingConfigXml.selectNodes("/configuration/logger");
        for (Node loggerNode : loggers) {
            String name = ((DefaultElement) loggerNode).attribute("name").getValue();
            DefaultElement appenderNode = (DefaultElement) loggerNode.selectNodes("logAppender").get(0);
            Class<LogAppender> appenderClass = (Class<LogAppender>) Class.forName(appenderNode.getText());
            LogAppender logAppender = appenderClass.newInstance();
            Logger logger = new SimpleLogger(name, logAppender, MonitorConfiguration.getLogMonitors());
            name2LoggerMap.put(name, logger);
        }

        return name2LoggerMap;
    }

    public static LogAppender getDefaultAppender() {
        return new ConsoleAppender();
    }
}
