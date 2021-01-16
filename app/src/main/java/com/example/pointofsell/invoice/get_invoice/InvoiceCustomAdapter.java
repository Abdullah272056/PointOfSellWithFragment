package com.example.pointofsell.invoice.get_invoice;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointofsell.R;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvoiceCustomAdapter extends RecyclerView.Adapter<InvoiceCustomAdapter.MyViewHolderInvoice> {


    Context context;
    String token;
    List<Invoice> invoiceList;



    ApiInterface apiInterface;
    public InvoiceCustomAdapter(Context context, String token, List<Invoice> invoiceList) {
        this.context = context;
        this.token = token;
        this.invoiceList = invoiceList;
        apiInterface = RetrofitClient.getRetrofit("http://mern-pos.herokuapp.com/").create(ApiInterface.class);
    }

    @NonNull
    @Override
    public MyViewHolderInvoice onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.invoice_item,parent,false);
        return new InvoiceCustomAdapter.MyViewHolderInvoice(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolderInvoice holder, final int position){

        holder.invoiceItemCustomerNameTextView.setText(invoiceList.get(position).getCustomer().getName());

        String string=String.valueOf(invoiceList.get(position).getCreatedAt());
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy \nhh:mm a", Locale.forLanguageTag(string));
        String time = df.format(new Date());

        holder.invoiceItemDateTextView.setText(String.valueOf(time));
        holder.invoiceItemPayAmountTextView.setText(String.valueOf(invoiceList.get(position).getTotalAmountAfterDiscount()));
        holder.invoiceItemSerialTextView.setText(String.valueOf(position+1));



    }

    @Override
    public int getItemCount(){
        return invoiceList.size();
    }

    public class MyViewHolderInvoice extends RecyclerView.ViewHolder {
        ImageView deleteInvoiceImageView;
        TextView invoiceItemCustomerNameTextView,invoiceItemDateTextView,
                invoiceItemPayAmountTextView,invoiceItemSerialTextView;
        LinearLayout inVoiceItem;
        public MyViewHolderInvoice(@NonNull View itemView) {
            super(itemView);
            invoiceItemCustomerNameTextView=itemView.findViewById(R.id.invoiceItemCustomerNameTextViewId);
            invoiceItemDateTextView=itemView.findViewById(R.id.invoiceItemDateTextViewId);
            invoiceItemPayAmountTextView=itemView.findViewById(R.id.invoiceItemTotalAmountTextViewId);
            invoiceItemSerialTextView=itemView.findViewById(R.id.invoiceItemSerialTextViewId);
            deleteInvoiceImageView=itemView.findViewById(R.id.deleteInvoiceImageViewId);
            inVoiceItem=itemView.findViewById(R.id.inVoiceItemId);

        }
    }
}
