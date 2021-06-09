package ru.sviridov.lesson6;

import okhttp3.*;

import java.io.IOException;

public class Homework_6 {
    private static final String SPB_KEY = "474212_PC";
    private static final String API_KEY = "vk1UB9AllxTLX8DKSl5bATFJoeJQkgsd";

    public static void main(String[] args) throws IOException, NullPointerException {
        OkHttpClient client = new OkHttpClient();

        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegments("forecasts/v1/daily/5day")
                .addPathSegment(SPB_KEY)
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("language", "ru-ru")
                .addQueryParameter("details", "false")
                .addQueryParameter("metric", "true") // для цельсия
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String jsonResponse = response.body().string();
        String[] tokens = jsonResponse.split("DailyForecasts");
        String[] onlyForecasts = tokens[1].split("\"Date\":");
        for (int i = 1; i < onlyForecasts.length; i++) {
            System.out.println();
            System.out.println(onlyForecasts[i]);
        }
    }
}
