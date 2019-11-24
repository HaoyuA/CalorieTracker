package com.example.assignment2;

import java.util.Date;

public class User {

    private Integer userId;
    private String name;
    private String surname;
    private String email;
    private Date dob;
    private Integer height;
    private Integer weight;
    private String gender;
    private String address;
    private Integer postcode;
    private Integer levelOfActivity;
    private Integer stepPerMile;

    public User(String name, String surname, String email, Date dob, Integer height, Integer weight, String gender, String address, Integer postcode, Integer levelOfActivity, Integer stepPerMile) {

        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dob = dob;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.address = address;
        this.postcode = postcode;
        this.levelOfActivity = levelOfActivity;
        this.stepPerMile = stepPerMile;
    }

    public User(){

    }

    public void setUserId(Integer userId){
        this.userId = userId;
    }
    public Integer getUserId(){
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public Integer getLevelOfActivity() {
        return levelOfActivity;
    }

    public void setLevelOfActivity(Integer levelOfActivity) {
        this.levelOfActivity = levelOfActivity;
    }

    public Integer getStepPerMile() {
        return stepPerMile;
    }

    public void setStepPerMile(Integer stepPerMile) {
        this.stepPerMile = stepPerMile;
    }
}