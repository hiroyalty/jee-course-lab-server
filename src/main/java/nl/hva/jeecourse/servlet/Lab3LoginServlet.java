package nl.hva.jeecourse.servlet;


import nl.hva.jeecourse.repository.Repository;
import nl.hva.jeecourse.servlet.cors.CorsServlet;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("api/auth/login")
public class Lab3LoginServlet extends CorsServlet {

    @Inject
    private Repository repo;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        boolean valid = repo.isValidUser(email,password);

        PrintWriter out = resp.getWriter();

        resp.setContentType("application/json");

        addEmailCookie(email,req,resp);
        incrementVisits(req,resp);

        out.print(valid);

    }

    private void addEmailCookie(String email, HttpServletRequest req, HttpServletResponse resp) {

        Cookie mailCookie = new Cookie("email",email);
        mailCookie.setPath("/");
        mailCookie.setMaxAge(60 * 60 * 24); // 1 day

        resp.addCookie(mailCookie);
    }

    private void incrementVisits(HttpServletRequest req, HttpServletResponse resp) {

        int visits = getIntCookie("visits",req);

        visits ++;

        Cookie visitCookie = new Cookie("visits",Integer.toString(visits));
        visitCookie.setPath("/");
        visitCookie.setMaxAge(60*60*24);

        resp.addCookie(visitCookie);
    }

    private int getIntCookie(String key, HttpServletRequest req ) {
        Cookie cookies [] = req.getCookies();

        for(Cookie cookie : cookies) {

            if(cookie.getName().equals(key)) {
                return Integer.parseInt(cookie.getValue());
            }
        }
        return 0;
    }
}
