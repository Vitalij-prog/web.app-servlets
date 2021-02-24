package app;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ExitServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(ExitServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HttpSession session = req.getSession();
        log.info("user " + session.getAttribute("username") + " log out");
        session.removeAttribute("username");
        session.removeAttribute("password");
        session.removeAttribute("home");

        String path = req.getContextPath();
        resp.sendRedirect(path);
    }
}
