package db;

import Entities.Order;
import Entities.Product;
import Entities.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;


public class MyDB {
    String url = "jdbc:mysql://localhost/shop?useUnicode=true&serverTimezone=UTC";
    String login = "root";
    String password = "root";
    String driver = "com.mysql.cj.jdbc.Driver";
    Connection connection;
   /* private static InitialContext initContext;

    static {
        try {
            initContext = new InitialContext();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private static DataSource ds;
    private static Context envContext;
    static {
        try {
            envContext = (Context) initContext.lookup("java:comp/env");
            ds = (DataSource) envContext.lookup("jdbc/firstWebApp");
            //ds = (DataSource) initContext.lookup("java:comp/env/jdbc/firstWebApp");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }*/
    //Connection connection = ds.getConnection();

    public MyDB() {
       /* Properties props = new Properties();
        try(FileInputStream in = new FileInputStream("database.properties")) {
            props.load(in);
            String driver = props.getProperty("driver");

        } catch(ClassNotFoundException | IOException e) {
            System.out.println("Load Driver Exception" + e);
        }*/
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        /*String url = props.getProperty("url");
        String login = props.getProperty("login");
        String password = props.getProperty("password");*/
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);

        }
       /* try {
            connection = ds.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }
    }

    public String searchUser(String login, String pass) throws SQLException {
        String query = "select * from users";
        //StringBuilder res = new StringBuilder();
        PreparedStatement st = connection.prepareStatement(query);
        ResultSet resSet = st.executeQuery();
        boolean flag = false;
        String role = "";
        while (resSet.next()) {
            //res.append(resSet.getString(1)).append("\t").append(resSet.getString(2)).append("\n");
            if (login.equals(resSet.getString(2)) && pass.equals(resSet.getString(3))) {

                role = resSet.getString(4);
                flag = true;
                break;
            }
        }
        if (!flag) {
            System.out.println("wrong login or password");
        }
        resSet.close();
        st.close();
        return role;
    }

    public ArrayList<Product> getProducts() throws SQLException {
        String query = "select * from products";
        PreparedStatement st = connection.prepareStatement(query);
        ResultSet resSet = st.executeQuery();
        ArrayList<Product> list = new ArrayList<>();
        while(resSet.next()) {
            list.add(new Product(resSet.getInt(1), resSet.getString(2), resSet.getDouble(3), resSet.getInt(4)));
        }
        resSet.close();
        return list;
    }

    public ArrayList<Order> getOrders(String username) throws SQLException{

        String query = "select orders.id, users.user_name, products.product_name, orders.amount," +
                " orders.order_price, orders.order_date," +
                "   orders.order_time, orders.status" +
                "   from orders inner join users on orders.userId = users.id" +
                "   inner join products on orders.productId = products.id" +
                "   where users.user_name = ?";
        PreparedStatement st = connection.prepareStatement(query);
        st.setString(1, username);
        st.addBatch();
        ResultSet resSet = st.executeQuery();
        ArrayList<Order> list = new ArrayList<>();

        while(resSet.next()) {
            list.add(new Order(resSet.getInt(1), resSet.getString(2),
                    resSet.getString(3), resSet.getInt(4),
                    resSet.getDouble(5),resSet.getDate(6),
                    resSet.getTime(7), resSet.getString(8)));
        }
        resSet.close();

        return list;
    }

    public ArrayList<Order> getOrders() throws SQLException{

        String query = "select orders.id, users.user_name, products.product_name, orders.amount," +
                " orders.order_price, orders.order_date," +
                "   orders.order_time, orders.status" +
                "   from orders inner join users on orders.userId = users.id" +
                "   inner join products on orders.productId = products.id";
        PreparedStatement st = connection.prepareStatement(query);
        ResultSet resSet = st.executeQuery();
        ArrayList<Order> list = new ArrayList<>();

        while(resSet.next()) {
            list.add(new Order(resSet.getInt(1), resSet.getString(2),
                    resSet.getString(3), resSet.getInt(4),
                    resSet.getDouble(5),resSet.getDate(6),
                    resSet.getTime(7), resSet.getString(8)));
        }
        resSet.close();

        return list;
    }

    public String addOrder(Order or) throws SQLException {
        PreparedStatement st = connection.prepareStatement("select id from users where user_name = ?");
        st.setString(1, or.getUser_name());
        st.addBatch();
        ResultSet resSet = st.executeQuery();

        if (resSet.next()) {
            or.setUser_id(resSet.getInt(1));
        }

        st = connection.prepareStatement("select id from products where product_name = ?");
        st.setString(1, or.getProd_name());
        st.addBatch();
        resSet = st.executeQuery();

        if (resSet.next()) {
            or.setProd_id(resSet.getInt(1));
        }
        resSet.close();
        st = connection.prepareStatement("insert into orders (userId, productId, amount, order_price, order_date, order_time) values(?,?,?,?,?,?) ");
        st.setInt(1, or.getUser_id());
        st.setInt(2, or.getProd_id());
        st.setInt(3, or.getAmount());
        st.setDouble(4, or.getPrice());
        st.setDate(5, or.getDate());
        st.setTime(6, or.getTime());
        st.addBatch();
        st.executeBatch();


        st.close();
        return "success";
    }

    public double getPrice(String prod_name) throws SQLException {
        System.out.println(prod_name);
        String query = "select product_price from products where product_name = ?";
        PreparedStatement st = connection.prepareStatement(query);
        st.setString(1, prod_name);
        st.addBatch();

        ResultSet resSet = st.executeQuery();
        double price = 0;
        int i = 0;
        if(resSet.next()) {
            price = resSet.getDouble(1);
            i++;
        }
        if(i == 0) {
            resSet.close();
            st.close();
            return -1;
        }
        // System.out.println(resSet.getDouble(1));
        resSet.close();
        st.close();
        return price;
    }

    public String addUser(User user) throws SQLException {
        String query = "select * from users";
        PreparedStatement st = connection.prepareStatement(query);
        ResultSet resSet = st.executeQuery();
        boolean flag = false;
        while(resSet.next()) {
            if(user.getUser_name().equals(resSet.getString(2))) {

                flag = true;
                break;
            }
        }
        resSet.close();
        if(flag) {
            resSet.close();
            st.close();
            return "";
        }
        st = connection.prepareStatement("insert into users (user_name, user_password, role) values(?,?,?) ");
        st.setString(1, user.getUser_name());
        st.setString(2, user.getPassword());
        st.setString(3, user.getRole());
        st.addBatch();
        st.executeBatch();

        st.close();
        return "success";
    }

    public ArrayList<User> getUsers() throws SQLException {
        String query = "select * from users";
        PreparedStatement st = connection.prepareStatement(query);
        ResultSet resSet = st.executeQuery();
        ArrayList<User> list = new ArrayList<>();

        while(resSet.next()) {
            list.add(new User(resSet.getInt(1), resSet.getString(2), resSet.getString(4)));
        }
        resSet.close();
        st.close();
        return list;

    }

    public String updateOrderStatus(String id, String newStatus) throws SQLException {
        int orderId = Integer.parseInt(id);
        String query = "update orders set status = ? where id = ?";
        PreparedStatement st = connection.prepareStatement(query);
        st.setString(1, newStatus);
        st.setInt(2, orderId);
        st.addBatch();

        st.executeBatch();


        return "success";
    }
}
