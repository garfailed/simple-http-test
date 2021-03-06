package com.danielszabo.app;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet(urlPatterns = "/ajax")
public class AjaxResponseController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
