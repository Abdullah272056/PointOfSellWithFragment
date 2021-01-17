package com.example.pointofsell.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pointofsell.OthersFragment;
import com.example.pointofsell.R;

public class CustomerAllInfoFragment extends Fragment {
    TextView customerNameTextView,customerDataTextView,customerPayDueTextView,
            customerDuePayHistoryTextView,customerTotalSellTextView;
    String customer_id,token,cDue;


Fragment fragment;
View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.customer_all_info_fragment, container, false);

        //data receive
        Bundle bundle=this.getArguments();
        customer_id=bundle.getString("cId");
        token= bundle.getString("token");
        cDue= bundle.getString("cDue");

        //textView Finding
        customerNameTextView=view.findViewById(R.id.customerNameTextViewId);
        customerDataTextView=view.findViewById(R.id.customerDataTextViewId);
        customerPayDueTextView=view.findViewById(R.id.customerPayDueTextViewId);
        customerDuePayHistoryTextView=view.findViewById(R.id.customerDuePayHistoryTextViewId);
        customerTotalSellTextView=view.findViewById(R.id.customerTotalSellTextViewId);

        customerPayDueTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Bundle bundle=new Bundle();
                bundle.putString("token",token);
                bundle.putString("customerId",customer_id);
                bundle.putString("cDue",cDue);
                fragment=new CustomerPayDueFragment();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.frameViewId,fragment).commit();




            }
        });



        return view;
    }


    }
