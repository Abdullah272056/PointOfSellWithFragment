package com.example.pointofsell;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.pointofsell.customer.CustomerFragment;
import com.example.pointofsell.customer.SingleCustomerTotalSellFragment;
import com.example.pointofsell.customer.get_customer.CustomerCountResponse;
import com.example.pointofsell.invoice.get_all_sell_info.GetAllSellInfoResponse;
import com.example.pointofsell.product.get_all_product_info.GetAllProductInfoDataResponse;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OthersFragment extends Fragment {

    Toolbar toolbar;
    LinearLayout linearLayout;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    TextView customerTextView,productTextView,invoiceTextView;
    TextView totalSaleAmountTextView,totalSoldProductQuantityTextView,totalSoldInvoiceTextView,
            totalDueAmountTextView,totalProfitTextView;
    TextView totalProductCostTextView,totalProductStockTextView,totalProductTypeTextView,extraInfoTextView;
    TextView customerCountTextView;
    Button sellInfoByDateButton;

    ApiInterface apiInterface;
    String token;
    String customerCount;
    View view;
    Fragment fragment;
    SharePref sharePref;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.others_fragment,container,false);
            Bundle bundle=this.getArguments();
            token=bundle.getString("token");


        linearLayout=view.findViewById(R.id.mainLayoutId);
        toolbar=view.findViewById (R.id.toolbarId);
        drawerLayout=view.findViewById (R.id.drawerLayoutId);
        navigationView=view.findViewById (R.id.myNavigationViewId);

        //finding textView
        customerCountTextView=view.findViewById(R.id.customerCountId);
        totalSaleAmountTextView=view.findViewById(R.id.totalSaleAmountTextViewId);
        totalSoldProductQuantityTextView=view.findViewById(R.id.totalSoldProductQuantityTextViewId);
        totalSoldInvoiceTextView=view.findViewById(R.id.totalSoldInvoiceTextViewId);
        totalDueAmountTextView=view.findViewById(R.id.totalDueAmountTextViewId);
        totalProfitTextView=view.findViewById(R.id.totalProfitTextViewId);
        totalProductCostTextView=view.findViewById(R.id.totalProductCostTextViewId);
        totalProductStockTextView=view.findViewById(R.id.totalProductStockTextViewId);
        totalProductTypeTextView=view.findViewById(R.id.totalProductTypeTextViewId);
        apiInterface = RetrofitClient.getRetrofit("http://mern-pos.herokuapp.com/").create(ApiInterface.class);
        sharePref=new SharePref();
        navigationDrawer();
        getCustomerCount();
        getAllSellInfo();
        getAllProductInfo();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                fragment=new UpComingFeatureFragment();
                switch(item.getItemId ()){

                    case R.id.reportItemIdId:
                        getFragmentManager().beginTransaction().replace(R.id.frameViewId,fragment).commit();
                        break;
                    case R.id.eCommerceItemIdId:
                        getFragmentManager().beginTransaction().replace(R.id.frameViewId,fragment).commit();
                        break;
                    case R.id.currentMonthItemIdId:
                        getFragmentManager().beginTransaction().replace(R.id.frameViewId,fragment).commit();
                        break;
                    case R.id.lastQuarterItemIdId:
                        getFragmentManager().beginTransaction().replace(R.id.frameViewId,fragment).commit();
                        break;
                    case R.id.yearEndSaleItemId:
                        getFragmentManager().beginTransaction().replace(R.id.frameViewId,fragment).commit();

                        break;
                    case R.id.calculatorItemIdId:
                        getFragmentManager().beginTransaction().replace(R.id.frameViewId,fragment).commit();
                        break;

                    case R.id.logOutId:
                        sharePref.rememberData(getActivity(),"","");
                        Intent intent=new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                        break;
//
                    case R.id.dashBoardAllDataItemIdId:

                        Bundle bundle = new Bundle();
                        bundle.putString("token", token);
                        Fragment fragment = new AboutMeFragment();
                        fragment.setArguments(bundle);

                        getFragmentManager().beginTransaction().replace(R.id.frameViewId,fragment).commit();
                        break;

                }
                return false;
            }
        });
        return view;
    }

    // getting total customer count
    private void getCustomerCount() {
        apiInterface.getCustomerCount("Bearer "+token).enqueue(new Callback<CustomerCountResponse>() {
            @Override
            public void onResponse(Call<CustomerCountResponse> call, Response<CustomerCountResponse> response) {
                if(getActivity() != null) {
                    if (response.code()==200){
                CustomerCountResponse customerCountResponse=response.body();
                customerCount=customerCountResponse.getCustomerCount().toString();
                customerCountTextView.setText(customerCount);
            }
          }
        }

            @Override
            public void onFailure(Call<CustomerCountResponse> call, Throwable t) {
                if(getActivity() != null) {

            }}
        });
    }


    // getting  Get all sale info
    private void getAllSellInfo() {
        apiInterface.getAllSellInfo("Bearer "+token).enqueue(new Callback<GetAllSellInfoResponse>() {
            @Override
            public void onResponse(Call<GetAllSellInfoResponse> call, Response<GetAllSellInfoResponse> response) {

                if (response.code()==200){
                    if(getActivity() != null) {
                        GetAllSellInfoResponse getAllSellInfoResponse = response.body();
                        totalSaleAmountTextView.setText(getAllSellInfoResponse.getGetAllSellInfoData().getTotalSaleAmount().toString());
                        totalSoldProductQuantityTextView.setText(getAllSellInfoResponse.getGetAllSellInfoData().getTotalSoldProductQuantity().toString());
                        totalSoldInvoiceTextView.setText(getAllSellInfoResponse.getGetAllSellInfoData().getTotalSoldInvoice().toString());
                        totalDueAmountTextView.setText(getAllSellInfoResponse.getGetAllSellInfoData().getTotalDueAmount().toString());
                        totalProfitTextView.setText(getAllSellInfoResponse.getGetAllSellInfoData().getTotalProfit().toString());
                    }
                }
            }
            @Override
            public void onFailure(Call<GetAllSellInfoResponse> call, Throwable t) {
                if(getActivity() != null) {
                    //Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    // Get all select_customer info
    private void getAllProductInfo(){

        apiInterface.getAllProductInfo("Bearer "+token).enqueue(new Callback<GetAllProductInfoDataResponse>() {
            @Override
            public void onResponse(Call<GetAllProductInfoDataResponse> call, Response<GetAllProductInfoDataResponse> response) {
                if(getActivity() != null) {
                if (response.code()==200){
                    GetAllProductInfoDataResponse getAllProductInfoDataResponse=response.body();
                    totalProductCostTextView.setText(getAllProductInfoDataResponse.getGetAllProductInfoData().getTotalProductCost().toString());
                    totalProductStockTextView.setText(getAllProductInfoDataResponse.getGetAllProductInfoData().getTotalProduct().toString());
                    totalProductTypeTextView.setText(getAllProductInfoDataResponse.getGetAllProductInfoData().getTotalProductType().toString());

                }
                else if (response.code()==401){
                    //Toast.makeText(HomePage.this, "Invalid token", Toast.LENGTH_SHORT).show();
                }else if (response.code()==404){
                    //Toast.makeText(HomePage.this, "No information found", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Toast.makeText(HomePage.this, "Invalid token", Toast.LENGTH_SHORT).show();
                }
            }}
            @Override
            public void onFailure(Call<GetAllProductInfoDataResponse> call, Throwable t) {
                if(getActivity() != null) {
                //Toast.makeText(OthersInformation.this, "failed", Toast.LENGTH_SHORT).show();
            }}
        });
    }



    // create for drawerLayout
    private void navigationDrawer() {

        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(
                getActivity(),drawerLayout,toolbar,R.string.open,R.string.closed){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast.makeText (getActivity(), "Open", Toast.LENGTH_SHORT).show ();
            }
            @Override
            public void onDrawerClosed(View drawerView){
                super.onDrawerClosed(drawerView);
                Toast.makeText (getActivity(), "Closed", Toast.LENGTH_SHORT).show ();
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorWhite));
        actionBarDrawerToggle.syncState();
    }

}
