package com.example.pointofsell.product.create_product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDataResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("product")
    @Expose
    private ProductData productData;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public ProductData getProductData() {
        return productData;
    }

    public void setProductData(ProductData productData) {
        this.productData = productData;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
