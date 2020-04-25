package com.danielszabo.app;

import com.danielszabo.app.util.LatencyUtil;
import com.danielszabo.app.util.LoggingUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

@WebServlet(urlPatterns = "/long-async-request", asyncSupported = true)
public class LongRunningAsyncServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LoggingUtil.prettyPrintMessage("Entered the [/long-async-request] controller.");
        int timeoutMilliseconds = Integer.parseInt(request.getParameter("timeout"));

        var asyncContext = request.startAsync();
        var threadPool = (ExecutorService) request.getServletContext().getAttribute("webappThreadPool");
        threadPool.execute(() -> {
            var latencySimulationMessage = LatencyUtil.simulateLatency(timeoutMilliseconds);

            var asyncResponse = (HttpServletResponse) asyncContext.getResponse();
            try {
                asyncResponse.getWriter().print(latencySimulationMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            asyncContext.complete();
        });

        LoggingUtil.prettyPrintMessage("Finished processing in the [/long-async-request] controller.");
    }
}
