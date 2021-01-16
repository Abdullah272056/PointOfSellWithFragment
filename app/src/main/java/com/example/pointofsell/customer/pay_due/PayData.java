package com.example.pointofsell.customer.pay_due;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayData {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("payAmount")
    @Expose
    private Integer payAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

    public PayData(String id, Integer payAmount) {
        this.id = id;
        this.payAmount = payAmount;
    }

    public PayData() {
    }
}
