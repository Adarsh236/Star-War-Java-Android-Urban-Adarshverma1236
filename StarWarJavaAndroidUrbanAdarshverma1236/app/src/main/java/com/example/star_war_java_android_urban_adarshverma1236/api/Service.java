package com.example.star_war_java_android_urban_adarshverma1236.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Service {
    @GET("people/")
    Call<Character> getPeople(@Query("page") int pageIndex);

}
