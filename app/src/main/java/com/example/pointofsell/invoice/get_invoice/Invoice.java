package com.example.pointofsell.invoice.get_invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Invoice {
    @SerializedName("due")
    @Expose
    private Integer due;
    @SerializedName("totalAmountAfterDiscount")
    @Expose
    private Integer totalAmountAfterDiscount;
    @SerializedName("payAmount")
    @Expose
    private Integer payAmount;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("customer")
    @Expose
    private Customer customer;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;

    public Integer getDue() {
        return due;
    }

    public void setDue(Integer due) {
        this.due = due;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
