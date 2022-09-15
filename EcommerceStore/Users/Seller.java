package com.EcommerceStore.Users;

import com.EcommerceStore.Product.Products;

public class Seller extends User {
    @Override
    public boolean verifyUser() {
        return true;
    }

    private Products[] productListed;
    private Products [] publishProduct;

    public Products[] getPublishProduct() {
        return publishProduct;
    }

    public void setPublishProduct(Products[] publishProduct) {
        this.publishProduct = publishProduct;
    }

    public Products[] getProductListed() {
        return productListed;
    }

    public void setProductListed(Products[] productListed) {
        this.productListed = productListed;
    }
}
