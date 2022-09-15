package com.EcommerceStore.Users;

import com.EcommerceStore.Product.Cart;

public class Customer extends User {
    @Override
    public boolean verifyUser() {
        return true;
    }

    // For the Cart class
    Cart allCart;                                                           //! why it's null...............
    Cart newCart=new Cart();

    public Cart getAllCustomerCart() {
        return newCart;
    }

    public void setAllCustomerCart(Cart allCart) {
        this.newCart = allCart;
    }
}
