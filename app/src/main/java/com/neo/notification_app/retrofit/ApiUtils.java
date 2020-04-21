package com.neo.notification_app.retrofit;

public class ApiUtils {

    private ApiUtils() {
    }

    private static final String BASE_URL = "http:// the  base url";

    public static RetrofitApi getApiService() {
        return RetrofitClient.getClient(BASE_URL).create(RetrofitApi.class);
    }

}
