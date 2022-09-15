package com.EcommerceStore.Categories.Women;

import com.EcommerceStore.Product.Products;

public class Kurtis {
    private Products[] listOfNewKurtis;
    private Products[] allKurtis;

    public Products[] getListOfDummyKurtis() {
        Products[] listOfDummyKurtis = new Products[4];
        Products product1=new Products();
        product1.setProductName("Kurtis 1");
        product1.setProductId("K1");
        product1.setProductCost(1000);

        Products product2=new Products();
        product2.setProductName("Kurtis 2");
        product2.setProductId("K2");
        product2.setProductCost(1200);

        Products product3=new Products();
        product3.setProductName("Kurtis 3");
        product3.setProductId("K3");
        product3.setProductCost(1500);

        Products product4=new Products();
        product4.setProductName("Kurtis 4");
        product4.setProductId("K4");
        product4.setProductCost(1800);

        listOfDummyKurtis [0]=product1;
        listOfDummyKurtis [1]=product2;
        listOfDummyKurtis [2]=product3;
        listOfDummyKurtis [3]=product4;
        return listOfDummyKurtis;
    }

    public Products[] getListOfNewKurtis() {
        return listOfNewKurtis;
    }

    public void setListOfNewKurtis(Products[] listOfNewKurtis) {
        this.listOfNewKurtis = listOfNewKurtis;
    }

    public Products[] getAllKurtis() {
        return allKurtis;
    }

    public void setAllKurtis(Products[] allKurtis) {
        this.allKurtis = allKurtis;
    }
}
