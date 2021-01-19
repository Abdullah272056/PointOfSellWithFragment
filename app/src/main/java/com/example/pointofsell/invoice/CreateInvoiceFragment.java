package com.example.pointofsell.invoice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointofsell.R;
import com.example.pointofsell.customer.get_customer.CustomerInformationData;
import com.example.pointofsell.invoice.create_invoice.CustomerCustomAdapter;
import com.example.pointofsell.invoice.create_invoice.SetInVoiceResponse;
import com.example.pointofsell.invoice.create_invoice.SetProductData;
import com.example.pointofsell.product.delete_product.GetProductData;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

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









        return view;
    }

    @Override
    public void onContactClick1(int position) {

    }
}
