package com.example.pointofsell.customer.get_customer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import com.example.pointofsell.R;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;

import java.util.List;


public class CustomerCustomAdapter extends RecyclerView.Adapter<CustomerCustomAdapter.MyViewHolder> {
 // List<CustomerInformationData> customerInformationList;

    Context context;
    String token;
    List<CustomerInformationData> customerInformationList;



    EditText customerNameEditText,customerEmailEditText,customerPhoneEditText,customerAddressEditText;
    Button addCustomerDataButton,cancelCustomerButton;
    //CustomerData customerData;
    ProgressBar progressBar;

    ApiInterface apiInterface;

    public CustomerCustomAdapter(Context context, String token, List<CustomerInformationData> customerInformationList) {
        this.context = context;
        this.token = token;
        this.customerInformationList = customerInformationList;
        apiInterface = RetrofitClient.getRetrofit("http://mern-pos.herokuapp.com/").create(ApiInterface.class);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.customer_recyclerview_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.customerNameTextView.setText(String.valueOf(customerInformationList.get(position).getName()));
        holder.customerPhoneTextView.setText(String.valueOf(customerInformationList.get(position).getPhone()));
        holder.customerDueTextView.setText(String.valueOf(customerInformationList.get(position).getDue()));
        holder.customerSerialTextView.setText(String.valueOf(position+1));




    }

    @Override
    public int getItemCount() {
        return customerInformationList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView customerNameTextView,customerPhoneTextView,customerDueTextView,customerSerialTextView;
        ImageView editImageView,deleteImageView;
        LinearLayout customerItem;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            customerNameTextView=itemView.findViewById(R.id.customerNameTextViewId);
            customerPhoneTextView=itemView.findViewById(R.id.customerPhoneTextViewId);
            customerDueTextView=itemView.findViewById(R.id.customerDueTextViewId);
            customerSerialTextView=itemView.findViewById(R.id.customerSerialTextViewId);
            editImageView=itemView.findViewById(R.id.editImageViewId);
            deleteImageView=itemView.findViewById(R.id.deleteImageViewId);

            customerItem=itemView.findViewById(R.id.customerItemId);
        }
    }


}
