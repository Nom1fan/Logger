package com.nom1fan.logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

final class LoggingQueue {

    private final BlockingQueue<LogRecord> queue = new ArrayBlockingQueue<>(1000, true);

    public LoggingQueue() {
        new Thread(this::processQueue).start();
    }

    void queueLog(LogRecord logRecord) {
        new Thread(() -> _queueLog(logRecord)).start();
    }

    private void processQueue() {
        while (true) {
            try {
                LogRecord logRecord = queue.take();
                logRecord.append();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    private void _queueLog(LogRecord logRecord) {
        try {
            queue.put(logRecord);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
