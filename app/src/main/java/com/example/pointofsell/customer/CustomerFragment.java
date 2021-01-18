package com.example.pointofsell.customer;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointofsell.R;
import com.example.pointofsell.customer.create_customer.AddCustomerResponse;
import com.example.pointofsell.customer.create_customer.CustomerData;
import com.example.pointofsell.customer.get_customer.CustomerCustomAdapter;
import com.example.pointofsell.customer.get_customer.CustomerInformationData;
import com.example.pointofsell.customer.get_customer.CustomerInformationDataResponse;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerFragment extends Fragment  {
    RecyclerView customerRecyclerView;
    FloatingActionButton addCustomerButton;
    ImageView backImageView;


    List<CustomerInformationData> customerInformationList;
    String token;

    ApiInterface apiInterface;
    EditText customerNameEditText,customerEmailEditText,customerPhoneEditText,customerAddressEditText;
    Button addCustomerDataButton,cancelCustomerButton;
    CustomerData customerData;
    ProgressBar progressBar,mainProgressBar;
    CustomerCustomAdapter customerCustomAdapter;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.customer_fragment,container,false);

        //receive token
        Bundle bundle=this.getArguments();
        token=bundle.getString("token");

        mainProgressBar=view.findViewById(R.id.customerProgressBarId);
        mainProgressBar.setVisibility(View.VISIBLE);
        customerRecyclerView=view.findViewById(R.id.customerRecyclerViewId);
        addCustomerButton=view.findViewById(R.id.addCustomerButtonId);
        backImageView=view.findViewById(R.id.backImageViewId);

        apiInterface = RetrofitClient.getRetrofit("http://mern-pos.herokuapp.com/").create(ApiInterface.class);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT).show();
            }
        });
        addCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCustomerInformation();
            }
        });
        getAllCustomer();


        return view;

    }


    public void getAllCustomer(){
        apiInterface.getAllCustomerInformation("Bearer "+token).enqueue(new Callback<CustomerInformationDataResponse>() {
            @Override
            public void onResponse(Call<CustomerInformationDataResponse> call, Response<CustomerInformationDataResponse> response) {

                if(getActivity() != null){
                CustomerInformationDataResponse customerInformationDataResponse = response.body();
                if (response.code() == 500) {
                    Toast.makeText(getActivity(), "Cannot read property 'id' of null", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 200) {
                    customerInformationList = new ArrayList<>();
                    customerInformationList.addAll(response.body().getCustomerInformation());
                    if (customerInformationList.size() > 0) {
                        if (getActivity() != null){
                            Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                        customerCustomAdapter = new CustomerCustomAdapter(getActivity(), token, customerInformationList);
                        customerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        customerRecyclerView.setAdapter(customerCustomAdapter);
                    }

                    }
                } else if (response.code() == 404) {
                    Toast.makeText(getActivity(), "No customer found", Toast.LENGTH_SHORT).show();
                } else {

                }

                mainProgressBar.setVisibility(View.GONE);
            }
            }
            @Override
            public void onFailure(Call<CustomerInformationDataResponse> call, Throwable t) {
                if(getActivity() != null) {
                    Toast.makeText(getActivity(), "fail:  " + t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    mainProgressBar.setVisibility(View.GONE);
                }
            }
        });


    }

    private void addCustomerInformation(){

        AlertDialog.Builder builder     =new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater   =LayoutInflater.from(getActivity());
        View view                       =layoutInflater.inflate(R.layout.add_customer_data,null);
        builder.setView(view);
        final AlertDialog alertDialog   = builder.create();

        customerNameEditText=view.findViewById(R.id.customerNameEditTextId);
        customerPhoneEditText=view.findViewById(R.id.customerPhoneEditTextId);
        customerEmailEditText=view.findViewById(R.id.customerEmailEditTextId);
        customerAddressEditText=view.findViewById(R.id.customerAddressEditTextId);
        progressBar=view.findViewById(R.id.newCustomerProgressBarId);

        addCustomerDataButton=view.findViewById(R.id.saveCustomerDataButtonId);
        cancelCustomerButton=view.findViewById(R.id.cancelCustomerDataButtonId);
        addCustomerDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customerName=customerNameEditText.getText().toString();
                String customerPhone=customerPhoneEditText.getText().toString();
                String customerEmail=customerEmailEditText.getText().toString();
                String customerAddress=customerAddressEditText.getText().toString();

                if (TextUtils.isEmpty(customerName) || customerAddress==null){
                    customerNameEditText.setError("Enter customer name");
                    customerNameEditText.requestFocus();
                    return;
                }
                if (customerName.length()<4){
                    customerNameEditText.setError("don't smaller than 4 character");
                    customerNameEditText.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(customerPhone)|| customerPhone==null){
                    customerPhoneEditText.setError("Enter customer phone");
                    customerPhoneEditText.requestFocus();
                    return;
                }
                if (customerPhone.length()<8){
                    customerPhoneEditText.setError("don't smaller than 8 character");
                    customerPhoneEditText.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(customerAddress) ||customerAddress==null){
                    customerAddressEditText.setError("Enter customer name");
                    customerAddressEditText.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(customerEmail)|| customerEmail==null){
                    customerData=new CustomerData(customerName,customerPhone,customerAddress);

                }
                if (!TextUtils.isEmpty(customerEmail ) && customerEmail!=null){
                    if (!Patterns.EMAIL_ADDRESS.matcher(customerEmail).matches()){
                        customerEmailEditText.setError("Enter a valid  email address");
                        customerEmailEditText.requestFocus();
                        return;
                    }else {
                        customerData=new CustomerData(customerName,customerPhone,customerEmail,customerAddress);
                    }
                }
                progressBar.setVisibility(View.VISIBLE);
                apiInterface.addCustomerInformation("Bearer "+token,customerData).enqueue(
                        new Callback<AddCustomerResponse>() {
                            @Override
                            public void onResponse(Call<AddCustomerResponse> call, Response<AddCustomerResponse> response) {
                                if (getActivity()!=null){
                                if (response.code()==201){
                                    Toast.makeText(getActivity(), "add successful", Toast.LENGTH_SHORT).show();
                                    alertDialog.dismiss();
                                }else if (response.code()==400){
                                    Toast.makeText(getActivity(), "safe phone number", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getActivity(), "failed", Toast.LENGTH_SHORT).show();
                                }
                                progressBar.setVisibility(View.GONE);
                                getAllCustomer();
                                }
                            }
                            @Override
                            public void onFailure(Call<AddCustomerResponse> call, Throwable t) {
                                if (getActivity()!=null) {
                                    Toast.makeText(getActivity(), "fail:  " + t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        }
                );
            }
        });
        cancelCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                progressBar.setVisibility(View.GONE);
            }
        });
        alertDialog.show();

    }



}
