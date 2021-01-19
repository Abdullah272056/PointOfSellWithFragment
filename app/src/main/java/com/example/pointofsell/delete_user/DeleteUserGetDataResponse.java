package com.example.pointofsell.delete_user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteUserGetDataResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private DeleteUserGetData deleteUserGetData;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DeleteUserGetData getDeleteUserGetData() {
        return deleteUserGetData;
    }

    public void setDeleteUserGetData(DeleteUserGetData deleteUserGetData) {
        this.deleteUserGetData = deleteUserGetData;
    }
}
