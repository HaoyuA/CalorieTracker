package com.example.assignment2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class NutrientDatabaseAPI {
    private static final String API_KEY = "vdd04nhgDnyM9KgxngLeXxkq2D5vzJo8cVTjak2c";

    public static String search(String keyword) {

        URL url = null;
        HttpURLConnection connection = null;
        String result = "";

        try {
            url = new URL("https://api.nal.usda.gov/ndb/search/?format=json&sort=n&max=1&offset=0&api_key="+ API_KEY +"&q=" + keyword);
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {
                result += scanner.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return result;

    }
    public static String getFoodNo(String result) {
        String foodNo = "";
        try {
            JSONObject jsonObject = new JSONObject(result);

            foodNo = jsonObject.getJSONObject("list").getJSONArray("item").getJSONObject(0).getString("ndbno");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return foodNo;
    }

    public static String getFatAndCalorie(String foodNo) {

        URL url = null;
        HttpURLConnection connection = null;
        String textResult = "";

        try {
            url = new URL("https://api.nal.usda.gov/ndb/nutrients/?format=json&api_key="+API_KEY+"&nutrients=204&nutrients=208&ndbno="+foodNo);
            connection = (HttpURLConnection)url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {
                textResult += scanner.nextLine();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally{
            connection.disconnect();
        }


        return textResult;

    }
    public static String getFat(String textResult) {

        String fat = "0";

        try {
            JSONObject jsonObject = new JSONObject(textResult);

            fat = jsonObject.getJSONObject("report").getJSONArray("foods").getJSONObject(0).getJSONArray("nutrients").getJSONObject(0).getString("value");


        } catch (Exception e) {
            e.printStackTrace();
        }
        return fat;
    }

    public static String getCalorie(String textResult) {

        String calorie = "0";

        try {
            JSONObject jsonObject = new JSONObject(textResult);


            calorie = jsonObject.getJSONObject("report").getJSONArray("foods").getJSONObject(0).getJSONArray("nutrients").getJSONObject(1).getString("value");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return calorie;
    }

    public static String getMeasure(String textResult) {

        String measure = "";

        try {
            JSONObject jsonObject = new JSONObject(textResult);


            measure = jsonObject.getJSONObject("report").getJSONArray("foods").getJSONObject(0).getString("measure");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return measure;
    }



}
