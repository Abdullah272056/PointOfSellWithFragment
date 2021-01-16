package com.example.pointofsell.customer.get_customer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.example.pointofsell.OthersFragment;
import com.example.pointofsell.R;
import com.example.pointofsell.customer.CustomerFragment;
import com.example.pointofsell.customer.create_customer.AddCustomerResponse;
import com.example.pointofsell.customer.create_customer.CustomerData;
import com.example.pointofsell.customer.delete_customer.CustomerDeleteResponse;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerCustomAdapter extends RecyclerView.Adapter<CustomerCustomAdapter.MyViewHolder> {
 // List<CustomerInformationData> customerInformationList;

    Context context;
    String token;
    List<CustomerInformationData> customerInformationList;

    EditText customerNameEditText,customerEmailEditText,customerPhoneEditText,customerAddressEditText;
    Button addCustomerDataButton,cancelCustomerButton;
    CustomerData customerData;
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

        holder.deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteCustomer(position,v);
                Log.e("idid",customerInformationList.get(position).getId());

            }
        });


        holder.editImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCustomerInformation(position,v);
                Log.e("idid",customerInformationList.get(position).getId());
            }
        });

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


    private  void  deleteCustomer(final int position, final View v){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Delete?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                apiInterface.deleteCustomer("Bearer "+token,customerInformationList.get(position).getId().toString())
                        .enqueue(new Callback<CustomerDeleteResponse>() {
                            @Override
                            public void onResponse(Call<CustomerDeleteResponse> call, Response<CustomerDeleteResponse> response) {
                                if (response.code()==200){
                                    Toast.makeText(context, "Customer deleted successfully", Toast.LENGTH_SHORT).show();

                                    Bundle  bundle=new Bundle();
                                    bundle.putString("token",token);
                                    Fragment fragment=new CustomerFragment();
                                    fragment.setArguments(bundle);
                                    AppCompatActivity activity=(AppCompatActivity)v.getContext();
                                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameViewId,fragment).commit();


                                }else if (response.code()==500){
                                    Toast.makeText(context, "customer id not found", Toast.LENGTH_SHORT).show();
                                }else if (response.code()==401){
                                    Toast.makeText(context, "You are not authorized to access this route", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
                                }
//                                CustomerCustomAdapter.notifyDataSetChanged();
//                                customerInformationList.remove(position);
//                                notifyDataSetChanged();

                                //customerInformationList.notify();
                                //new CustomerFragment().getAllCustomer();
                            }

                            @Override
                            public void onFailure(Call<CustomerDeleteResponse> call, Throwable t) {
                                Toast.makeText(context, "fail delete", Toast.LENGTH_SHORT).show();

                            }
                        });

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


    private void updateCustomerInformation(final int position,final View v){

        AlertDialog.Builder builder     =new AlertDialog.Builder(context);
        LayoutInflater layoutInflater   =LayoutInflater.from(context);
        View view                       =layoutInflater.inflate(R.layout.add_customer_data,null);
        builder.setView(view);
        final AlertDialog alertDialog   = builder.create();


        customerNameEditText=view.findViewById(R.id.customerNameEditTextId);
        customerPhoneEditText=view.findViewById(R.id.customerPhoneEditTextId);
        customerEmailEditText=view.findViewById(R.id.customerEmailEditTextId);
        customerAddressEditText=view.findViewById(R.id.customerAddressEditTextId);
        progressBar=view.findViewById(R.id.newCustomerProgressBarId);

        addCustomerDataButton=view.findViewById(R.id.saveCustomerDataButtonId);
        cancelCustomerButton=view.findViewById(R.id.cancelCustomerDataButtonId);


        customerNameEditText.setText(customerInformationList.get(position).getName());
        customerAddressEditText.setText(customerInformationList.get(position).getAddress());
        customerPhoneEditText.setText(customerInformationList.get(position).getPhone());
        if (customerInformationList.get(position).getEmail()!=null || TextUtils.isEmpty(customerInformationList.get(position).getEmail())){
            customerEmailEditText.setText(customerInformationList.get(position).getEmail());
        }


        addCustomerDataButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v) {
                String customerName=customerNameEditText.getText().toString();
                String customerPhone=customerPhoneEditText.getText().toString();
                String customerEmail=customerEmailEditText.getText().toString();
                String customerAddress=customerAddressEditText.getText().toString();

                if (TextUtils.isEmpty(customerName) || customerAddress==null){
                    customerNameEditText.setError("Enter customer name");
                    customerNameEditText.requestFocus();
                    return;
                }
                if (customerName.length()<4){
                    customerNameEditText.setError("don't smaller than 4 character");
                    customerNameEditText.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(customerPhone)|| customerPhone==null){
                    customerPhoneEditText.setError("Enter customer phone");
                    customerPhoneEditText.requestFocus();
                    return;
                }
                if (customerPhone.length()<8){
                    customerPhoneEditText.setError("don't smaller than 8 character");
                    customerPhoneEditText.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(customerAddress) ||customerAddress==null){
                    customerAddressEditText.setError("Enter customer name");
                    customerAddressEditText.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(customerEmail)|| customerEmail==null){
                    customerData=new CustomerData(customerName,customerPhone,customerAddress);
                }
                if (!TextUtils.isEmpty(customerEmail ) && customerEmail!=null){
                    if (!Patterns.EMAIL_ADDRESS.matcher(customerEmail).matches()){
                        customerEmailEditText.setError("Enter a valid  email address");
                        customerEmailEditText.requestFocus();
                        return;
                    }else {
                        customerData=new CustomerData(customerName,customerPhone,customerEmail,customerAddress);
                    }
                }
                progressBar.setVisibility(View.VISIBLE);

                apiInterface.updateCustomerData("Bearer "+token,customerInformationList.get(position).getId().toString(),customerData)
                        .enqueue(new Callback<AddCustomerResponse>(){
                            @Override
                            public void onResponse(Call<AddCustomerResponse> call, Response<AddCustomerResponse> response) {

                                if (response.code()==200){
                                    Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show();
                                    Bundle  bundle=new Bundle();
                                    bundle.putString("token",token);
                                    Fragment fragment=new CustomerFragment();
                                    fragment.setArguments(bundle);
                                    AppCompatActivity activity=(AppCompatActivity)v.getContext();
                                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameViewId,fragment).commit();

                                }
                                else if (response.code()==401){
                                    Toast.makeText(context, "You are not authorized to access this route", Toast.LENGTH_LONG).show();
                                }
                                else if(response.code()==400){
                                    Toast.makeText(context, "safe phone number", Toast.LENGTH_LONG).show();
                                }
                                else {
                                    Toast.makeText(context, "failed try again", Toast.LENGTH_LONG).show();
                                }

                                alertDialog.dismiss();
                               // new CustomerFragment();
                               // ((CustomerActivity)context).getAllCustomer();
                                progressBar.setVisibility(View.GONE);

                                Bundle  bundle=new Bundle();
                                bundle.putString("token",token);

//                               Fragment fragment=new OthersFragment();
//                                fragment.setArguments(bundle);
//                                FragmentTransaction fragmentManager=context.getApplicationContext().getFragmentManager().beginTransaction();
//                                fragmentManager.replace(R.id.frameViewId,fragment);
//                                fragmentManager.commit();

                            }
                            @Override
                            public void onFailure(Call<AddCustomerResponse> call, Throwable t) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(context, "fail:  "+t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        cancelCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                progressBar.setVisibility(View.GONE);
            }
        });
        alertDialog.show();

    }
}
