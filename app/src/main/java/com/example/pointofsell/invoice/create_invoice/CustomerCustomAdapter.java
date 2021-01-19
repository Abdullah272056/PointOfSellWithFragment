package com.example.pointofsell.invoice.create_invoice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.pointofsell.R;
import com.example.pointofsell.customer.get_customer.CustomerInformationData;

import java.util.List;

public class CustomerCustomAdapter extends RecyclerView.Adapter<CustomerCustomAdapter.MyViewHolder> {
    Context context;
    String token;
    List<CustomerInformationData> customerInformationDataList;

    CustomerCustomAdapter.OnContactClickListener1 onContactClickListener;
    public CustomerCustomAdapter(Context context, String token, List<CustomerInformationData> customerInformationDataList, CustomerCustomAdapter.OnContactClickListener1 onContactClickListener) {
        this.context = context;
        this.token = token;
        this.customerInformationDataList = customerInformationDataList;
        this.onContactClickListener = onContactClickListener;


    }
    @NonNull
    @Override
    public CustomerCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.select_customer_item,parent,false);
        return new CustomerCustomAdapter.MyViewHolder(view,onContactClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerCustomAdapter.MyViewHolder holder, final int position) {
        holder.cNameTextView.setText(String.valueOf(customerInformationDataList.get(position).getName()));
        holder.customerPhoneTextView.setText(String.valueOf(customerInformationDataList.get(position).getPhone()));


    }

    @Override
    public int getItemCount(){
        return customerInformationDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cNameTextView,customerPhoneTextView;
        CustomerCustomAdapter.OnContactClickListener1 onContactClickListener;
        public MyViewHolder(@NonNull View itemView, CustomerCustomAdapter.OnContactClickListener1 onContactClickListener){
            super(itemView);

            cNameTextView=itemView.findViewById(R.id.customerNameTextViewId);
            customerPhoneTextView=itemView.findViewById(R.id.customerPhoneTextViewId);
            this.onContactClickListener=onContactClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onContactClickListener.onContactClick1(getAdapterPosition());
        }
    }

    public  interface  OnContactClickListener1{
        void onContactClick1(int position);
    }

}
