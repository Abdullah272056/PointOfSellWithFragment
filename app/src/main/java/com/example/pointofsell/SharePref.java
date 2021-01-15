package com.example.pointofsell;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePref {

    public void saveDetails(Context context,String email,String password){
        SharedPreferences sharedPreferences=context.getSharedPreferences("details", context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("email",email);
        editor.putString("password",password);
        editor.commit();
    }



    public String loadEmail(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("details",context.MODE_PRIVATE);
        String email=sharedPreferences.getString("email","");
        return email;
    }


    public String loadPassword(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("details",context.MODE_PRIVATE);
        String password=sharedPreferences.getString("password","");
        return password;
    }


    public void rememberData(Context context,String rememberEmail,String rememberPassword){
        SharedPreferences sharedPreferences=context.getSharedPreferences("rememberData", context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("rememberEmail",rememberEmail);
        editor.putString("rememberPassword",rememberPassword);
        editor.commit();
    }


    public String loadRememberEmail(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("rememberData",context.MODE_PRIVATE);
        String rememberEmail=sharedPreferences.getString("rememberEmail","");
        return rememberEmail;
    }


    public String loadRememberPassword(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("rememberData",context.MODE_PRIVATE);
        String rememberPassword=sharedPreferences.getString("rememberPassword","");
        return rememberPassword;
    }


}
