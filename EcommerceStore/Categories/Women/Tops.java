package com.EcommerceStore.Categories.Women;

import com.EcommerceStore.Product.Products;

public class Tops {
    private Products[] listOfNewTops;
    private Products[] allTops;

    public Products[] getListOfDummyTops() {
        Products[] listOfDummyTops = new Products[4];
        Products product1=new Products();
        product1.setProductName("Top 1");
        product1.setProductId("T1");
        product1.setProductCost(1000);

        Products product2=new Products();
        product2.setProductName("Top 2");
        product2.setProductId("T2");
        product2.setProductCost(1200);

        Products product3=new Products();
        product3.setProductName("Top 3");
        product3.setProductId("T3");
        product3.setProductCost(1500);

        Products product4=new Products();
        product4.setProductName("Top 4");
        product4.setProductId("T4");
        product4.setProductCost(1800);

        listOfDummyTops [0]=product1;
        listOfDummyTops [1]=product2;
        listOfDummyTops [2]=product3;
        listOfDummyTops [3]=product4;
        return listOfDummyTops;
    }

    public Products[] getListOfNewTops() {
        return listOfNewTops;
    }

    public void setListOfNewTops(Products[] listOfNewTops) {
        this.listOfNewTops = listOfNewTops;
    }

    public Products[] getAllTops() {
        return allTops;
    }

    public void setAllTops(Products[] allTops) {
        this.allTops = allTops;
    }
}
