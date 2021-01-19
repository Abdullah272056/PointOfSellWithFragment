package com.example.pointofsell.invoice.create_invoice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointofsell.R;
import com.example.pointofsell.product.delete_product.GetProductData;

import java.util.List;

public class ProductCustomAdapter2 extends RecyclerView.Adapter<ProductCustomAdapter2.MyViewHolder> {

    Context context;
    String token;
    List<GetProductData> productDataList;

   ProductCustomAdapter2.OnContactClickListener3 onContactClickListener;
    public ProductCustomAdapter2(Context context, String token, List<GetProductData> productDataList, ProductCustomAdapter2.OnContactClickListener3 onContactClickListener) {
        this.context = context;
        this.token = token;
        this.productDataList = productDataList;

        this.onContactClickListener = onContactClickListener;

    }
    @NonNull
    @Override
    public ProductCustomAdapter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.select_product_item,parent,false);
        return new ProductCustomAdapter2.MyViewHolder(view,onContactClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCustomAdapter2.MyViewHolder holder, final int position) {
        holder.productNameTextView.setText(String.valueOf(productDataList.get(position).getName()));
        holder.productSellingPriceTextView.setText(String.valueOf(productDataList.get(position).getSellingPrice()));
        holder.productStockTextView.setText(String.valueOf(productDataList.get(position).getStock()));
        holder.quantityTextView.setText(String.valueOf(productDataList.get(position).getQuantity()));

        holder.itemDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productDataList.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.addQuantityTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (productDataList.get(position).getQuantity()<productDataList.get(position).getStock()){
                    productDataList.set(position,new GetProductData(productDataList.get(position).getPrice(),
                            productDataList.get(position).getSellingPrice(),
                            productDataList.get(position).getStock(),
                            productDataList.get(position).getQuantity()+1,productDataList.get(position).getId(),
                            productDataList.get(position).getName(),
                            productDataList.get(position).getUnit()));

                    notifyDataSetChanged();
                }
                else {
                    Toast.makeText(context, "Stock limit", Toast.LENGTH_SHORT).show();
                }

            }
        });
        holder.subQuantityTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (productDataList.get(position).getQuantity()>1){
                    productDataList.set(position,new GetProductData(productDataList.get(position).getPrice(),
                            productDataList.get(position).getSellingPrice(),
                            productDataList.get(position).getStock(),
                            productDataList.get(position).getQuantity()-1,productDataList.get(position).getId(),
                            productDataList.get(position).getName(),
                            productDataList.get(position).getUnit()));
                    notifyDataSetChanged();
                }
                else {
                    Toast.makeText(context, "Can not sub ", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }

    @Override
    public int getItemCount(){
        return productDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView productNameTextView,productSellingPriceTextView,productStockTextView;
        TextView addQuantityTextView,quantityTextView,subQuantityTextView;
        ProductCustomAdapter2.OnContactClickListener3 onContactClickListener;
        ImageView itemDeleteButton;

        public MyViewHolder(@NonNull View itemView, ProductCustomAdapter2.OnContactClickListener3 onContactClickListener){
            super(itemView);

            addQuantityTextView=itemView.findViewById(R.id.addQuantityTextViewId);
            quantityTextView=itemView.findViewById(R.id.quantityTextViewId);
            subQuantityTextView=itemView.findViewById(R.id.subQuantityTextViewId);

            productNameTextView=itemView.findViewById(R.id.productNameTextViewId);
            productSellingPriceTextView=itemView.findViewById(R.id.productSellingPriceTextViewId);
            productStockTextView=itemView.findViewById(R.id.productStockTextViewId);
            itemDeleteButton=itemView.findViewById(R.id.itemDeleteButtonId);

            this.onContactClickListener=onContactClickListener;
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            onContactClickListener.onContactClick3(getAdapterPosition());
        }
    }

    public  interface  OnContactClickListener3{
        void onContactClick3(int position);
    }

}
