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
import com.example.pointofsell.customer.single_customer.SingleCustomerGetResponse;
import com.example.pointofsell.customer.single_customer.SingleCustomerProduct;
import com.example.pointofsell.customer.single_customer.SingleCustomerSellsDetailsCustomAdapter;
import com.example.pointofsell.customer.single_customer.SingleCustomerTotalSell;
import com.example.pointofsell.customer.single_customer.SingleCustomerTotalSellCustomAdapter;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellDetailsFragment extends Fragment {
    TextView titleBarTextView;
    ImageView backImageView;
    View view;

    int position;
    String customer_id,token;


    SingleCustomerSellsDetailsCustomAdapter singleCustomerSellsDetailsCustomAdapter;
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

        //title bar view finding
        titleBarTextView=view.findViewById(R.id.titleBarTextViewId);
        backImageView=view.findViewById(R.id.backImageViewId);
        titleBarTextView.setText("Sell Details");

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

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("token",token);
                bundle.putString("customerId",customer_id);
                Fragment fragment=new SingleCustomerTotalSellFragment();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.frameViewId,fragment).commit();

            }
        });
        singleCustomerTotalSell();

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
                                singleCustomerProductList=new ArrayList<>();

                                singleCustomerTotalSellList.addAll(response.body().getSingleCustomerInformation().getTotalSell());
                                singleCustomerProductList.addAll(singleCustomerTotalSellList.get(position).getProducts());
                                if (singleCustomerProductList.size()>0){
                                    if (getActivity()!=null){
                                        singleCustomerSellsDetailsCustomAdapter = new SingleCustomerSellsDetailsCustomAdapter(getActivity(),token,singleCustomerProductList,position);
                                        sellDetailsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                        sellDetailsRecyclerView.setAdapter(singleCustomerSellsDetailsCustomAdapter);
//

                                        totalAmountAfterDiscountTextView.setText(String.valueOf(singleCustomerTotalSellList.get(position).getTotalAmountAfterDiscount()));
                                        payAmountTextView.setText(String.valueOf(singleCustomerTotalSellList.get(position).getPayAmount()));
                                        dueTextView.setText(String.valueOf(singleCustomerTotalSellList.get(position).getDue()));
                                        discountTextView.setText("after "+String.valueOf(singleCustomerTotalSellList.get(position).getDiscount())+"% discount : ");

                                        int size=singleCustomerProductList.size();
                                        for ( int i=size-1; i>=0;i--){
                                            Log.e(String.valueOf(i),String.valueOf(singleCustomerProductList.get(i).getSellingPrice()));
                                            sub=sub+(singleCustomerProductList.get(i).getSellingPrice()*singleCustomerProductList.get(i).getQuantity());
                                            Log.e("svs",String.valueOf(sub));
                                        }
                                        productTotalPriceTextView.setText(String.valueOf(sub));

                                    }}

                            }
                            else {

                            }
                            sellDetailsProgressBar.setVisibility(View.INVISIBLE);
                        }




                    }

                    @Override
                    public void onFailure(Call<SingleCustomerGetResponse> call, Throwable t) {
                        if (getActivity()!=null){
                        sellDetailsProgressBar.setVisibility(View.INVISIBLE);

                    }}
                });
    }

}

