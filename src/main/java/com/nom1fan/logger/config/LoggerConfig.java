package com.nom1fan.logger.config;

import com.nom1fan.logger.appender.LogAppender;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoggerConfig {

    private LogAppender logAppender;
}
