package com.example.assignment2;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GeocodingAPI {
    private static final String API_KEY = "AIzaSyCzYqyJEJUL20LMUXS-wG7RtZysMlMbcMM";

    public static String search(String address,String postcode) {
        URL url = null;
        HttpURLConnection connection = null;
        String textResult = "";

        try {
            url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" +
                  address +"postal_code="+postcode+ "&key="+API_KEY );
            connection = (HttpURLConnection)url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {
                textResult += scanner.nextLine(); }
        }catch (Exception e){ e.printStackTrace();
        }finally{ connection.disconnect();
        }
        return textResult;
    }

    public static String getlat(String result){

        String lat = null;
        try{
            JSONObject jsonObject = new JSONObject(result);

            lat =jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getString("lat");

        }catch (Exception e){
            e.printStackTrace();
            lat = "NO INFO FOUND";
        }
        return lat;
    }

    public static String getlng(String result){

        String lng = null;
        try{
            JSONObject jsonObject = new JSONObject(result);

            lng =jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getString("lng");

        }catch (Exception e){ e.printStackTrace();
            lng = "NO INFO FOUND";
        }
        return lng;
    }
}
