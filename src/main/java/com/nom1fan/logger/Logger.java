package com.nom1fan.logger;

public interface Logger {

    void info(String msg);

    void debug(String msg);

    void warn(String msg);

    void error(String msg);

    void error(String msg, Throwable throwable);
}
