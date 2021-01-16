package com.example.pointofsell.invoice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointofsell.R;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Invoice_Fragment extends Fragment {
    ApiInterface apiInterface;
    String token;
//    List<Invoice> invoiceList;
//    InvoiceCustomAdapter invoiceCustomAdapter;
    RecyclerView invoiceRecyclerView;
    ProgressBar invoiceProgressBar;
    FloatingActionButton addInvoiceButton;



    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.invoice_fragment, container, false);

        invoiceRecyclerView=view.findViewById(R.id.invoiceRecyclerViewId);
        invoiceProgressBar=view.findViewById(R.id.invoiceProgressBarId);
        addInvoiceButton=view.findViewById(R.id.addInvoiceButtonId);

        Bundle bundle=this.getArguments();
        token=bundle.getString("token");
        apiInterface = RetrofitClient.getRetrofit("http://mern-pos.herokuapp.com/").create(ApiInterface.class);


        return view;
        }
    }
