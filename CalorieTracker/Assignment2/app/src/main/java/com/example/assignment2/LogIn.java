package com.example.assignment2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {
    private static String password = "";
    private boolean logInAuthentication = false;
    String message;
    int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText editText= (EditText) findViewById(R.id.userName_editText);
        final EditText editText1 = (EditText) findViewById(R.id.password_editText);
        Button logInButton = (Button) findViewById(R.id.btnLogIn);
        logInButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                UserAsyncTask userAsyncTask = new UserAsyncTask();
                String userName = editText.getText().toString();
                password = editText1.getText().toString();
                //Toast.makeText(LogIn.this, "message", Toast.LENGTH_SHORT).show();
                if(!userName.isEmpty()&&!password.isEmpty()){
                    userAsyncTask.execute(userName);
                }
                else{
                    Toast.makeText(getApplicationContext(), "ENTER TEXT FIRST",Toast.LENGTH_LONG).show();
                }
            }
        });


        Button signUpButton = (Button) findViewById(R.id.btnSignUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LogIn.this,
                        SignUp.class);
                startActivityForResult(intent, 1);
            }
        });
    }
    private class UserAsyncTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground (String...params){
            String result = "";
            logInAuthentication = false;
            if((RestClient.findByUserName(params[0])).equals("[]"))
            {
                result = "User does not exist, please enter your name again";
            }
            else if(!HashPasswordGenerator.hashPassword(password).equals(RestClient.getPassword(RestClient.findByUserName(params[0]))))
            {
                result = "Password incorrect,please enter again";
            }
            else{
                logInAuthentication = true;
                message = RestClient.getFirstName(RestClient.findByUserId(RestClient.getUserId(RestClient.findByUserName(params[0]))));
                userId = RestClient.getUserId(RestClient.findByUserName(params[0]));
            }
            return result;
        }

        @Override
        protected void onPostExecute (String result){
            if(logInAuthentication == true){



                Intent intent = new Intent(LogIn.this,
                        MainActivity.class);
               Bundle bundle=new Bundle();
                bundle.putString("firstName", message);
                bundle.putInt("userId", userId);
                intent.putExtras (bundle);
                //intent.putExtra("message",message);
                startActivity(intent);
            }

                TextView resultTextView = (TextView) findViewById(R.id.tvResult);
                resultTextView.setText(result);

        }
    }

}
