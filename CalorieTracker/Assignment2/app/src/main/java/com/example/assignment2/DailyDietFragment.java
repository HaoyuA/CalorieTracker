package com.example.assignment2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;

public class DailyDietFragment extends Fragment {
    View vDailyDiet;
    String spinnerText1;
    String category;
    EditText editText1;
    ImageView imageView;
    Bitmap bitmap;
    Button addButton;
    Button enterButton;
    MainActivity mainActivity;


    List<String> list = new ArrayList<>();
    List<String> list2 = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        vDailyDiet = inflater.inflate(R.layout.fragment_daily_diet, container, false);
        //getActivity().setTitle("Daily diet");
        mainActivity = (MainActivity) getActivity();
        //final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        addButton = vDailyDiet.findViewById(R.id.btnAdd);
        enterButton = (Button) vDailyDiet.findViewById(R.id.btnEnter);
        final Spinner sCategory = (Spinner) vDailyDiet.findViewById(R.id.spinner);
        Spinner sFood = (Spinner) vDailyDiet.findViewById(R.id.spinner2);
        CategoryAsyncTask categoryAsyncTask = new CategoryAsyncTask();
        categoryAsyncTask.execute();



            /*final ArrayAdapter<String> spinnerAdapter1 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, list);
            spinnerAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sCategory.setAdapter(spinnerAdapter1);*/
        sCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerText1 = (String) parent.getItemAtPosition(position);
                FoodSelectedAsyncTask foodSelectedAsyncTask = new FoodSelectedAsyncTask();
                foodSelectedAsyncTask.execute(spinnerText1);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

//                    final Spinner sFood = (Spinner) vDailyDiet.findViewById(R.id.spinner2);
//                    final ArrayAdapter<String> spinnerAdapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,list3 );
//                    spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    sFood.setAdapter(spinnerAdapter2);

            }
        });
        sFood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerText1 = (String) parent.getItemAtPosition(position);
                if (!spinnerText1.isEmpty()) {
                    AddtToConsumptionAsyncTask addtToConsumptionAsyncTask = new AddtToConsumptionAsyncTask();
                    addtToConsumptionAsyncTask.execute(spinnerText1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText1 = (EditText) vDailyDiet.findViewById(R.id.editText1);

                FatAndCalorieAsyncTask fatAndCalorieAsyncTask = new FatAndCalorieAsyncTask();
                GoogleSearchAsyncTask googleSearchAsyncTask = new GoogleSearchAsyncTask();
                if (!editText1.getText().toString().isEmpty()) {
                    fatAndCalorieAsyncTask.execute(editText1.getText().toString());
                    googleSearchAsyncTask.execute(editText1.getText().toString());

                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "ENTER TEXT FIRST", Toast.LENGTH_LONG).show();
                }
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 editText1 = (EditText) vDailyDiet.findViewById(R.id.editText1);
                 String name = editText1.getText().toString();
                 AddtToFoodAsyncTask addtToFoodAsyncTask = new AddtToFoodAsyncTask();
                 addtToFoodAsyncTask.execute(name);
            }
        });


        return vDailyDiet;
    }

    private class AddtToFoodAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            if(params[0].isEmpty()){
                return "0";
            }
            String foodId = NutrientDatabaseAPI.getFoodNo(NutrientDatabaseAPI.search(params[0]));
            if(foodId.isEmpty()){
                return "1";
            }
            String result = NutrientDatabaseAPI.getFatAndCalorie(foodId);
            Double cal = Double.valueOf(NutrientDatabaseAPI.getCalorie(result));
            Double fat = Double.valueOf(NutrientDatabaseAPI.getFat(result));
            String measure = NutrientDatabaseAPI.getMeasure(result);
            String[] strs = measure.split(" ");
            Double servingAomunt = Double.valueOf(strs[0]);
            String servingUnit = strs[1];
            Food food = new Food(params[0],category,cal.intValue(),servingUnit,servingAomunt,fat.intValue());
            RestClient.createFood(food);
            return "";

        }

        @Override
        protected void onPostExecute(String result) {
            if(result.isEmpty()){
            Toast.makeText(getActivity().getApplicationContext(), "Food added",Toast.LENGTH_LONG).show();
            }
            else if(result=="1"){
                Toast.makeText(getActivity().getApplicationContext(), "No record. Enter a food again",Toast.LENGTH_LONG).show();
            }
            else{Toast.makeText(getActivity().getApplicationContext(), " Enter a food first",Toast.LENGTH_LONG).show();}
        }
    }

    private class AddtToConsumptionAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            Consumption consumption = null;
            Date date = new Date();
            Integer quantity = 1;
            Integer userId = mainActivity.getUserId();
            Integer foodId = Integer.valueOf(RestClient.findIdByFoodName(spinnerText1));
            System.out.println(" -- " + userId);
            System.out.println(" -- " + foodId);
            User user = new User();
            user.setUserId(userId);
            Food food = new Food();
            food.setFoodId(foodId);


            consumption = new Consumption(date, quantity, food, user);


            RestClient.createConsumption(consumption);
            return "";
        }

        @Override
        protected void onPostExecute(String result) {

        }
    }

    private class GoogleSearchAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String result = "";
            String s = SearchGoogleAPI.search(params[0]);
            String link = SearchGoogleAPI.getLink(SearchGoogleAPI.searchImage(params[0]));
            bitmap = loadBitmap(link);
            result = SearchGoogleAPI.getSnippet(s);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            TextView resultTextView = (TextView) vDailyDiet.findViewById(R.id.tvResult2);
            imageView = (ImageView) vDailyDiet.findViewById(R.id.imageView);
            resultTextView.setText(result);
            imageView.setImageBitmap(bitmap);
        }
    }

    private class FatAndCalorieAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String foodNo = NutrientDatabaseAPI.getFoodNo(NutrientDatabaseAPI.search(params[0]));
            String json = NutrientDatabaseAPI.getFatAndCalorie(foodNo);
            String calorie = "Calorie: " + NutrientDatabaseAPI.getCalorie(json) + "g  ";
            String fat = "Fat: " + NutrientDatabaseAPI.getFat(json) + "kcal";
            String result = calorie + fat;


            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            TextView resultTextView = (TextView) vDailyDiet.findViewById(R.id.tvResult);

            resultTextView.setText(result);
        }
    }

    private class CategoryAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            String food = RestClient.getFood();
            try {
                JSONArray jsonArray = new JSONArray(food);
                for (int i = 0; i < jsonArray.length(); i++) {

                    String s = jsonArray.getJSONObject(i).getString("category");
                    if (!list.contains(s)) {
                        list.add(s);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            final Spinner sCategory = (Spinner) vDailyDiet.findViewById(R.id.spinner);
            final ArrayAdapter<String> spinnerAdapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
            spinnerAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sCategory.setAdapter(spinnerAdapter1);
        }
    }

    private class FoodSelectedAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String result = "";
            category = params[0];
            list2 = new ArrayList<>();
            list2.add("");
            result = RestClient.findFoodByCategory(params[0]);
            try {
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {

                    String s = jsonArray.getJSONObject(i).getString("foodName");

                    list2.add(s);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            final Spinner sFood = (Spinner) vDailyDiet.findViewById(R.id.spinner2);
            final ArrayAdapter<String> spinnerAdapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list2);
            spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sFood.setAdapter(spinnerAdapter2);

        }
    }

    public Bitmap loadBitmap(String url) {
        Bitmap bm = null;
        InputStream is = null;
        BufferedInputStream bis = null;
        try {
            URLConnection conn = new URL(url).openConnection();
            conn.connect();
            is = conn.getInputStream();
            bis = new BufferedInputStream(is, 8192);
            bm = BitmapFactory.decodeStream(bis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bm;
    }

}

