package com.example.star_war_java_android_urban_adarshverma1236.api;

import com.example.star_war_java_android_urban_adarshverma1236.model.CharactersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET("people/")
    Call<CharactersResponse> getPeople(@Query("page") int pageIndex);

}
