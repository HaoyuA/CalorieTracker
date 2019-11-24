package com.example.assignment2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.app.Fragment;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Date;


public class CalorieTrackerFragment extends Fragment{
    View vCalorieTracker;
    SimpleDateFormat sdf;
    TextView textView;
    String calorieGoal;
    StepDatabase db;
    MainActivity mainActivity;
    int totalStep;
    Button updateButton;
    int totalCalorieConsumed;
    int totalCalorieBurned;
    private AlarmManager alarmMgr;
    private Intent alarmIntent;
    private PendingIntent pendingIntent;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        vCalorieTracker = inflater.inflate(R.layout.fragment_calorie_tracker, container, false);
        getActivity().setTitle("Calorie tracker");
        mainActivity = (MainActivity) getActivity();
        calorieGoal = mainActivity.getCalorieGoal();
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String pickedDate = sdf.format(date);
        db = mainActivity.getDataBase();
        textView = vCalorieTracker.findViewById(R.id.textView0);
        updateButton = vCalorieTracker.findViewById(R.id.btnUpdate);
        if(!calorieGoal.isEmpty()){
        textView.setText("Set calorie goal: " + calorieGoal);}
        ReadDatabase readDatabase = new ReadDatabase();
        readDatabase.execute();
        CalorieBurnedAsyncTask calorieBurnedAsyncTask = new CalorieBurnedAsyncTask();
        calorieBurnedAsyncTask.execute();
        CalorieConsumedAsyncTask calorieConsumedAsyncTask = new CalorieConsumedAsyncTask();
        calorieConsumedAsyncTask.execute(pickedDate);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateAsyncTask updateAsyncTask = new UpdateAsyncTask();
                updateAsyncTask.execute();
            }
        });

        alarmMgr = (AlarmManager) mainActivity.getSystemService(Context.ALARM_SERVICE);
        alarmIntent = new Intent(getActivity(), ScheduledIntentService.class);
        pendingIntent = PendingIntent.getService(getActivity(), 0, alarmIntent, 0);
        alarmMgr.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime(), 60 * 1000, pendingIntent);
        SharedPreferences sp = getActivity().getApplicationContext().getSharedPreferences(
                "service", Context.MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sp.edit();
          if(calorieGoal.isEmpty()){
            calorieGoal = "0";
          }
        spEditor.putInt("calorieGoal",Integer.valueOf(calorieGoal) );
        spEditor.putInt("totalCalorieConsumed",totalCalorieConsumed);
        spEditor.putInt("totalCalorieBurned",totalCalorieBurned);
        spEditor.putInt("totalStep",totalStep);
        spEditor.putInt("userId",mainActivity.getUserId());
          Date curr=new Date();
          Calendar c = Calendar.getInstance();
          c.setTime(curr);
          c.set(Calendar.SECOND, 0);

          Calendar c1 = Calendar.getInstance();
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),24*60*60*1000, pendingIntent);


        return vCalorieTracker;

    }
    private class UpdateAsyncTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            Integer userId = mainActivity.getUserId();
            String result = "s";
            Date date = new Date();
            if(!calorieGoal.equals("0")) {
                int goal = Integer.valueOf(calorieGoal);
                User user = new User();
                user.setUserId(userId);

                Report report = new Report(user, date, totalCalorieConsumed, totalCalorieBurned, totalStep, goal);
                RestClient.createReport(report);
            }else{
                result = "";
            }
            StepDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(),
                    StepDatabase.class, "StepDatabase") .fallbackToDestructiveMigration()
                    .build();
            db.stepDao().deleteAll();

            return result;

        }
        @Override
        protected void onPostExecute(String result) {
            if(result.isEmpty()) {
                Toast.makeText(getActivity().getApplicationContext(), "set a goal first", Toast.LENGTH_LONG).show();
            }
        }
    }

    private class ReadDatabase extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            List<Step> records = db.stepDao().getAll();
            if (!(records.isEmpty() || records == null) ){
                totalStep = 0;
                for (Step temp : records) {
                    totalStep += temp.getStepNumber() ;

                }
                return String.valueOf(totalStep); }
            else
                return "0";
        }
        @Override
        protected void onPostExecute(String result) {
            TextView resultTextView = (TextView) vCalorieTracker.findViewById(R.id.textView1);
            resultTextView.setText("Total steps taken: "+result);
        }
    }

    private class CalorieBurnedAsyncTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            String userId = String.valueOf(mainActivity.getUserId());
            Double calorieBurnedPerStep = Double.valueOf(RestClient.getCaloriesBurnedPerStep(userId));
            Double totalCalorieBurnedStep = totalStep * calorieBurnedPerStep;
            Double calorieBurnedRest = Double.valueOf(RestClient.getCalorieBurnedRest(userId));
            Calendar rightNow = Calendar.getInstance();
            int hour = rightNow.get(Calendar.HOUR_OF_DAY);
            Double totalCalorie = totalCalorieBurnedStep + calorieBurnedRest*(hour/24);
            totalCalorieBurned = totalCalorie.intValue();
            String result = String.valueOf(totalCalorieBurned);

            return result;

        }
        @Override
        protected void onPostExecute(String result) {
            TextView resultTextView = (TextView) vCalorieTracker.findViewById(R.id.textView3);
            resultTextView.setText("Total calorie burned: "+result);
        }
    }

    private class CalorieConsumedAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String userId = String.valueOf(mainActivity.getUserId());
            totalCalorieConsumed = 0;
            String consumptions = RestClient.findByUserIdANDConsumptionDate(userId,params[0]);
            try{
                JSONArray jsonArray = new JSONArray(consumptions);
                for(int i =0; i< jsonArray.length();i++) {
                    String quantity = jsonArray.getJSONObject(i).getString("quantity");
                    String calorieAmount = jsonArray.getJSONObject(i).getJSONObject("foodId").getString("calorieAmount");
                    totalCalorieConsumed += Integer.valueOf(quantity)*Integer.valueOf(calorieAmount);
                }
            }catch (Exception e){
                e.printStackTrace();

            }

            return String.valueOf(totalCalorieConsumed);
        }
        @Override
        protected void onPostExecute(String result) {
            TextView resultTextView = (TextView) vCalorieTracker.findViewById(R.id.textView2);
            resultTextView.setText("Total calorie consumed: "+result);
        }
    }
}
