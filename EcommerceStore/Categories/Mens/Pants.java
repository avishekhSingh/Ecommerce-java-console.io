package com.EcommerceStore.Categories.Mens;
import com.EcommerceStore.Product.Products;

public class Pants {
    private Products[] listOfNewPants;
    private Products[] allPants;

//! All dummy pants
    public Products[] getListOfDummyPants() {
        Products[] listOfDummyPants = new Products[4];

        Products product1=new Products();
        product1.setProductName("Pants 1");
        product1.setProductId("P1");
        product1.setProductCost(1000);

        Products product2=new Products();
        product2.setProductName("Pants 2");
        product2.setProductId("P2");
        product2.setProductCost(1200);

        Products product3=new Products();
        product3.setProductName("Pants 3");
        product3.setProductId("P3");
        product3.setProductCost(1500);

        Products product4=new Products();
        product4.setProductName("Pants 4");
        product4.setProductId("P4");
        product4.setProductCost(1800);

        listOfDummyPants[0]=product1;
        listOfDummyPants[1]=product2;
        listOfDummyPants[2]=product3;
        listOfDummyPants[3]=product4;

        return listOfDummyPants;
    }
    //! All new pants
    public Products[] getListOfNewPants() {
        return listOfNewPants;
    }

    public void setListOfNewPants(Products[] listOfNewPants) {
        this.listOfNewPants = listOfNewPants;
    }
//! All pants including dummy pants and new added pants.
    public Products[] getAllPants() {
        return allPants;
    }

    public void setAllPants(Products[] allPants) {
        this.allPants = allPants;
    }
}
