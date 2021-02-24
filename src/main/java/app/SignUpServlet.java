package app;

import Entities.User;
import db.MyDB;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class SignUpServlet  extends HttpServlet {
    private static final Logger log = Logger.getLogger(SignUpServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "/WEB-INF/view/registration.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);

        String redirectPath = req.getContextPath();
        req.setAttribute("back", redirectPath);

        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //String path = "/WEB-INF/view/registration.jsp";
        /*ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);*/

        //String redirectPath = request.getContextPath();


        String username = request.getParameter("username");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        if(username.equals("") || password1.equals("") || password2.equals("")) {
            request.setAttribute("message", "there is empty field");
            request.setAttribute("color", "orange");
            //request.setAttribute("back", redirectPath);
            doGet(request, response);
            //requestDispatcher.forward(request, response);
        } else if (!password1.equals(password2)) {
            request.setAttribute("message", "passwords are not equal");
            request.setAttribute("color", "red");
            //request.setAttribute("back", redirectPath);
            doGet(request, response);
            //requestDispatcher.forward(request, response);
        } else {
            /*String redirectPath = request.getContextPath() + "/login";
            response.sendRedirect(redirectPath);*/
            /*request.setAttribute("message", "success");
            request.setAttribute("color", "green");*/
            //request.setAttribute("back", redirectPath);
            //doGet(request, response);

            User user = new User(0, username, password1, "user");
            MyDB mydb = new MyDB();
            String answer = "";
            try {
                 answer = mydb.addUser(user);
                mydb.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(answer.equals("success")) {
                log.info("user " + username + " sign up");
                String redirectPath = request.getContextPath() + "/login";
                response.sendRedirect(redirectPath);
            } else {
                log.error("registration failed");
                request.setAttribute("message", "user with the same name already exists");
                request.setAttribute("color", "red");
                doGet(request, response);
            }
        }

    }
}
