package com.example.pointofsell.product.get_all_product_info;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllProductInfoDataResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("productInfo")
    @Expose
    private GetAllProductInfoData getAllProductInfoData;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success){
        this.success = success;
    }

    public GetAllProductInfoData getGetAllProductInfoData() {
        return getAllProductInfoData;
    }

    public void setGetAllProductInfoData(GetAllProductInfoData getAllProductInfoData) {
        this.getAllProductInfoData = getAllProductInfoData;
    }

    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

}
