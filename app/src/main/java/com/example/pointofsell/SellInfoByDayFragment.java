package com.example.pointofsell;

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

import com.example.pointofsell.retrofit.ApiInterface;

public class SellInfoByDayFragment extends Fragment {
    String token;
    View view;


    ApiInterface apiInterface;
    String day;

    Button sellInfoByDayOkButton;
    EditText sellInfoByDayEditText;
    TextView sellProductTextView,saleAmountTextView,profitTxtView,
            currentCashTextView,dueTextView,inVoiceTextView,productCostTextView;
    ProgressBar sellInfoByDayProgressBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sell_info_by_day_fragment, container, false);
        //receive token
        Bundle bundle=this.getArguments();
        token=bundle.getString("token");

        //finding progressBar
        sellInfoByDayProgressBar=view.findViewById(R.id.sellInfoByDayProgressBarId);
        //finding view
        sellInfoByDayOkButton=view.findViewById(R.id.sellInfoByDayOkButtonId);
        sellInfoByDayEditText=view.findViewById(R.id.sellInfoByDayEditTextId);
        //finding textView
        sellProductTextView=view.findViewById(R.id.sellProductTextViewId);
        saleAmountTextView=view.findViewById(R.id.saleAmountTextViewId);
        profitTxtView=view.findViewById(R.id.profitTxtViewId);
        currentCashTextView=view.findViewById(R.id.currentCashTextViewId);
        dueTextView=view.findViewById(R.id.dueTextViewId);
        inVoiceTextView=view.findViewById(R.id.inVoiceTextViewId);
        productCostTextView=view.findViewById(R.id.productCostTextViewId);


        return view;
    }
}
