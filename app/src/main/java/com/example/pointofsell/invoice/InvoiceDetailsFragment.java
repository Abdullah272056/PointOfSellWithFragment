package com.example.pointofsell.invoice;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointofsell.OthersFragment;
import com.example.pointofsell.R;
import com.example.pointofsell.invoice.single_invoice.SingleInvoiceCustomAdapter;
import com.example.pointofsell.invoice.single_invoice.SingleInvoiceData;
import com.example.pointofsell.invoice.single_invoice.SingleInvoiceGetResponse;
import com.example.pointofsell.invoice.single_invoice.SingleInvoiceProductData;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvoiceDetailsFragment extends Fragment {
    TextView titleBarTextView;
    ImageView backImageView;


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

        //receive token
        Bundle bundle=this.getArguments();
        token=bundle.getString("token");

        invoice_id=bundle.getString("invoice_id");


        //title bar view Finding
        titleBarTextView=view.findViewById(R.id.titleBarTextViewId);
        backImageView=view.findViewById(R.id.backImageViewId);
        titleBarTextView.setText("Invoice Details ");
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("token",token);
                Fragment fragment=new Invoice_Fragment();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.frameViewId,fragment).commit();

            }
        });


        //textView finding
        productTotalPriceTextView=view.findViewById(R.id.productTotalPriceTextViewId);
        totalAmountAfterDiscountTextView=view.findViewById(R.id.totalAmountAfterDiscountTextViewId);
        payAmountTextView=view.findViewById(R.id.payAmountTextViewId);
        dueTextView=view.findViewById(R.id.dueTextViewId);
        discountTextView=view.findViewById(R.id.discountTextViewId);
        //recyclerview finding
        inVoiceDetailsRecyclerView=view.findViewById(R.id.inVoiceDetailsRecyclerViewId);
        //progressbar finding
        invoiceDetailsProgressBar=view.findViewById(R.id.invoiceDetailsProgressBarId);

        apiInterface = RetrofitClient.getRetrofit("http://mern-pos.herokuapp.com/").create(ApiInterface.class);

        // call method
        singleInvoiceDetails();

        return view;
    }


    public void singleInvoiceDetails(){
        apiInterface.getSingleInvoiceInformation("Bearer "+token,invoice_id).enqueue(new Callback<SingleInvoiceGetResponse>() {
            @Override
            public void onResponse(Call<SingleInvoiceGetResponse> call, Response<SingleInvoiceGetResponse> response) {
                SingleInvoiceGetResponse singleInvoiceGetResponse=response.body();
                invoiceDetailsProgressBar.setVisibility(View.INVISIBLE);
                if (singleInvoiceGetResponse.getSuccess()==true){
                    singleInvoiceProductDataList=new ArrayList<>();
                    singleInvoiceData=response.body().getSingleInvoiceData();
                    singleInvoiceProductDataList.addAll(response.body().getSingleInvoiceData().getSingleInvoiceProductDataList());
                    if (singleInvoiceProductDataList.size()>0){
                        singleInvoiceCustomAdapter=new SingleInvoiceCustomAdapter(getActivity(),token,singleInvoiceProductDataList);
                        inVoiceDetailsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        inVoiceDetailsRecyclerView.setAdapter(singleInvoiceCustomAdapter);
//

                        totalAmountAfterDiscountTextView.setText(String.valueOf( singleInvoiceData.getTotalAmountAfterDiscount()));
                        payAmountTextView.setText(String.valueOf(singleInvoiceData.getPayAmount()));
                        dueTextView.setText(String.valueOf(singleInvoiceData.getDue()));
                        discountTextView.setText("after "+String.valueOf(singleInvoiceData.getDiscount())+"% discount : ");


                        int size=singleInvoiceProductDataList.size();
                        for ( int i=size-1; i>=0;i--){
                            Log.e(String.valueOf(i),String.valueOf(singleInvoiceProductDataList.get(i).getSellingPrice()));
                            sub=sub+(singleInvoiceProductDataList.get(i).getSellingPrice()*singleInvoiceProductDataList.get(i).getQuantity());
                            Log.e("svs",String.valueOf(sub));
                        }
                        productTotalPriceTextView.setText(String.valueOf(sub));


                    }

                }
            }

            @Override
            public void onFailure(Call<SingleInvoiceGetResponse> call, Throwable t) {
                Toast.makeText(getActivity(), String.valueOf(t.getMessage()), Toast.LENGTH_LONG).show();
                invoiceDetailsProgressBar.setVisibility(View.INVISIBLE);
                Log.e("tsd",String.valueOf(t.getMessage()));
            }
        });

    }

    }
