package com.example.assignment2;


import java.util.Date;


public class Report {
    private Integer reportId;
    private User userId;
    private Date reportDate;
    private Integer totalCaloriesConsumed;
    private Integer totalCaloriesBurned;
    private Integer totalStepsTaken;
    private Integer setCalorieGoal;


    public Report(User userId, Date reportDate, Integer totalCaloriesConsumed, Integer totalCaloriesBurned, Integer totalStepsTaken, Integer setCalorieGoal) {
        this.userId = userId;
        this.reportDate = reportDate;
        this.totalCaloriesConsumed = totalCaloriesConsumed;
        this.totalCaloriesBurned = totalCaloriesBurned;
        this.totalStepsTaken = totalStepsTaken;
        this.setCalorieGoal = setCalorieGoal;
    }

    public Report(){}

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Integer getTotalCaloriesConsumed() {
        return totalCaloriesConsumed;
    }

    public void setTotalCaloriesConsumed(Integer totalCaloriesConsumed) {
        this.totalCaloriesConsumed = totalCaloriesConsumed;
    }

    public Integer getTotalCaloriesBurned() {
        return totalCaloriesBurned;
    }

    public void setTotalCaloriesBurned(Integer totalCaloriesBurned) {
        this.totalCaloriesBurned = totalCaloriesBurned;
    }

    public Integer getTotalStepsTaken() {
        return totalStepsTaken;
    }

    public void setTotalStepsTaken(Integer totalStepsTaken) {
        this.totalStepsTaken = totalStepsTaken;
    }

    public Integer getSetCalorieGoal() {
        return setCalorieGoal;
    }

    public void setSetCalorieGoal(Integer setCalorieGoal) {
        this.setCalorieGoal = setCalorieGoal;
    }

}

