package com.EcommerceStore.Categories.Mens;

import com.EcommerceStore.Product.Products;

public class TShirts {
    private Products[] listOfNewTShirts;
    private Products[] allTShirts;

    public Products[] getListOfDummyTShirt() {
        Products[] listOfDummyTShirt = new Products[4];
        Products product1=new Products();
        product1.setProductName("T-shirt 1");
        product1.setProductId("TS1");
        product1.setProductCost(1000);

        Products product2=new Products();
        product2.setProductName("T-shirt 2");
        product2.setProductId("TS2");
        product2.setProductCost(1200);

        Products product3=new Products();
        product3.setProductName("T-shirt 3");
        product3.setProductId("TS3");
        product3.setProductCost(1500);

        Products product4=new Products();
        product4.setProductName("T-shirt 4");
        product4.setProductId("TS4");
        product4.setProductCost(1800);

        listOfDummyTShirt [0]=product1;
        listOfDummyTShirt [1]=product2;
        listOfDummyTShirt [2]=product3;
        listOfDummyTShirt [3]=product4;

        return listOfDummyTShirt;
    }

    public Products[] getListOfNewTShirts() {
        return listOfNewTShirts;
    }

    public void setListOfNewTShirts(Products[] listOfNewTShirts) {
        this.listOfNewTShirts = listOfNewTShirts;
    }

    public Products[] getAllTShirts() {
        return allTShirts;
    }

    public void setAllTShirts(Products[] allTShirts) {
        this.allTShirts = allTShirts;
    }
}
