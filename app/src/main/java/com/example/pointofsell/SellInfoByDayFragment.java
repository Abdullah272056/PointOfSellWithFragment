package com.example.pointofsell;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pointofsell.invoice.get_all_sell_info.GetSellInfoByDayResponse;
import com.example.pointofsell.invoice.get_all_sell_info.GetSellInfoByDays;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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


        apiInterface = RetrofitClient.getRetrofit("http://mern-pos.herokuapp.com/").create(ApiInterface.class);

        sellInfoByDayOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSellInfoByDay();
            }
        });

        return view;
    }


    public void getSellInfoByDay(){
        day =sellInfoByDayEditText.getText().toString();
        if (TextUtils.isEmpty(day)){
            sellInfoByDayEditText.setError("Enter a value");
            sellInfoByDayEditText.requestFocus();
            return;
        }
        sellInfoByDayProgressBar.setVisibility(View.VISIBLE);

        apiInterface.getSellInfoByDay("Bearer "+token,Integer.valueOf(day)).enqueue(new Callback<GetSellInfoByDayResponse>() {
            @Override
            public void onResponse(Call<GetSellInfoByDayResponse> call, Response<GetSellInfoByDayResponse> response) {
                if (response.body().getSuccess()==true){
                    GetSellInfoByDays getSellInfoByDays= response.body().getGetSellInfoByDays();
                    sellProductTextView.setText(String.valueOf(getSellInfoByDays.getTotalSoldProduct()));
                    saleAmountTextView.setText(String.valueOf(getSellInfoByDays.getTotalSaleAmount()));
                    profitTxtView.setText(String.valueOf(getSellInfoByDays.getTotalProfit()));
                    currentCashTextView.setText(String.valueOf(getSellInfoByDays.getCurrentCash()));
                    dueTextView.setText(String.valueOf(getSellInfoByDays.getTotalDue()));
                    inVoiceTextView.setText(String.valueOf(getSellInfoByDays.getTotalSoldInvoice()));
                    productCostTextView.setText(String.valueOf(getSellInfoByDays.getTotalProductCost()));
                    sellInfoByDayProgressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(), String.valueOf(response.body().getGetSellInfoByDays().getTotalSoldProduct()), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<GetSellInfoByDayResponse> call, Throwable t) {
                sellInfoByDayProgressBar.setVisibility(View.INVISIBLE);
                sellProductTextView.setText("00");
                saleAmountTextView.setText("00");
                profitTxtView.setText("00");
                currentCashTextView.setText("00");
                dueTextView.setText("00");
                inVoiceTextView.setText("00");
                productCostTextView.setText("00");
            }
        });
    }
}
