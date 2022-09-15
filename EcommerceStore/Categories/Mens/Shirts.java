package com.EcommerceStore.Categories.Mens;

import com.EcommerceStore.Product.Products;

public class Shirts {
    private Products[] listOfNewShirts;
    private Products[] allShirts;

    public Products[] getListOfNewShirts() {
        return listOfNewShirts;
    }

    public void setListOfNewShirts(Products[] listOfNewShirts) {
        this.listOfNewShirts = listOfNewShirts;
    }

    public Products[] getAllShirts() {
        return allShirts;
    }

    public void setAllShirts(Products[] allShirts) {
        this.allShirts = allShirts;
    }

    public Products[] getListOfDummyShirts() {
        Products[] listOfDummyShirts = new Products[4];

        Products product1=new Products();
        product1.setProductName("Shirts 1");
        product1.setProductId("S1");
        product1.setProductCost(1000);

        Products product2=new Products();
        product2.setProductName("Shirts 2");
        product2.setProductId("S2");
        product2.setProductCost(1200);

        Products product3=new Products();
        product3.setProductName("Shirts 3");
        product3.setProductId("S3");
        product3.setProductCost(1500);

        Products product4=new Products();
        product4.setProductName("Shirts 4");
        product4.setProductId("S4");
        product4.setProductCost(1800);

        listOfDummyShirts [0]=product1;
        listOfDummyShirts [1]=product2;
        listOfDummyShirts [2]=product3;
        listOfDummyShirts [3]=product4;
        return listOfDummyShirts;
    }
}
