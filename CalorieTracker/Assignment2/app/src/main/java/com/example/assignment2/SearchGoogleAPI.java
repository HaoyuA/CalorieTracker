package com.example.assignment2;


import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class SearchGoogleAPI {
    private static final String API_KEY = "";
    private static final String SEARCH_ID_cx = "000684672915535320929:glthmkw6-yw";
    public static String search(String keyword) {
        URL url = null;
        HttpURLConnection connection = null;
        String textResult = "";

        try {
            url = new URL("https://www.googleapis.com/customsearch/v1?key="+ API_KEY + "&cx="+ SEARCH_ID_cx +
                    "&q=" + keyword+ "&siteSearch=en.wikipedia.org/" );
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

    public static String searchImage(String keyword) {
        URL url = null;
        HttpURLConnection connection = null;
        String textResult = "";

        try {
            url = new URL("https://www.googleapis.com/customsearch/v1?key="+ API_KEY + "&cx="+ SEARCH_ID_cx +
                    "&q=" + keyword+ "&siteSearch=en.wikipedia.org/"+"&searchType=image"+"&exactTerms=food" );
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

    public static String getLink(String result){

        String link = null;
        try{
            JSONObject jsonObject = new JSONObject(result);

                link =jsonObject.getJSONArray("items").getJSONObject(0).getString("link");

        }catch (Exception e){ e.printStackTrace();
            link = "NO INFO FOUND";
        }
        return link;
    }



    public static String getSnippet(String result){
        String snippet = null;
        try{
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            if(jsonArray != null && jsonArray.length() > 0) {
                snippet =jsonArray.getJSONObject(0).getString("snippet");
            }
        }catch (Exception e){ e.printStackTrace();
            snippet = "NO INFO FOUND";
        }
        return snippet;
    }
}
