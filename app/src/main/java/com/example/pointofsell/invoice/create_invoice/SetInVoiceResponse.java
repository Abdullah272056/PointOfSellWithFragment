package com.example.pointofsell.invoice.create_invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SetInVoiceResponse {
    @SerializedName("customerId")
    @Expose
    private String customerId;
    @SerializedName("payAmount")
    @Expose
    private Integer payAmount;
    @SerializedName("totalProductAmount")
    @Expose
    private Integer totalProductAmount;
    @SerializedName("discount")
    @Expose
    private Integer discount;
    @SerializedName("products")
    @Expose
    private List<SetProductData> setProductDataList = null;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getTotalProductAmount() {
        return totalProductAmount;
    }

    public void setTotalProductAmount(Integer totalProductAmount) {
        this.totalProductAmount = totalProductAmount;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount){
        this.discount = discount;
    }

    public List<SetProductData> getSetProductDataList() {
        return setProductDataList;
    }

    public void setSetProductDataList(List<SetProductData> setProductDataList) {
        this.setProductDataList = setProductDataList;
    }

    public SetInVoiceResponse(String customerId, Integer payAmount, Integer totalProductAmount, Integer discount, List<SetProductData> setProductDataList) {
        this.customerId = customerId;
        this.payAmount = payAmount;
        this.totalProductAmount = totalProductAmount;
        this.discount = discount;
        this.setProductDataList = setProductDataList;
    }
}
