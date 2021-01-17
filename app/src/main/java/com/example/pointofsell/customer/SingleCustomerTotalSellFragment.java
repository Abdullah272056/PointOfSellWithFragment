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
import com.example.pointofsell.customer.single_customer.SingleCustomerGetResponse;
import com.example.pointofsell.customer.single_customer.SingleCustomerTotalSell;
import com.example.pointofsell.customer.single_customer.SingleCustomerTotalSellCustomAdapter;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleCustomerTotalSellFragment extends Fragment {
    View view;
    String customer_id,token;

    SingleCustomerTotalSellCustomAdapter singleCustomerTotalSellCustomAdapter;
    List<SingleCustomerTotalSell> singleCustomerTotalSellList;

    RecyclerView singleCustomerTotalSellRecyclerView;
    ApiInterface apiInterface;
    ProgressBar singleCustomerTotalSellProgressBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.single_customer_total_sell_fragment,container,false);
        //data receive
        Bundle bundle=this.getArguments();
        customer_id=bundle.getString("customerId");
        token= bundle.getString("token");


        //recycler view finding
        singleCustomerTotalSellRecyclerView=view.findViewById(R.id.singleCustomerTotalSellRecyclerViewId);
        //progressbar finding
        singleCustomerTotalSellProgressBar=view.findViewById(R.id.singleCustomerTotalSellProgressBarId);

        apiInterface = RetrofitClient.getRetrofit("http://mern-pos.herokuapp.com/").create(ApiInterface.class);
        singleCustomerTotalSell();


    return view;
    }

    public void singleCustomerTotalSell(){
        apiInterface.getSingleCustomerInformation("Bearer "+token,customer_id)
                .enqueue(new Callback<SingleCustomerGetResponse>() {
                    @Override
                    public void onResponse(Call<SingleCustomerGetResponse> call, Response<SingleCustomerGetResponse> response) {
                       if (getActivity()!=null){
                        SingleCustomerGetResponse singleCustomerGetResponse=response.body();
                        singleCustomerTotalSellProgressBar.setVisibility(View.INVISIBLE);
                        if (singleCustomerGetResponse.getSuccess()==true){
                            singleCustomerTotalSellList=new ArrayList<>();
                            singleCustomerTotalSellList.addAll(response.body().getSingleCustomerInformation().getTotalSell());
                            // reverse list inserting
                            //Collections.reverse(singleCustomerTotalSellList);
                            if (singleCustomerTotalSellList.size()>0){
                                if (getActivity()!=null) {
                                    singleCustomerTotalSellCustomAdapter = new SingleCustomerTotalSellCustomAdapter(getActivity(), token, singleCustomerTotalSellList);
                                    singleCustomerTotalSellRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                    singleCustomerTotalSellRecyclerView.setAdapter(singleCustomerTotalSellCustomAdapter);
                                } }
                            Log.e("oooo",String.valueOf(singleCustomerTotalSellList.size()));
                        }
                    }}

                    @Override
                    public void onFailure(Call<SingleCustomerGetResponse> call, Throwable t) {
                        if (getActivity()!=null) {
                            singleCustomerTotalSellProgressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
    }
}
