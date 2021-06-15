package com.example.pointofsell.product;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointofsell.R;
import com.example.pointofsell.product.create_product.ProductDataResponse;
import com.example.pointofsell.product.delete_product.GetProductData;
import com.example.pointofsell.product.get_product.GetProductDataResponse;

import com.example.pointofsell.product.get_product.ProductCustomAdapter;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class ProductFragment extends Fragment {
    TextView titleBarTextView;
    String productName,productRegularPrice,productSellingPrice,productStock,productDescription,unitType;

    Spinner unitTypeSpinner;
    List<GetProductData> getProductDataList;
    String token;
    ApiInterface apiInterface;
    ProductCustomAdapter productCustomAdapter;
    RecyclerView productRecyclerView;
    ProgressBar productProgressBar;
    FloatingActionButton addProductButton;
    EditText productNameEditText,productRegularPriceEditText,productSellingPriceEditText,
            productStockEditText,productDescriptionEditText;
    TextView pieceTextView;
    ImageView productSelectImageView;
    TextView imgSizeTextView;
    Button uploadProductButton;

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99 ;
    private static final int CAPTURE_REQUEST_CODE = 0;
    private static final int SELECT_REQUEST_CODE = 1;
    // private OurRetrofitClient ourRetrofitClient;
    private ProgressDialog progressDialog;

    File file;
    Uri imageUri=null;

    AlertDialog.Builder builder;
    AlertDialog alertDialog;

    View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.product_fragment, container, false);
        Bundle bundle=this.getArguments();
        token=bundle.getString("token");
        apiInterface = RetrofitClient.getRetrofit("http://mern-pos.herokuapp.com/").create(ApiInterface.class);

        productRecyclerView=view.findViewById(R.id.productRecyclerViewId);
        productProgressBar=view.findViewById(R.id.productProgressBarId);
        addProductButton=view.findViewById(R.id.addProductButtonId);
        unitTypeSpinner=view.findViewById(R.id.unitTypeSpinnerId);
        titleBarTextView=view.findViewById(R.id.titleBarTextViewId);
        titleBarTextView.setText("Product list");

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Product Upload....");

        //alert dialog view show
        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createProduct();
            }
        });
        getAllProduct();
        return view;
    }


    public void getAllProduct() {
        apiInterface.getAllProduct("Bearer "+token).enqueue(new Callback<GetProductDataResponse>() {
            @Override
            public void onResponse(Call<GetProductDataResponse> call, Response<GetProductDataResponse> response) {
                if(getActivity() != null) {
                    if (response.code() == 200) {
                        if(getActivity() != null) {
                            Toast.makeText(getActivity(), "All product fetched", Toast.LENGTH_SHORT).show();
                            getProductDataList = new ArrayList<>();
                            getProductDataList.addAll(response.body().getProducts());
                            if (getProductDataList.size()>0){
                                productCustomAdapter = new ProductCustomAdapter(getActivity(), token, getProductDataList);
                                productRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                productRecyclerView.setAdapter(productCustomAdapter);
                            }
                        }
                    } else if (response.code() == 404) {
                        Toast.makeText(getActivity(), "Product not found", Toast.LENGTH_SHORT).show();
                    } else if (response.code() == 401) {
                        Toast.makeText(getActivity(), "Invalid token", Toast.LENGTH_SHORT).show();
                    } else {
                    }

                    productProgressBar.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(Call<GetProductDataResponse> call, Throwable t) {
                if(getActivity() != null) {
                    productProgressBar.setVisibility(View.GONE);
                }  }
        });
    }


    private void createProduct(){
        builder     =new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater   =LayoutInflater.from(getActivity());
        View view                       =layoutInflater.inflate(R.layout.create_product,null);
        builder.setView(view);
        alertDialog   = builder.create();

        productNameEditText=view.findViewById(R.id.productNameEditTextId);
        productRegularPriceEditText=view.findViewById(R.id.productRegularPriceEditTextId);
        productSellingPriceEditText=view.findViewById(R.id.productSellingPriceEditTextId);
        productStockEditText=view.findViewById(R.id.productStockEditTextId);
        productDescriptionEditText=view.findViewById(R.id.productDescriptionEditTextId);

        productSelectImageView=view.findViewById(R.id.productSelectImageViewId);
        imgSizeTextView=view.findViewById(R.id.imgSizeTextView);
        uploadProductButton=view.findViewById(R.id.uploadProductButtonId);
        unitTypeSpinner=view.findViewById(R.id.unitTypeSpinnerId);


        productSelectImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(CheckPermission()) {
                    Intent select = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                    startActivityForResult(select, SELECT_REQUEST_CODE);
                }
            }
        });



        uploadProductButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                productName=productNameEditText.getText().toString();
                productRegularPrice=productRegularPriceEditText.getText().toString();
                productSellingPrice=productSellingPriceEditText.getText().toString();
                productStock=productStockEditText.getText().toString();
                productDescription=productDescriptionEditText.getText().toString();
                unitType=unitTypeSpinner.getSelectedItem().toString();


                if (TextUtils.isEmpty(productName)){
                    productNameEditText.setError("Enter product name");
                    productNameEditText.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(productRegularPrice)){
                    productRegularPriceEditText.setError("Enter product regular price");
                    productRegularPriceEditText.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(productSellingPrice)){
                    productSellingPriceEditText.setError("Enter product selling price");
                    productSellingPriceEditText.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(productStock)){
                    productStockEditText.setError("Enter product stock");
                    productStockEditText.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(productDescription)){
                    productDescriptionEditText.setError("Enter product description");
                    productDescriptionEditText.requestFocus();
                    return;
                }
                if (productSelectImageView.getDrawable()==null){
                    Toast.makeText(getActivity(), "select image", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (imageUri == null){
                    Toast.makeText(getActivity(), "image url not found", Toast.LENGTH_SHORT).show();
                }
                UploadProduct(productName,productRegularPrice,productSellingPrice,unitType,productStock,productDescription);


            }
        });
        alertDialog.show();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK){

            imageUri=data.getData();

            try {
                final InputStream imageStream=getContext().getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage= BitmapFactory.decodeStream(imageStream);
                productSelectImageView.setImageBitmap(selectedImage);
                file=new File(imageUri.getPath().replace("raw/",""));
                imgSizeTextView.setText("Size"+ Formatter.formatShortFileSize(getContext(),file.length()));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(getContext(), "No image selected", Toast.LENGTH_SHORT).show();

        }


//        switch (requestCode){
//
//            case CAPTURE_REQUEST_CODE:
//
////                if(resultCode == RESULT_OK){
////
////                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
////                    imageView.setImageBitmap(bitmap);
////                    progressDialog.show();
////                    //ImageUpload(bitmap);
////
////                }
//
//
//                break;
//
//            case SELECT_REQUEST_CODE:
//                if(resultCode == RESULT_OK && data!=null
//                        && data.getData()!=null){
//                    try {
//                        imageUri = data.getData();
//                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
//                        productSelectImageView.setImageBitmap(bitmap);
//                        // progressDialog.show();
//                        //ImageUpload(imageUri);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                else {
//                    Toast.makeText(getActivity(), "url empty", Toast.LENGTH_SHORT).show();
//                }
//                break;
//        }

    }

    private void UploadProduct(String productName,String productRegularPrice,String productSellingPrice,
                               String productPiece,String productStock,String productDescription) {


        if (file!=null){

            MultipartBody.Part imageFile = null;

            final RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
            imageFile = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

            RequestBody name = RequestBody.create(MediaType.parse("multipart/form-data"), productName);
            RequestBody price = RequestBody.create(MediaType.parse("multipart/form-data"), productRegularPrice);
            RequestBody sellingPrice = RequestBody.create(MediaType.parse("multipart/form-data"), productSellingPrice);
            RequestBody unit = RequestBody.create(MediaType.parse("multipart/form-data"), productPiece);
            RequestBody stock = RequestBody.create(MediaType.parse("multipart/form-data"), productStock);
            RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), productDescription);

            apiInterface.uploadImage("Bearer "+token,imageFile,name,price,sellingPrice,unit,stock,description).
                    enqueue(new Callback<ProductDataResponse>(){
                        @Override
                        public void onResponse(Call<ProductDataResponse> call, Response<ProductDataResponse> response) {
                            if (response.code()==201){
                                Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();
                                getAllProduct();
                            }else if (response.code()==500){
                                Toast.makeText(getActivity(), "File type is not supported", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getActivity(), "try again", Toast.LENGTH_SHORT).show();
                            }

                            progressDialog.dismiss();

                        }
                        @Override
                        public void onFailure(Call<ProductDataResponse> call, Throwable t) {
                            Toast.makeText(getActivity(), "Failed to Upload", Toast.LENGTH_SHORT).show();
                            Log.e("asd",t.getMessage());
                            progressDialog.dismiss();
                        }
                    });

        }
        else {
            Toast.makeText(getActivity(), "uri not found", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean CheckPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.CAMERA) || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Permission")
                        .setMessage("Please accept the permissions")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        MY_PERMISSIONS_REQUEST_LOCATION);


//                                startActivity(new Intent(ProductActivity
//                                        .this, ProductActivity.class));
                                getActivity().overridePendingTransition(0, 0);
                            }
                        })
                        .create()
                        .show();


            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }

            return false;
        } else {

            return true;

        }
    }





}
