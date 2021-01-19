package com.example.pointofsell.invoice.create_invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetInVoiceResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("invoice")
    @Expose
    private GetInVoiceData getInVoiceData;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public GetInVoiceData getGetInVoiceData() {
        return getInVoiceData;
    }

    public void setGetInVoiceData(GetInVoiceData getInVoiceData) {
        this.getInVoiceData = getInVoiceData;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
