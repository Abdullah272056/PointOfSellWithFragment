package com.example.pointofsell.product.get_product;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointtosellpractice.R;
import com.example.pointtosellpractice.product.ProductActivity;
import com.example.pointtosellpractice.product.delete_product.DeleteProductDataResponse;
import com.example.pointtosellpractice.product.delete_product.GetProductData;
import com.example.pointtosellpractice.retrofit.ApiInterface;
import com.example.pointtosellpractice.retrofit.RetrofitClient;
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

        holder.productRecyclerViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
                addProductInformation(position);
            }
        });

        // delete button clicked
        holder.deleteProductImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProduct(position);
                notifyDataSetChanged();
                ((ProductActivity)context).getAllProduct();
            }
        }); // delete button clicked
        holder.editProductImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Not available", Toast.LENGTH_SHORT).show();
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


    private void addProductInformation(int position){
        AlertDialog.Builder builder     =new AlertDialog.Builder(context);
        LayoutInflater layoutInflater   =LayoutInflater.from(context);
        View view                       =layoutInflater.inflate(R.layout.product_details,null);
        builder.setView(view);
        final AlertDialog alertDialog   = builder.create();

        productNameTextView=view.findViewById(R.id.productNameTextViewId);
        productRegularPriceTextView=view.findViewById(R.id.productRegularPriceTextViewId);
        productSellingPriceTextView=view.findViewById(R.id.productSellingPriceTextViewId);
        productStockTextView=view.findViewById(R.id.productStockTextViewId);
        productUnitTextView=view.findViewById(R.id.productUnitTextViewId);
        productDescriptionTextView=view.findViewById(R.id.productDescriptionTextViewId);
        productAddDateTextView=view.findViewById(R.id.productAddDateTextViewId);
        productUpdateDateTextView=view.findViewById(R.id.productUpdateDateTextViewId);
        productIDetailsImageView=view.findViewById(R.id.productImageViewId);

        okButton=view.findViewById(R.id.okButtonId);


        productNameTextView.setText(String.valueOf(productDataList.get(position).getName()));
        productRegularPriceTextView.setText(String.valueOf(productDataList.get(position).getPrice()));
        productSellingPriceTextView.setText(String.valueOf(productDataList.get(position).getSellingPrice()));
        productStockTextView.setText(String.valueOf(productDataList.get(position).getStock()));
        productDescriptionTextView.setText(String.valueOf(productDataList.get(position).getDescription()));

      //  String string=String.valueOf(productDataList.get(position).getCreatedAt());
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy \nhh:mm a", Locale.forLanguageTag(String.valueOf(productDataList.get(position).getCreatedAt())));
        String getCreatedAt = df.format(new Date());
        productAddDateTextView.setText(String.valueOf(getCreatedAt));

        SimpleDateFormat df1 = new SimpleDateFormat("dd MMM yyyy \nhh:mm a", Locale.forLanguageTag(String.valueOf(productDataList.get(position).getUpdatedAt())));
        String getUpdatedAt = df.format(new Date());
        productUpdateDateTextView.setText(String.valueOf(getUpdatedAt));

        productUnitTextView.setText(String.valueOf(productDataList.get(position).getUnit()));


        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });


        Picasso.with(context).load(Uri.parse(String.valueOf(productDataList.get(position).getImage()))).into(productIDetailsImageView);

        alertDialog.show();
    }


private  void deleteProduct(final int position){
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

                            ((ProductActivity)context).getAllProduct();
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
