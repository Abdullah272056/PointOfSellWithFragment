package com.example.pointofsell.customer.pay_due;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DuePayDataResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("customer")
    @Expose
    private DuePayData duePayData;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public DuePayData getDuePayData() {
        return duePayData;
    }

    public void setDuePayData(DuePayData duePayData) {
        this.duePayData = duePayData;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
