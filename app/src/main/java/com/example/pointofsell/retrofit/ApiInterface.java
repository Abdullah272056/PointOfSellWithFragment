package com.example.pointofsell.retrofit;

import com.example.pointofsell.customer.create_customer.AddCustomerResponse;
import com.example.pointofsell.customer.create_customer.CustomerData;
import com.example.pointofsell.customer.delete_customer.CustomerDeleteResponse;
import com.example.pointofsell.customer.get_customer.CustomerCountResponse;
import com.example.pointofsell.customer.get_customer.CustomerInformationDataResponse;
import com.example.pointofsell.customer.pay_due.DuePayDataResponse;
import com.example.pointofsell.customer.pay_due.PayData;
import com.example.pointofsell.customer.single_customer.SingleCustomerGetResponse;
import com.example.pointofsell.invoice.delete_invoice.DeleteInVoiceGetInVoiceDataResponse;
import com.example.pointofsell.invoice.get_all_sell_info.GetAllSellInfoResponse;
import com.example.pointofsell.invoice.get_invoice.InVoiceResponse;
import com.example.pointofsell.log_in.LogInData;
import com.example.pointofsell.log_in.LogInResponse;
import com.example.pointofsell.owner_all_information.OwnerDataWithResponse;
import com.example.pointofsell.product.create_product.ProductDataResponse;
import com.example.pointofsell.product.delete_product.DeleteProductDataResponse;
import com.example.pointofsell.product.get_all_product_info.GetAllProductInfoDataResponse;
import com.example.pointofsell.product.get_product.GetProductDataResponse;
import com.example.pointofsell.register.RegistrationData;
import com.example.pointofsell.register.RegistrationResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    // registration
    @POST("api/auth/register/")
    Call<RegistrationResponse> registrationData(@Body RegistrationData registrationData);

    //signIn
    @POST("api/auth/login/")
    Call<LogInResponse>logInData(@Body LogInData logInData);

//    ///////////////////



    //Get Me
    @GET("api/auth/me")
    Call<OwnerDataWithResponse> getUserAllInformation(@Header("Authorization") String authorization);
//    // change password
//    //http://mern-pos.herokuapp.com/api/auth/change-password
//    @PUT("api/auth/change-password")
//    Call<ChangePasswordGetResponse> changePassword(@Header("Authorization") String authorization, @Body ChangePasswordSetResponse changePasswordSetResponse);
//    //delete user
//    @POST("api/auth/delete")
//    Call<DeleteUserGetDataResponse> deleteUser(@Header("Authorization") String authorization, @Body DeleteUserSetDataResponse deleteUserSetDataResponse);
//
//
//
//
//    ////customer/////
    //add customer | create customer
    @POST("api/customer")
    Call<AddCustomerResponse> addCustomerInformation(@Header("Authorization") String authorization, @Body CustomerData customerData);
    // get allCustomer data
    @GET("api/customer")
    Call<CustomerInformationDataResponse> getAllCustomerInformation(@Header("Authorization") String authorization1);
    //customer Data Edit
    @PUT("api/customer/{id}")
    Call<AddCustomerResponse> updateCustomerData(@Header("Authorization") String authorization2, @Path("id") String id, @Body CustomerData customerData);
    //delete customer
    @DELETE("api/customer/{id}")
    Call<CustomerDeleteResponse> deleteCustomer(@Header("Authorization") String authorization, @Path("id") String id);
    // get customer Count
    @GET("api/customer/count")
    Call<CustomerCountResponse> getCustomerCount(@Header("Authorization") String authorization);

    //Pay Due
    @PUT("api/customer/due/pay")
    Call<DuePayDataResponse> payDue(@Header("Authorization") String authorization, @Body PayData payData);

//    // http://mern-pos.herokuapp.com/api/customer/details/5fabf8a2bb2709001736251c
    //get single customer information
    @GET("api/customer/details/{id}")
    Call<SingleCustomerGetResponse> getSingleCustomerInformation(@Header("Authorization") String authorization, @Path("id") String id);


//
//    ///// invoice/////
    //GetAllSellInfoResponse
    @GET("api/invoice/sale-info")
    Call<GetAllSellInfoResponse> getAllSellInfo(@Header("Authorization") String authorization);
    //get all invoice
    @GET("api/invoice")
    Call<InVoiceResponse> getInvoice(@Header("Authorization") String authorization);
//    //get single invoice information
//    @GET("api/invoice/{id}")
//    Call<SingleInvoiceGetResponse> getSingleInvoiceInformation(@Header("Authorization") String authorization, @Path("id") String id);
//    // Get sale info by date
//    // http://mern-pos.herokuapp.com/api/invoice/sale/day?day=0
//    @GET("/api/invoice/sale/day")
//    Call<GetSellInfoByDayResponse> getSellInfoByDay(@Header("Authorization") String authorization, @Query("day") Integer dayInt);
//    // create inVoice
//    @POST("/api/invoice")
//    Call<OwnerDataWithResponse> getInvoiceResponse(@Header("Authorization") String authorization, @Body SetInVoiceResponse setInVoiceResponse);
//
    // delete invoice
    @DELETE("api/invoice/{id}")
    Call<DeleteInVoiceGetInVoiceDataResponse> deleteInVoice(@Header("Authorization") String authorization, @Path("id") String id);


//    //product
    // get al product
    @GET("api/product")
    Call<GetProductDataResponse> getAllProduct(@Header("Authorization") String authorization);
    //delete  product
    @DELETE("api/product/{id}")
    Call<DeleteProductDataResponse> deleteProduct(@Header("Authorization") String authorization, @Path("id") String id);
    // Get all product info
    @GET("api/product/info")
    Call<GetAllProductInfoDataResponse> getAllProductInfo(@Header("Authorization") String authorization);
    // create ProductActivity
    @Multipart
    @POST("api/product")
    Call<ProductDataResponse> uploadImage(@Header("Authorization") String authorization,
                                          @Part MultipartBody.Part image,
                                          @Part("name") RequestBody name,
                                          @Part("price") RequestBody price,
                                          @Part("sellingPrice") RequestBody sellingPrice,
                                          @Part("unit") RequestBody unit,
                                          @Part("stock") RequestBody stock,
                                          @Part("description") RequestBody description
    );




}
