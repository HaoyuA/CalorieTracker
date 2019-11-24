package com.example.assignment2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class SignUp extends AppCompatActivity {

    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String editText12 = "";
    String editText10 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        final Spinner sLevelofActivity = (Spinner) findViewById(R.id.spinner);
        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_item, list);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sLevelofActivity.setAdapter(spinnerAdapter);
        sLevelofActivity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                editText12 = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final EditText editText0 = (EditText) findViewById(R.id.firstName_editText);
        final EditText editText1 = (EditText) findViewById(R.id.surname_editText);
        final EditText editText2 = (EditText) findViewById(R.id.email_editText);
        final EditText editText3 = (EditText) findViewById(R.id.height_editText);
        final EditText editText4 = (EditText) findViewById(R.id.weight_editText);
        final EditText editText5 = (EditText) findViewById(R.id.address_editText);
        final EditText editText6 = (EditText) findViewById(R.id.postCode_editText);
        final EditText editText7 = (EditText) findViewById(R.id.stepPerMile_editText);
        final EditText editText8 = (EditText) findViewById(R.id.username_editText);
        final EditText editText9 = (EditText) findViewById(R.id.password_editText);

        final RadioGroup radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);;



        final DatePicker datePicker;
        datePicker = (DatePicker) findViewById(R.id.datePicker);




        Button signUpButton = (Button) findViewById(R.id.btnSignUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try{
                    editText10 =
                            ((RadioButton)findViewById(radioSexGroup.getCheckedRadioButtonId()))
                                    .getText().toString();
                }catch(Exception e) {
                    e.printStackTrace();
                }

                int  day  = datePicker.getDayOfMonth();
                int  month = datePicker.getMonth();
                int  year = datePicker.getYear();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                final String editText11 = sdf.format(calendar.getTime());
                PostUserAsyncTask postUserAsyncTask = new PostUserAsyncTask();
                PostCredentialAsyncTask postCredentialAsyncTask = new PostCredentialAsyncTask();
                if(!editText0.getText().toString().isEmpty()&&!editText1.getText().toString().isEmpty()
                        &&!editText2.getText().toString().isEmpty()&&!editText3.getText().toString().isEmpty()
                        &&!editText4.getText().toString().isEmpty()&&!editText5.getText().toString().isEmpty()
                        &&!editText6.getText().toString().isEmpty()&&!editText7.getText().toString().isEmpty()
                        &&!editText8.getText().toString().isEmpty()&&!editText9.getText().toString().isEmpty()
                        &&!editText10.isEmpty()&&!editText11.isEmpty()&&!editText12.isEmpty()){
                    postUserAsyncTask.execute(editText0.getText().toString(),editText1.getText().toString(),
                            editText2.getText().toString(),editText3.getText().toString(),editText4.getText().toString(),
                            editText5.getText().toString(),editText6.getText().toString(),editText7.getText().toString(),
                            editText8.getText().toString(),editText9.getText().toString(),editText10,editText11,editText12
                    );
                    postCredentialAsyncTask.execute(editText2.getText().toString(),editText8.getText().toString(),
                            editText9.getText().toString());


                }
                else{
                    Toast.makeText(getApplicationContext(), "ENTER TEXT FIRST",Toast.LENGTH_LONG).show();
                }
            }
        });


    }


    private class PostUserAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            User user = null;

            try {
                user = new User(params[0],params[1],
                        params[2], sdf.parse(params[11]),Integer.valueOf(params[3]),
                        Integer.valueOf(params[4]),params[10],params[5],Integer.valueOf(params[6]),
                        Integer.valueOf(params[12]),Integer.valueOf(params[7]));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            RestClient.createUser(user);
            return "User was created";
        }
        @Override
        protected void onPostExecute(String response) {
            TextView resultTextView = (TextView) findViewById(R.id.textView15);
            resultTextView.setText(response);
        }
    }
    private class PostCredentialAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            Credential credential = null;
            String password = HashPasswordGenerator.hashPassword(params[2]);
            Calendar cal = Calendar.getInstance();
            Date date = cal.getTime();
            Integer userId = RestClient.getUserId(RestClient.findByEmail(params[0]));

            credential = new Credential(params[1],userId,
                    password, date);


            RestClient.createCredential(credential);
            return "User was created";
        }
        @Override
        protected void onPostExecute(String response) {
            TextView resultTextView = (TextView) findViewById(R.id.textView15);
            resultTextView.setText(response);
        }
    }
}
