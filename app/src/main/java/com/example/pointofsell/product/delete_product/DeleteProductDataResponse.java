package com.example.pointofsell.product.delete_product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteProductDataResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("select_customer")
    @Expose
    private GetProductData getProductData;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public GetProductData getGetProductData() {
        return getProductData;
    }

    public void setGetProductData(GetProductData getProductData) {
        this.getProductData = getProductData;
    }
}
