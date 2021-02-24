package app;

import Entities.Order;
import Entities.Product;
import db.MyDB;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

public class OrderProductServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(OrderProductServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Product> products = null;
        MyDB mydb = new MyDB();
        try {
            products = mydb.getProducts();
            mydb.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("products", products);
        String path = "/WEB-INF/view/orderProduct.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);

       // resp.addCookie(new Cookie("price", ""));
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String prod_name = req.getParameter("product_name");
        String amount = req.getParameter("amount");
        MyDB mydb = new MyDB();
        double prod_price = 0;
        try {
            prod_price = mydb.getPrice(prod_name);

        } catch (SQLException e) {
            e.printStackTrace();
        }
       /* Cookie[] cookies = req.getCookies();
        String price = "";
        if(cookies !=null) {
            for(Cookie c: cookies) {
                if(c.getName().equals("price")) {
                    price = c.getValue();
                    break;
                }
            }
        }*/
        double order_price = Integer.parseInt(amount) * prod_price;
        String username = (String) req.getSession().getAttribute("username");
        log.info("user " + username + " ordered product");
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        Time time = new Time(Calendar.getInstance().getTime().getTime());

        Order order = new Order(0,username, prod_name, Integer.parseInt(amount), order_price, date, time, "proccesing");
        String answer = "";
        try {
             answer = mydb.addOrder(order);
            mydb.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(answer.equals("success")) {
            req.setAttribute("order", order);
            String path = "/WEB-INF/view/currentOrder.jsp";
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
            requestDispatcher.forward(req,resp);
        } else {
            doGet(req,resp);
        }

    }
}
