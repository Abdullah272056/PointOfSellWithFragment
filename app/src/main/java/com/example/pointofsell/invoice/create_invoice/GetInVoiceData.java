package com.example.pointofsell.invoice.create_invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetInVoiceData {

    @SerializedName("due")
    @Expose
    private Integer due;
    @SerializedName("discount")
    @Expose
    private Integer discount;
    @SerializedName("totalAmountAfterDiscount")
    @Expose
    private Integer totalAmountAfterDiscount;
    @SerializedName("payAmount")
    @Expose
    private Integer payAmount;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("customer")
    @Expose
    private GetInvoiceCustomerData getCustomerData;
    @SerializedName("products")
    @Expose
    private List<GetInVoiceProductData> getProductDataList = null;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Integer getDue() {
        return due;
    }

    public void setDue(Integer due) {
        this.due = due;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getTotalAmountAfterDiscount() {
        return totalAmountAfterDiscount;
    }

    public void setTotalAmountAfterDiscount(Integer totalAmountAfterDiscount) {
        this.totalAmountAfterDiscount = totalAmountAfterDiscount;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public GetInvoiceCustomerData getGetCustomerData() {
        return getCustomerData;
    }

    public void setGetCustomerData(GetInvoiceCustomerData getCustomerData) {
        this.getCustomerData = getCustomerData;
    }

    public List<GetInVoiceProductData> getGetProductDataList() {
        return getProductDataList;
    }

    public void setGetProductDataList(List<GetInVoiceProductData> getProductDataList) {
        this.getProductDataList = getProductDataList;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
