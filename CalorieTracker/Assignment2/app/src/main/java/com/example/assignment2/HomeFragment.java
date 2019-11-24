package com.example.assignment2;

import android.arch.persistence.room.Database;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment {
    View vMain;
    Calendar calander;
    SimpleDateFormat simpledateformat;
    String Date;
    EditText editText;
    TextView DisplayDateTime;
    Button setGoalButton;
    StepDatabase db;
    MainActivity mainActivity;
    Button mapButton;
    int userId;
    String message;
    String postcode;
    String address;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        vMain = inflater.inflate(R.layout.fragment_home, container, false);
        mainActivity = (MainActivity) getActivity();
        message = mainActivity.getName();
        userId = mainActivity.getUserId();

        TextView textView=(TextView) vMain.findViewById(R.id.textView1);
        DisplayDateTime = (TextView)vMain.findViewById(R.id.textView2);
        setGoalButton = vMain.findViewById(R.id.btnSetGoal);
        editText = vMain.findViewById(R.id.step_editText);
        mapButton = vMain.findViewById(R.id.btnMap);


        textView.setText("Welcome " + message + "!");
        calander = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date = simpledateformat.format(calander.getTime());
        DisplayDateTime.setText(Date);
        db = mainActivity.getDataBase();

        editText = vMain.findViewById(R.id.step_editText);
        setGoalButton.setOnClickListener(new View.OnClickListener() {
            //including onClick() method
            public void onClick(View v) {
                String kalorieGoal = editText.getText().toString();
                if(!(kalorieGoal.isEmpty())){
                    try{
                       int i = Integer.parseInt(kalorieGoal);
                        TextView resultTextView = (TextView) vMain.findViewById(R.id.tvResult2);
                        resultTextView.setText("Your kalorie goal today is: " + kalorieGoal);
                        mainActivity.setKalorieGoal(kalorieGoal);
                    }catch (NumberFormatException ex) {
                        Toast.makeText(getActivity().getApplicationContext(), "ENTER A NUMBER",Toast.LENGTH_LONG).show();
                    }

                }
                else{
                    Toast.makeText(getActivity().getApplicationContext(), "ENTER SOMETHING FIRST",Toast.LENGTH_LONG).show();
                }
            }
        });
        mapButton.setOnClickListener(new View.OnClickListener() {
            //including onClick() method
            public void onClick(View v) {
                GetPostCodeAndAdressAsyncTask getPostCodeAndAdressAsyncTask = new GetPostCodeAndAdressAsyncTask();
                getPostCodeAndAdressAsyncTask.execute();

            }
        });
        return vMain;
    }

    private class GetPostCodeAndAdressAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String result = "";
            postcode = RestClient.getPostCode(RestClient.findByUserId(userId));
            address = RestClient.getAddress(RestClient.findByUserId(userId));
            Intent intent = new Intent(getActivity(), MapFragmentActivity.class);
            Bundle bundle= new Bundle();
            bundle.putString("postcode",postcode);
            bundle.putString("address",address);
            bundle.putString("firstName", message);
            bundle.putInt("userId", userId);
            intent.putExtras (bundle);
            startActivity(intent);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {

        }
    }


}

