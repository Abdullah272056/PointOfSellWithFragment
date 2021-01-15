package com.example.pointofsell.customer.create_customer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddCustomerData {
    @SerializedName("due")
    @Expose
    private Integer due;
    @SerializedName("allTimeSellAmount")
    @Expose
    private Integer allTimeSellAmount;
    @SerializedName("totalSell")
    @Expose
    private List<Object> totalSell = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("duePayHistory")
    @Expose
    private List<Object> duePayHistory = null;
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

    public Integer getAllTimeSellAmount() {
        return allTimeSellAmount;
    }

    public void setAllTimeSellAmount(Integer allTimeSellAmount) {
        this.allTimeSellAmount = allTimeSellAmount;
    }

    public List<Object> getTotalSell() {
        return totalSell;
    }

    public void setTotalSell(List<Object> totalSell) {
        this.totalSell = totalSell;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<Object> getDuePayHistory() {
        return duePayHistory;
    }

    public void setDuePayHistory(List<Object> duePayHistory) {
        this.duePayHistory = duePayHistory;
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
