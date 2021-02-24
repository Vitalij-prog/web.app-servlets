package app;

import Entities.Product;
import db.MyDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShowProductsServlet extends HttpServlet {
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
        String userPath = "/WEB-INF/view/products.jsp";
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(userPath);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
