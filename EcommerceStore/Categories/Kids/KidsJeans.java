package com.EcommerceStore.Categories.Kids;

import com.EcommerceStore.Product.Products;

public class KidsJeans  {
    private Products[] listOfNewKidsJeans;
    private Products[] allKidsJeans;

    public Products[] getListOfDummyKidsJeans() {
        Products[] listOfDummyKidsJeans = new Products[4];
        Products product1=new Products();
        product1.setProductName("Kids jeans 1");
        product1.setProductId("KS1");
        product1.setProductCost(1000);

        Products product2=new Products();
        product2.setProductName("Kids jeans 2");
        product2.setProductId("KS2");
        product2.setProductCost(1200);

        Products product3=new Products();
        product3.setProductName("Kids jeans 3");
        product3.setProductId("KS3");
        product3.setProductCost(1500);

        Products product4=new Products();
        product4.setProductName("Kids jeans 4");
        product4.setProductId("K4");
        product4.setProductCost(1800);

        listOfDummyKidsJeans [0]=product1;
        listOfDummyKidsJeans [1]=product2;
        listOfDummyKidsJeans [2]=product3;
        listOfDummyKidsJeans [3]=product4;
        return listOfDummyKidsJeans;
    }

    public Products[] getListOfNewKidsJeans() {
        return listOfNewKidsJeans;
    }

    public void setListOfNewKidsJeans(Products[] listOfNewKidsJeans) {
        this.listOfNewKidsJeans = listOfNewKidsJeans;
    }

    public Products[] getAllKidsJeans() {
        return allKidsJeans;
    }

    public void setAllKidsJeans(Products[] allKidsJeans) {
        this.allKidsJeans = allKidsJeans;
    }
}
