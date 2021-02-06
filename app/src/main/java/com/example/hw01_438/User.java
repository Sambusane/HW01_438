package com.example.hw01_438;

public class User {
    private int uId;
    private String userName;
    private String password;

    public User(int uId, String userName, String password) {
        this.uId = uId;
        this.userName = userName;
        this.password = password;
    }

    public int getuId() {
        return uId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
