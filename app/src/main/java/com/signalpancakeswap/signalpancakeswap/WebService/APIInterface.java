package com.signalpancakeswap.signalpancakeswap.WebService;


import com.signalpancakeswap.signalpancakeswap.models.signal.getSignalModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("get.php")
    Call<List<getSignalModel>> getSignals(@Query("date") String date);

    @GET("index.php")
    Call<Boolean> sendEmail(@Query("message") String message);

}