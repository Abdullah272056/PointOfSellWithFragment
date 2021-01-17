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
import com.example.pointofsell.customer.single_customer.SingleCustomerTotalSell;
import com.example.pointofsell.customer.single_customer.SingleCustomerTotalSellCustomAdapter;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;

import java.util.List;

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
        customer_id=bundle.getString("cId");
        token= bundle.getString("token");


        //recycler view finding
        singleCustomerTotalSellRecyclerView=view.findViewById(R.id.singleCustomerTotalSellRecyclerViewId);
        //progressbar finding
        singleCustomerTotalSellProgressBar=view.findViewById(R.id.singleCustomerTotalSellProgressBarId);

        apiInterface = RetrofitClient.getRetrofit("http://mern-pos.herokuapp.com/").create(ApiInterface.class);
       // singleCustomerTotalSell();


    return view;
    }
}
