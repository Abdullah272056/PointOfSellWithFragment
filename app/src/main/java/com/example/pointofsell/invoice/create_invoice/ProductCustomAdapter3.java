package com.example.pointofsell.invoice.create_invoice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointofsell.R;
import com.example.pointofsell.product.delete_product.GetProductData;
import com.example.pointofsell.retrofit.ApiInterface;

import java.util.List;


public class ProductCustomAdapter3 extends RecyclerView.Adapter<ProductCustomAdapter3.MyViewHolder> {
    Context context;
    String token;
    List<GetProductData> productDataList;

OnContactClickListener onContactClickListener;
    ApiInterface apiInterface;
    public ProductCustomAdapter3(Context context, String token, List<GetProductData> productDataList, OnContactClickListener onContactClickListener) {
        this.context = context;
        this.token = token;
        this.productDataList = productDataList;
        this.onContactClickListener = onContactClickListener;


    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item,parent,false);
        return new ProductCustomAdapter3.MyViewHolder(view,onContactClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.productNameTextView.setText(String.valueOf(productDataList.get(position).getName()));
        holder.productSellingPriceTextView.setText(String.valueOf(productDataList.get(position).getSellingPrice()));
        holder.productStockTextView.setText(String.valueOf(productDataList.get(position).getStock()));

    }

    @Override
    public int getItemCount(){
        return productDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView productNameTextView,productSellingPriceTextView,productStockTextView;

        OnContactClickListener onContactClickListener;
        public MyViewHolder(@NonNull View itemView,OnContactClickListener onContactClickListener){
            super(itemView);

            productNameTextView=itemView.findViewById(R.id.productNameTextViewId);
            productSellingPriceTextView=itemView.findViewById(R.id.productSellingPriceTextViewId);
            productStockTextView=itemView.findViewById(R.id.productStockTextViewId);

            this.onContactClickListener=onContactClickListener;
           itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onContactClickListener.onContactClick(getAdapterPosition());
        }
    }

    public  interface  OnContactClickListener{
        void onContactClick(int position);
    }

}
