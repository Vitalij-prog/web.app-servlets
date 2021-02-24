package app;

import Entities.Order;
import Entities.User;
import db.MyDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShowUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<User> users = null;
        MyDB mydb = new MyDB();
        try {
            users = mydb.getUsers();
            mydb.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("users", users);
        String userPath = "/WEB-INF/view/users.jsp";
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(userPath);
        requestDispatcher.forward(req, resp);
    }
}
