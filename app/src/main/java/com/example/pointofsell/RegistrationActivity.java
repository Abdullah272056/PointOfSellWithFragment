package com.example.pointofsell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pointofsell.register.RegistrationData;
import com.example.pointofsell.register.RegistrationResponse;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    EditText companyNameEditText,companyOwnerEditText,emailEditText,phoneEditText,
            addressEditText,aboutCompanyEditText,passwordEditText,confirmPasswordEditText;
    Spinner companyTypeSpinner;
    Button signUpButton;
    TextView signInTextView;

    ApiInterface apiInterface;

    ProgressBar registrationProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // view finding
        companyNameEditText=findViewById(R.id.signUpCompanyNameEditTextId);
        companyOwnerEditText=findViewById(R.id.signUpCompanyOwnerEditTextId);
        emailEditText=findViewById(R.id.signUpEmailEditTextId);
        phoneEditText=findViewById(R.id.signUpPhoneEditTextId);
        addressEditText=findViewById(R.id.signUpAddressEditTextId);
        aboutCompanyEditText=findViewById(R.id.signUpAboutCompanyEditTextId);
        passwordEditText=findViewById(R.id.signUpPasswordEditTextId);
        confirmPasswordEditText=findViewById(R.id.signUpConfirmPasswordEditTextId);
        signUpButton=findViewById(R.id.signUpButtonId);
        companyTypeSpinner=findViewById(R.id.businessTypeSpinnerId);
        signInTextView=findViewById(R.id.signInTextViewId);
        registrationProgressBar=findViewById(R.id.registrationProgressBarId);

        //initialize apiInterface
        apiInterface = RetrofitClient.getRetrofit("http://mern-pos.herokuapp.com/").create(ApiInterface.class);

        signInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  companyName=companyNameEditText.getText().toString().trim();
                String  companyOwner=companyOwnerEditText.getText().toString().trim();
                String  email=emailEditText.getText().toString().trim();
                String  phone=phoneEditText.getText().toString().trim();
                String  address=addressEditText.getText().toString().trim();
                String  aboutCompany=aboutCompanyEditText.getText().toString().trim();
                String  password=passwordEditText.getText().toString().trim();
                String  confirmPassword=confirmPasswordEditText.getText().toString().trim();
                String companyType=companyTypeSpinner.getSelectedItem().toString();


                if (TextUtils.isEmpty(companyName)){
                    companyNameEditText.setError("Enter your company name");
                    companyNameEditText.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(companyOwner)){
                    companyOwnerEditText.setError("Enter your company owner name");
                    companyOwnerEditText.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(email)){
                    emailEditText.setError("Enter your email");
                    emailEditText.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailEditText.setError("Enter a valid  email address");
                    emailEditText.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(phone)){
                    phoneEditText.setError("Enter your phone number");
                    phoneEditText.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(address)){
                    addressEditText.setError("Enter your address");
                    addressEditText.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(aboutCompany)){
                    aboutCompanyEditText.setError("Enter about company");
                    aboutCompanyEditText.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    passwordEditText.setError("Enter your password");
                    passwordEditText.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(confirmPassword)){
                    confirmPasswordEditText.setError("Enter your confirm password");
                    confirmPasswordEditText.requestFocus();
                    return;
                }
                if (!password.equals(confirmPassword)){
                    confirmPasswordEditText.setError("can not matching confirm password");
                    confirmPasswordEditText.requestFocus();
                    return;
                }



                registrationProgressBar.setVisibility(View.VISIBLE);

                final RegistrationData registrationData       =new RegistrationData(companyName,companyOwner,email,companyType,
                        aboutCompany,password,address,phone);
                apiInterface.registrationData(registrationData).enqueue(new Callback<RegistrationResponse>() {
                    @Override
                    public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {

                        if (response.code()==200){
                            Toast.makeText(RegistrationActivity.this,"Please check your email abdullah272056@gmail.com to complete signup process in order to use the application", Toast.LENGTH_LONG).show();
                        }
                        else if (response.code()==500){
                            Toast.makeText(RegistrationActivity.this,"email can not valid" , Toast.LENGTH_LONG).show();
                        }else if(response.code()==400){
                            Toast.makeText(RegistrationActivity.this,"Email already Exits" , Toast.LENGTH_LONG).show();
                        }
                        else  {
                            Toast.makeText(RegistrationActivity.this,"try again", Toast.LENGTH_LONG).show();
                        }

                        registrationProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                        Log.e("wwe",t.getMessage().toString());
                        Toast.makeText(RegistrationActivity.this, "failed ! try again", Toast.LENGTH_SHORT).show();
                        registrationProgressBar.setVisibility(View.GONE);

                    }
                });

            }
        });

    }
}