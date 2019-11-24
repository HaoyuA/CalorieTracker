package com.example.assignment2;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.app.Fragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String firstName = "";
    StepDatabase db = null;
    String kalorieGoal = "";
    Integer userId = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        Bundle bundle=intent.getExtras();
        //String message= intent.getStringExtra("message");
        firstName = bundle.getString("firstName");
        userId = bundle.getInt("userId");



        db = Room.databaseBuilder(getApplicationContext(),
                StepDatabase.class, "StepDatabase") .fallbackToDestructiveMigration()
                .build();



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setTitle("Home Page");

       FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new
                HomeFragment()).commit();
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data ) {
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                String message= data.getStringExtra("message");
                TextView textView=(TextView) findViewById(R.id.textView1);
                textView.setText(message);
            }
        }
    }*/

    /*@Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the MainActivity/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment nextFragment = null;


        switch (id) {
            case R.id.nav_calorie_tracker:
                nextFragment = new CalorieTrackerFragment();
                break;
            case R.id.nav_daily_diet:
                nextFragment = new DailyDietFragment();
                break;
            case R.id.nav_report:
                nextFragment = new ReportFragment();
                break;
            case R.id.nav_step:
                nextFragment = new StepFragment();
                break;
            case R.id.nav_home:
                nextFragment = new HomeFragment();
                break;
        }
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame,
                nextFragment).commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String getName(){
        return firstName;
    }

    public StepDatabase getDataBase(){
        return db;
    }

    public String getCalorieGoal(){
        return kalorieGoal;
    }

    public void setKalorieGoal(String kalorieGoal){
        this.kalorieGoal = kalorieGoal;
    }

    public Integer getUserId(){
        return userId;
    }

}
