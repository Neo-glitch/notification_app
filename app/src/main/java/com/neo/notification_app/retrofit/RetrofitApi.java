package com.neo.notification_app.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitApi {

    @POST("/end point")
    Call<LoginPostResponse> login(@Body PostRequests postRequests);

    @GET("/end_point")
    Call<List<PendingTasksResponse>> getPendingData();

    @GET("/end_point")
    Call<List<CompletedTasksResponse>> getCompletedData();


}
