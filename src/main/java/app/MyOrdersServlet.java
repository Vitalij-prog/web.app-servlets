package app;

import Entities.Order;
import db.MyDB;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MyOrdersServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(MyOrdersServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ArrayList<Order> orders = null;
        String username = (String) session.getAttribute("username");
        log.info("user " + username + " viewed products");
        MyDB mydb = new MyDB();
        try {
            orders = mydb.getOrders(username);
            mydb.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("orders", orders);
        String userPath = "/WEB-INF/view/ordersOfUser.jsp";
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(userPath);
        requestDispatcher.forward(req, resp);
    }
}
