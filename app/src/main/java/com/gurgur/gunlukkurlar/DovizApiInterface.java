package com.gurgur.gunlukkurlar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface DovizApiInterface {
    @Headers("Content-Type: application/json")
    @GET("today.json")
    Call<ResponseBody> OgrencileriAl();
}
