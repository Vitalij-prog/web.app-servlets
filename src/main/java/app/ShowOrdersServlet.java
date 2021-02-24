package app;

import Entities.Order;
import db.MyDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShowOrdersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Order> orders = null;
        MyDB mydb = new MyDB();
        try {
            orders = mydb.getOrders();
            mydb.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("orders", orders);
        String userPath = "/WEB-INF/view/orders.jsp";
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(userPath);
        requestDispatcher.forward(req, resp);
    }

}
