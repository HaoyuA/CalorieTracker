package com.example.assignment2;

import java.util.Date;

public class Consumption {
    private Integer comsumptionId;
    private Date consumptionDate;
    private Integer quantity;
    private Food foodId;
    private User userId;

    public Consumption(Date consumptionDate, Integer quantity, Food foodId, User userId) {
        this.consumptionDate = consumptionDate;
        this.quantity = quantity;
        this.foodId = foodId;
        this.userId = userId;
    }

    public Integer getComsumptionId() {
        return comsumptionId;
    }

    public void setComsumptionId(Integer comsumptionId) {
        this.comsumptionId = comsumptionId;
    }

    public Date getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(Date consumptionDate) {
        this.consumptionDate = consumptionDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Food getFoodId() {
        return foodId;
    }

    public void setFoodId(Food foodId) {
        this.foodId = foodId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
