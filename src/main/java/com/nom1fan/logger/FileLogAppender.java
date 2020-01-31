package com.nom1fan.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileLogAppender implements LogAppender {

    private static final String DEFAULT_PATTERN = "[%s] [%s] [%s] [%s]";
    private final BufferedWriter bufferedWriter;

    public FileLogAppender() throws IOException {
        File logFile = File.createTempFile("application", ".log");
        bufferedWriter = Files.newBufferedWriter(Paths.get(logFile.getPath()));
    }

    public void append(String threadName, String name, LogLevel logLevel, String msg) {
        String logMsg = String.format(DEFAULT_PATTERN, threadName, name, logLevel.toString(), msg);
        try {
            bufferedWriter.write(logMsg);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
