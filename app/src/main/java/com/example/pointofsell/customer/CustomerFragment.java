package com.example.pointofsell.customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointofsell.R;
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

public class CustomerFragment extends Fragment {
    RecyclerView customerRecyclerView;
    FloatingActionButton addCustomerButton;


    List<CustomerInformationData> customerInformationList;
    String token;

    ApiInterface apiInterface;
    EditText customerNameEditText,customerEmailEditText,customerPhoneEditText,customerAddressEditText;
    Button addCustomerDataButton,cancelCustomerButton;
    //CustomerData customerData;
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

        apiInterface = RetrofitClient.getRetrofit("http://mern-pos.herokuapp.com/").create(ApiInterface.class);

        getAllCustomer();

        return view;

    }


    public void getAllCustomer(){
        apiInterface.getAllCustomerInformation("Bearer "+token).enqueue(new Callback<CustomerInformationDataResponse>() {
            @Override
            public void onResponse(Call<CustomerInformationDataResponse> call, Response<CustomerInformationDataResponse> response) {
                CustomerInformationDataResponse customerInformationDataResponse=response.body();
                if (response.code()==500){
                    Toast.makeText(getActivity(), "Cannot read property 'id' of null", Toast.LENGTH_SHORT).show();
                }
                else if (response.code()==200){
                    customerInformationList=new ArrayList<>();
                    customerInformationList.addAll(response.body().getCustomerInformation());
                    if (customerInformationList.size ()>0){
                       // Toast.makeText(getActivity(), String.valueOf(customerInformationList.size()), Toast.LENGTH_SHORT).show();
                        customerCustomAdapter = new CustomerCustomAdapter(getActivity(),token,customerInformationList);
                        customerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        customerRecyclerView.setAdapter(customerCustomAdapter);
                    }
                }
                else if (response.code()==404){
                    Toast.makeText(getActivity(), "No customer found", Toast.LENGTH_SHORT).show();
                }else {

                }

                mainProgressBar.setVisibility(View.GONE);

            }
            @Override
            public void onFailure(Call<CustomerInformationDataResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "fail:  "+t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                mainProgressBar.setVisibility(View.GONE);
            }
        });


    }
  }
