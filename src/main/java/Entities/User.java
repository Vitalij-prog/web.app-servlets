package Entities;

import java.io.Serializable;

public class User implements Serializable {
    int id;
    String user_name;
    String password;
    String role;

    public User(int id, String user_name, String password, String role){
        this.id = id;
        this.user_name = user_name;
        this.password = password;
        this.role = role;
    }
    public User(int id, String user_name, String role){
        this.id = id;
        this.user_name = user_name;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}