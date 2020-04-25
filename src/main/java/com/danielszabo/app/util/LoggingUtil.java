package com.danielszabo.app.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LoggingUtil {

    public static void prettyPrintMessage(String message) {
        var extendedMessage = "[" +
                LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS")) +
                " - " +
                Thread.currentThread().getName() +
                "] " +
                message;
        System.out.println(extendedMessage);
    }
}
