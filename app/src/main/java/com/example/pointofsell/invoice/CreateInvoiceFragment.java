package com.example.pointofsell.invoice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointofsell.R;
import com.example.pointofsell.customer.get_customer.CustomerInformationData;
import com.example.pointofsell.customer.get_customer.CustomerInformationDataResponse;
import com.example.pointofsell.invoice.create_invoice.CustomerCustomAdapter;
import com.example.pointofsell.invoice.create_invoice.SetInVoiceResponse;
import com.example.pointofsell.invoice.create_invoice.SetProductData;
import com.example.pointofsell.product.delete_product.GetProductData;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateInvoiceFragment extends Fragment implements
        CustomerCustomAdapter.OnContactClickListener1{


    int changeStatus=0;

    TextView nameTextView,phoneTextView,addressTextView,oldDueTextView,customerIdTextView;
    String  name,phone,address,due,customerId;

    TextView subTotalTextView,discountTextView,grandTotalTextView,dueTextView;
    Button subCountButton,addCountButton,calculateButton;
    EditText payAmountEditText;


    ApiInterface apiInterface;
    String token;
    List<SetProductData> setProductDataList;
    List<GetProductData> newList=new ArrayList<>();

    List<CustomerInformationData> customerInformationDataList;
    List<GetProductData> getProductDataList;
    List<GetProductData> filterProductDataList;

    SetInVoiceResponse setInVoiceResponse;
    Button inVoiceButton,product,selectCustomerButton;
    RecyclerView productRecyclerView;
    ListView listView;
//    ProductCustomAdapter3.OnContactClickListener onContactClickListener;
//    ProductCustomAdapter2.OnContactClickListener3 onContactClickListener3;
    CustomerCustomAdapter.OnContactClickListener1 onContactClickListener1;

//    ProductCustomAdapter3 productCustomAdapter;
//    ProductCustomAdapter2 productCustomAdapter2;
    CustomerCustomAdapter customerCustomAdapter;
    AlertDialog alertDialog;

    RecyclerView selectRecyclerView;


    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.create_invoice_fragment, container, false);


       // onContactClickListener=this;
        onContactClickListener1=this;
       // onContactClickListener3=this;


        inVoiceButton=view.findViewById(R.id.inVoiceButtonId);
        selectCustomerButton=view.findViewById(R.id.selectCustomerButtonId);
        product=view.findViewById(R.id.productId);
        selectRecyclerView=view.findViewById(R.id.selectRecyclerView);
        customerIdTextView=view.findViewById(R.id.customerIdTextViewId);

        nameTextView=view.findViewById(R.id.nameTextViewId);
        phoneTextView=view.findViewById(R.id.phoneTextViewId);
        addressTextView=view.findViewById(R.id.addressTextViewId);
        oldDueTextView=view.findViewById(R.id.oldDueTextViewId);

        subTotalTextView=view.findViewById(R.id.subTotalTextViewId);
        discountTextView=view.findViewById(R.id.discountTextViewId);
        grandTotalTextView=view.findViewById(R.id.grandTotalTextViewId);
        dueTextView=view.findViewById(R.id.dueTextViewId);
        subCountButton=view.findViewById(R.id.subCountButtonId);
        addCountButton=view.findViewById(R.id.addCountButtonId);
        calculateButton=view.findViewById(R.id.calculateButtonId);
        payAmountEditText=view.findViewById(R.id.payAmountEditTextId);
        apiInterface = RetrofitClient.getRetrofit("http://mern-pos.herokuapp.com/").create(ApiInterface.class);

//        //receive user token
        Bundle bundle=this.getArguments();
        token=bundle.getString("token");



        selectCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllCustomerInformation();
            }
        });





        return view;
    }
    // get All customer
    public  void getAllCustomerInformation(){
        apiInterface.getAllCustomerInformation("Bearer "+token).enqueue(new Callback<CustomerInformationDataResponse>() {
            @Override
            public void onResponse(Call<CustomerInformationDataResponse> call, Response<CustomerInformationDataResponse> response) {


                if (response.code()==500){
                    Toast.makeText(getActivity(), "Cannot read property 'id' of null", Toast.LENGTH_SHORT).show();
                }
                else if (response.code()==200){
                    customerInformationDataList=new ArrayList<>();
                    customerInformationDataList.addAll(response.body().getCustomerInformation());
                    if (customerInformationDataList.size ()>0){
                        addCustomerInformation(customerInformationDataList);
                        Toast.makeText(getActivity(), String.valueOf(customerInformationDataList.size()), Toast.LENGTH_SHORT).show();
                    }
                }
                else if (response.code()==404){
                    Toast.makeText(getActivity(), "No customer found", Toast.LENGTH_SHORT).show();
                }else {

                }

            }
            @Override
            public void onFailure(Call<CustomerInformationDataResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "fail Customer", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addCustomerInformation(List<CustomerInformationData> customerInformationDataList1){
        AlertDialog.Builder builder     =new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater   =LayoutInflater.from(getActivity());
        View view                       =layoutInflater.inflate(R.layout.select_customer,null);
        builder.setView(view);
        alertDialog   = builder.create();
        productRecyclerView=view.findViewById(R.id.productRecyclerViewId);

        customerCustomAdapter = new CustomerCustomAdapter(getActivity(),token,customerInformationDataList1,onContactClickListener1);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        productRecyclerView.setAdapter(customerCustomAdapter);
        alertDialog.show();
    }




    @Override
    public void onContactClick1(int position) {

    }
}
