package com.example.pointofsell.invoice;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.example.pointofsell.customer.CustomerPayDueFragment;
import com.example.pointofsell.customer.get_customer.CustomerInformationData;
import com.example.pointofsell.customer.get_customer.CustomerInformationDataResponse;
import com.example.pointofsell.invoice.create_invoice.CustomerCustomAdapter;
import com.example.pointofsell.invoice.create_invoice.ProductCustomAdapter2;
import com.example.pointofsell.invoice.create_invoice.ProductCustomAdapter3;
import com.example.pointofsell.invoice.create_invoice.SetInVoiceResponse;
import com.example.pointofsell.invoice.create_invoice.SetProductData;
import com.example.pointofsell.owner_all_information.OwnerDataWithResponse;
import com.example.pointofsell.product.delete_product.GetProductData;
import com.example.pointofsell.product.get_product.GetProductDataResponse;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateInvoiceFragment extends Fragment implements
        CustomerCustomAdapter.OnContactClickListener1, ProductCustomAdapter2.OnContactClickListener3, ProductCustomAdapter3.OnContactClickListener{

    TextView titleBarTextView;
    ImageView backImageView;

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
    ProductCustomAdapter3.OnContactClickListener onContactClickListener;
    ProductCustomAdapter2.OnContactClickListener3 onContactClickListener3;
    CustomerCustomAdapter.OnContactClickListener1 onContactClickListener1;

    ProductCustomAdapter3 productCustomAdapter;
    ProductCustomAdapter2 productCustomAdapter2;
    CustomerCustomAdapter customerCustomAdapter;
    AlertDialog alertDialog;

    RecyclerView selectRecyclerView;


    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.create_invoice_fragment, container, false);


        onContactClickListener=this;
        onContactClickListener1=this;
       onContactClickListener3=this;

        //title bar view Finding
        titleBarTextView=view.findViewById(R.id.titleBarTextViewId);
        backImageView=view.findViewById(R.id.backImageViewId);
        titleBarTextView.setText("Create invoice");

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

        //selected product button click
        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeStatus=0;
                getAllProduct();
            }
        });

        // text change listener add
        payAmountEditText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                changeStatus=0;
            }
        });

        // percent decrement button
        subCountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int discount=Integer.parseInt(discountTextView.getText().toString());
                if (discount>0){
                    discount--;
                }
                discountTextView.setText(String.valueOf(discount));
                changeStatus=0;
            }
        });

        // percent increment button
        addCountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int discount=Integer.parseInt(discountTextView.getText().toString());
                if (discount<100){
                    discount++;
                }
                discountTextView.setText(String.valueOf(discount));
                changeStatus=0;
            }
        });
        // create invoice button click
        inVoiceButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (changeStatus==1){
                    Toast.makeText(getActivity(), "Ready", Toast.LENGTH_SHORT).show();
                    setInVoice();
                }else {
                    Toast.makeText(getActivity(), "click calculate button", Toast.LENGTH_SHORT).show();
                }
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int  payDue=0,due;
                int discountAmount=0;
                int subTotal=0;
                int sz=newList.size();
                if (sz>0){

                    for (int i=0;sz-1>=i;i++){
                        subTotal=subTotal+(newList.get(i).getSellingPrice()*newList.get(i).getQuantity());
                    }
                }
                int discount=Integer.parseInt(discountTextView.getText().toString());

                if (discount>0){
                    float dis=(subTotal*discount)/100;
                    discountAmount= (int) dis;
                }

                if (!TextUtils.isEmpty(payAmountEditText.getText().toString())){
                    payDue=Integer.parseInt(payAmountEditText.getText().toString());
                }
                subTotalTextView.setText(String.valueOf(subTotal));
                grandTotalTextView.setText(String.valueOf(subTotal-discountAmount));
                due=(subTotal-discountAmount)-payDue;
                dueTextView.setText(String.valueOf(due));
                changeStatus=1;
            }
        });

        return view;
    }

    public void  setInVoice(){
        customerId= customerIdTextView.getText().toString().trim();
        if (TextUtils.isEmpty(customerId)){
            nameTextView.setError("Enter your email");
            nameTextView.requestFocus();
            return;
        }if (newList.size()<=0){
            Toast.makeText(getActivity(), "please select add product", Toast.LENGTH_SHORT).show();
            return;
        }
//        name= nameTextView.getText().toString().trim();
//        phone= phoneTextView.getText().toString().trim();


        int  totalProductAmount= Integer.parseInt(grandTotalTextView.getText().toString());
        int  discount= Integer.parseInt(discountTextView.getText().toString());
        int payAmount=0;

        if (!TextUtils.isEmpty(payAmountEditText.getText().toString())){
            payAmount= Integer.parseInt(payAmountEditText.getText().toString());
        }

        setProductDataList=new ArrayList<>();

        int sz=newList.size();
        for (int i=0;sz-1>=i;i++){
            setProductDataList.add(new SetProductData(newList.get(i).getId(),newList.get(i).getQuantity()));
        }




        // int discount=Integer.parseInt(discountTextView.getText().toString());


        int TotalAmount=0;
        int sze=newList.size();
        int discountAmount=0;
        if (sze>0){

            for (int i=0;sze-1>=i;i++){
                TotalAmount=TotalAmount+(newList.get(i).getSellingPrice()*newList.get(i).getQuantity());
            }
        }
        if (discount>0){
            float dis=(TotalAmount*discount)/100;
            discountAmount= (int) dis;
        }


        setInVoiceResponse=new SetInVoiceResponse(customerId,payAmount,
                (TotalAmount-discountAmount),discount, setProductDataList);
        apiInterface.getInvoiceResponse("Bearer "+token,setInVoiceResponse).enqueue(new Callback<OwnerDataWithResponse>() {
            @Override
            public void onResponse(Call<OwnerDataWithResponse> call, Response<OwnerDataWithResponse> response) {
                if (response.code()==201){
                    Bundle bundle=new Bundle();
                    bundle.putString("token",token);
                    Fragment fragment=new Invoice_Fragment();
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.frameViewId,fragment).commit();
                    Toast.makeText(getActivity(), "create success", Toast.LENGTH_SHORT).show();
                }
                else if (response.code()==500){

                    Toast.makeText(getActivity(), "internal server error", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(), "create failed", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<OwnerDataWithResponse> call, Throwable t) {
                Toast.makeText(getActivity(), String.valueOf(t.getMessage()), Toast.LENGTH_SHORT).show();
                Log.e("getInvoice", String.valueOf(t.getMessage()));
            }
        });

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

    public void getAllProduct() {
        apiInterface.getAllProduct("Bearer "+token).enqueue(new Callback<GetProductDataResponse>() {
            @Override
            public void onResponse(Call<GetProductDataResponse> call, Response<GetProductDataResponse> response) {

                if (response.code()==200){
                    getProductDataList=new ArrayList<>();
                    filterProductDataList=new ArrayList<>();
                    getProductDataList.addAll(response.body().getProducts());
                    if (getProductDataList.size ()>0){

                        int sz=getProductDataList.size();
                        for (int i=0;sz-1>=i;i++){
                            int stck=getProductDataList.get(i).getStock();
                            if (stck>0){
                                filterProductDataList.add(getProductDataList.get(i));
                                Toast.makeText(getActivity(), String.valueOf(filterProductDataList.size()), Toast.LENGTH_SHORT).show();
                            }
                        }
                        addProductInformation(filterProductDataList);
                    }

                    // Toast.makeText(CreateInVoice_Activity.this, "All product fetched", Toast.LENGTH_SHORT).show();
                }else if (response.code()==404){
                    Toast.makeText(getActivity(), "Product not found", Toast.LENGTH_SHORT).show();
                }
                else if (response.code()==401){
                    Toast.makeText(getActivity(), "Invalid token", Toast.LENGTH_SHORT).show();
                }else {
                }

            }
            @Override
            public void onFailure(Call<GetProductDataResponse> call, Throwable t) {
            }
        });
    }

    private void addProductInformation( List<GetProductData> getProductDataList){
        AlertDialog.Builder builder     =new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater   =LayoutInflater.from(getActivity());
        View view                       =layoutInflater.inflate(R.layout.product,null);
        builder.setView(view);
        alertDialog   = builder.create();

        productRecyclerView=view.findViewById(R.id.productRecyclerViewId);
        productCustomAdapter = new ProductCustomAdapter3(getActivity(),token,getProductDataList,onContactClickListener);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        productRecyclerView.setAdapter(productCustomAdapter);

        alertDialog.show();
    }






    //customer
    @Override
    public void onContactClick1(int position) {
        nameTextView.setError(null);
        nameTextView.requestFocus();
        nameTextView.setText(String.valueOf(customerInformationDataList.get(position).getName()));
        phoneTextView.setText(String.valueOf(customerInformationDataList.get(position).getPhone()));
        addressTextView.setText(String.valueOf(customerInformationDataList.get(position).getAddress()));
        oldDueTextView.setText(String.valueOf(customerInformationDataList.get(position).getDue()));
        customerIdTextView.setText(String.valueOf(customerInformationDataList.get(position).getId()));
        alertDialog.dismiss();

    }

    @Override
    public void onContactClick3(int position) {

    }


    //product
    @Override
    public void onContactClick(int position) {

        int sSize= newList.size();
        if (sSize>0){

            // double not selected
            for (int i=0;sSize-1>=i;i++){
                String id=newList.get(i).getId();
                if (id.equals(filterProductDataList.get(position).getId())){
                    Toast.makeText(getActivity(), "already selected", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    return;
                }
                newList.add(new GetProductData(filterProductDataList.get(position).getPrice(),
                        filterProductDataList.get(position).getSellingPrice(),
                        filterProductDataList.get(position).getStock(),
                        1,filterProductDataList.get(position).getId(),
                        filterProductDataList.get(position).getName(),
                        filterProductDataList.get(position).getUnit()));
            }
        }else {

            newList.add(new GetProductData(filterProductDataList.get(position).getPrice(),
                    filterProductDataList.get(position).getSellingPrice(),
                    filterProductDataList.get(position).getStock(),
                    1,filterProductDataList.get(position).getId(),
                    filterProductDataList.get(position).getName(),
                    filterProductDataList.get(position).getUnit()));
        }


        productCustomAdapter2 = new ProductCustomAdapter2(getActivity(),token,newList, onContactClickListener3);
        selectRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        selectRecyclerView.setAdapter(productCustomAdapter2);
        Log.e("size",String.valueOf(newList.size()));
        alertDialog.dismiss();
    }

}
