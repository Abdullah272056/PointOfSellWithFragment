package com.example.pointofsell;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pointofsell.log_in.LogInData;
import com.example.pointofsell.log_in.LogInResponse;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText signInEmailEditText,signInPasswordEditText;
    Button signInButton;
    TextView signUpTextView,resetPasswordTextView;
    ProgressBar logInProgressBar;

    ApiInterface apiInterface;
    CheckBox rememberCheckBox;
    String signInEmail,signInPassword;

    SharePref sharePref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //view finding
        signInEmailEditText=findViewById(R.id.signInEmailEditTextId);
        signInPasswordEditText=findViewById(R.id.signInPasswordEditTextId);
        signInButton=findViewById(R.id.signInButtonId);
        signUpTextView=findViewById(R.id.signUpTextViewId);
        resetPasswordTextView=findViewById(R.id.resetPasswordTextViewId);
        logInProgressBar=findViewById(R.id.signInProgressBarId);
        rememberCheckBox=findViewById(R.id.rememberCheckBoxId);



        // listener set
        signInButton.setOnClickListener(this);
        signUpTextView.setOnClickListener(this);
        resetPasswordTextView.setOnClickListener(this);

        //initialize apiInterface
        apiInterface = RetrofitClient.getRetrofit("http://mern-pos.herokuapp.com/").create(ApiInterface.class);


        // remember data retrieve and checking
        sharePref=new SharePref();
        String emailValue= sharePref.loadRememberEmail(LoginActivity.this);
        String passwordValue= sharePref.loadRememberPassword(LoginActivity.this);

        if (!emailValue.isEmpty() && !passwordValue.isEmpty()){
            signInEmailEditText.setText(String.valueOf(emailValue));
            signInPasswordEditText.setText(String.valueOf(passwordValue));
            signIn();
        }
        else {
            // Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signInButtonId:
                signIn();

                break;
            case R.id.signUpTextViewId:
                Intent intent=new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);


                break;
            case R.id.resetPasswordTextViewId:

                break;
        }
    }

    private void signIn() {

        signInEmail= signInEmailEditText.getText().toString().trim();
        signInPassword= signInPasswordEditText.getText().toString().trim();
        if (TextUtils.isEmpty(signInEmail)){
            signInEmailEditText.setError("Enter your email");
            signInEmailEditText.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(signInEmail).matches()){
            signInEmailEditText.setError("Enter a valid  email address");
            signInEmailEditText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(signInPassword)){
            signInPasswordEditText.setError("Enter  password");
            signInPasswordEditText.requestFocus();
            return;
        }
        if (signInPassword.length()<6){
            signInPasswordEditText.setError("Minimum length of a password should be 6");
            signInPasswordEditText.requestFocus();
            return;
        }


        LogInData logInData       =new LogInData(signInEmail,signInPassword);
        logInProgressBar.setVisibility(View.VISIBLE);

        apiInterface.logInData(logInData).enqueue(new Callback<LogInResponse>() {
            @Override
            public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {

                if (response.code()==200){
                    if (response.body().getSuccess()==true){

                        sharePref=new SharePref();
                        if (rememberCheckBox.isChecked()){
                            sharePref.rememberData(LoginActivity.this,signInEmail,signInPassword);
                            Toast.makeText(LoginActivity.this, "success", Toast.LENGTH_SHORT).show();
                        }

                        if(!LoginActivity.this.isFinishing()){
                        Intent intent=new Intent(LoginActivity.this, HomePage.class);
                        intent.putExtra("token",response.body().getToken());
                        startActivity(intent);
                        finish();
                        }

                    }
                    Log.e("res",response.body().getToken().toString());
                }
                else if (response.code()==404){
                    signInEmailEditText.setError("User not registered");
                    signInEmailEditText.requestFocus();
                    Toast.makeText(LoginActivity.this, "User not found", Toast.LENGTH_SHORT).show();

                }
                else if (response.code()==400){
                    Toast.makeText(LoginActivity.this, "password incorrect", Toast.LENGTH_SHORT).show();
                    signInPasswordEditText.setError("incorrect password");
                    signInPasswordEditText.requestFocus();
                }else {
                    Toast.makeText(LoginActivity.this, "Some error! try again", Toast.LENGTH_SHORT).show();
                }

                logInProgressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<LogInResponse> call, Throwable t) {
                if(getApplication() != null){
                Toast.makeText(LoginActivity.this, "login fail ! Try again", Toast.LENGTH_SHORT).show();
                Log.e("lee",t.getMessage());
                logInProgressBar.setVisibility(View.GONE);

            }
            }
        });

    }

    @Override
    public void onBackPressed() {
        LoginActivity.this.finish();
    }

}