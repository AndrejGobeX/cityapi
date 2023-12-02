package com.cityapi.cityapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class WeatherApiBridge {
    private final static String weatherQuery =
            "https://api.weatherapi.com/v1/current.json?key=<KEY>&q='<CITY>'";
    private final static String weatherApiKey =
            "46c3b90ee84c4b58981130132230112";

    public static String makeQuery(String cityName){
        return weatherQuery
                .replaceAll("<KEY>", weatherApiKey)
                .replaceAll("<CITY>", cityName)
                .replaceAll(" ", "+");
    }

    public static String getResponse(String url) throws IOException, URISyntaxException {
        URL weatherAPIURL = new URI(url).toURL();
        URLConnection connection = weatherAPIURL.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()));

        String inputLine;
        StringBuilder result = new StringBuilder();

        while ((inputLine = in.readLine()) != null)
            result.append(inputLine);
        in.close();

        return result.toString();
    }

    public static Double getTemp(String city) throws IOException, URISyntaxException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(
                getResponse(makeQuery(city))
        );

        Double result = null;

        try{
            result = Double.parseDouble(node.at("/current/temp_c").toString());
        }
        catch (NumberFormatException ignored){}

        return result;
    }
}
