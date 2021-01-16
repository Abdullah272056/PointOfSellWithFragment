package com.example.pointofsell.customer.single_customer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleCustomerDuePayHistory {
    @SerializedName("payAmount")
    @Expose
    private Double payAmount;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("_id")
    @Expose
    private String id;

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
