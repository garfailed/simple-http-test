package com.danielszabo.app.configuration;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@WebListener
public class ThreadPoolConfiguration implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ExecutorService threadPool = Executors.newFixedThreadPool(15);
        sce.getServletContext().setAttribute("webappThreadPool", threadPool);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        var threadPool = (ExecutorService) sce.getServletContext().getAttribute("webappThreadPool");
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
