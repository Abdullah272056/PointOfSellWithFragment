package com.example.pointofsell.invoice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointofsell.R;
import com.example.pointofsell.invoice.get_invoice.InVoiceResponse;
import com.example.pointofsell.invoice.get_invoice.Invoice;
import com.example.pointofsell.invoice.get_invoice.InvoiceCustomAdapter;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Invoice_Fragment extends Fragment {
    ApiInterface apiInterface;
    String token;
    List<Invoice> invoiceList;
    InvoiceCustomAdapter invoiceCustomAdapter;
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

      


        getAllInVoice();

        return view;
        }

    // getting  Get all invoice
    public void getAllInVoice(){

        apiInterface.getInvoice("Bearer "+token).enqueue(new Callback<InVoiceResponse>() {
            @Override
            public void onResponse(Call<InVoiceResponse> call, Response<InVoiceResponse> response) {

                if (response.code()==200){
                    invoiceList=new ArrayList<>();
                    invoiceList.addAll(response.body().getInvoices());
                    if (invoiceList.size ()>0){
                        Toast.makeText(getActivity(), String.valueOf(invoiceList.size()), Toast.LENGTH_SHORT).show();
                        Log.e("se",String.valueOf(invoiceList.get(0).getCustomer().getName()));
                        invoiceCustomAdapter = new InvoiceCustomAdapter(getActivity(),token,invoiceList);
                        invoiceRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        invoiceRecyclerView.setAdapter(invoiceCustomAdapter);
                    }
                }else if (response.code()==404){
                    Toast.makeText(getActivity(), "No invoice found", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();

                }


                invoiceProgressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<InVoiceResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "fail", Toast.LENGTH_SHORT).show();
                invoiceProgressBar.setVisibility(View.GONE);
            }
        });

    }



    }
