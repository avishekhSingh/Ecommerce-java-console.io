package com.EcommerceStore.Categories.Kids;

import com.EcommerceStore.Product.Products;

public class KidsTshirt {
    private Products[] listOfNewKidsTShirt;
    private Products[] allKidsTShirt;

    public Products[] getListOfDummyKidsTShirt() {
        Products[] listOfDummyKidsTShirt = new Products[4];
        Products product1=new Products();
        product1.setProductName("Kids TShirt 1");
        product1.setProductId("KS1");
        product1.setProductCost(1000);

        Products product2=new Products();
        product2.setProductName("Kids TShirt 2");
        product2.setProductId("KS2");
        product2.setProductCost(1200);

        Products product3=new Products();
        product3.setProductName("Kids TShirt 3");
        product3.setProductId("KS3");
        product3.setProductCost(1500);

        Products product4=new Products();
        product4.setProductName("Kids TShirt 4");
        product4.setProductId("K4");
        product4.setProductCost(1800);

        listOfDummyKidsTShirt [0]=product1;
        listOfDummyKidsTShirt [1]=product2;
        listOfDummyKidsTShirt [2]=product3;
        listOfDummyKidsTShirt [3]=product4;
        return listOfDummyKidsTShirt;
    }

    public Products[] getListOfNewKidsTShirt() {
        return listOfNewKidsTShirt;
    }

    public void setListOfNewKidsTShirt(Products[] listOfNewKidsTShirt) {
        this.listOfNewKidsTShirt = listOfNewKidsTShirt;
    }

    public Products[] getAllKidsTShirt() {
        return allKidsTShirt;
    }

    public void setAllKidsTShirt(Products[] allKidsTShirt) {
        this.allKidsTShirt = allKidsTShirt;
    }
}
