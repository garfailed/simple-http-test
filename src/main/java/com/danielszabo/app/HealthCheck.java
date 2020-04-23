package com.danielszabo.app;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/health-check")
public class HealthCheck extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Hello World3 from get servlet.");
    }
}
