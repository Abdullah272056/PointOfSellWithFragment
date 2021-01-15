package com.example.pointofsell.product.get_all_product_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllProductInfoData {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("totalProductCost")
    @Expose
    private Integer totalProductCost;
    @SerializedName("totalProduct")
    @Expose
    private Integer totalProduct;
    @SerializedName("totalProductType")
    @Expose
    private Integer totalProductType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTotalProductCost() {
        return totalProductCost;
    }

    public void setTotalProductCost(Integer totalProductCost) {
        this.totalProductCost = totalProductCost;
    }

    public Integer getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(Integer totalProduct) {
        this.totalProduct = totalProduct;
    }

    public Integer getTotalProductType() {
        return totalProductType;
    }

    public void setTotalProductType(Integer totalProductType) {
        this.totalProductType = totalProductType;
    }
}
