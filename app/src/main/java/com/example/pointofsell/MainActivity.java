package com.example.pointofsell;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pointofsell.retrofit.ApiInterface;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText signInEmailEditText,signInPasswordEditText;
    Button signInButton;
    TextView signUpTextView,resetPasswordTextView;
    ProgressBar logInProgressBar;

    ApiInterface apiInterface;
    CheckBox rememberCheckBox;
    String signInEmail,signInPassword;

   // SharePref sharePref;

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



    }

    @Override
    public void onClick(View v) {

    }
}