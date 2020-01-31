package com.nom1fan.logger;

import com.nom1fan.logger.config.MonitorConfiguration;
import org.junit.jupiter.api.Test;

class LoggerFactoryTest {

    // Half manual e2e test. Output should include the error alert
    @Test
    public void getNonExistingLogger_DefaultConsoleAppenderUsed() {
        Logger nonExistingLogger = LoggerFactory.getLogger("NonExistingLogger");
        for (int i = 0; i < MonitorConfiguration.getLogLevelErrorThreshold(); i++) {
            nonExistingLogger.error("error message");
        }

        //TODO Add assertion on console output for log + alert
    }

    // Half manual e2e test. Output should include the error alert
    @Test
    public void getExistingLogger_CorrectAppenderUsed_AsDefinedInLoggingXml() {
        Logger existingLogger = LoggerFactory.getLogger("com.nom1fan.logger");
        for (int i = 0; i < MonitorConfiguration.getLogLevelErrorThreshold(); i++) {
            existingLogger.error("error message");
        }

        //TODO Add assertion on temp file for output + console for alert
    }

}