package com.example.pointofsell.customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pointofsell.R;

public class CustomerAllInfoFragment extends Fragment {
    TextView customerNameTextView,customerDataTextView,customerPayDueTextView,
            customerDuePayHistoryTextView,customerTotalSellTextView;
    String customer_id,token,cDue;

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


    return view;


    }
    }
