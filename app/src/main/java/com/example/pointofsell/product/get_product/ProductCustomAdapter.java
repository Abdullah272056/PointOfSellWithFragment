package com.example.pointofsell.product.get_product;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointofsell.R;
import com.example.pointofsell.customer.CustomerFragment;
import com.example.pointofsell.product.ProductFragment;
import com.example.pointofsell.product.delete_product.DeleteProductDataResponse;
import com.example.pointofsell.product.delete_product.GetProductData;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductCustomAdapter extends RecyclerView.Adapter<ProductCustomAdapter.MyViewHolder> {
    String idd;


    TextView productNameTextView,productRegularPriceTextView,productSellingPriceTextView,
           productStockTextView,productUnitTextView,productDescriptionTextView,
           productAddDateTextView,productUpdateDateTextView;
   ImageView productIDetailsImageView;

   Button okButton;

    Context context;
    String token;
    List<GetProductData> productDataList;

    ApiInterface apiInterface;
    public ProductCustomAdapter(Context context, String token, List<GetProductData> productDataList) {
        this.context = context;
        this.token = token;
        this.productDataList = productDataList;
        apiInterface = RetrofitClient.getRetrofit("http://mern-pos.herokuapp.com/").create(ApiInterface.class);
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_recyclerview_item,parent,false);
        return new ProductCustomAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.productNameTextView.setText(String.valueOf(productDataList.get(position).getName()));
        holder.productSellingPriceTextView.setText(String.valueOf(productDataList.get(position).getSellingPrice()));
        holder.productStockTextView.setText(String.valueOf(productDataList.get(position).getStock()));

        //image Load
        Picasso.with(context).load(Uri.parse(String.valueOf(productDataList.get(position).getImage()))).into(holder.productImageView);


        // delete button clicked
        holder.deleteProductImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProduct(position,v);

            }
        });



    }

    @Override
    public int getItemCount() {
        return productDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView productNameTextView,productSellingPriceTextView,productStockTextView;
        ImageView productImageView;
        LinearLayout productRecyclerViewItem;
        ImageView editProductImageView,deleteProductImageView;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            productNameTextView=itemView.findViewById(R.id.productNameTextViewId);
            productSellingPriceTextView=itemView.findViewById(R.id.productSellingPriceTextViewId);
            productStockTextView=itemView.findViewById(R.id.productStockTextViewId);
            productImageView=itemView.findViewById(R.id.productImageViewId);
            productRecyclerViewItem=itemView.findViewById(R.id.productRecyclerViewItemId);

            editProductImageView=itemView.findViewById(R.id.editProductImageViewId);
            deleteProductImageView=itemView.findViewById(R.id.deleteProductImageViewId);
        }
    }


    private  void deleteProduct(final int position, final View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Delete?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                apiInterface.deleteProduct("Bearer "+token,productDataList.get(position).getId())
                        .enqueue(new Callback<DeleteProductDataResponse>() {
                            @Override
                            public void onResponse(Call<DeleteProductDataResponse> call, Response<DeleteProductDataResponse> response) {
                                if (response.code()==200){

                                    Bundle bundle=new Bundle();
                                    bundle.putString("token",token);
                                    Fragment fragment=new ProductFragment();
                                    fragment.setArguments(bundle);
                                    AppCompatActivity activity=(AppCompatActivity)v.getContext();
                                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameViewId,fragment).commit();

                                    Toast.makeText(context, "Product deleted successfully", Toast.LENGTH_SHORT).show();
                                
                                }
                                else if (response.code()==404){
                                    Toast.makeText(context, "Not found", Toast.LENGTH_SHORT).show();
                                }
                                else if (response.code()==401){
                                    Toast.makeText(context, "You are not authorized to access this route", Toast.LENGTH_SHORT).show();

                                }else {
                                    Toast.makeText(context, "try again", Toast.LENGTH_SHORT).show();
                                }



                                //((ProductActivity)context).getAllProduct();
                            }

                            @Override
                            public void onFailure(Call<DeleteProductDataResponse> call, Throwable t) {
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

}
