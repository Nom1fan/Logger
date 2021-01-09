package com.nom1fan.logger.appender;

import com.nom1fan.logger.LogLevel;
import com.nom1fan.logger.LogRecord;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.Date;
import java.util.TimeZone;

public class FileLogAppender implements LogAppender {

    private final BufferedWriter bufferedWriter;

    public FileLogAppender() throws IOException {
        File logFile = File.createTempFile("application", ".log");
        bufferedWriter = Files.newBufferedWriter(Paths.get(logFile.getPath()));
    }
    public void append(LogRecord logRecord) {
        String logMsg = String.format(DEFAULT_PATTERN, sdf.format(new Date()), logRecord.getLogThreadName(),
                logRecord.getLoggerName(), logRecord.getLogLevel().toString(), logRecord.getMsg());
        try {
            bufferedWriter.write(logMsg);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
