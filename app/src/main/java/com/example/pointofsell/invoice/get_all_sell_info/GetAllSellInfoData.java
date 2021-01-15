package com.example.pointofsell.invoice.get_all_sell_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllSellInfoData {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("totalSaleAmount")
    @Expose
    private Integer totalSaleAmount;
    @SerializedName("totalSoldInvoice")
    @Expose
    private Integer totalSoldInvoice;
    @SerializedName("totalDueAmount")
    @Expose
    private Integer totalDueAmount;
    @SerializedName("totalSoldProductQuantity")
    @Expose
    private Integer totalSoldProductQuantity;
    @SerializedName("totalProductCost")
    @Expose
    private Integer totalProductCost;
    @SerializedName("totalProfit")
    @Expose
    private Integer totalProfit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTotalSaleAmount() {
        return totalSaleAmount;
    }

    public void setTotalSaleAmount(Integer totalSaleAmount) {
        this.totalSaleAmount = totalSaleAmount;
    }

    public Integer getTotalSoldInvoice() {
        return totalSoldInvoice;
    }

    public void setTotalSoldInvoice(Integer totalSoldInvoice) {
        this.totalSoldInvoice = totalSoldInvoice;
    }

    public Integer getTotalDueAmount() {
        return totalDueAmount;
    }

    public void setTotalDueAmount(Integer totalDueAmount) {
        this.totalDueAmount = totalDueAmount;
    }

    public Integer getTotalSoldProductQuantity() {
        return totalSoldProductQuantity;
    }

    public void setTotalSoldProductQuantity(Integer totalSoldProductQuantity) {
        this.totalSoldProductQuantity = totalSoldProductQuantity;
    }

    public Integer getTotalProductCost() {
        return totalProductCost;
    }

    public void setTotalProductCost(Integer totalProductCost) {
        this.totalProductCost = totalProductCost;
    }

    public Integer getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Integer totalProfit) {
        this.totalProfit = totalProfit;
    }
}
