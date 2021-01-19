package com.example.pointofsell.invoice.single_invoice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.pointofsell.R;

import java.util.List;

public class SingleInvoiceCustomAdapter extends RecyclerView.Adapter<SingleInvoiceCustomAdapter.MyViewHolder> {
    Context context;
    String token;
    List<SingleInvoiceProductData> singleInvoiceProductDataList;

    public SingleInvoiceCustomAdapter(Context context, String token, List<SingleInvoiceProductData> singleInvoiceProductDataList) {
        this.context = context;
        this.token = token;
        this.singleInvoiceProductDataList = singleInvoiceProductDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sell_details_recclerview_item,parent,false);
        return new SingleInvoiceCustomAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.productNameTextView.setText(String.valueOf(singleInvoiceProductDataList.get(position).getName()));
        holder.productPriceTextView.setText(String.valueOf(singleInvoiceProductDataList.get(position).getSellingPrice()));
        holder.productQuantityTexView.setText(String.valueOf(singleInvoiceProductDataList.get(position).getQuantity())+String.valueOf(singleInvoiceProductDataList.get(position).getUnit()));
        holder.productTotalPriceTextView.setText(String.valueOf(singleInvoiceProductDataList.get(position).getSellingPrice()*singleInvoiceProductDataList.get(position).getQuantity()));
        holder.serialTextView.setText(String.valueOf(position+1));
    }

    @Override
    public int getItemCount() {
        return singleInvoiceProductDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView productNameTextView,productPriceTextView,productQuantityTexView,
                productTotalPriceTextView,serialTextView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView=itemView.findViewById(R.id.productNameTextViewId);
            productPriceTextView=itemView.findViewById(R.id.productPriceTextViewId);
            productQuantityTexView=itemView.findViewById(R.id.productQuantityTexViewId);
            productTotalPriceTextView=itemView.findViewById(R.id.productTotalPriceTextViewId);
            serialTextView=itemView.findViewById(R.id.serialTextViewId);
        }
    }
}
