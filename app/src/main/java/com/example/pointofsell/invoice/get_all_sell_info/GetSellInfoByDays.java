package com.example.pointofsell.invoice.get_all_sell_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetSellInfoByDays {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("totalSaleAmount")
    @Expose
    private Integer totalSaleAmount;
    @SerializedName("totalSoldProduct")
    @Expose
    private Integer totalSoldProduct;
    @SerializedName("totalSoldInvoice")
    @Expose
    private Integer totalSoldInvoice;
    @SerializedName("totalProductCost")
    @Expose
    private Integer totalProductCost;
    @SerializedName("totalDue")
    @Expose
    private Integer totalDue;
    @SerializedName("totalProfit")
    @Expose
    private Integer totalProfit;
    @SerializedName("currentCash")
    @Expose
    private Integer currentCash;

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

    public Integer getTotalSoldProduct() {
        return totalSoldProduct;
    }

    public void setTotalSoldProduct(Integer totalSoldProduct) {
        this.totalSoldProduct = totalSoldProduct;
    }

    public Integer getTotalSoldInvoice() {
        return totalSoldInvoice;
    }

    public void setTotalSoldInvoice(Integer totalSoldInvoice) {
        this.totalSoldInvoice = totalSoldInvoice;
    }

    public Integer getTotalProductCost() {
        return totalProductCost;
    }

    public void setTotalProductCost(Integer totalProductCost) {
        this.totalProductCost = totalProductCost;
    }

    public Integer getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(Integer totalDue) {
        this.totalDue = totalDue;
    }

    public Integer getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Integer totalProfit) {
        this.totalProfit = totalProfit;
    }

    public Integer getCurrentCash() {
        return currentCash;
    }

    public void setCurrentCash(Integer currentCash) {
        this.currentCash = currentCash;
    }

    public GetSellInfoByDays() {
    }

    public GetSellInfoByDays(String id, Integer totalSaleAmount, Integer totalSoldProduct, Integer totalSoldInvoice, Integer totalProductCost, Integer totalDue, Integer totalProfit, Integer currentCash) {
        this.id = id;
        this.totalSaleAmount = totalSaleAmount;
        this.totalSoldProduct = totalSoldProduct;
        this.totalSoldInvoice = totalSoldInvoice;
        this.totalProductCost = totalProductCost;
        this.totalDue = totalDue;
        this.totalProfit = totalProfit;
        this.currentCash = currentCash;
    }
}
