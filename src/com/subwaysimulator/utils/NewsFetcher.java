package com.subwaysimulator.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NewsFetcher {

    private static final String API_KEY = "YOUR_API_KEY"; // Replace with your NewsCatcher API key

    public static String fetchNews(String query, String apiKey) throws Exception {
        String urlString = "https://api.newscatcherapi.com/v2/search?q=" + query + "&api_key=" + apiKey;
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        conn.disconnect();

        return content.toString();
    }
}
