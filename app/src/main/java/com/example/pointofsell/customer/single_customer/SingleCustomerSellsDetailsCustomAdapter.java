package com.example.pointofsell.customer.single_customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointofsell.R;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;
import java.util.List;

public class SingleCustomerSellsDetailsCustomAdapter extends RecyclerView.Adapter<SingleCustomerSellsDetailsCustomAdapter.MyViewHolder> {
    Context context;
    String token;
    List<SingleCustomerProduct> singleCustomerProductList;
    int position1;
    ApiInterface apiInterface;
    public SingleCustomerSellsDetailsCustomAdapter(Context context, String token, List<SingleCustomerProduct> singleCustomerProductList, int position1) {
        this.context = context;
        this.token = token;
        this.singleCustomerProductList = singleCustomerProductList;
        this.position1 = position1;
        apiInterface = RetrofitClient.getRetrofit("http://mern-pos.herokuapp.com/").create(ApiInterface.class);

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sell_details_recclerview_item,parent,false);
        return new SingleCustomerSellsDetailsCustomAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.productNameTextView.setText(String.valueOf(singleCustomerProductList.get(position).getName()));
        holder.productPriceTextView.setText(String.valueOf(singleCustomerProductList.get(position).getSellingPrice()));
        holder.productQuantityTexView.setText(String.valueOf(singleCustomerProductList.get(position).getQuantity())+String.valueOf(singleCustomerProductList.get(position).getUnit()));
        holder.productTotalPriceTextView.setText(String.valueOf(singleCustomerProductList.get(position).getSellingPrice()*singleCustomerProductList.get(position).getQuantity()));
        holder.serialTextView.setText(String.valueOf(position+1));

    }

    @Override
    public int getItemCount() {
        return singleCustomerProductList.size();
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
