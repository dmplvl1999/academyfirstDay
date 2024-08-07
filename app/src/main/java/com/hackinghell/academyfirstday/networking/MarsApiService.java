package com.hackinghell.academyfirstday.networking;

import retrofit2.Call;
import  retrofit2.Retrofit;
import retrofit2.http.GET;

public interface MarsApiService {

    @GET("photos")
    Call<String> getPhotos();

    class MarsApi {

        private static final String BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com";

        private static final Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).build();

        public static final MarsApiService retrofitService = retrofit.create(MarsApiService.class);
    }
 }