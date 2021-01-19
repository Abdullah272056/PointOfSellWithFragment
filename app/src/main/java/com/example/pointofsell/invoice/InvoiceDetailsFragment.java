package com.example.pointofsell.invoice;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointofsell.R;
import com.example.pointofsell.invoice.single_invoice.SingleInvoiceCustomAdapter;
import com.example.pointofsell.invoice.single_invoice.SingleInvoiceData;
import com.example.pointofsell.invoice.single_invoice.SingleInvoiceGetResponse;
import com.example.pointofsell.invoice.single_invoice.SingleInvoiceProductData;
import com.example.pointofsell.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvoiceDetailsFragment extends Fragment {
    View view;
    ApiInterface apiInterface;
    String token,invoice_id;

    List<SingleInvoiceProductData> singleInvoiceProductDataList;
    SingleInvoiceData singleInvoiceData;

    SingleInvoiceCustomAdapter singleInvoiceCustomAdapter;
    RecyclerView inVoiceDetailsRecyclerView;
    TextView productTotalPriceTextView,totalAmountAfterDiscountTextView,
            payAmountTextView, dueTextView,discountTextView;
    int sub=0;
    ProgressBar invoiceDetailsProgressBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.invoice_details_fragment, container, false);



        return view;
    }


    

    }
