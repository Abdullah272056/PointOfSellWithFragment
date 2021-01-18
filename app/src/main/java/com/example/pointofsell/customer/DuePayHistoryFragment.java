package com.example.pointofsell.customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointofsell.R;
import com.example.pointofsell.customer.single_customer.SingleCustomerDuePayCustomAdapter;
import com.example.pointofsell.customer.single_customer.SingleCustomerDuePayHistory;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;

import java.util.List;

public class DuePayHistoryFragment extends Fragment {
    String customer_id,token;
    SingleCustomerDuePayCustomAdapter singleCustomerDuePayCustomAdapter;
    List<SingleCustomerDuePayHistory> singleCustomerDuePayHistoryList;

    RecyclerView duePayHistoryRecyclerView;
    ApiInterface apiInterface;
    ProgressBar pauDueHistoryProgressBar;


   View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.customer_fragment, container, false);

        //receive data
        Bundle bundle=this.getArguments();
        customer_id= bundle.getString("customerId");
        token=bundle.getString("token");
        
        //recycler view finding
        duePayHistoryRecyclerView=view.findViewById(R.id.duePayHistoryRecyclerViewId);
        //ProgressBar finding
        pauDueHistoryProgressBar=view.findViewById(R.id.pauDueHistoryProgressBarId);
        apiInterface = RetrofitClient.getRetrofit("http://mern-pos.herokuapp.com/").create(ApiInterface.class);


        return view;
    }
    }
