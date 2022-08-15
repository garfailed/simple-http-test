package com.danielszabo.app;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.manager.JMXProxyServlet;
import org.apache.juli.ClassLoaderLogManager;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/ajax")
public class AjaxResponseController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var servletContext = req.getServletContext();
        var logManager = ClassLoaderLogManager.getLogManager();
        Logger logger = logManager.getLogger(AjaxResponseController.class.getName());
        logger.log(Level.INFO, "Hello World!");

        var name = req.getParameter("name");

        Properties properties = new Properties();
        properties.load(this.getClass().getResourceAsStream("/application.properties"));
        String serverMessage = properties.getProperty("serverMessage");
        String responseMessage = String.format(serverMessage, name);

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain");
        resp.getWriter().println(responseMessage);
    }
}
