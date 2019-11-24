package com.example.assignment2;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RestClient {
    private static final String BASE_URL =
            "http://10.0.2.2:8080/Assgin1/webresources/";
    public static String findByUserName(String userName) {
        final String methodPath = "assgin1.credential/findByUserName/" + userName; //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
//Making HTTP request
        try {
            url = new URL(BASE_URL + methodPath);
//open the connection
            conn = (HttpURLConnection) url.openConnection();
//set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
//set the connection method to GET
            conn.setRequestMethod("GET");
//add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json"); //Read the response
            Scanner inStream = new Scanner(conn.getInputStream()); //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace(); }
            finally {
            conn.disconnect();
        }
        return textResult;
    }

    public static String findByEmail(String email) {
        final String methodPath = "assgin1.usertable/findByEmail/" + email; //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
//Making HTTP request
        try {
            url = new URL(BASE_URL + methodPath);
//open the connection
            conn = (HttpURLConnection) url.openConnection();
//set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
//set the connection method to GET
            conn.setRequestMethod("GET");
//add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json"); //Read the response
            Scanner inStream = new Scanner(conn.getInputStream()); //read the input steream and store it as string
            while (inStream.hasNextLine()) { textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace(); }
        finally {
            conn.disconnect();
        }
        return textResult;
    }

    public static String findByUserId(Integer id) {
        final String methodPath = "assgin1.usertable/" + id; //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
//Making HTTP request
        try {
            url = new URL(BASE_URL + methodPath);
//open the connection
            conn = (HttpURLConnection) url.openConnection();
//set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
//set the connection method to GET
            conn.setRequestMethod("GET");
//add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json"); //Read the response
            Scanner inStream = new Scanner(conn.getInputStream()); //read the input steream and store it as string
            while (inStream.hasNextLine()) { textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace(); }
        finally {
            conn.disconnect();
        }
        return textResult;
    }

    public static String findFoodByCategory(String Category){
        final String methodPath = "assgin1.food/findByCategory/" + Category; //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
//Making HTTP request
        try {
            url = new URL(BASE_URL + methodPath);
//open the connection
            conn = (HttpURLConnection) url.openConnection();
//set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
//set the connection method to GET
            conn.setRequestMethod("GET");
//add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json"); //Read the response
            Scanner inStream = new Scanner(conn.getInputStream()); //read the input steream and store it as string
            while (inStream.hasNextLine()) { textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace(); }
        finally {
            conn.disconnect();
        }
        return textResult;
    }

    public static String getFood(){
        final String methodPath = "assgin1.food"; //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
//Making HTTP request
        try {
            url = new URL(BASE_URL + methodPath);
//open the connection
            conn = (HttpURLConnection) url.openConnection();
//set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
//set the connection method to GET
            conn.setRequestMethod("GET");
//add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json"); //Read the response
            Scanner inStream = new Scanner(conn.getInputStream()); //read the input steream and store it as string
            while (inStream.hasNextLine()) { textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace(); }
        finally {
            conn.disconnect();
        }
        return textResult;
    }

    public static String getPostCode(String textResult){
        String postcode = null;
        try{
            JSONObject jsonObject = new JSONObject(textResult);
//            if(jsonArray != null && jsonArray.length() > 0) {
            postcode =jsonObject.getString("postcode");
//            }
        }catch (Exception e){
            e.printStackTrace();
            postcode = "NO INFO FOUND";
        }
        return postcode;
    }

    public static String getAddress(String textResult){
        String address = null;
        try{
            JSONObject jsonObject = new JSONObject(textResult);
//            if(jsonArray != null && jsonArray.length() > 0) {
            address =jsonObject.getString("address");
//            }
        }catch (Exception e){
            e.printStackTrace();
            address = "NO INFO FOUND";
        }
        return address;
    }

    public static String getPassword(String textResult){
        String password = null;
        try{
            JSONArray jsonArray = new JSONArray(textResult);
//            if(jsonArray != null && jsonArray.length() > 0) {
            password =jsonArray.getJSONObject(0).getString("password").toString();
//            }
        }catch (Exception e){
            e.printStackTrace();
            password = "NO INFO FOUND";
        }
        return password;
    }

    public static Integer getUserId(String textResult){
        Integer userId = 0;
        try{
            JSONArray jsonArray = new JSONArray(textResult);
//            if(jsonArray != null && jsonArray.length() > 0) {
            userId =jsonArray.getJSONObject(0).getInt("userId");
//            }
        }catch (Exception e){
            e.printStackTrace();

        }

        return userId;
    }

    public static String findIdByFoodName(String foodName){
        final String methodPath = "assgin1.food/findIdByFoodName/" + foodName; //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
//Making HTTP request
        try {
            url = new URL(BASE_URL + methodPath);
//open the connection
            conn = (HttpURLConnection) url.openConnection();
//set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
//set the connection method to GET
            conn.setRequestMethod("GET");
//add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "text/plain");
            conn.setRequestProperty("Accept", "text/plain"); //Read the response
            Scanner inStream = new Scanner(conn.getInputStream()); //read the input steream and store it as string
            while (inStream.hasNextLine()) { textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace(); }
        finally {
            conn.disconnect();
        }
        return textResult;
    }

    public static String findByUserIdANDReportDate(String userId,String date){

            final String methodPath = "assgin1.report/findByUserIdANDReportDate/" + userId +"/"+ date; //initialise
            URL url = null;
            HttpURLConnection conn = null;
            String textResult = "";
//Making HTTP request
            try {
                url = new URL(BASE_URL + methodPath);
//open the connection
                conn = (HttpURLConnection) url.openConnection();
//set the timeout
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
//set the connection method to GET
                conn.setRequestMethod("GET");
//add http headers to set your response type to json
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept", "application/json"); //Read the response
                Scanner inStream = new Scanner(conn.getInputStream()); //read the input steream and store it as string
                while (inStream.hasNextLine()) { textResult += inStream.nextLine();
                }
            } catch (Exception e) {
                e.printStackTrace(); }
            finally {
                conn.disconnect();
            }
            return textResult;

    }

    public static String addUpCalorieAndStep(String userId,String startDate,String endDate){
        final String methodPath = "assgin1.report/addUpCalorieAndStep/" + userId +"/"+ startDate +"/"+endDate; //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
//Making HTTP request
        try {
            url = new URL(BASE_URL + methodPath);
//open the connection
            conn = (HttpURLConnection) url.openConnection();
//set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
//set the connection method to GET
            conn.setRequestMethod("GET");
//add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json"); //Read the response
            Scanner inStream = new Scanner(conn.getInputStream()); //read the input steream and store it as string
            while (inStream.hasNextLine()) { textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace(); }
        finally {
            conn.disconnect();
        }
        return textResult;

    }

    public static String findByUserIdANDConsumptionDate(String userId,String date){

        final String methodPath = "assgin1.consumption/findByUserIdANDConsumptionDate/" + userId +"/"+ date; //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
//Making HTTP request
        try {
            url = new URL(BASE_URL + methodPath);
//open the connection
            conn = (HttpURLConnection) url.openConnection();
//set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
//set the connection method to GET
            conn.setRequestMethod("GET");
//add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json"); //Read the response
            Scanner inStream = new Scanner(conn.getInputStream()); //read the input steream and store it as string
            while (inStream.hasNextLine()) { textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace(); }
        finally {
            conn.disconnect();
        }
        return textResult;

    }

    public static String getCalorieBurnedRest(String userId){
        final String methodPath = "assgin1.usertable/calculateTotalDailyCaloriesBurned/"+ userId;
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
//Making HTTP request
        try {
            url = new URL(BASE_URL + methodPath);
//open the connection
            conn = (HttpURLConnection) url.openConnection();
//set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
//set the connection method to GET
            conn.setRequestMethod("GET");
//add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "text/plain");
            conn.setRequestProperty("Accept", "text/plain"); //Read the response
            Scanner inStream = new Scanner(conn.getInputStream()); //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
            textResult = "1";
        }
        finally {
            conn.disconnect();
        }
        return textResult;

    }

    public static String getCaloriesBurnedPerStep(String userId){
        final String methodPath = "assgin1.usertable/calculateCaloriesBurnedPerStep/"+ userId;
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
//Making HTTP request
        try {
            url = new URL(BASE_URL + methodPath);
//open the connection
            conn = (HttpURLConnection) url.openConnection();
//set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
//set the connection method to GET
            conn.setRequestMethod("GET");
//add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "text/plain");
            conn.setRequestProperty("Accept", "text/plain"); //Read the response
            Scanner inStream = new Scanner(conn.getInputStream()); //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
            textResult = "0.5";
        }
        finally {
            conn.disconnect();
        }
        return textResult;

    }

    public static String getFirstName(String textResult){
        String firstName = null;
        try{
            JSONObject jsonObject = new JSONObject(textResult);
//            if(jsonArray != null && jsonArray.length() > 0) {
            firstName =jsonObject.getString("name");
//            }
        }catch (Exception e){
            e.printStackTrace();
            firstName = "NO INFO FOUND";
        }
        return firstName;
    }

    public static String getTotalCalorieConsumed(String textResult){
        String totalCalorieConsumed = null;
        try{
            JSONArray jsonArray = new JSONArray(textResult);
            totalCalorieConsumed = jsonArray.getJSONObject(0).getString("totalCaloriesConsumed");

        }catch (Exception e){
            e.printStackTrace();
            totalCalorieConsumed = "1";
        }
        return totalCalorieConsumed;
    }

    public static String getTotalStepsTaken(String textResult){
        String totalStepsTaken = null;
        try{
            JSONArray jsonArray = new JSONArray(textResult);
            totalStepsTaken = jsonArray.getJSONObject(0).getString("totalStepsTaken");

        }catch (Exception e){
            e.printStackTrace();
            totalStepsTaken = "1000";
        }
        return totalStepsTaken;
    }

    public static String getCalorieGoal(String textResult){
        String calorieGoal = null;
        try{
            JSONArray jsonArray = new JSONArray(textResult);
            calorieGoal = jsonArray.getJSONObject(0).getString("setCalorieGoal");

        }catch (Exception e){
            e.printStackTrace();
            calorieGoal = "1";
        }
        return calorieGoal;
    }

    public static void createUser(User user) { //initialise
        URL url = null;
        HttpURLConnection conn = null;
        final String methodPath = "assgin1.usertable";
        try {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").create();
            String stringUserJson = gson.toJson(user);
            url = new URL(BASE_URL + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to POST
            conn.setRequestMethod("POST");
            //set the output to true
            conn.setDoOutput(true);
            //set length of the data you want to send
            conn.setFixedLengthStreamingMode(stringUserJson.getBytes().length); //add HTTP headers
            conn.setRequestProperty("Content-Type", "application/json");
            //Send the POST out
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(stringUserJson);
            out.close();
            Log.i("error", new Integer(conn.getResponseCode()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }

    public static void createCredential(Credential credential){ //initialise
        URL url = null;
        HttpURLConnection conn = null;
        final String methodPath="assgin1.credential";
        try {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").create();
            String stringCredentialJson=gson.toJson(credential); url = new URL(BASE_URL + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to POST
            conn.setRequestMethod("POST");
            //set the output to true
            conn.setDoOutput(true);
            //set length of the data you want to send
            conn.setFixedLengthStreamingMode(stringCredentialJson.getBytes().length); //add HTTP headers
            conn.setRequestProperty("Content-Type", "application/json");
            //Send the POST out
            PrintWriter out= new PrintWriter(conn.getOutputStream()); out.print(stringCredentialJson);
            out.close();
            Log.i("error",new Integer(conn.getResponseCode()).toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            conn.disconnect();
        }
    }

    public static void createFood(Food food){
        URL url = null;
        HttpURLConnection conn = null;
        final String methodPath="assgin1.food";
        try {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").create();
            String stringFoodJson=gson.toJson(food); url = new URL(BASE_URL + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to POST
            conn.setRequestMethod("POST");
            //set the output to true
            conn.setDoOutput(true);
            //set length of the data you want to send
            conn.setFixedLengthStreamingMode(stringFoodJson.getBytes().length); //add HTTP headers
            conn.setRequestProperty("Content-Type", "application/json");
            //Send the POST out
            PrintWriter out= new PrintWriter(conn.getOutputStream()); out.print(stringFoodJson);
            out.close();
            Log.i("error",new Integer(conn.getResponseCode()).toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            conn.disconnect();
        }
    }

    public static void createReport(Report report){
        URL url = null;
        HttpURLConnection conn = null;
        final String methodPath="assgin1.report";
        try {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").create();
            String stringReportJson=gson.toJson(report); url = new URL(BASE_URL + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to POST
            conn.setRequestMethod("POST");
            //set the output to true
            conn.setDoOutput(true);
            //set length of the data you want to send
            conn.setFixedLengthStreamingMode(stringReportJson.getBytes().length); //add HTTP headers
            conn.setRequestProperty("Content-Type", "application/json");
            //Send the POST out
            PrintWriter out= new PrintWriter(conn.getOutputStream()); out.print(stringReportJson);
            out.close();
            Log.i("error",new Integer(conn.getResponseCode()).toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            conn.disconnect();
        }
    }

    public static void createConsumption(Consumption consumption){
        URL url = null;
        HttpURLConnection conn = null;
        final String methodPath="assgin1.consumption";
        try {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").create();
            String stringReportJson=gson.toJson(consumption);
            url = new URL(BASE_URL + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to POST
            conn.setRequestMethod("POST");
            //set the output to true
            conn.setDoOutput(true);
            //set length of the data you want to send
            conn.setFixedLengthStreamingMode(stringReportJson.getBytes().length); //add HTTP headers
            conn.setRequestProperty("Content-Type", "application/json");
            //Send the POST out
            PrintWriter out= new PrintWriter(conn.getOutputStream());
            out.print(stringReportJson);
            out.close();
            Log.i("error",new Integer(conn.getResponseCode()).toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            conn.disconnect();
        }
    }

}

