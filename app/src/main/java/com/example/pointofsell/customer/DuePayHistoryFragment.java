package com.example.pointofsell.customer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointofsell.R;
import com.example.pointofsell.customer.single_customer.SingleCustomerDuePayCustomAdapter;
import com.example.pointofsell.customer.single_customer.SingleCustomerDuePayHistory;
import com.example.pointofsell.customer.single_customer.SingleCustomerGetResponse;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        view = inflater.inflate(R.layout.due_pay_history_fragment, container, false);

        //receive data
        Bundle bundle=this.getArguments();
        customer_id= bundle.getString("customerId");
        token=bundle.getString("token");

        //recycler view finding
        duePayHistoryRecyclerView=view.findViewById(R.id.duePayHistoryRecyclerViewId);
        //ProgressBar finding
        pauDueHistoryProgressBar=view.findViewById(R.id.pauDueHistoryProgressBarId);
        apiInterface = RetrofitClient.getRetrofit("http://mern-pos.herokuapp.com/").create(ApiInterface.class);
        singleCustomerDuePayHistory();

        return view;
    }

    public void singleCustomerDuePayHistory(){
        apiInterface.getSingleCustomerInformation("Bearer "+token,customer_id)
                .enqueue(new Callback<SingleCustomerGetResponse>() {
                    @Override
                    public void onResponse(Call<SingleCustomerGetResponse> call, Response<SingleCustomerGetResponse> response) {

                        SingleCustomerGetResponse singleCustomerGetResponse=response.body();
                        if (getActivity()!=null){
                        if (singleCustomerGetResponse.getSuccess()==true){
                            pauDueHistoryProgressBar.setVisibility(View.INVISIBLE);
                            singleCustomerDuePayHistoryList=new ArrayList<>();
                            singleCustomerDuePayHistoryList.addAll(response.body().getSingleCustomerInformation().getDuePayHistory());
                            // reverse list inserting
                            Collections.reverse(singleCustomerDuePayHistoryList);
                            if (singleCustomerDuePayHistoryList.size()>0){
                                if (getActivity()!=null){
                                    Toast.makeText(getActivity(), singleCustomerGetResponse.getMsg().toString(), Toast.LENGTH_SHORT).show();
                                singleCustomerDuePayCustomAdapter = new SingleCustomerDuePayCustomAdapter(getActivity(),token,singleCustomerDuePayHistoryList);
                                duePayHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                duePayHistoryRecyclerView.setAdapter(singleCustomerDuePayCustomAdapter);
                            }}

                            Log.e("asas",String.valueOf(singleCustomerDuePayHistoryList.size()));
                        }

                    }
                    }
                    @Override
                    public void onFailure(Call<SingleCustomerGetResponse> call, Throwable t) {
                        if (getActivity()!=null){
                        pauDueHistoryProgressBar.setVisibility(View.INVISIBLE);
                    }}
                });
    }
    }
