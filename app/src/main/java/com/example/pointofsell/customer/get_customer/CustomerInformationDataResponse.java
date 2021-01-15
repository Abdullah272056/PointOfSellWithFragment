package com.example.pointofsell.customer.get_customer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomerInformationDataResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("customer")
    @Expose
    private List<CustomerInformationData> customerInformation = null;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<CustomerInformationData> getCustomerInformation() {
        return customerInformation;
    }

    public void setCustomerInformation(List<CustomerInformationData> customerInformation) {
        this.customerInformation = customerInformation;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
