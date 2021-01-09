package com.nom1fan.logger;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class ThreadSafetyTest {

    // Half manual e2e test. Output should include a proper uncut log file
    @Test
    public void testWriteToLogWithXThreads() throws InterruptedException {
        Set<Thread> threads = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new LoggerRunner());
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }

    private static class LoggerRunner implements Runnable {
        private static final Logger logger = LoggerFactory.getLogger("com.nom1fan.logger");

        @Override
        public void run() {
            int secsToWork = new Random().nextInt(10);
            for (int i = 0; i < secsToWork; i++) {
                logger.info("This is a long message that should not be cut by the long message of other threads");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

        }
    }

}