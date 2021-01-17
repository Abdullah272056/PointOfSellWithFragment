package com.example.pointofsell.customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointofsell.R;
import com.example.pointofsell.customer.single_customer.SingleCustomerProduct;
import com.example.pointofsell.customer.single_customer.SingleCustomerTotalSell;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;

import java.util.List;

public class SellDetailsFragment extends Fragment {
    View view;

    int position;
    String customer_id,token;


    //SingleCustomerSellsDetailsCustomAdapter singleCustomerSellsDetailsCustomAdapter;
    List<SingleCustomerTotalSell> singleCustomerTotalSellList;
    List<SingleCustomerProduct> singleCustomerProductList;
    TextView productTotalPriceTextView,totalAmountAfterDiscountTextView,
            payAmountTextView, dueTextView,discountTextView;
    RecyclerView sellDetailsRecyclerView;
    ProgressBar sellDetailsProgressBar;
    ApiInterface apiInterface;
    int sub=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.sell_details_fragment,container,false);

        //receive token
        Bundle bundle=this.getArguments();
        position=bundle.getInt("position",10);
        customer_id= bundle.getString("customerId");
        token=bundle.getString("token");

        //textView finding
        productTotalPriceTextView=view.findViewById(R.id.productTotalPriceTextViewId);
        totalAmountAfterDiscountTextView=view.findViewById(R.id.totalAmountAfterDiscountTextViewId);
        payAmountTextView=view.findViewById(R.id.payAmountTextViewId);
        dueTextView=view.findViewById(R.id.dueTextViewId);
        discountTextView=view.findViewById(R.id.discountTextViewId);

        //recycler view finding
        sellDetailsRecyclerView=view.findViewById(R.id.sellDetailsRecyclerViewId);
        //progressbar finding
        sellDetailsProgressBar=view.findViewById(R.id.sellDetailsProgressBarId);

        apiInterface = RetrofitClient.getRetrofit("http://mern-pos.herokuapp.com/").create(ApiInterface.class);



        
        return view;
    }}

