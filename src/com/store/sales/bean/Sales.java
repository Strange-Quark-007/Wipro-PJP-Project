package com.store.sales.bean;


public class Sales {
    String salesID;
    String salesDate;
    String productID;
    int quantitySold;
    double salesPricePerUnit;

    public String getSalesID() {
        return salesID;
    }

    public void setSalesID(String salesID) {
        this.salesID = salesID;
    }

    public String getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(String salesDate)
    {
        this.salesDate = salesDate;
    }


    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public double getSalesPricePerUnit() {
        return salesPricePerUnit;
    }

    public void setSalesPricePerUnit(double salesPricePerUnit) {
        this.salesPricePerUnit = salesPricePerUnit;
    }
}
