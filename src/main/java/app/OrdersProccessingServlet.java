package app;

import Entities.Order;
import db.MyDB;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

public class OrdersProccessingServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(OrdersProccessingServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<String> options = new ArrayList<>();
        options.add("processing");
        options.add("confirmed");
        options.add("cancelled");
        ArrayList<Order> orders = null;
        MyDB mydb = new MyDB();
        try {
            orders = mydb.getOrders();
            mydb.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("orders", orders);
        req.setAttribute("options", options);

        String path = "/WEB-INF/view/ordersProccessing.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String order_id = req.getParameter("order_id");

        String option_type = req.getParameter("option_type");
        MyDB mydb = new MyDB();
        String username = (String) req.getSession().getAttribute("username");
        log.info("user " + username + " started ordering of order");
        String answer = "";
        try {
            answer = mydb.updateOrderStatus(order_id, option_type);
            mydb.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(answer.equals("success")) {
            req.setAttribute("message", "success");
            req.setAttribute("color", "green");
            doGet(req,resp);
            log.info("user " + username + " ordered a order " + order_id);
        } else {
            req.setAttribute("message", "problems");
            req.setAttribute("color", "red");
            doGet(req,resp);
            log.error("ordering failed");
        }
    }
}
