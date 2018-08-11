package nl.hva.jeecourse.servlet.cors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsServlet extends HttpServlet {

    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.addHeader("Access-Control-Allow-Origin","http://localhost:8090");
        resp.addHeader("Access-Control-Allow-Headers","Content-Type, authorization, X-*");
        resp.addHeader("Access-Control-Expose-Headers","*");
        resp.addHeader("Access-Control-Allow-Methods","POST,GET,PUT,DELETE,OPTIONS");
        resp.addHeader("Access-Control-Allow-Credentials","true");
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);
        super.service(req, resp);
    }
}
