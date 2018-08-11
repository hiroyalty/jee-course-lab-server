package nl.hva.jeecourse.servlet;

import nl.hva.jeecourse.servlet.cors.CorsServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("api/custom-about")
public class Lab2AboutServlet extends CorsServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        out.write("<b>Updated by [participant] during the summer course of 2018 at the Metropolia University of " +
                "Applied Sciences<b>");

    }
}