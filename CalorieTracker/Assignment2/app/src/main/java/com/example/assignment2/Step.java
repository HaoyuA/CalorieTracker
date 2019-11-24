package com.example.assignment2;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

@Entity
@TypeConverters(DateConverter.class)
public class Step {
    @PrimaryKey(autoGenerate = true)
    public int stepid;

    @ColumnInfo(name = "step_number")
    public int stepNumber;

    @ColumnInfo(name = "date")
    public Date date;

    public Step( int stepNumber, Date date) {
        this.stepNumber = stepNumber;
        this.date = date;
    }

    public int getStepid() {
        return stepid;
    }

    public void setStepid(int stepid) {
        this.stepid = stepid;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
