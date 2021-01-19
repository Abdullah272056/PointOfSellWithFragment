package com.example.pointofsell.invoice.create_invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SetProductData {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public SetProductData(String id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}
