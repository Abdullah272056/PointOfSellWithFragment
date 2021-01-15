package com.example.pointofsell;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pointofsell.retrofit.ApiInterface;

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



//        TextView txt=view.findViewById(R.id.txt);
//        txt.setText(token);
      //  Toast.makeText(HomePage.this, "", Toast.LENGTH_SHORT).show();



        return view;
    }


}
