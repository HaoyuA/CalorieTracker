package com.example.assignment2;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class StepFragment extends Fragment {
    View vStep;
    Button setStepButton;
    Button viewRecordButton;
    EditText editText;
    Calendar calander;
    StepDatabase db;

    public StepFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vStep = inflater.inflate(R.layout.fragment_step, container, false);

        MainActivity mainActivity = (MainActivity) getActivity();
        db = mainActivity.getDataBase();
        setStepButton = vStep.findViewById(R.id.btnSetStep);
        viewRecordButton = vStep.findViewById(R.id.btnViewRecord);
        editText = vStep.findViewById(R.id.editText1);
        setStepButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                InsertDatabase addDatabase = new InsertDatabase();
                if(!(editText.getText().toString().isEmpty())){
                    addDatabase.execute();
                }
                else{
                    Toast.makeText(getActivity().getApplicationContext(), "ENTER STEP FIRST",Toast.LENGTH_LONG).show();
                }
            }
        });
        viewRecordButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                ReadDatabase readDatabase = new ReadDatabase();
                readDatabase.execute();
            }
        });

        return vStep;
    }
    private class InsertDatabase extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            int steps = 0;
            try {
                steps  = Integer.parseInt(editText.getText().toString());

            }catch(NumberFormatException e) {
                Toast.makeText(getActivity().getApplicationContext(), "ENTER A NUMBER",Toast.LENGTH_LONG).show();
                return "";
            }
            calander = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            Step step = new Step(steps,date);
            long id = db.stepDao().insert(step);
            return "";

        }
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getActivity().getApplicationContext(), "ADDED",Toast.LENGTH_LONG).show();
        }
    }
    private class ReadDatabase extends AsyncTask<Void, Void, String> {
        @Override
    protected String doInBackground(Void... params) {
            List<Step> records = db.stepDao().getAll();
            if (!(records.isEmpty() || records == null) ){
        String allUsers = "";
        for (Step temp : records) {
            String userstr = (temp.getDate() + " "
                    + temp.getStepNumber() + ", " );
            allUsers = allUsers + userstr;
        }
                return allUsers; }
            else
                return "";
        }
        @Override
        protected void onPostExecute(String result) {
            TextView resultTextView = (TextView) vStep.findViewById(R.id.tvResult2);
            resultTextView.setText(result);
        }
    }

}
