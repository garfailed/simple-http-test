package com.danielszabo.app.util;

public class LatencyUtil {

    public static String simulateLatency(int timeoutMilliseconds) {
        try {
            LoggingUtil.prettyPrintMessage(String.format("This thread will sleep for [%d] milliseconds.", timeoutMilliseconds));

            Thread.sleep(timeoutMilliseconds);

            LoggingUtil.prettyPrintMessage("Thread sleep finished.");

            return String.format("This request waited for %d milliseconds.", timeoutMilliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "The thread was interrupted while sleeping!";
        }
    }
}
