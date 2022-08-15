package com.danielszabo.app;

import com.danielszabo.app.util.LatencyUtil;
import com.danielszabo.app.util.LoggingUtil;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/long-request")
public class LongRunningServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LoggingUtil.prettyPrintMessage("Entered the [/long-request] controller.");
        String timeout = request.getParameter("timeout");
        var session = request.getSession();
        var hitCounter = (AtomicInteger) session.getAttribute("hitCounter");

        if (hitCounter == null) {
            hitCounter = new AtomicInteger(1);
            session.setAttribute("hitCounter", hitCounter);
        } else {
            hitCounter.incrementAndGet();
        }

        if (timeout == null || timeout.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The mandatory [timeout] request parameter is missing.");
            LoggingUtil.prettyPrintMessage("The timeout parameter was missing. Request processing ends early.");
            return;
        }

        int timeoutMilliseconds;
        try {
            timeoutMilliseconds = Integer.parseInt(timeout);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The [timeout] parameter value was not a valid integer.");
            LoggingUtil.prettyPrintMessage("The timeout parameter was invalid. Request processing ends early.");
            return;
        }

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(LatencyUtil.simulateLatency(timeoutMilliseconds));
        response.getWriter().println(String.format("Request count in this session is [%d].", hitCounter.get()));
        response.getWriter().println(String.format("Is new session: [%s]", session.isNew()));
        LoggingUtil.prettyPrintMessage("Finished processing in the [/long-request] controller.");
    }
}
