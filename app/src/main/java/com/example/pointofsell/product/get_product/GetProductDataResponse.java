package com.example.pointofsell.product.get_product;

import com.example.pointofsell.product.delete_product.GetProductData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetProductDataResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("products")
    @Expose
    private List<GetProductData> products = null;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<GetProductData> getProducts() {
        return products;
    }

    public void setProducts(List<GetProductData> products) {
        this.products = products;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
