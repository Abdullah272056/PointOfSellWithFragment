package com.example.pointofsell.customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointofsell.R;
import com.example.pointofsell.customer.get_customer.CustomerInformationData;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CustomerFragment extends Fragment {
    RecyclerView customerRecyclerView;
    FloatingActionButton addCustomerButton;

    // List<CustomerInformationData> customerInformationDataList;
    List<CustomerInformationData> customerInformationList;
    String token;

    ApiInterface apiInterface;
    EditText customerNameEditText,customerEmailEditText,customerPhoneEditText,customerAddressEditText;
    Button addCustomerDataButton,cancelCustomerButton;
    //CustomerData customerData;
    ProgressBar progressBar,mainProgressBar;
    //CustomerCustomAdapter customerCustomAdapter;
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



        return view;

    }
  }
