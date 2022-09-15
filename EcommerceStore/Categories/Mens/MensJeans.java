package com.EcommerceStore.Categories.Mens;
import com.EcommerceStore.Product.Products;
public class MensJeans  {

    private Products[] listOfNewJeans;
    private Products[] allJeans;

    public Products[] getListOfDummyJeans () {
        Products[] listOfDummyMensJeans = new Products[7];
        Products product1=new Products();
        product1.setProductName("Mens Jeans 1");
        product1.setProductId("MJ1");
        product1.setProductCost(1000);

        Products product2=new Products();
        product2.setProductName("Mens Jeans 2");
        product2.setProductId("MJ2");
        product2.setProductCost(1200);

        Products product3=new Products();
        product3.setProductName("Mens Jeans 3");
        product3.setProductId("MJ3");
        product3.setProductCost(1500);

        Products product4=new Products();
        product4.setProductName("Mens Jeans 4");
        product4.setProductId("MJ4");
        product4.setProductCost(1800);

        Products product5=new Products();
        product5.setProductName("Mens Jeans 5");
        product5.setProductId("MJ5");
        product5.setProductCost(1999);

        Products product6=new Products();
        product6.setProductName("Mens Jeans 6");
        product6.setProductId("MJ6");
        product6.setProductCost(2199);

        Products product7=new Products();
        product7.setProductName("Mens Jeans 7");
        product7.setProductId("MJ7");
        product7.setProductCost(2349);


        listOfDummyMensJeans[0]=product1;
        listOfDummyMensJeans[1]=product2;
        listOfDummyMensJeans[2]=product3;
        listOfDummyMensJeans[3]=product4;
        listOfDummyMensJeans[4]=product5;
        listOfDummyMensJeans[5]=product6;
        listOfDummyMensJeans[6]=product7;
        return listOfDummyMensJeans ;
    }

    public Products[] getListOfNewJeans() {
        return listOfNewJeans;
    }

    public void setListOfNewJeans(Products[] listOfNewJeans) {
        this.listOfNewJeans = listOfNewJeans;
    }

    public Products[] getAllJeans() {
        return allJeans;
    }

    public void setAllJeans(Products[] allJeans) {
        this.allJeans = allJeans;
    }
}
