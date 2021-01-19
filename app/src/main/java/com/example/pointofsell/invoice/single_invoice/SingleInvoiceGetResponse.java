package com.example.pointofsell.invoice.single_invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleInvoiceGetResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("invoice")
    @Expose
    private SingleInvoiceData singleInvoiceData;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public SingleInvoiceData getSingleInvoiceData() {
        return singleInvoiceData;
    }

    public void setSingleInvoiceData(SingleInvoiceData singleInvoiceData) {
        this.singleInvoiceData = singleInvoiceData;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
