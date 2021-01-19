package com.example.pointofsell.customer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointofsell.R;
import com.example.pointofsell.customer.single_customer.SingleCustomerDuePayCustomAdapter;
import com.example.pointofsell.customer.single_customer.SingleCustomerGetResponse;
import com.example.pointofsell.customer.single_customer.SingleCustomerTotalSell;
import com.example.pointofsell.customer.single_customer.SingleCustomerTotalSellCustomAdapter;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleCustomerTotalSellFragment extends Fragment {
    TextView titleBarTextView;
    ImageView backImageView;

    View view;
    String customer_id,token,customerName;

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
        customerName= bundle.getString("customerName");

        //title bar view finding
        titleBarTextView=view.findViewById(R.id.titleBarTextViewId);
        backImageView=view.findViewById(R.id.backImageViewId);
        titleBarTextView.setText(customerName+"'s sell history");

        //recycler view finding
        singleCustomerTotalSellRecyclerView=view.findViewById(R.id.singleCustomerTotalSellRecyclerViewId);
        //progressbar finding
        singleCustomerTotalSellProgressBar=view.findViewById(R.id.singleCustomerTotalSellProgressBarId);

        apiInterface = RetrofitClient.getRetrofit("http://mern-pos.herokuapp.com/").create(ApiInterface.class);
        singleCustomerTotalSell();
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("token",token);
                bundle.putString("customerId",customer_id);
                bundle.putString("customerName",customerName);
                Fragment fragment=new CustomerAllInfoFragment();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.frameViewId,fragment).commit();

            }
        });

    return view;
    }

    public void singleCustomerTotalSell(){
        apiInterface.getSingleCustomerInformation("Bearer "+token,customer_id)
                .enqueue(new Callback<SingleCustomerGetResponse>() {
                    @Override
                    public void onResponse(Call<SingleCustomerGetResponse> call, Response<SingleCustomerGetResponse> response) {
                        if (getActivity()!=null){
                            if (response.code()==404){
                                Toast.makeText(getActivity(), "No customer found", Toast.LENGTH_SHORT).show();
                            }else if (response.code()==500){
                                Toast.makeText(getActivity(), "internal server error", Toast.LENGTH_SHORT).show();
                            }else if (response.code()==200){
                                singleCustomerTotalSellList=new ArrayList<>();
                                singleCustomerTotalSellList.addAll(response.body().getSingleCustomerInformation().getTotalSell());
                                // reverse list inserting
                                //Collections.reverse(singleCustomerTotalSellList);
                                if (singleCustomerTotalSellList.size()>0){

                                        if (getActivity()!=null){
                                        singleCustomerTotalSellCustomAdapter = new SingleCustomerTotalSellCustomAdapter(getActivity(), token,customerName, singleCustomerTotalSellList);
                                        singleCustomerTotalSellRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                        singleCustomerTotalSellRecyclerView.setAdapter(singleCustomerTotalSellCustomAdapter);
                                     }}
                            }
                            else {

                            }
                            singleCustomerTotalSellProgressBar.setVisibility(View.INVISIBLE);
                        }

                    }




                    @Override
                    public void onFailure(Call<SingleCustomerGetResponse> call, Throwable t) {
                        if (getActivity()!=null) {
                            singleCustomerTotalSellProgressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
    }
}
