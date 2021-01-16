package com.example.pointofsell.invoice.delete_invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteInVoiceGetInVoiceDataResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("invoice")
    @Expose
    private DeleteInVoiceGetInVoiceData invoice;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public DeleteInVoiceGetInVoiceData getInvoice() {
        return invoice;
    }

    public void setInvoice(DeleteInVoiceGetInVoiceData invoice) {
        this.invoice = invoice;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
