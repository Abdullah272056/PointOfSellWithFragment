package com.example.pointofsell.invoice.get_all_sell_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllSellInfoResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("totalSaleInfo")
    @Expose
    private GetAllSellInfoData getAllSellInfoData;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public GetAllSellInfoData getGetAllSellInfoData() {
        return getAllSellInfoData;
    }

    public void setGetAllSellInfoData(GetAllSellInfoData getAllSellInfoData) {
        this.getAllSellInfoData = getAllSellInfoData;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
