package app;

import db.MyDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class LoginServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(LoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path = "/WEB-INF/view/login.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);

        String redirectPath = req.getContextPath();
        req.setAttribute("back", redirectPath);

        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = "";
        if(username.equals("") || password.equals("")) {
            request.setAttribute("message", "there is empty field");
            //request.setAttribute("back", redirectPath);
            doGet(request, response);
        } else {
            MyDB mydb = new MyDB();
            try {
                role = mydb.searchUser(username,password);
                mydb.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(role.equals("")) {
                log.error("wrong login or password");
                request.setAttribute("message", "wrong login or password");
                doGet(request, response);
            } else if(role.equals("user")) {
                log.info("user " + username + " login");
                HttpSession session = request.getSession();
                session.setAttribute("username", request.getParameter("username"));
                session.setAttribute("password", request.getParameter("password"));
                session.setAttribute("home", "homeUser");
                //request.setAttribute("username",request.getParameter("username"));
                //request.setAttribute("password",request.getParameter("password"));
                String userPath = "/homeUser";
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(userPath);
                requestDispatcher.forward(request, response);
            } else {
                log.info("admin " + username + " login");
                HttpSession session = request.getSession();
                session.setAttribute("username", request.getParameter("username"));
                session.setAttribute("password", request.getParameter("password"));
                session.setAttribute("home", "homeAdmin");
                String userPath = "/homeAdmin";
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(userPath);
                requestDispatcher.forward(request, response);
            }
        }


       /* HttpSession session = request.getSession();
        session.setAttribute("username", "HttpSession");
        String path = "/WEB-INF/view/first.jsp";
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("username", "servletContext");*/

       /* request.setAttribute("username",request.getParameter("username"));
        request.setAttribute("password",request.getParameter("password"));

        String path = "/WEB-INF/view/first.jsp";
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(path);
        requestDispatcher.forward(request, response);*/

    }
}
