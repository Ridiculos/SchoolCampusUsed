package com.domain;

import java.io.Serializable;

public class User implements Serializable {
    private String userId="";
    private String name="";
    private String username="";
    private String password="";
    private int sex=0;
    private String email="";
    private int status=1;
    private int admin=0;

    public User(String userId, String name, String username, String password, int sex, String email, int status, int admin) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.email = email;
        this.status = status;
        this.admin = admin;
    }

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", admin=" + admin +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
}
