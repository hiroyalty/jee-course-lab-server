package nl.hva.jeecourse.servlet;

import nl.hva.jeecourse.repository.Repository;
import nl.hva.jeecourse.repository.impl.RepositoryImpl;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/api/image/state")
public class Lab4StatePictureServlet extends HttpServlet {

    @Inject
    private Repository repo;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String abbrev = req.getParameter("abbrev");
        String type = req.getParameter("type");

        if(!"flag".equals(type) && !"map".equals(type)) {

            resp.sendError(400,
                    "Bad request: Type must be map or flag. Received [" + type + "]");
            return;
        }

        byte image[];

        if(type.equals("flag")) {
            image = repo.getStateFlag(abbrev);
        } else {
            image = repo.getStateMap(abbrev);
        }

        resp.setContentType("image/png");
        resp.setContentLength(image.length);

        OutputStream os = resp.getOutputStream();

        os.write(image,0,image.length);
    }
}
