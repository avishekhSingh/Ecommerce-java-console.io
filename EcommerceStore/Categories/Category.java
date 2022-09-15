package com.EcommerceStore.Categories;

public class Category {
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category[] getMensCategory() {
        Category pant = new Category();
        pant.categoryName = "Men's pant";
        Category shirt = new Category();
        shirt.categoryName = "Men's Shirt";
        Category jeans = new Category();
        jeans.categoryName = "Men's Jeans";
        Category tshirt = new Category();
        tshirt.categoryName = "Men's T-Shirt";
        Category[] mensCategory = new Category[4];
        mensCategory[0] = pant;
        mensCategory[1] = shirt;
        mensCategory[2] = jeans;
        mensCategory[3] = tshirt;
        return mensCategory;
    }

    public Category[] getWomenCategory() {
        Category top = new Category();
        top.categoryName = "Top";
        Category jeans = new Category();
        jeans.categoryName = "Women's Jeans";
        Category kurtis = new Category();
        kurtis.categoryName = "Kurtis";
        Category[] womenCategory = new Category[3];
        womenCategory[0] = top;
        womenCategory[1] = jeans;
        womenCategory[2] = kurtis;
        return womenCategory;
    }

    public Category[] getKidCategory() {
        Category jeans = new Category();
        jeans.categoryName = "Kid's Jeans";
        Category tshirt = new Category();
        tshirt.categoryName = "Kid's T-Shirt";
        Category[] kidCategory = new Category[2];
        kidCategory[0] = jeans;
        kidCategory[1] = tshirt;
        return kidCategory;
    }

    public Category[] getSubCategory() {
        Category mens = new Category();
        mens.categoryName = "Men's";
        Category womens = new Category();
        womens.categoryName = "Women's";
        Category kids = new Category();
        kids.categoryName = "Kid's";
        Category[] subCategory = new Category[3];
        subCategory[0] = mens;
        subCategory[1] = womens;
        subCategory[2] = kids;

        return subCategory;
    }
}
