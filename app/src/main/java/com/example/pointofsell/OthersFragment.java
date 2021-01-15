package com.example.pointofsell;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pointofsell.customer.get_customer.CustomerCountResponse;
import com.example.pointofsell.invoice.get_all_sell_info.GetAllSellInfoResponse;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OthersFragment extends Fragment {
    TextView customerTextView,productTextView,invoiceTextView;
    TextView totalSaleAmountTextView,totalSoldProductQuantityTextView,totalSoldInvoiceTextView,
            totalDueAmountTextView,totalProfitTextView;
    TextView totalProductCostTextView,totalProductStockTextView,totalProductTypeTextView,extraInfoTextView;
    TextView customerCountTextView;
    Button sellInfoByDateButton;

    ApiInterface apiInterface;
    String token;
    String customerCount;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.others_fragment,container,false);
            Bundle bundle=this.getArguments();
            token=bundle.getString("token");

        //finding textView
        customerCountTextView=view.findViewById(R.id.customerCountId);
        totalSaleAmountTextView=view.findViewById(R.id.totalSaleAmountTextViewId);
        totalSoldProductQuantityTextView=view.findViewById(R.id.totalSoldProductQuantityTextViewId);
        totalSoldInvoiceTextView=view.findViewById(R.id.totalSoldInvoiceTextViewId);
        totalDueAmountTextView=view.findViewById(R.id.totalDueAmountTextViewId);
        totalProfitTextView=view.findViewById(R.id.totalProfitTextViewId);
        totalProductCostTextView=view.findViewById(R.id.totalProductCostTextViewId);
        totalProductStockTextView=view.findViewById(R.id.totalProductStockTextViewId);
        totalProductTypeTextView=view.findViewById(R.id.totalProductTypeTextViewId);
        apiInterface = RetrofitClient.getRetrofit("http://mern-pos.herokuapp.com/").create(ApiInterface.class);


        getCustomerCount();
        getAllSellInfo();

        return view;
    }

    // getting total customer count
    private void getCustomerCount() {
        apiInterface.getCustomerCount("Bearer "+token).enqueue(new Callback<CustomerCountResponse>() {
            @Override
            public void onResponse(Call<CustomerCountResponse> call, Response<CustomerCountResponse> response) {
                Log.e("count","success");
                CustomerCountResponse customerCountResponse=response.body();
                customerCount=customerCountResponse.getCustomerCount().toString();
                customerCountTextView.setText(customerCount);
            }

            @Override
            public void onFailure(Call<CustomerCountResponse> call, Throwable t) {
                Log.e("count","success");
            }
        });
    }


    // getting  Get all sale info
    private void getAllSellInfo() {
        apiInterface.getAllSellInfo("Bearer "+token).enqueue(new Callback<GetAllSellInfoResponse>() {
            @Override
            public void onResponse(Call<GetAllSellInfoResponse> call, Response<GetAllSellInfoResponse> response) {

                if (response.isSuccessful()){
                    if (response.body().getSuccess()==true){
                        GetAllSellInfoResponse getAllSellInfoResponse=response.body();
                        Log.e("totalSaleAmount",getAllSellInfoResponse.getGetAllSellInfoData().getTotalSaleAmount().toString());
                        totalSaleAmountTextView.setText(getAllSellInfoResponse.getGetAllSellInfoData().getTotalSaleAmount().toString());
                        totalSoldProductQuantityTextView.setText(getAllSellInfoResponse.getGetAllSellInfoData().getTotalSoldProductQuantity().toString());
                        totalSoldInvoiceTextView.setText(getAllSellInfoResponse.getGetAllSellInfoData().getTotalSoldInvoice().toString());
                        totalDueAmountTextView.setText(getAllSellInfoResponse.getGetAllSellInfoData().getTotalDueAmount().toString());
                        totalProfitTextView.setText(getAllSellInfoResponse.getGetAllSellInfoData().getTotalProfit().toString());
                    }
                }
            }
            @Override
            public void onFailure(Call<GetAllSellInfoResponse> call, Throwable t) {
                Log.e("ts","success");

            }
        });

    }


}
