package com.example.pointofsell.customer.create_customer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddCustomerResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("customer")
    @Expose
    private AddCustomerData addCustomerData;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public AddCustomerData getAddCustomerData() {
        return addCustomerData;
    }

    public void setAddCustomerData(AddCustomerData addCustomerData) {
        this.addCustomerData = addCustomerData;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
