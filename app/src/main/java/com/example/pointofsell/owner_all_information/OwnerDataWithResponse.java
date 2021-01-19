package com.example.pointofsell.owner_all_information;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OwnerDataWithResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private OwnerData data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public OwnerData getData() {
        return data;
    }

    public void setData(OwnerData data) {
        this.data = data;
    }
}
