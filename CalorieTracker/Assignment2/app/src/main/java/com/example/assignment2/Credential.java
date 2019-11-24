package com.example.assignment2;

import java.util.Date;

public class Credential {

    private String userName;

    private Integer userId;

    private String password;

    private Date signUpDate;

    public Credential(String userName, Integer userId, String password, Date signUpDate) {
        this.userName = userName;
        this.userId = userId;
        this.password = password;
        this.signUpDate = signUpDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(Date signUpDate) {
        this.signUpDate = signUpDate;
    }
}
