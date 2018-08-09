package nl.hva.jeecourse.servlet;

import nl.hva.jeecourse.model.Customer;
import nl.hva.jeecourse.model.State;
import nl.hva.jeecourse.repository.Repository;
import nl.hva.jeecourse.repository.impl.RepositoryImpl;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/test")
public class TestServlet extends HttpServlet {

    @Inject
    private Repository repo;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();

        State any = new State();
        any.setAbbreviation("XY");
        any.setName("Any");
        repo.addState(any);

        Customer c = new Customer();
        c.setFirstName("John");
        c.setLastName("Martin");
        c.setAddress("Lindenlaan, 34");
        c.setCity("Zaandijk");
        c.setGender("male");
        c.setLatitude(33299);
        c.setLongitude(-111963);
        c.setState(any);

        c = repo.addCustomer(c);

        out.println("<br>Customer " + c.getId());


    }
}
