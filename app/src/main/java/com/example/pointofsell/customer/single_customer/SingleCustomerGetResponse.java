package com.example.pointofsell.customer.single_customer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleCustomerGetResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("customer")
    @Expose
    private SingleCustomerInformation singleCustomerInformation;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public SingleCustomerInformation getSingleCustomerInformation() {
        return singleCustomerInformation;
    }

    public void setSingleCustomerInformation(SingleCustomerInformation singleCustomerInformation) {
        this.singleCustomerInformation = singleCustomerInformation;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
