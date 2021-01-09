package com.nom1fan.logger;

import com.nom1fan.logger.appender.ConsoleAppender;
import com.nom1fan.logger.appender.LogAppender;

final class AppenderFactory {

    private AppenderFactory() {

    }

    public static LogAppender getDefaultAppender() {
        return new ConsoleAppender();
    }

}
