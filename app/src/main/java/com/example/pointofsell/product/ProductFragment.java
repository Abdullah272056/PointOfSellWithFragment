package com.example.pointofsell.product;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointofsell.R;
import com.example.pointofsell.retrofit.ApiInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.List;

public class ProductFragment extends Fragment {
    String productName,productRegularPrice,productSellingPrice,productStock,productDescription,unitType;

    Spinner unitTypeSpinner;
    //List<GetProductData> getProductDataList;
    String token;
    ApiInterface apiInterface;
    //ProductCustomAdapter productCustomAdapter;
    RecyclerView productRecyclerView;
    ProgressBar productProgressBar;
    FloatingActionButton addProductButton;
    EditText productNameEditText,productRegularPriceEditText,productSellingPriceEditText,
            productStockEditText,productDescriptionEditText;
    TextView pieceTextView;
    ImageView productSelectImageView;
    Button uploadProductButton;

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99 ;
    private static final int CAPTURE_REQUEST_CODE = 0;
    private static final int SELECT_REQUEST_CODE = 1;
    // private OurRetrofitClient ourRetrofitClient;
    private ProgressDialog progressDialog;
    Button imageUploadButton;

    File file;
    Uri imageUri=null;

    AlertDialog.Builder builder;
    AlertDialog alertDialog;

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.product_fragment, container, false);

        productRecyclerView=view.findViewById(R.id.productRecyclerViewId);
        productProgressBar=view.findViewById(R.id.productProgressBarId);
        addProductButton=view.findViewById(R.id.addProductButtonId);
       // unitTypeSpinner=view.findViewById(R.id.unitTypeSpinnerId);

        return view;
    }
}
