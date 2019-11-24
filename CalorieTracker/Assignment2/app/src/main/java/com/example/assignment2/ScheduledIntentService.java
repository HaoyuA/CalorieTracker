package com.example.assignment2;

import android.app.IntentService;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;


public class ScheduledIntentService extends IntentService {
    static int counter=0;

    public ScheduledIntentService() {
        super("ScheduledIntentService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        StepDatabase db = Room.databaseBuilder(getApplicationContext(),
                StepDatabase.class, "StepDatabase") .fallbackToDestructiveMigration()
                .build();
        db.stepDao().deleteAll();
        SharedPreferences sp = getApplicationContext().getSharedPreferences(
                "service", Context.MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sp.edit();
        int calorieGoal = sp.getInt("calorieGoal",0);
        int totalCalorieConsumed = sp.getInt("totalCalorieConsumed",0);
        int totalCalorieBurned = sp.getInt("totalCalorieBurned",0);
        int totalStep=  sp.getInt("totalStep",0);
        int userId = sp.getInt("userId",0);
        User user = new User();
        user.setUserId(userId);
        Date date = new Date();
        Report report = new Report(user,date,totalCalorieConsumed,totalCalorieBurned,totalStep,calorieGoal);
        RestClient.createReport(report);


    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent,flags,startId);
    }
}