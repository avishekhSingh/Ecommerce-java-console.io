package com.EcommerceStore.Categories.Women;

import com.EcommerceStore.Product.Products;

public class WomenJeans {
    private Products[] listOfNewWomenJeans;
    private Products[] allWomenJeans;

    public Products[] getListOfDummyWomenJeans() {
        Products[] listOfDummyWomenJeans = new Products[4];
        Products product1=new Products();
        product1.setProductName("Women jeans 1");
        product1.setProductId("WJ1");
        product1.setProductCost(1000);

        Products product2=new Products();
        product2.setProductName("Women jeans 2");
        product2.setProductId("WJ2");
        product2.setProductCost(1200);

        Products product3=new Products();
        product3.setProductName("Women jeans 3");
        product3.setProductId("WJ3");
        product3.setProductCost(1500);

        Products product4=new Products();
        product4.setProductName("Women jeans 4");
        product4.setProductId("WJ4");
        product4.setProductCost(1800);

        listOfDummyWomenJeans [0]=product1;
        listOfDummyWomenJeans [1]=product2;
        listOfDummyWomenJeans [2]=product3;
        listOfDummyWomenJeans [3]=product4;
        return listOfDummyWomenJeans;
    }

    public Products[] getListOfNewWomenJeans() {
        return listOfNewWomenJeans;
    }

    public void setListOfNewWomenJeans(Products[] listOfNewWomenJeans) {
        this.listOfNewWomenJeans = listOfNewWomenJeans;
    }

    public Products[] getAllWomenJeans() {
        return allWomenJeans;
    }

    public void setAllWomenJeans(Products[] allWomenJeans) {
        this.allWomenJeans = allWomenJeans;
    }
}
