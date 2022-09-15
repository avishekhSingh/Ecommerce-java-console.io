package com.EcommerceStore.Product;

import com.EcommerceStore.Users.Seller;

public class Products {
    private String productName;
    private String productId;
    private double productCost;
    private Seller sellers;

    public Seller getSellers() {
        return sellers;
    }

    public void setSellers(Seller sellers) {
        this.sellers = sellers;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getProductCost() {
        return productCost;
    }

    public void setProductCost(double productCost) {
        this.productCost = productCost;
    }

}
