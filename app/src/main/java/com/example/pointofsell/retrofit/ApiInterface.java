package com.example.pointofsell.retrofit;

import com.example.pointofsell.log_in.LogInData;
import com.example.pointofsell.log_in.LogInResponse;
import com.example.pointofsell.register.RegistrationData;
import com.example.pointofsell.register.RegistrationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    // registration
    @POST("api/auth/register/")
    Call<RegistrationResponse> registrationData(@Body RegistrationData registrationData);

    //signIn
    @POST("api/auth/login/")
    Call<LogInResponse>logInData(@Body LogInData logInData);
}
