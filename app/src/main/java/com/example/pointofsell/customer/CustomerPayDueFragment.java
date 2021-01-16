package com.example.pointofsell.customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pointofsell.R;
import com.example.pointofsell.retrofit.ApiInterface;

public class CustomerPayDueFragment extends Fragment {
    ApiInterface apiInterface;
    ProgressBar pauDueProgressBar;

    TextView cNameTextView,cPhoneTextView,cEmailTextView,cAddressTextView;
    TextView dueTextView,allTimeSellTextView;

    EditText duePayAmountEditText;
    Button payDueButton;
    String customer_id,token;

    String duePayAmount;
//    PayData payData;
//    DuePayDataResponse duePayDataResponse;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.customer_pay_due_fragment, container, false);

        // textView finding
        cNameTextView=view.findViewById(R.id.customerNameTextViewId);
        cPhoneTextView=view.findViewById(R.id.customerPhoneTextViewId);
        cEmailTextView=view.findViewById(R.id.customerEmailTextViewId);
        cAddressTextView=view.findViewById(R.id.customerAddressTextViewId);
        dueTextView=view.findViewById(R.id.dueTextViewId);
        allTimeSellTextView=view.findViewById(R.id.allTimeSellTextViewId);
        //button finding
        payDueButton=view.findViewById(R.id.payDueButtonId);
        //editText finding
        duePayAmountEditText=view.findViewById(R.id.duePayAmountEditTextId);
        pauDueProgressBar=view.findViewById(R.id.pauDueProgressBarId);
        //data receive

        return view;
    }
}