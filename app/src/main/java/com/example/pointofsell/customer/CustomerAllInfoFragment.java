package com.example.pointofsell.customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pointofsell.R;

public class CustomerAllInfoFragment extends Fragment  {
    TextView titleBarTextView;
    ImageView backImageView;

    TextView customerNameTextView,customerDataTextView,customerPayDueTextView,
            customerDuePayHistoryTextView,customerTotalSellTextView;
    String customer_id,token,customerName;


Fragment fragment;
View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.customer_all_info_fragment, container, false);

        //data receive
        Bundle bundle=this.getArguments();
        customer_id=bundle.getString("customerId");
        token= bundle.getString("token");
        customerName= bundle.getString("customerName");


        //title bar view Finding
        titleBarTextView=view.findViewById(R.id.titleBarTextViewId);
        backImageView=view.findViewById(R.id.backImageViewId);
        titleBarTextView.setText(customerName+"'s all info");

        customerNameTextView=view.findViewById(R.id.customerNameTextViewId);
        customerDataTextView=view.findViewById(R.id.customerDataTextViewId);
        customerPayDueTextView=view.findViewById(R.id.customerPayDueTextViewId);
        customerDuePayHistoryTextView=view.findViewById(R.id.customerDuePayHistoryTextViewId);
        customerTotalSellTextView=view.findViewById(R.id.customerTotalSellTextViewId);

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Bundle bundle=new Bundle();
                bundle.putString("token",token);
                bundle.putString("customerName",customerName);
                fragment=new CustomerFragment();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.frameViewId,fragment).commit();

            }
        });

        customerPayDueTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("token",token);
                bundle.putString("customerId",customer_id);
                bundle.putString("customerName",customerName);
                fragment=new CustomerPayDueFragment();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.frameViewId,fragment).commit();
            }
        });
        customerTotalSellTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("token",token);
                bundle.putString("customerId",customer_id);
                bundle.putString("customerName",customerName);
                fragment=new SingleCustomerTotalSellFragment();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.frameViewId,fragment).commit();

            }
        });


        customerDuePayHistoryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("token",token);
                bundle.putString("customerId",customer_id);
                bundle.putString("customerName",customerName);
                fragment=new DuePayHistoryFragment();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.frameViewId,fragment).commit();

            }
        });



        return view;
    }
    // title bar  button clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(getContext(), "sdf", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




    }
