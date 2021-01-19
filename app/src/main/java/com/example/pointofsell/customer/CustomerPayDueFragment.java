package com.example.pointofsell.customer;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pointofsell.R;
import com.example.pointofsell.customer.pay_due.DuePayDataResponse;
import com.example.pointofsell.customer.pay_due.PayData;
import com.example.pointofsell.customer.single_customer.SingleCustomerGetResponse;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerPayDueFragment extends Fragment {
    TextView titleBarTextView;
    ImageView backImageView;

    ApiInterface apiInterface;
    ProgressBar pauDueProgressBar;

    TextView cNameTextView,cPhoneTextView,cEmailTextView,cAddressTextView;
    TextView dueTextView,allTimeSellTextView;

    EditText duePayAmountEditText;
    Button payDueButton;
    String customer_id,token,customerName;

    String duePayAmount;
    PayData payData;
    DuePayDataResponse duePayDataResponse;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.customer_pay_due_fragment, container, false);

        //data receive
        Bundle bundle=this.getArguments();
        customer_id=bundle.getString("customerId");
        token= bundle.getString("token");
        customerName= bundle.getString("customerName");

       //title bar view finding
        titleBarTextView=view.findViewById(R.id.titleBarTextViewId);
        backImageView=view.findViewById(R.id.backImageViewId);
        titleBarTextView.setText(customerName+" Pay Due");



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



        apiInterface = RetrofitClient.getRetrofit("http://mern-pos.herokuapp.com/").create(ApiInterface.class);
        customerInformation();

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
        payDueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payDue();
            }
        });


        return view;
    }

    public void customerInformation(){
        apiInterface.getSingleCustomerInformation("Bearer "+token,customer_id)
                .enqueue(new Callback<SingleCustomerGetResponse>() {
                    @Override
                    public void onResponse(Call<SingleCustomerGetResponse> call, Response<SingleCustomerGetResponse> response) {
                        if(getActivity() != null) {
                        if (response.code()==200){
                            dueTextView.setText(String.valueOf(response.body().getSingleCustomerInformation().getDue()));
                            allTimeSellTextView.setText(String.valueOf(response.body().getSingleCustomerInformation().getAllTimeSellAmount()));

                            cNameTextView.setText("Name :  "+String.valueOf(response.body().getSingleCustomerInformation().getName()));
                            cPhoneTextView.setText("Phone :  "+String.valueOf(response.body().getSingleCustomerInformation().getPhone()));
                            cEmailTextView.setText("Email :  "+String.valueOf(response.body().getSingleCustomerInformation().getEmail()));
                            cAddressTextView.setText("Address :  "+String.valueOf(response.body().getSingleCustomerInformation().getAddress()));
                        }else {

                        }

                    }}

                    @Override
                    public void onFailure(Call<SingleCustomerGetResponse> call, Throwable t) {
                        if(getActivity() != null) {
                        Log.e("awer",t.getMessage().toString());
                        //pauDueHistoryProgressBar.setVisibility(View.INVISIBLE);
                    }}
                });
    }
    public void payDue(){
        duePayAmount = duePayAmountEditText.getText().toString();
        if (TextUtils.isEmpty(duePayAmount)){
            duePayAmountEditText.setError("Enter  pay amount");
            duePayAmountEditText.requestFocus();
            return;
        }if(Integer.parseInt(duePayAmount)>Integer.parseInt(dueTextView.getText().toString())){
            duePayAmountEditText.setError("You send more amount than due");
            duePayAmountEditText.requestFocus();
            return;
        }
        payDueButton.setVisibility(View.INVISIBLE);
        pauDueProgressBar.setVisibility(View.VISIBLE);
        payData=new PayData(customer_id,Integer.parseInt(duePayAmount));

        apiInterface.payDue("Bearer "+token,payData)
                .enqueue(new Callback<DuePayDataResponse>() {
                    @Override
                    public void onResponse(Call<DuePayDataResponse> call, Response<DuePayDataResponse> response) {
                        if(getActivity() != null) {
                            if (response.code() == 404) {

                                Toast.makeText(getActivity(), "You send more amount than due", Toast.LENGTH_SHORT).show();
                            } else if (response.code() == 200) {
                                if(getActivity() != null) {
                                    customerInformation();
                                    Toast.makeText(getActivity(), "Due has been updated", Toast.LENGTH_SHORT).show();
                                }
                            } else if (response.code() == 401) {
                                Toast.makeText(getActivity(), "You are not authorized to access this route", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getActivity(), "failed", Toast.LENGTH_LONG).show();
                            }
                            pauDueProgressBar.setVisibility(View.INVISIBLE);
                            payDueButton.setVisibility(View.VISIBLE);
                        } }
                    @Override
                    public void onFailure(Call<DuePayDataResponse> call, Throwable t) {
                        if(getActivity() != null) {
                        pauDueProgressBar.setVisibility(View.INVISIBLE);
                        payDueButton.setVisibility(View.VISIBLE);
                    }}
                });

    }
}