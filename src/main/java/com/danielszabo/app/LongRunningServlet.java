package com.danielszabo.app;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/long-request", asyncSupported = true)
public class LongRunningServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String timeout = request.getParameter("timeout");

        if (timeout == null || timeout.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The mandatory [timeout] request parameter is missing.");
            return;
        }

        int timeoutMilliseconds;
        try {
            timeoutMilliseconds = Integer.parseInt(timeout);
        } catch (NumberFormatException e) {
            System.err.println(String.format("The user sent an invalid timeout value [%s].", timeout));
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The [timeout] parameter value was not a valid integer.");
            return;
        }
        try {
            Thread.sleep(timeoutMilliseconds);
            response.getWriter().print(String.format("This request waited for %s milliseconds.", timeoutMilliseconds));
        } catch (InterruptedException e) {
            System.err.println("Somehow this sleeping thread has been interrupted." + e.getMessage());
        }
    }
}
