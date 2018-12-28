package com.lyarc.tp.corp.common.config;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpClient {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static Logger logger = LoggerFactory.getLogger(HttpClient.class);

    public static String get(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        return getResponse(client, request);
    }

    public static String post(String url, String parmsJson) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, parmsJson);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        return getResponse(client, request);
    }

    private static String getResponse(OkHttpClient client, Request request) {
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

}
