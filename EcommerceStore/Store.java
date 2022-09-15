package com.EcommerceStore;

import com.EcommerceStore.Categories.Category;
import com.EcommerceStore.Categories.Kids.KidsJeans;
import com.EcommerceStore.Categories.Kids.KidsTshirt;
import com.EcommerceStore.Categories.Mens.MensJeans;
import com.EcommerceStore.Categories.Mens.Pants;
import com.EcommerceStore.Categories.Mens.Shirts;
import com.EcommerceStore.Categories.Mens.TShirts;
import com.EcommerceStore.Categories.Women.Kurtis;
import com.EcommerceStore.Categories.Women.Tops;
import com.EcommerceStore.Categories.Women.WomenJeans;
import com.EcommerceStore.Order.Orders;
import com.EcommerceStore.Product.Cart;
import com.EcommerceStore.Product.Catalogue;
import com.EcommerceStore.Product.Products;
import com.EcommerceStore.Users.Customer;
import com.EcommerceStore.Users.Seller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

/*
 * Which type of user you are?       1.  Customer    2. Seller   3. Administrator
 * Customer  ==> View Products/ View Cart / Checkout / Contact us
 * Seller    ==> View your products / Add a Products /Contact us / Delete products
 * Administrator ==> ...
 * Product
 * Category
 * Cart
 * Order
 * */

public class Store {

    Store() {
        System.out.println("Welcome to E-commerce Store.");
    }

    public static void main(String[] args) {

        Scanner scans = new Scanner(System.in);
        Seller seller = new Seller();
        Customer newCustomer = new Customer();
        Catalogue newCatalogue = new Catalogue();
        Category newCategory = new Category();
        Cart newCart = new Cart();
        Products[] cartItems = new Products[25];                                            //? Maximum 25 cart items.
        //! New Subcategory products
        Pants Pant = new Pants();
        Shirts Shirt = new Shirts();
        TShirts TShirts = new TShirts();
        KidsTshirt KidsTShirt = new KidsTshirt();
        MensJeans MensJean = new MensJeans();
        KidsJeans KidsJean = new KidsJeans();
        WomenJeans WomenJean = new WomenJeans();
        Tops Top = new Tops();
        Kurtis Kurti = new Kurtis();
        int totalCartNumb = 0;


        boolean users = true;
        boolean publishRequest = false;
        while (users) {
            System.out.println("Enter type of user you are ? ==> 1 for Customer/ 2 for Seller/ 3 for Administrator");
            int userEntry = scans.nextInt();
            if (userEntry == 1) {
                System.out.println("New Customer");

                System.out.println("Enter User ID");                // Why I can't able to input 2 scan input.
                scans.nextLine();
                String d = scans.nextLine();
                newCustomer.setUserId(d);
                System.out.println("Enter Password");
                String newPassword = scans.nextLine();
                newCustomer.setPassword(newPassword);
                System.out.println("Your user id:\t" + newCustomer.getUserId() + " and Password:\t" + newCustomer.getPassword() + " is set successfully");

                if (newCustomer.verifyUser()) {
                    System.out.println("User data is matched to our database");
                    boolean customerPage = true;

                    //! Customer page starts
                    while (customerPage) {
                        int freshCart = 0;

                        System.out.println("Enter any ==> 1. View products  2.View Cart   3. View Product by Category  4. Contact us   5. Back to Main menu");
                        int choice = scans.nextInt();
                        if (choice == 1) {

                            System.out.println("These are all Products");
                            Products[] allProducts = newCatalogue.getListOfAllProducts();                                         //? return => Product [] listOfProducts ==> Which is also array.
                            if (allProducts == null) {
                                System.out.println("Oops:\tNo product is available to show.");
                            } else {
                                for (int i = 0; i < allProducts.length; i++) {
                                    System.out.println(allProducts[i].getProductName() + " " + allProducts[i].getProductId() + " " + allProducts[i].getProductCost());
                                }

                                //! Add product to cart...
                                System.out.println("Do you want to add any product to cart? Y/N ");
                                String addToCart = scans.next();
                                boolean addingToCart = true;

                                if (addToCart.equals("Y")) {
                                    scans.nextLine();
                                    while (addingToCart) {
                                        System.out.println("Enter product ID?");
                                        String productIdByUser = scans.nextLine();

                                        for (int i = 0; i < allProducts.length; i++) {
                                            if (productIdByUser.equals(allProducts[i].getProductId())) {
                                                System.out.println(allProducts[i].getProductName() + " is Successfully added to Carts.");

                                                for (int j = totalCartNumb; j < totalCartNumb + 1; j++) {
                                                    cartItems[j] = allProducts[i];
                                                    totalCartNumb++;
                                                    freshCart++;
                                                    break;
                                                }
                                            }
                                        }
                                        //! For adding more product to cart.
                                        System.out.println("Want to add products. Y/N ?");
                                        String addMoreProduct = scans.nextLine();
                                        if (addMoreProduct.equals("N")) {
                                            //! Merge previous cart and create a final array of cart.
                                            Products[] previousCartItems = newCart.getListOfCartProducts();
                                            if (previousCartItems != null) {
                                                Products[] finalCarts = new Products[25];

                                                for (int i = 0; i < freshCart; i++) {
                                                    finalCarts[i] = previousCartItems[i];
                                                }
                                                for (int i = 0; i < totalCartNumb; i++) {
                                                    finalCarts[freshCart + i] = cartItems[i + freshCart];
                                                }
                                                //! Final array is saving to Cart.
                                                newCart.setListOfCartProducts(finalCarts);
                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                addingToCart = false;
                                                System.out.println("Congrats! Modified product in your cart and also saved to cart");
                                            } else {
                                                newCart.setListOfCartProducts(cartItems);                                       //! Saving all product to cart, Finally.
                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                addingToCart = false;
                                                System.out.println("Congrats! Your all new product is saved to cart");
                                            }
                                        } else if (addMoreProduct.equals("Y")) {
                                            System.out.println("OKAY!");
                                        } else {
                                            System.out.println("Please enter valid response");
                                        }
                                    }
                                } else if (addToCart.equals("N")) {
                                    System.out.println("Ok! Thank you for visiting store");
                                } else System.out.println("Error:\tWrong input!");
                            }
                        } else if (choice == 2) {
                            System.out.println("Your Cart items");
                            Products[] cartProducts = newCustomer.getAllCustomerCart().getListOfCartProducts();
                            double totalPrice = 0;
                            if (cartProducts != null) {
                                for (int i = 0; i < totalCartNumb; i++) {
                                    System.out.println(cartProducts[i].getProductName() + " " + cartProducts[i].getProductId() + " " + cartProducts[i].getProductCost());
                                    totalPrice += cartProducts[i].getProductCost();
                                }
                                System.out.println("Do you want to checkout?\tY/N ?");
                                scans.nextLine();
                                String checkout = scans.nextLine();
                                if (checkout.equals("Y")) {
                                    System.out.println("Enter your address.");
                                    String address = scans.nextLine();
                                    Orders newOrder = new Orders();
                                    //! Generating Order ID
                                    int newAsciiVal = 0;
                                    StringBuilder orderId = new StringBuilder();
                                    String order = cartProducts[0].getProductName().toUpperCase() + cartProducts[0].getProductCost() + cartProducts[0].getProductId().toUpperCase();
                                    for (int i = 0; i < order.length(); i++) {
                                        int asciiVal = order.charAt(i);
                                        if (asciiVal > 73) {
                                            newAsciiVal = asciiVal - 7;
                                        } else if (asciiVal > 48) {
                                            newAsciiVal = asciiVal + 17;
                                        }
                                        Character character = (char) newAsciiVal;
                                        orderId.append(character);
                                    }
                                    orderId.append((int) totalPrice);

                                    //! Setting all value for checkout.
                                    newOrder.setOrderId(orderId.toString());
                                    newOrder.setPlace(address);
                                    newOrder.setCheckOut("Order of " + totalCartNumb + " items for Rs " + totalPrice + " Only /- is placed successfully." + "\nThank you for shopping");

                                    System.out.println("Your Order ID: " + newOrder.getOrderId());
                                    System.out.println("Location:\t" + newOrder.getPlace());
                                    System.out.println(newOrder.getCheckOut());

                                    //! Generating order details and write in new file.
                                    String fileName = "src/com/swagofindia/OrderDetails/" + newOrder.getOrderId() + ".txt";
                                    File file = new File(fileName);

                                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                                        writer.write("ORDER DETAILS");
                                        writer.newLine();
                                        writer.newLine();
                                        writer.write("Order ID: " + newOrder.getOrderId());
                                        writer.newLine();
                                        writer.newLine();
                                        for (int i = 0; i < totalCartNumb; i++) {
                                            writer.write("Product Number: " + (i + 1));
                                            writer.newLine();
                                            writer.write(" Product Name: " + cartProducts[i].getProductName() + " Product ID: " + cartProducts[i].getProductId() + " Product Cost: " + cartProducts[i].getProductCost());
                                            writer.newLine();
                                        }
                                        writer.newLine();
                                        writer.newLine();
                                        writer.write("Shipping Address: " + newOrder.getPlace());
                                        writer.newLine();
                                        writer.newLine();
                                        writer.write("Total Cost: Rs" + totalPrice);
                                        writer.newLine();
                                        writer.newLine();
                                        writer.write("Thank you for Shopping with us.");
                                    } catch (IOException error) {
                                        System.out.println("Writing Interrupted");
                                    }

                                }
                            } else {
                                System.out.println("No cart items is available.");
                            }


                        } else if (choice == 3) {
                            boolean categoryLoop = true;
                            while (categoryLoop) {
                                System.out.println("1.Mens wear\t2.Women's Wear\t 3.Kid's Wear\t4. Back");
                                int choicePerson = scans.nextInt();
                                if (choicePerson == 1) {
                                    boolean subCategory = true;
                                    while (subCategory) {
                                        System.out.println("1.Pant\t2.Shirt\t3.Jeans\t4.T-shirt\t5.Back");
                                        int category = scans.nextInt();
                                        if (category == 1) {
                                            System.out.println("These are list of Pants.");
                                            Products[] pant = Pant.getAllPants();
                                            if (pant != null) {
                                                for (int i = 0; i < pant.length; i++) {
                                                    System.out.println(pant[i].getProductName() + " " + pant[i].getProductId() + " " + pant[i].getProductCost());
                                                }
                                                //! Add product to cart...
                                                System.out.println("Do you want to add any product to cart? Y/N ");
                                                String addToCart = scans.next();
                                                boolean addingToCart = true;
                                                if (addToCart.equals("Y")) {
                                                    scans.nextLine();
                                                    while (addingToCart) {
                                                        System.out.println("Enter product ID?");
                                                        String productIdByUser = scans.nextLine();

                                                        for (int i = 0; i < pant.length; i++) {
                                                            if (productIdByUser.equals(pant[i].getProductId())) {
                                                                System.out.println(pant[i].getProductName() + " is Successfully added to Carts.");

                                                                for (int j = totalCartNumb; j < totalCartNumb + 1; j++) {
                                                                    cartItems[j] = pant[i];
                                                                    totalCartNumb++;
                                                                    freshCart++;
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        //! For adding more product to cart.
                                                        System.out.println("Want to add products. Y/N ?");
                                                        String addMoreProduct = scans.nextLine();
                                                        if (addMoreProduct.equals("N")) {
                                                            //! Merge previous cart and create a final array of cart.
                                                            Products[] previousCartItems = newCart.getListOfCartProducts();

                                                            if (previousCartItems != null) {
                                                                Products[] finalCarts = new Products[25];

                                                                for (int i = 0; i < freshCart; i++) {
                                                                    finalCarts[i] = previousCartItems[i];
                                                                }
                                                                for (int i = 0; i < totalCartNumb; i++) {
                                                                    finalCarts[freshCart + i] = cartItems[i + freshCart];
                                                                }
                                                                //! Final array is saving to Cart.
                                                                newCart.setListOfCartProducts(finalCarts);
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Modified product in your cart and also saved to cart");
                                                            } else {
                                                                newCart.setListOfCartProducts(cartItems);                                       //! Saving all product to cart, Finally.
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Your all new product is saved to cart");
                                                            }
                                                        } else if (addMoreProduct.equals("Y")) {
                                                            System.out.println("OKAY!");
                                                        } else {
                                                            System.out.println("Please enter valid response");
                                                        }
                                                    }
                                                } else if (addToCart.equals("N")) {
                                                    System.out.println("Ok! Thank you for visiting store");
                                                } else System.out.println("Error:\tWrong input!");

                                            } else {
                                                Products[] dummyPant = Pant.getListOfDummyPants();
                                                for (int i = 0; i < dummyPant.length; i++) {
                                                    System.out.println(dummyPant[i].getProductName() + " " + dummyPant[i].getProductId() + " " + dummyPant[i].getProductCost());
                                                }
                                                //! Add product to cart...
                                                System.out.println("Do you want to add any product to cart? Y/N ");
                                                String addToCart = scans.next();
                                                boolean addingToCart = true;
                                                if (addToCart.equals("Y")) {
                                                    scans.nextLine();
                                                    while (addingToCart) {
                                                        System.out.println("Enter product ID?");
                                                        String productIdByUser = scans.nextLine();

                                                        for (int i = 0; i < dummyPant.length; i++) {
                                                            if (productIdByUser.equals(dummyPant[i].getProductId())) {
                                                                System.out.println(dummyPant[i].getProductName() + " is Successfully added to Carts.");

                                                                System.out.println("Total n " + totalCartNumb);
                                                                for (int j = totalCartNumb; j < totalCartNumb + 1; j++) {
                                                                    cartItems[j] = dummyPant[i];
                                                                    totalCartNumb++;
                                                                    freshCart++;
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        //! For adding more product to cart.
                                                        System.out.println("Want to add products. Y/N ?");
                                                        String addMoreProduct = scans.nextLine();
                                                        if (addMoreProduct.equals("N")) {
                                                            //! Merge previous cart and create a final array of cart.
                                                            Products[] previousCartItems = newCart.getListOfCartProducts();


                                                            if (previousCartItems != null) {
                                                                Products[] finalCarts = new Products[25];

                                                                for (int i = 0; i < freshCart; i++) {
                                                                    finalCarts[i] = previousCartItems[i];
                                                                }
                                                                for (int i = 0; i < totalCartNumb; i++) {
                                                                    finalCarts[freshCart + i] = cartItems[i + freshCart];
                                                                }
                                                                //! Final array is saving to Cart.
                                                                newCart.setListOfCartProducts(finalCarts);
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Modified product in your cart and also saved to cart");
                                                            } else {
                                                                newCart.setListOfCartProducts(cartItems);                                       //! Saving all product to cart, Finally.
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Your all new product is saved to cart");
                                                            }
                                                        } else if (addMoreProduct.equals("Y")) {
                                                            System.out.println("OKAY!");
                                                        } else {
                                                            System.out.println("Please enter valid response");
                                                        }
                                                    }
                                                } else if (addToCart.equals("N")) {
                                                    System.out.println("Ok! Thank you for visiting store");
                                                } else System.out.println("Error:\tWrong input!");

                                            }

                                        } else if (category == 2) {
                                            System.out.println("These are list of Shirt's.");
                                            Products[] shirt = Shirt.getAllShirts();
                                            if (shirt != null) {
                                                for (int i = 0; i < shirt.length; i++) {
                                                    System.out.println(shirt[i].getProductName() + " " + shirt[i].getProductId() + " " + shirt[i].getProductCost());
                                                }
                                                //! Add product to cart...
                                                System.out.println("Do you want to add any product to cart? Y/N ");
                                                String addToCart = scans.next();
                                                boolean addingToCart = true;
                                                if (addToCart.equals("Y")) {
                                                    scans.nextLine();
                                                    while (addingToCart) {
                                                        System.out.println("Enter product ID?");
                                                        String productIdByUser = scans.nextLine();

                                                        for (int i = 0; i < shirt.length; i++) {
                                                            if (productIdByUser.equals(shirt[i].getProductId())) {
                                                                System.out.println(shirt[i].getProductName() + " is Successfully added to Carts.");

                                                                System.out.println("Total n " + totalCartNumb);
                                                                for (int j = totalCartNumb; j < totalCartNumb + 1; j++) {
                                                                    cartItems[j] = shirt[i];
                                                                    totalCartNumb++;
                                                                    freshCart++;
                                                                    System.out.println(freshCart);
                                                                    System.out.println(freshCart);
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        //! For adding more product to cart.
                                                        System.out.println("Want to add products. Y/N ?");
                                                        String addMoreProduct = scans.nextLine();
                                                        if (addMoreProduct.equals("N")) {
                                                            //! Merge previous cart and create a final array of cart.
                                                            Products[] previousCartItems = newCart.getListOfCartProducts();


                                                            if (previousCartItems != null) {
                                                                Products[] finalCarts = new Products[25];

                                                                for (int i = 0; i < freshCart; i++) {
                                                                    finalCarts[i] = previousCartItems[i];
                                                                }
                                                                for (int i = 0; i < totalCartNumb; i++) {
                                                                    finalCarts[freshCart + i] = cartItems[i + freshCart];
                                                                }
                                                                //! Final array is saving to Cart.
                                                                newCart.setListOfCartProducts(finalCarts);
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Modified product in your cart and also saved to cart");
                                                            } else {
                                                                newCart.setListOfCartProducts(cartItems);                                       //! Saving all product to cart, Finally.
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Your all new product is saved to cart");
                                                            }
                                                        } else if (addMoreProduct.equals("Y")) {
                                                            System.out.println("OKAY!");
                                                        } else {
                                                            System.out.println("Please enter valid response");
                                                        }
                                                    }
                                                } else if (addToCart.equals("N")) {
                                                    System.out.println("Ok! Thank you for visiting store");
                                                } else System.out.println("Error:\tWrong input!");

                                            } else {
                                                Products[] dummyShirt = Shirt.getListOfDummyShirts();
                                                for (int i = 0; i < dummyShirt.length; i++) {
                                                    System.out.println(dummyShirt[i].getProductName() + " " + dummyShirt[i].getProductId() + " " + dummyShirt[i].getProductCost());
                                                }
                                                //! Add product to cart...
                                                System.out.println("Do you want to add any product to cart? Y/N ");
                                                String addToCart = scans.next();
                                                boolean addingToCart = true;
                                                if (addToCart.equals("Y")) {
                                                    scans.nextLine();
                                                    while (addingToCart) {
                                                        System.out.println("Enter product ID?");
                                                        String productIdByUser = scans.nextLine();

                                                        for (int i = 0; i < dummyShirt.length; i++) {
                                                            if (productIdByUser.equals(dummyShirt[i].getProductId())) {
                                                                System.out.println(dummyShirt[i].getProductName() + " is Successfully added to Carts.");

                                                                System.out.println("Total n " + totalCartNumb);
                                                                for (int j = totalCartNumb; j < totalCartNumb + 1; j++) {
                                                                    cartItems[j] = dummyShirt[i];
                                                                    totalCartNumb++;
                                                                    freshCart++;
                                                                    System.out.println(freshCart);

                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        //! For adding more product to cart.
                                                        System.out.println("Want to add products. Y/N ?");
                                                        String addMoreProduct = scans.nextLine();
                                                        if (addMoreProduct.equals("N")) {
                                                            //! Merge previous cart and create a final array of cart.
                                                            Products[] previousCartItems = newCart.getListOfCartProducts();


                                                            if (previousCartItems != null) {
                                                                Products[] finalCarts = new Products[25];

                                                                for (int i = 0; i < freshCart; i++) {
                                                                    finalCarts[i] = previousCartItems[i];
                                                                }
                                                                for (int i = 0; i < totalCartNumb; i++) {
                                                                    finalCarts[freshCart + i] = cartItems[i + freshCart];
                                                                }
                                                                //! Final array is saving to Cart.
                                                                newCart.setListOfCartProducts(finalCarts);
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Modified product in your cart and also saved to cart");
                                                            } else {
                                                                newCart.setListOfCartProducts(cartItems);                                       //! Saving all product to cart, Finally.
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Your all new product is saved to cart");
                                                            }
                                                        } else if (addMoreProduct.equals("Y")) {
                                                            System.out.println("OKAY!");
                                                        } else {
                                                            System.out.println("Please enter valid response");
                                                        }
                                                    }
                                                } else if (addToCart.equals("N")) {
                                                    System.out.println("Ok! Thank you for visiting store");
                                                } else System.out.println("Error:\tWrong input!");

                                            }
                                        } else if (category == 3) {
                                            System.out.println("These are list of Jeans.");
                                            Products[] mensJeans = MensJean.getAllJeans();
                                            if (mensJeans != null) {
                                                for (int i = 0; i < mensJeans.length; i++) {
                                                    System.out.println(mensJeans[i].getProductName() + " " + mensJeans[i].getProductId() + " " + mensJeans[i].getProductCost());
                                                }
                                                //! Add product to cart...
                                                System.out.println("Do you want to add any product to cart? Y/N ");
                                                String addToCart = scans.next();
                                                boolean addingToCart = true;
                                                if (addToCart.equals("Y")) {
                                                    scans.nextLine();
                                                    while (addingToCart) {
                                                        System.out.println("Enter product ID?");
                                                        String productIdByUser = scans.nextLine();

                                                        for (int i = 0; i < mensJeans.length; i++) {
                                                            if (productIdByUser.equals(mensJeans[i].getProductId())) {
                                                                System.out.println(mensJeans[i].getProductName() + " is Successfully added to Carts.");

                                                                System.out.println("Total n " + totalCartNumb);
                                                                for (int j = totalCartNumb; j < totalCartNumb + 1; j++) {
                                                                    cartItems[j] = mensJeans[i];
                                                                    totalCartNumb++;
                                                                    freshCart++;
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        //! For adding more product to cart.
                                                        System.out.println("Want to add products. Y/N ?");
                                                        String addMoreProduct = scans.nextLine();
                                                        if (addMoreProduct.equals("N")) {
                                                            //! Merge previous cart and create a final array of cart.
                                                            Products[] previousCartItems = newCart.getListOfCartProducts();


                                                            if (previousCartItems != null) {
                                                                Products[] finalCarts = new Products[25];

                                                                for (int i = 0; i < freshCart; i++) {
                                                                    finalCarts[i] = previousCartItems[i];
                                                                }
                                                                for (int i = 0; i < totalCartNumb; i++) {
                                                                    finalCarts[freshCart + i] = cartItems[i + freshCart];
                                                                }
                                                                //! Final array is saving to Cart.
                                                                newCart.setListOfCartProducts(finalCarts);
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Modified product in your cart and also saved to cart");
                                                            } else {
                                                                newCart.setListOfCartProducts(cartItems);                                       //! Saving all product to cart, Finally.
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Your all new product is saved to cart");
                                                            }
                                                        } else if (addMoreProduct.equals("Y")) {
                                                            System.out.println("OKAY!");
                                                        } else {
                                                            System.out.println("Please enter valid response");
                                                        }
                                                    }
                                                } else if (addToCart.equals("N")) {
                                                    System.out.println("Ok! Thank you for visiting store");
                                                } else System.out.println("Error:\tWrong input!");

                                            } else {
                                                Products[] dummyJeans = MensJean.getListOfDummyJeans();
                                                for (int i = 0; i < dummyJeans.length; i++) {
                                                    System.out.println(dummyJeans[i].getProductName() + " " + dummyJeans[i].getProductId() + " " + dummyJeans[i].getProductCost());
                                                }
                                                //! Add product to cart...
                                                System.out.println("Do you want to add any product to cart? Y/N ");
                                                String addToCart = scans.next();
                                                boolean addingToCart = true;
                                                if (addToCart.equals("Y")) {
                                                    scans.nextLine();
                                                    while (addingToCart) {
                                                        System.out.println("Enter product ID?");
                                                        String productIdByUser = scans.nextLine();

                                                        for (int i = 0; i < dummyJeans.length; i++) {
                                                            if (productIdByUser.equals(dummyJeans[i].getProductId())) {
                                                                System.out.println(dummyJeans[i].getProductName() + " is Successfully added to Carts.");

                                                                System.out.println("Total n " + totalCartNumb);
                                                                for (int j = totalCartNumb; j < totalCartNumb + 1; j++) {
                                                                    cartItems[j] = dummyJeans[i];
                                                                    totalCartNumb++;
                                                                    freshCart++;
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        //! For adding more product to cart.
                                                        System.out.println("Want to add products. Y/N ?");
                                                        String addMoreProduct = scans.nextLine();
                                                        if (addMoreProduct.equals("N")) {
                                                            //! Merge previous cart and create a final array of cart.
                                                            Products[] previousCartItems = newCart.getListOfCartProducts();


                                                            if (previousCartItems != null) {
                                                                Products[] finalCarts = new Products[25];

                                                                for (int i = 0; i < freshCart; i++) {
                                                                    finalCarts[i] = previousCartItems[i];
                                                                }
                                                                for (int i = 0; i < totalCartNumb; i++) {
                                                                    finalCarts[freshCart + i] = cartItems[i + freshCart];
                                                                }
                                                                //! Final array is saving to Cart.
                                                                newCart.setListOfCartProducts(finalCarts);
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Modified product in your cart and also saved to cart");
                                                            } else {
                                                                newCart.setListOfCartProducts(cartItems);                                       //! Saving all product to cart, Finally.
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Your all new product is saved to cart");
                                                            }
                                                        } else if (addMoreProduct.equals("Y")) {
                                                            System.out.println("OKAY!");
                                                        } else {
                                                            System.out.println("Please enter valid response");
                                                        }
                                                    }
                                                } else if (addToCart.equals("N")) {
                                                    System.out.println("Ok! Thank you for visiting store");
                                                } else System.out.println("Error:\tWrong input!");

                                            }
                                        } else if (category == 4) {
                                            System.out.println("These are list of T-shirt.");
                                            Products[] tShirt = TShirts.getAllTShirts();
                                            if (tShirt != null) {
                                                for (int i = 0; i < tShirt.length; i++) {
                                                    System.out.println(tShirt[i].getProductName() + " " + tShirt[i].getProductId() + " " + tShirt[i].getProductCost());
                                                }//! Add product to cart...
                                                System.out.println("Do you want to add any product to cart? Y/N ");
                                                String addToCart = scans.next();
                                                boolean addingToCart = true;
                                                if (addToCart.equals("Y")) {
                                                    scans.nextLine();
                                                    while (addingToCart) {
                                                        System.out.println("Enter product ID?");
                                                        String productIdByUser = scans.nextLine();

                                                        for (int i = 0; i < tShirt.length; i++) {
                                                            if (productIdByUser.equals(tShirt[i].getProductId())) {
                                                                System.out.println(tShirt[i].getProductName() + " is Successfully added to Carts.");

                                                                System.out.println("Total n " + totalCartNumb);
                                                                for (int j = totalCartNumb; j < totalCartNumb + 1; j++) {
                                                                    cartItems[j] = tShirt[i];
                                                                    totalCartNumb++;
                                                                    freshCart++;
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        //! For adding more product to cart.
                                                        System.out.println("Want to add products. Y/N ?");
                                                        String addMoreProduct = scans.nextLine();
                                                        if (addMoreProduct.equals("N")) {
                                                            //! Merge previous cart and create a final array of cart.
                                                            Products[] previousCartItems = newCart.getListOfCartProducts();


                                                            if (previousCartItems != null) {
                                                                Products[] finalCarts = new Products[25];

                                                                for (int i = 0; i < freshCart; i++) {
                                                                    finalCarts[i] = previousCartItems[i];
                                                                }
                                                                for (int i = 0; i < totalCartNumb; i++) {
                                                                    finalCarts[freshCart + i] = cartItems[i + freshCart];
                                                                }
                                                                //! Final array is saving to Cart.
                                                                newCart.setListOfCartProducts(finalCarts);
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Modified product in your cart and also saved to cart");
                                                            } else {
                                                                newCart.setListOfCartProducts(cartItems);                                       //! Saving all product to cart, Finally.
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Your all new product is saved to cart");
                                                            }
                                                        } else if (addMoreProduct.equals("Y")) {
                                                            System.out.println("OKAY!");
                                                        } else {
                                                            System.out.println("Please enter valid response");
                                                        }
                                                    }
                                                } else if (addToCart.equals("N")) {
                                                    System.out.println("Ok! Thank you for visiting store");
                                                } else System.out.println("Error:\tWrong input!");

                                            } else {
                                                Products[] dummyTShirt = TShirts.getListOfDummyTShirt();
                                                for (int i = 0; i < dummyTShirt.length; i++) {
                                                    System.out.println(dummyTShirt[i].getProductName() + " " + dummyTShirt[i].getProductId() + " " + dummyTShirt[i].getProductCost());
                                                }//! Add product to cart...
                                                System.out.println("Do you want to add any product to cart? Y/N ");
                                                String addToCart = scans.next();
                                                boolean addingToCart = true;
                                                if (addToCart.equals("Y")) {
                                                    scans.nextLine();
                                                    while (addingToCart) {
                                                        System.out.println("Enter product ID?");
                                                        String productIdByUser = scans.nextLine();

                                                        for (int i = 0; i < dummyTShirt.length; i++) {
                                                            if (productIdByUser.equals(dummyTShirt[i].getProductId())) {
                                                                System.out.println(dummyTShirt[i].getProductName() + " is Successfully added to Carts.");

                                                                System.out.println("Total n " + totalCartNumb);
                                                                for (int j = totalCartNumb; j < totalCartNumb + 1; j++) {
                                                                    cartItems[j] = dummyTShirt[i];
                                                                    totalCartNumb++;
                                                                    freshCart++;
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        //! For adding more product to cart.
                                                        System.out.println("Want to add products. Y/N ?");
                                                        String addMoreProduct = scans.nextLine();
                                                        if (addMoreProduct.equals("N")) {
                                                            //! Merge previous cart and create a final array of cart.
                                                            Products[] previousCartItems = newCart.getListOfCartProducts();


                                                            if (previousCartItems != null) {
                                                                Products[] finalCarts = new Products[25];

                                                                for (int i = 0; i < freshCart; i++) {
                                                                    finalCarts[i] = previousCartItems[i];
                                                                }
                                                                for (int i = 0; i < totalCartNumb; i++) {
                                                                    finalCarts[freshCart + i] = cartItems[i + freshCart];
                                                                }
                                                                //! Final array is saving to Cart.
                                                                newCart.setListOfCartProducts(finalCarts);
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Modified product in your cart and also saved to cart");
                                                            } else {
                                                                newCart.setListOfCartProducts(cartItems);                                       //! Saving all product to cart, Finally.
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Your all new product is saved to cart");
                                                            }
                                                        } else if (addMoreProduct.equals("Y")) {
                                                            System.out.println("OKAY!");
                                                        } else {
                                                            System.out.println("Please enter valid response");
                                                        }
                                                    }
                                                } else if (addToCart.equals("N")) {
                                                    System.out.println("Ok! Thank you for visiting store");
                                                } else System.out.println("Error:\tWrong input!");

                                            }
                                        } else if (category == 5) {
                                            subCategory = false;
                                        } else System.out.println("Error:\tPlease select valid category.");
                                    }
                                } else if (choicePerson == 2) {
                                    boolean subCategory = true;
                                    while (subCategory) {
                                        System.out.println("1.Tops\t2.Jeans\t3.Kurtis\t4.Back");
                                        int category = scans.nextInt();
                                        if (category == 1) {
                                            System.out.println("These are list of Tops.");
                                            Products[] top = Top.getAllTops();
                                            if (top != null) {
                                                for (int i = 0; i < top.length; i++) {
                                                    System.out.println(top[i].getProductName() + " " + top[i].getProductId() + " " + top[i].getProductCost());
                                                }//! Add product to cart...
                                                System.out.println("Do you want to add any product to cart? Y/N ");
                                                String addToCart = scans.next();
                                                boolean addingToCart = true;
                                                if (addToCart.equals("Y")) {
                                                    scans.nextLine();
                                                    while (addingToCart) {
                                                        System.out.println("Enter product ID?");
                                                        String productIdByUser = scans.nextLine();

                                                        for (int i = 0; i < top.length; i++) {
                                                            if (productIdByUser.equals(top[i].getProductId())) {
                                                                System.out.println(top[i].getProductName() + " is Successfully added to Carts.");

                                                                System.out.println("Total n " + totalCartNumb);
                                                                for (int j = totalCartNumb; j < totalCartNumb + 1; j++) {
                                                                    cartItems[j] = top[i];
                                                                    totalCartNumb++;
                                                                    freshCart++;
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        //! For adding more product to cart.
                                                        System.out.println("Want to add products. Y/N ?");
                                                        String addMoreProduct = scans.nextLine();
                                                        if (addMoreProduct.equals("N")) {
                                                            //! Merge previous cart and create a final array of cart.
                                                            Products[] previousCartItems = newCart.getListOfCartProducts();


                                                            if (previousCartItems != null) {
                                                                Products[] finalCarts = new Products[25];

                                                                for (int i = 0; i < freshCart; i++) {
                                                                    finalCarts[i] = previousCartItems[i];
                                                                }
                                                                for (int i = 0; i < totalCartNumb; i++) {
                                                                    finalCarts[freshCart + i] = cartItems[i + freshCart];
                                                                }
                                                                //! Final array is saving to Cart.
                                                                newCart.setListOfCartProducts(finalCarts);
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Modified product in your cart and also saved to cart");
                                                            } else {
                                                                newCart.setListOfCartProducts(cartItems);                                       //! Saving all product to cart, Finally.
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Your all new product is saved to cart");
                                                            }
                                                        } else if (addMoreProduct.equals("Y")) {
                                                            System.out.println("OKAY!");
                                                        } else {
                                                            System.out.println("Please enter valid response");
                                                        }
                                                    }
                                                } else if (addToCart.equals("N")) {
                                                    System.out.println("Ok! Thank you for visiting store");
                                                } else System.out.println("Error:\tWrong input!");

                                            } else {
                                                Products[] dummyTop = Top.getListOfDummyTops();
                                                for (int i = 0; i < dummyTop.length; i++) {
                                                    System.out.println(dummyTop[i].getProductName() + " " + dummyTop[i].getProductId() + " " + dummyTop[i].getProductCost());
                                                }//! Add product to cart...
                                                System.out.println("Do you want to add any product to cart? Y/N ");
                                                String addToCart = scans.next();
                                                boolean addingToCart = true;
                                                if (addToCart.equals("Y")) {
                                                    scans.nextLine();
                                                    while (addingToCart) {
                                                        System.out.println("Enter product ID?");
                                                        String productIdByUser = scans.nextLine();

                                                        for (int i = 0; i < dummyTop.length; i++) {
                                                            if (productIdByUser.equals(dummyTop[i].getProductId())) {
                                                                System.out.println(dummyTop[i].getProductName() + " is Successfully added to Carts.");

                                                                System.out.println("Total n " + totalCartNumb);
                                                                for (int j = totalCartNumb; j < totalCartNumb + 1; j++) {
                                                                    cartItems[j] = dummyTop[i];
                                                                    totalCartNumb++;
                                                                    freshCart++;
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        //! For adding more product to cart.
                                                        System.out.println("Want to add products. Y/N ?");
                                                        String addMoreProduct = scans.nextLine();
                                                        if (addMoreProduct.equals("N")) {
                                                            //! Merge previous cart and create a final array of cart.
                                                            Products[] previousCartItems = newCart.getListOfCartProducts();


                                                            if (previousCartItems != null) {
                                                                Products[] finalCarts = new Products[25];

                                                                for (int i = 0; i < freshCart; i++) {
                                                                    finalCarts[i] = previousCartItems[i];
                                                                }
                                                                for (int i = 0; i < totalCartNumb; i++) {
                                                                    finalCarts[freshCart + i] = cartItems[i + freshCart];
                                                                }
                                                                //! Final array is saving to Cart.
                                                                newCart.setListOfCartProducts(finalCarts);
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Modified product in your cart and also saved to cart");
                                                            } else {
                                                                newCart.setListOfCartProducts(cartItems);                                       //! Saving all product to cart, Finally.
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Your all new product is saved to cart");
                                                            }
                                                        } else if (addMoreProduct.equals("Y")) {
                                                            System.out.println("OKAY!");
                                                        } else {
                                                            System.out.println("Please enter valid response");
                                                        }
                                                    }
                                                } else if (addToCart.equals("N")) {
                                                    System.out.println("Ok! Thank you for visiting store");
                                                } else System.out.println("Error:\tWrong input!");

                                            }
                                        } else if (category == 2) {
                                            System.out.println("These are list of Women Jeans.");
                                            Products[] womenJeans = WomenJean.getAllWomenJeans();
                                            if (womenJeans != null) {
                                                for (int i = 0; i < womenJeans.length; i++) {
                                                    System.out.println(womenJeans[i].getProductName() + " " + womenJeans[i].getProductId() + " " + womenJeans[i].getProductCost());
                                                }
                                                //! Add product to cart...
                                                System.out.println("Do you want to add any product to cart? Y/N ");
                                                String addToCart = scans.next();
                                                boolean addingToCart = true;
                                                if (addToCart.equals("Y")) {
                                                    scans.nextLine();
                                                    while (addingToCart) {
                                                        System.out.println("Enter product ID?");
                                                        String productIdByUser = scans.nextLine();

                                                        for (int i = 0; i < womenJeans.length; i++) {
                                                            if (productIdByUser.equals(womenJeans[i].getProductId())) {
                                                                System.out.println(womenJeans[i].getProductName() + " is Successfully added to Carts.");

                                                                System.out.println("Total n " + totalCartNumb);
                                                                for (int j = totalCartNumb; j < totalCartNumb + 1; j++) {
                                                                    cartItems[j] = womenJeans[i];
                                                                    totalCartNumb++;
                                                                    freshCart++;
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        //! For adding more product to cart.
                                                        System.out.println("Want to add products. Y/N ?");
                                                        String addMoreProduct = scans.nextLine();
                                                        if (addMoreProduct.equals("N")) {
                                                            //! Merge previous cart and create a final array of cart.
                                                            Products[] previousCartItems = newCart.getListOfCartProducts();


                                                            if (previousCartItems != null) {
                                                                Products[] finalCarts = new Products[25];

                                                                for (int i = 0; i < freshCart; i++) {
                                                                    finalCarts[i] = previousCartItems[i];
                                                                }
                                                                for (int i = 0; i < totalCartNumb; i++) {
                                                                    finalCarts[freshCart + i] = cartItems[i + freshCart];
                                                                }
                                                                //! Final array is saving to Cart.
                                                                newCart.setListOfCartProducts(finalCarts);
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Modified product in your cart and also saved to cart");
                                                            } else {
                                                                newCart.setListOfCartProducts(cartItems);                                       //! Saving all product to cart, Finally.
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Your all new product is saved to cart");
                                                            }
                                                        } else if (addMoreProduct.equals("Y")) {
                                                            System.out.println("OKAY!");
                                                        } else {
                                                            System.out.println("Please enter valid response");
                                                        }
                                                    }
                                                } else if (addToCart.equals("N")) {
                                                    System.out.println("Ok! Thank you for visiting store");
                                                } else System.out.println("Error:\tWrong input!");

                                            } else {
                                                Products[] dummyWomenJeans = WomenJean.getListOfDummyWomenJeans();
                                                for (int i = 0; i < dummyWomenJeans.length; i++) {
                                                    System.out.println(dummyWomenJeans[i].getProductName() + " " + dummyWomenJeans[i].getProductId() + " " + dummyWomenJeans[i].getProductCost());
                                                }//! Add product to cart...
                                                System.out.println("Do you want to add any product to cart? Y/N ");
                                                String addToCart = scans.next();
                                                boolean addingToCart = true;
                                                if (addToCart.equals("Y")) {
                                                    scans.nextLine();
                                                    while (addingToCart) {
                                                        System.out.println("Enter product ID?");
                                                        String productIdByUser = scans.nextLine();

                                                        for (int i = 0; i < dummyWomenJeans.length; i++) {
                                                            if (productIdByUser.equals(dummyWomenJeans[i].getProductId())) {
                                                                System.out.println(dummyWomenJeans[i].getProductName() + " is Successfully added to Carts.");

                                                                System.out.println("Total n " + totalCartNumb);
                                                                for (int j = totalCartNumb; j < totalCartNumb + 1; j++) {
                                                                    cartItems[j] = dummyWomenJeans[i];
                                                                    totalCartNumb++;
                                                                    freshCart++;
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        //! For adding more product to cart.
                                                        System.out.println("Want to add products. Y/N ?");
                                                        String addMoreProduct = scans.nextLine();
                                                        if (addMoreProduct.equals("N")) {
                                                            //! Merge previous cart and create a final array of cart.
                                                            Products[] previousCartItems = newCart.getListOfCartProducts();


                                                            if (previousCartItems != null) {
                                                                Products[] finalCarts = new Products[25];

                                                                for (int i = 0; i < freshCart; i++) {
                                                                    finalCarts[i] = previousCartItems[i];
                                                                }
                                                                for (int i = 0; i < totalCartNumb; i++) {
                                                                    finalCarts[freshCart + i] = cartItems[i + freshCart];
                                                                }
                                                                //! Final array is saving to Cart.
                                                                newCart.setListOfCartProducts(finalCarts);
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Modified product in your cart and also saved to cart");
                                                            } else {
                                                                newCart.setListOfCartProducts(cartItems);                                       //! Saving all product to cart, Finally.
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Your all new product is saved to cart");
                                                            }
                                                        } else if (addMoreProduct.equals("Y")) {
                                                            System.out.println("OKAY!");
                                                        } else {
                                                            System.out.println("Please enter valid response");
                                                        }
                                                    }
                                                } else if (addToCart.equals("N")) {
                                                    System.out.println("Ok! Thank you for visiting store");
                                                } else System.out.println("Error:\tWrong input!");

                                            }
                                        } else if (category == 3) {
                                            System.out.println("These are list of Kurtis.");
                                            Products[] kurtis = Kurti.getAllKurtis();
                                            if (kurtis != null) {
                                                for (int i = 0; i < kurtis.length; i++) {
                                                    System.out.println(kurtis[i].getProductName() + " " + kurtis[i].getProductId() + " " + kurtis[i].getProductCost());
                                                }//! Add product to cart...
                                                System.out.println("Do you want to add any product to cart? Y/N ");
                                                String addToCart = scans.next();
                                                boolean addingToCart = true;
                                                if (addToCart.equals("Y")) {
                                                    scans.nextLine();
                                                    while (addingToCart) {
                                                        System.out.println("Enter product ID?");
                                                        String productIdByUser = scans.nextLine();

                                                        for (int i = 0; i < kurtis.length; i++) {
                                                            if (productIdByUser.equals(kurtis[i].getProductId())) {
                                                                System.out.println(kurtis[i].getProductName() + " is Successfully added to Carts.");

                                                                System.out.println("Total n " + totalCartNumb);
                                                                for (int j = totalCartNumb; j < totalCartNumb + 1; j++) {
                                                                    cartItems[j] = kurtis[i];
                                                                    totalCartNumb++;
                                                                    freshCart++;
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        //! For adding more product to cart.
                                                        System.out.println("Want to add products. Y/N ?");
                                                        String addMoreProduct = scans.nextLine();
                                                        if (addMoreProduct.equals("N")) {
                                                            //! Merge previous cart and create a final array of cart.
                                                            Products[] previousCartItems = newCart.getListOfCartProducts();


                                                            if (previousCartItems != null) {
                                                                Products[] finalCarts = new Products[25];

                                                                for (int i = 0; i < freshCart; i++) {
                                                                    finalCarts[i] = previousCartItems[i];
                                                                }
                                                                for (int i = 0; i < totalCartNumb; i++) {
                                                                    finalCarts[freshCart + i] = cartItems[i + freshCart];
                                                                }
                                                                //! Final array is saving to Cart.
                                                                newCart.setListOfCartProducts(finalCarts);
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Modified product in your cart and also saved to cart");
                                                            } else {
                                                                newCart.setListOfCartProducts(cartItems);                                       //! Saving all product to cart, Finally.
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Your all new product is saved to cart");
                                                            }
                                                        } else if (addMoreProduct.equals("Y")) {
                                                            System.out.println("OKAY!");
                                                        } else {
                                                            System.out.println("Please enter valid response");
                                                        }
                                                    }
                                                } else if (addToCart.equals("N")) {
                                                    System.out.println("Ok! Thank you for visiting store");
                                                } else System.out.println("Error:\tWrong input!");

                                            } else {
                                                Products[] dummyKurtis = Kurti.getListOfDummyKurtis();
                                                for (int i = 0; i < dummyKurtis.length; i++) {
                                                    System.out.println(dummyKurtis[i].getProductName() + " " + dummyKurtis[i].getProductId() + " " + dummyKurtis[i].getProductCost());
                                                }//! Add product to cart...
                                                System.out.println("Do you want to add any product to cart? Y/N ");
                                                String addToCart = scans.next();
                                                boolean addingToCart = true;
                                                if (addToCart.equals("Y")) {
                                                    scans.nextLine();
                                                    while (addingToCart) {
                                                        System.out.println("Enter product ID?");
                                                        String productIdByUser = scans.nextLine();

                                                        for (int i = 0; i < dummyKurtis.length; i++) {
                                                            if (productIdByUser.equals(dummyKurtis[i].getProductId())) {
                                                                System.out.println(dummyKurtis[i].getProductName() + " is Successfully added to Carts.");

                                                                System.out.println("Total n " + totalCartNumb);
                                                                for (int j = totalCartNumb; j < totalCartNumb + 1; j++) {
                                                                    cartItems[j] = dummyKurtis[i];
                                                                    totalCartNumb++;
                                                                    freshCart++;
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        //! For adding more product to cart.
                                                        System.out.println("Want to add products. Y/N ?");
                                                        String addMoreProduct = scans.nextLine();
                                                        if (addMoreProduct.equals("N")) {
                                                            //! Merge previous cart and create a final array of cart.
                                                            Products[] previousCartItems = newCart.getListOfCartProducts();


                                                            if (previousCartItems != null) {
                                                                Products[] finalCarts = new Products[25];

                                                                for (int i = 0; i < freshCart; i++) {
                                                                    finalCarts[i] = previousCartItems[i];
                                                                }
                                                                for (int i = 0; i < totalCartNumb; i++) {
                                                                    finalCarts[freshCart + i] = cartItems[i + freshCart];
                                                                }
                                                                //! Final array is saving to Cart.
                                                                newCart.setListOfCartProducts(finalCarts);
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Modified product in your cart and also saved to cart");
                                                            } else {
                                                                newCart.setListOfCartProducts(cartItems);                                       //! Saving all product to cart, Finally.
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Your all new product is saved to cart");
                                                            }
                                                        } else if (addMoreProduct.equals("Y")) {
                                                            System.out.println("OKAY!");
                                                        } else {
                                                            System.out.println("Please enter valid response");
                                                        }
                                                    }
                                                } else if (addToCart.equals("N")) {
                                                    System.out.println("Ok! Thank you for visiting store");
                                                } else System.out.println("Error:\tWrong input!");

                                            }
                                        } else if (category == 4) {
                                            subCategory = false;
                                        } else System.out.println("Error:\tPlease select valid category.");
                                    }
                                } else if (choicePerson == 3) {
                                    boolean subCategory = true;
                                    while (subCategory) {
                                        System.out.println("1.T-shirt\t2.Jeans\t3.Back");
                                        int category = scans.nextInt();
                                        if (category == 1) {
                                            System.out.println("These are list of T-shirt for kids.");
                                            Products[] kidTshirt = KidsTShirt.getAllKidsTShirt();
                                            if (kidTshirt != null) {
                                                for (int i = 0; i < kidTshirt.length; i++) {
                                                    System.out.println(kidTshirt[i].getProductName() + " " + kidTshirt[i].getProductId() + " " + kidTshirt[i].getProductCost());
                                                }//! Add product to cart...
                                                System.out.println("Do you want to add any product to cart? Y/N ");
                                                String addToCart = scans.next();
                                                boolean addingToCart = true;
                                                if (addToCart.equals("Y")) {
                                                    scans.nextLine();
                                                    while (addingToCart) {
                                                        System.out.println("Enter product ID?");
                                                        String productIdByUser = scans.nextLine();

                                                        for (int i = 0; i < kidTshirt.length; i++) {
                                                            if (productIdByUser.equals(kidTshirt[i].getProductId())) {
                                                                System.out.println(kidTshirt[i].getProductName() + " is Successfully added to Carts.");

                                                                System.out.println("Total n " + totalCartNumb);
                                                                for (int j = totalCartNumb; j < totalCartNumb + 1; j++) {
                                                                    cartItems[j] = kidTshirt[i];
                                                                    totalCartNumb++;
                                                                    freshCart++;
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        //! For adding more product to cart.
                                                        System.out.println("Want to add products. Y/N ?");
                                                        String addMoreProduct = scans.nextLine();
                                                        if (addMoreProduct.equals("N")) {
                                                            //! Merge previous cart and create a final array of cart.
                                                            Products[] previousCartItems = newCart.getListOfCartProducts();


                                                            if (previousCartItems != null) {
                                                                Products[] finalCarts = new Products[25];

                                                                for (int i = 0; i < freshCart; i++) {
                                                                    finalCarts[i] = previousCartItems[i];
                                                                }
                                                                for (int i = 0; i < totalCartNumb; i++) {
                                                                    finalCarts[freshCart + i] = cartItems[i + freshCart];
                                                                }
                                                                //! Final array is saving to Cart.
                                                                newCart.setListOfCartProducts(finalCarts);
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Modified product in your cart and also saved to cart");
                                                            } else {
                                                                newCart.setListOfCartProducts(cartItems);                                       //! Saving all product to cart, Finally.
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Your all new product is saved to cart");
                                                            }
                                                        } else if (addMoreProduct.equals("Y")) {
                                                            System.out.println("OKAY!");
                                                        } else {
                                                            System.out.println("Please enter valid response");
                                                        }
                                                    }
                                                } else if (addToCart.equals("N")) {
                                                    System.out.println("Ok! Thank you for visiting store");
                                                } else System.out.println("Error:\tWrong input!");

                                            } else {
                                                Products[] dummyKidsTShirt = KidsTShirt.getListOfDummyKidsTShirt();
                                                for (int i = 0; i < dummyKidsTShirt.length; i++) {
                                                    System.out.println(dummyKidsTShirt[i].getProductName() + " " + dummyKidsTShirt[i].getProductId() + " " + dummyKidsTShirt[i].getProductCost());
                                                }//! Add product to cart...
                                                System.out.println("Do you want to add any product to cart? Y/N ");
                                                String addToCart = scans.next();
                                                boolean addingToCart = true;
                                                if (addToCart.equals("Y")) {
                                                    scans.nextLine();
                                                    while (addingToCart) {
                                                        System.out.println("Enter product ID?");
                                                        String productIdByUser = scans.nextLine();

                                                        for (int i = 0; i < dummyKidsTShirt.length; i++) {
                                                            if (productIdByUser.equals(dummyKidsTShirt[i].getProductId())) {
                                                                System.out.println(dummyKidsTShirt[i].getProductName() + " is Successfully added to Carts.");

                                                                System.out.println("Total n " + totalCartNumb);
                                                                for (int j = totalCartNumb; j < totalCartNumb + 1; j++) {
                                                                    cartItems[j] = dummyKidsTShirt[i];
                                                                    totalCartNumb++;
                                                                    freshCart++;
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        //! For adding more product to cart.
                                                        System.out.println("Want to add products. Y/N ?");
                                                        String addMoreProduct = scans.nextLine();
                                                        if (addMoreProduct.equals("N")) {
                                                            //! Merge previous cart and create a final array of cart.
                                                            Products[] previousCartItems = newCart.getListOfCartProducts();


                                                            if (previousCartItems != null) {
                                                                Products[] finalCarts = new Products[25];

                                                                for (int i = 0; i < freshCart; i++) {
                                                                    finalCarts[i] = previousCartItems[i];
                                                                }
                                                                for (int i = 0; i < totalCartNumb; i++) {
                                                                    finalCarts[freshCart + i] = cartItems[i + freshCart];
                                                                }
                                                                //! Final array is saving to Cart.
                                                                newCart.setListOfCartProducts(finalCarts);
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Modified product in your cart and also saved to cart");
                                                            } else {
                                                                newCart.setListOfCartProducts(cartItems);                                       //! Saving all product to cart, Finally.
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Your all new product is saved to cart");
                                                            }
                                                        } else if (addMoreProduct.equals("Y")) {
                                                            System.out.println("OKAY!");
                                                        } else {
                                                            System.out.println("Please enter valid response");
                                                        }
                                                    }
                                                } else if (addToCart.equals("N")) {
                                                    System.out.println("Ok! Thank you for visiting store");
                                                } else System.out.println("Error:\tWrong input!");

                                            }


                                        } else if (category == 2) {
                                            System.out.println("These are list of Jeans for kids.");
                                            Products[] kidJeans = KidsJean.getAllKidsJeans();
                                            if (kidJeans != null) {
                                                for (int i = 0; i < kidJeans.length; i++) {
                                                    System.out.println(kidJeans[i].getProductName() + " " + kidJeans[i].getProductId() + " " + kidJeans[i].getProductCost());
                                                }
                                                //! Add product to cart...
                                                System.out.println("Do you want to add any product to cart? Y/N ");
                                                String addToCart = scans.next();
                                                boolean addingToCart = true;
                                                if (addToCart.equals("Y")) {
                                                    scans.nextLine();
                                                    while (addingToCart) {
                                                        System.out.println("Enter product ID?");
                                                        String productIdByUser = scans.nextLine();

                                                        for (int i = 0; i < kidJeans.length; i++) {
                                                            if (productIdByUser.equals(kidJeans[i].getProductId())) {
                                                                System.out.println(kidJeans[i].getProductName() + " is Successfully added to Carts.");

                                                                System.out.println("Total n " + totalCartNumb);
                                                                for (int j = totalCartNumb; j < totalCartNumb + 1; j++) {
                                                                    cartItems[j] = kidJeans[i];
                                                                    totalCartNumb++;
                                                                    freshCart++;
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        //! For adding more product to cart.
                                                        System.out.println("Want to add products. Y/N ?");
                                                        String addMoreProduct = scans.nextLine();
                                                        if (addMoreProduct.equals("N")) {
                                                            //! Merge previous cart and create a final array of cart.
                                                            Products[] previousCartItems = newCart.getListOfCartProducts();


                                                            if (previousCartItems != null) {
                                                                Products[] finalCarts = new Products[25];

                                                                for (int i = 0; i < freshCart; i++) {
                                                                    finalCarts[i] = previousCartItems[i];
                                                                }
                                                                for (int i = 0; i < totalCartNumb; i++) {
                                                                    finalCarts[freshCart + i] = cartItems[i + freshCart];
                                                                }
                                                                //! Final array is saving to Cart.
                                                                newCart.setListOfCartProducts(finalCarts);
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Modified product in your cart and also saved to cart");
                                                            } else {
                                                                newCart.setListOfCartProducts(cartItems);                                       //! Saving all product to cart, Finally.
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Your all new product is saved to cart");
                                                            }
                                                        } else if (addMoreProduct.equals("Y")) {
                                                            System.out.println("OKAY!");
                                                        } else {
                                                            System.out.println("Please enter valid response");
                                                        }
                                                    }
                                                } else if (addToCart.equals("N")) {
                                                    System.out.println("Ok! Thank you for visiting store");
                                                } else System.out.println("Error:\tWrong input!");

                                            } else {
                                                Products[] dummyKidsJeans = KidsJean.getListOfDummyKidsJeans();
                                                for (int i = 0; i < dummyKidsJeans.length; i++) {
                                                    System.out.println(dummyKidsJeans[i].getProductName() + " " + dummyKidsJeans[i].getProductId() + " " + dummyKidsJeans[i].getProductCost());
                                                }//! Add product to cart...
                                                System.out.println("Do you want to add any product to cart? Y/N ");
                                                String addToCart = scans.next();
                                                boolean addingToCart = true;
                                                if (addToCart.equals("Y")) {
                                                    scans.nextLine();
                                                    while (addingToCart) {
                                                        System.out.println("Enter product ID?");
                                                        String productIdByUser = scans.nextLine();

                                                        for (int i = 0; i < dummyKidsJeans.length; i++) {
                                                            if (productIdByUser.equals(dummyKidsJeans[i].getProductId())) {
                                                                System.out.println(dummyKidsJeans[i].getProductName() + " is Successfully added to Carts.");

                                                                System.out.println("Total n " + totalCartNumb);
                                                                for (int j = totalCartNumb; j < totalCartNumb + 1; j++) {
                                                                    cartItems[j] = dummyKidsJeans[i];
                                                                    totalCartNumb++;
                                                                    freshCart++;
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        //! For adding more product to cart.
                                                        System.out.println("Want to add products. Y/N ?");
                                                        String addMoreProduct = scans.nextLine();
                                                        if (addMoreProduct.equals("N")) {
                                                            //! Merge previous cart and create a final array of cart.
                                                            Products[] previousCartItems = newCart.getListOfCartProducts();


                                                            if (previousCartItems != null) {
                                                                Products[] finalCarts = new Products[25];

                                                                for (int i = 0; i < freshCart; i++) {
                                                                    finalCarts[i] = previousCartItems[i];
                                                                }
                                                                for (int i = 0; i < totalCartNumb; i++) {
                                                                    finalCarts[freshCart + i] = cartItems[i + freshCart];
                                                                }
                                                                //! Final array is saving to Cart.
                                                                newCart.setListOfCartProducts(finalCarts);
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Modified product in your cart and also saved to cart");
                                                            } else {
                                                                newCart.setListOfCartProducts(cartItems);                                       //! Saving all product to cart, Finally.
                                                                newCustomer.setAllCustomerCart(newCart);                                        //* New cart is saved in "Cart allCart"
                                                                addingToCart = false;
                                                                System.out.println("Congrats! Your all new product is saved to cart");
                                                            }
                                                        } else if (addMoreProduct.equals("Y")) {
                                                            System.out.println("OKAY!");
                                                        } else {
                                                            System.out.println("Please enter valid response");
                                                        }
                                                    }
                                                } else if (addToCart.equals("N")) {
                                                    System.out.println("Ok! Thank you for visiting store");
                                                } else System.out.println("Error:\tWrong input!");

                                            }


                                        } else if (category == 3) {
                                            subCategory = false;
                                        } else System.out.println("Error:\tPlease select valid category.");
                                    }
                                } else if (choicePerson == 4) {
                                    System.out.println("Going back to Category");
                                    categoryLoop = false;
                                } else System.out.println("Error:\tEnter valid choice!");
                            }
                        } else if (choice == 4) {
                            System.out.println("You can contact us on store@ecommerce.com ");
                        } else if (choice == 5) {
                            customerPage = false;
                        } else System.out.println("Enter your choice again.");
                    }
                }
            } else if (userEntry == 2) {
                boolean modifySeller = true;
                System.out.println("Welcome to our Seller page");
                while (modifySeller) {
                    System.out.println("Do you want to modify seller product page.\tY/N ?");
                    scans.nextLine();
                    String choice = scans.nextLine();
                    if (choice.equals("Y")) {
                        boolean addingNewProduct = true;
                        int numOfProductAdded = 0;
                        while (addingNewProduct) {
                            System.out.println("1. Add products\t 2. Remove Products \t 3. View Seller products \t 4.Add Products by Category  \t5. Exits");
                            int wantedTo = scans.nextInt();
                            if (wantedTo == 1) {
                                System.out.println("Total product available: " + numOfProductAdded);
                                System.out.println("Number of product you want to add.");
                                int productNum = scans.nextInt();
                                Products[] listOfProducts = new Products[productNum];

                                for (int i = 0; i < productNum; i++) {
                                    numOfProductAdded++;
                                    scans.nextLine();

                                    System.out.println("Enter Product Name.");
                                    String productName = scans.nextLine();
                                    System.out.println("Enter Product ID.");
                                    String productId = scans.nextLine();
                                    System.out.println("Enter Product Price.");
                                    double productCost = scans.nextInt();
                                    //! Adding new product to page.
                                    Products newProduct = new Products();
                                    newProduct.setProductId(productId);
                                    newProduct.setProductName(productName);
                                    newProduct.setProductCost(productCost);
                                    //! Adding product according to index number.
                                    listOfProducts[i] = newProduct;
                                    //! Message after product adding done.
                                    System.out.println("Product:\t" + listOfProducts[i].getProductName() + " is added successfully\n");

                                    //! After adding last product ==> All product saving in Products array.
                                    if (i == productNum - 1) {
                                        Products[] previousAddedProduct = seller.getProductListed();
                                        if (previousAddedProduct != null) {
                                            Products[] newListOfProducts = new Products[previousAddedProduct.length + listOfProducts.length];
                                            for (int p = 0; p < previousAddedProduct.length; p++) {
                                                newListOfProducts[p] = previousAddedProduct[p];
                                            }
                                            for (int l = 0; l < listOfProducts.length; l++) {
                                                newListOfProducts[previousAddedProduct.length + l] = listOfProducts[l];
                                            }
                                            seller.setProductListed(newListOfProducts);
                                        } else {
                                            seller.setProductListed(listOfProducts);
                                        }

                                        // * Saving all products....
                                        //! Showing all added product details.
                                        Products[] allProducts = seller.getProductListed();

                                        for (int j = 0; j < allProducts.length; j++) {
                                            System.out.println("Product " + (j + 1) + ":\t" + allProducts[j].getProductName() + "\t" + allProducts[j].getProductId() + "\t" + allProducts[j].getProductCost());
                                        }
                                    }
                                }
                                System.out.println(numOfProductAdded + " product is added Successfully");

                            } else if (wantedTo == 2) {
                                Products[] allProducts = seller.getProductListed();
                                if (allProducts == null) {
                                    System.out.println("Sorry! No any product to remove.");
                                } else {
                                    System.out.println("Ok fine! lets remove products");
                                    System.out.println("Enter your product ID");
                                    scans.nextLine();
                                    String removeProductId = scans.nextLine();
                                    for (int i = 0; i < allProducts.length; i++) {
                                        if (Objects.equals(removeProductId, allProducts[i].getProductId())) {
                                            System.out.println("Do you want to remove Product Name:" + "\t" + allProducts[i].getProductName() + " ?\tY/N");
                                            String removeProduct = scans.nextLine();
                                            if (removeProduct.equals("Y")) {
                                                Products[] revisedProducts = new Products[allProducts.length - 1];
                                                for (int j = i; j < allProducts.length - 1; j++) {
                                                    allProducts[j] = allProducts[j + 1];
                                                }
                                                for (int k = 0; k < revisedProducts.length; k++) {
                                                    revisedProducts[k] = allProducts[k];
                                                }
                                                if (revisedProducts.length != 0) {
                                                    seller.setProductListed(revisedProducts);
                                                    System.out.println("Product deleting successful...");
                                                } else {
                                                    seller.setProductListed(null);
                                                }
                                            } else System.out.println("Fine! Product removal is canceled.");
                                        }
                                    }
                                }


                            } else if (wantedTo == 3) {
                                System.out.println("All Products list");
                                Products[] allProducts = seller.getProductListed();
                                if (allProducts == null) {
                                    System.out.println("Oops:\tNo product is available right now");

                                } else {
                                    for (int j = 0; j < allProducts.length; j++) {
                                        System.out.println("Product " + (j + 1) + ":\t" + allProducts[j].getProductName() + "\t" + allProducts[j].getProductId() + "\t" + allProducts[j].getProductCost());
                                    }

                                    System.out.println("Do you want to publish all Products?\tY/N");
                                    scans.nextLine();
                                    String publish = scans.nextLine();
                                    if (publish.equals("Y")) {
                                        seller.setPublishProduct(allProducts);
                                        publishRequest = true;
                                        System.out.println("Your request for publishing Products is forwarded to Administrator.");
                                    } else {
                                        System.out.println("Ok! Do it later.");
                                    }
                                }
                                //! Adding product according to Sub-Category.
                            } else if (wantedTo == 4) {
                                Category[] subCategorys = newCategory.getSubCategory();
                                for (int i = 0; i < subCategorys.length; i++) {
                                    System.out.print((i + 1) + ". " + subCategorys[i].getCategoryName() + "\t");
                                }
                                System.out.println("\t4. Back");

                                int choicePerson = scans.nextInt();
                                if (choicePerson == 1) {
                                    boolean subCategory = true;
                                    while (subCategory) {
                                        Category[] mens = subCategorys[0].getMensCategory();
                                        for (int i = 0; i < mens.length; i++) {
                                            System.out.print((i + 1) + ". " + mens[i].getCategoryName() + "\t");
                                        }
                                        System.out.println("\t5.Back");
                                        int category = scans.nextInt();
                                        //! Adding new pants
                                        if (category == 1) {
                                            System.out.println("Number of pant want to add");
                                            int numOfPant = scans.nextInt();

                                            Products[] listOfAllNewPants = new Products[numOfPant];

                                            //! Adding new pant in All pants.
                                            for (int i = 0; i < numOfPant; i++) {
                                                Products newPants = new Products();
                                                scans.nextLine();
                                                System.out.println("Enter pant name");
                                                String pantName = scans.nextLine();
                                                newPants.setProductName(pantName);
                                                System.out.println("Enter pant ID");
                                                String pantId = scans.nextLine();
                                                newPants.setProductId(pantId);
                                                System.out.println("Enter pant cost");
                                                int pantCost = scans.nextInt();
                                                newPants.setProductCost(pantCost);

                                                listOfAllNewPants[i] = newPants;

                                                //! After adding last product ==> All product saving in Products array.
                                                if (i == numOfPant - 1) {
                                                    Products[] dummyPants = Pant.getListOfDummyPants();
                                                    Products[] previousAddedProduct = Pant.getListOfNewPants();

                                                    if (previousAddedProduct != null) {
                                                        Products[] updatePrevious = new Products[previousAddedProduct.length + numOfPant];
                                                        Products[] allPants = new Products[previousAddedProduct.length + dummyPants.length + numOfPant];

                                                        for (int d = 0; d < dummyPants.length; d++) {
                                                            allPants[d] = dummyPants[d];
                                                        }
                                                        for (int p = 0; p < previousAddedProduct.length; p++) {
                                                            updatePrevious[p] = previousAddedProduct[p];
                                                            allPants[dummyPants.length + p] = previousAddedProduct[p];
                                                        }
                                                        for (int n = 0; n < listOfAllNewPants.length; n++) {
                                                            updatePrevious[previousAddedProduct.length + n] = listOfAllNewPants[n];
                                                            allPants[dummyPants.length + previousAddedProduct.length + n] = listOfAllNewPants[n];
                                                        }
                                                        Pant.setListOfNewPants(updatePrevious);
                                                        Pant.setAllPants(allPants);

                                                    } else {
                                                        Products[] allPants = new Products[dummyPants.length + numOfPant];
                                                        for (int d = 0; d < dummyPants.length; d++) {
                                                            allPants[d] = dummyPants[d];
                                                        }
                                                        for (int n = 0; n < listOfAllNewPants.length; n++) {
                                                            allPants[dummyPants.length + n] = listOfAllNewPants[n];
                                                        }
                                                        Pant.setListOfNewPants(listOfAllNewPants);
                                                        Pant.setAllPants(allPants);
                                                    }

                                                    //! Showing all added product details.
                                                    Products[] allProducts = Pant.getAllPants();
                                                    for (int j = 0; j < allProducts.length; j++) {
                                                        System.out.println("Product " + (j + 1) + ":\t" + allProducts[j].getProductName() + "\t" + allProducts[j].getProductId() + "\t" + allProducts[j].getProductCost());
                                                    }

                                                }
                                            }
                                        } else if (category == 2) {
                                            System.out.println("Number of Shirt want to add");
                                            int numOfShirt = scans.nextInt();

                                            Products[] listOfAllNewShirt = new Products[numOfShirt];

                                            //! Adding new pant in All pants.
                                            for (int i = 0; i < numOfShirt; i++) {
                                                Products newShirts = new Products();
                                                scans.nextLine();
                                                System.out.println("Enter Shirt name");
                                                String shirtName = scans.nextLine();
                                                newShirts.setProductName(shirtName);
                                                System.out.println("Enter Shirt ID");
                                                String shirtId = scans.nextLine();
                                                newShirts.setProductId(shirtId);
                                                System.out.println("Enter pant cost");
                                                int shirtCost = scans.nextInt();
                                                newShirts.setProductCost(shirtCost);

                                                listOfAllNewShirt[i] = newShirts;

                                                //! After adding last product ==> All product saving in Products array.
                                                if (i == numOfShirt - 1) {
                                                    Products[] dummyShirt = Shirt.getListOfDummyShirts();
                                                    Products[] previousAddedProduct = Shirt.getListOfNewShirts();

                                                    if (previousAddedProduct != null) {
                                                        Products[] updatePrevious = new Products[previousAddedProduct.length + numOfShirt];
                                                        Products[] allShirts = new Products[previousAddedProduct.length + dummyShirt.length + numOfShirt];

                                                        for (int d = 0; d < dummyShirt.length; d++) {
                                                            allShirts[d] = dummyShirt[d];
                                                        }
                                                        for (int p = 0; p < previousAddedProduct.length; p++) {
                                                            updatePrevious[p] = previousAddedProduct[p];
                                                            allShirts[dummyShirt.length + p] = previousAddedProduct[p];
                                                        }
                                                        for (int n = 0; n < listOfAllNewShirt.length; n++) {
                                                            updatePrevious[previousAddedProduct.length + n] = listOfAllNewShirt[n];
                                                            allShirts[dummyShirt.length + previousAddedProduct.length + n] = listOfAllNewShirt[n];
                                                        }
                                                        Shirt.setListOfNewShirts(updatePrevious);
                                                        Shirt.setAllShirts(allShirts);


                                                    } else {
                                                        Products[] allShirts = new Products[dummyShirt.length + numOfShirt];
                                                        for (int d = 0; d < dummyShirt.length; d++) {
                                                            allShirts[d] = dummyShirt[d];
                                                        }
                                                        for (int n = 0; n < listOfAllNewShirt.length; n++) {
                                                            allShirts[dummyShirt.length + n] = listOfAllNewShirt[n];
                                                        }
                                                        Shirt.setListOfNewShirts(listOfAllNewShirt);
                                                        Shirt.setAllShirts(allShirts);
                                                    }

                                                    //! Showing all added product details.
                                                    Products[] allProducts = Shirt.getAllShirts();
                                                    for (int j = 0; j < allProducts.length; j++) {
                                                        System.out.println("Product " + (j + 1) + ":\t" + allProducts[j].getProductName() + "\t" + allProducts[j].getProductId() + "\t" + allProducts[j].getProductCost());
                                                    }
                                                }
                                            }
                                        } else if (category == 3) {
                                            System.out.println("Number of Jeans want to add");
                                            int numOfJeans = scans.nextInt();

                                            Products[] listOfAllNewJeans = new Products[numOfJeans];

                                            //! Adding new Jeans in All Jeans.
                                            for (int i = 0; i < numOfJeans; i++) {
                                                Products newJeans = new Products();
                                                scans.nextLine();
                                                System.out.println("Enter Jeans name");
                                                String JeansName = scans.nextLine();
                                                newJeans.setProductName(JeansName);
                                                System.out.println("Enter Jeans ID");
                                                String JeansId = scans.nextLine();
                                                newJeans.setProductId(JeansId);
                                                System.out.println("Enter Jeans cost");
                                                int JeansCost = scans.nextInt();
                                                newJeans.setProductCost(JeansCost);

                                                listOfAllNewJeans[i] = newJeans;

                                                //! After adding last product ==> All product saving in Products array.
                                                if (i == numOfJeans - 1) {
                                                    Products[] dummyJeans = MensJean.getListOfDummyJeans();
                                                    Products[] previousAddedProduct = MensJean.getListOfNewJeans();

                                                    if (previousAddedProduct != null) {
                                                        Products[] updatePrevious = new Products[previousAddedProduct.length + numOfJeans];
                                                        Products[] allJeans = new Products[previousAddedProduct.length + dummyJeans.length + numOfJeans];

                                                        for (int d = 0; d < dummyJeans.length; d++) {
                                                            allJeans[d] = dummyJeans[d];
                                                        }
                                                        for (int p = 0; p < previousAddedProduct.length; p++) {
                                                            updatePrevious[p] = previousAddedProduct[p];
                                                            allJeans[dummyJeans.length + p] = previousAddedProduct[p];
                                                        }
                                                        for (int n = 0; n < listOfAllNewJeans.length; n++) {
                                                            updatePrevious[previousAddedProduct.length + n] = listOfAllNewJeans[n];
                                                            allJeans[dummyJeans.length + previousAddedProduct.length + n] = listOfAllNewJeans[n];
                                                        }
                                                        MensJean.setListOfNewJeans(updatePrevious);
                                                        MensJean.setAllJeans(allJeans);

                                                    } else {
                                                        Products[] allJeans = new Products[dummyJeans.length + numOfJeans];
                                                        for (int d = 0; d < dummyJeans.length; d++) {
                                                            allJeans[d] = dummyJeans[d];
                                                        }
                                                        for (int n = 0; n < listOfAllNewJeans.length; n++) {
                                                            allJeans[dummyJeans.length + n] = listOfAllNewJeans[n];
                                                        }
                                                        MensJean.setListOfNewJeans(listOfAllNewJeans);
                                                        MensJean.setAllJeans(allJeans);
                                                    }

                                                    //! Showing all added product details.
                                                    Products[] allProducts = MensJean.getAllJeans();
                                                    for (int j = 0; j < allProducts.length; j++) {
                                                        System.out.println("Product " + (j + 1) + ":\t" + allProducts[j].getProductName() + "\t" + allProducts[j].getProductId() + "\t" + allProducts[j].getProductCost());
                                                    }

                                                }
                                            }
                                        } else if (category == 4) {
                                            System.out.println("Number of TShirt want to add");
                                            int numOfTShirts = scans.nextInt();

                                            Products[] listOfAllNewTShirt = new Products[numOfTShirts];

                                            //! Adding new TShirts in All TShirt.
                                            for (int i = 0; i < numOfTShirts; i++) {
                                                Products newTShirt = new Products();
                                                scans.nextLine();
                                                System.out.println("Enter TShirts name");
                                                String TShirtsName = scans.nextLine();
                                                newTShirt.setProductName(TShirtsName);
                                                System.out.println("Enter TShirts ID");
                                                String TShirtsId = scans.nextLine();
                                                newTShirt.setProductId(TShirtsId);
                                                System.out.println("Enter TShirts cost");
                                                int TShirtsCost = scans.nextInt();
                                                newTShirt.setProductCost(TShirtsCost);

                                                listOfAllNewTShirt[i] = newTShirt;

                                                //! After adding last product ==> All product saving in Products array.
                                                if (i == numOfTShirts - 1) {
                                                    Products[] dummyTShirt = TShirts.getListOfDummyTShirt();
                                                    Products[] previousAddedProduct = TShirts.getListOfNewTShirts();

                                                    if (previousAddedProduct != null) {
                                                        Products[] updatePrevious = new Products[previousAddedProduct.length + numOfTShirts];
                                                        Products[] allTShirt = new Products[previousAddedProduct.length + dummyTShirt.length + numOfTShirts];

                                                        for (int d = 0; d < dummyTShirt.length; d++) {
                                                            allTShirt[d] = dummyTShirt[d];
                                                        }
                                                        for (int p = 0; p < previousAddedProduct.length; p++) {
                                                            updatePrevious[p] = previousAddedProduct[p];
                                                            allTShirt[dummyTShirt.length + p] = previousAddedProduct[p];
                                                        }
                                                        for (int n = 0; n < listOfAllNewTShirt.length; n++) {
                                                            updatePrevious[previousAddedProduct.length + n] = listOfAllNewTShirt[n];
                                                            allTShirt[dummyTShirt.length + previousAddedProduct.length + n] = listOfAllNewTShirt[n];
                                                        }
                                                        TShirts.setListOfNewTShirts(updatePrevious);
                                                        TShirts.setAllTShirts(allTShirt);

                                                    } else {
                                                        Products[] allTShirt = new Products[dummyTShirt.length + numOfTShirts];
                                                        for (int d = 0; d < dummyTShirt.length; d++) {
                                                            allTShirt[d] = dummyTShirt[d];
                                                        }
                                                        for (int n = 0; n < listOfAllNewTShirt.length; n++) {
                                                            allTShirt[dummyTShirt.length + n] = listOfAllNewTShirt[n];
                                                        }
                                                        TShirts.setListOfNewTShirts(listOfAllNewTShirt);
                                                        TShirts.setAllTShirts(allTShirt);
                                                    }

                                                    //! Showing all added product details.
                                                    Products[] allProducts = TShirts.getAllTShirts();
                                                    for (int j = 0; j < allProducts.length; j++) {
                                                        System.out.println("Product " + (j + 1) + ":\t" + allProducts[j].getProductName() + "\t" + allProducts[j].getProductId() + "\t" + allProducts[j].getProductCost());
                                                    }
                                                }
                                            }

                                        } else if (category == 5) {
                                            subCategory = false;
                                        } else System.out.println("Error:\tPlease select valid category.");
                                    }
                                } else if (choicePerson == 2) {
                                    boolean subCategory = true;
                                    while (subCategory) {
                                        Category[] womens = subCategorys[1].getWomenCategory();
                                        for (int i = 0; i < womens.length; i++) {
                                            System.out.print((i + 1) + ". " + womens[i].getCategoryName() + "\t");
                                        }
                                        System.out.println("\t4.Back");
                                        int category = scans.nextInt();
                                        if (category == 1) {
                                            System.out.println("Number of Top want to add");
                                            int numOfTop = scans.nextInt();
                                            Products[] listOfAllNewTops = new Products[numOfTop];

                                            //! Adding new Top in All Tops.
                                            for (int i = 0; i < numOfTop; i++) {
                                                Products newTops = new Products();
                                                scans.nextLine();
                                                System.out.println("Enter Top name");
                                                String TopName = scans.nextLine();
                                                newTops.setProductName(TopName);
                                                System.out.println("Enter Top ID");
                                                String TopId = scans.nextLine();
                                                newTops.setProductId(TopId);
                                                System.out.println("Enter Top cost");
                                                int TopCost = scans.nextInt();
                                                newTops.setProductCost(TopCost);

                                                listOfAllNewTops[i] = newTops;

                                                //! After adding last product ==> All product saving in Products array.
                                                if (i == numOfTop - 1) {
                                                    Products[] dummyTops = Top.getListOfDummyTops();
                                                    Products[] previousAddedProduct = Top.getListOfNewTops();

                                                    if (previousAddedProduct != null) {
                                                        Products[] updatePrevious = new Products[previousAddedProduct.length + numOfTop];
                                                        Products[] allTops = new Products[previousAddedProduct.length + dummyTops.length + numOfTop];

                                                        for (int d = 0; d < dummyTops.length; d++) {
                                                            allTops[d] = dummyTops[d];
                                                        }
                                                        for (int p = 0; p < previousAddedProduct.length; p++) {
                                                            updatePrevious[p] = previousAddedProduct[p];
                                                            allTops[dummyTops.length + p] = previousAddedProduct[p];
                                                        }
                                                        for (int n = 0; n < listOfAllNewTops.length; n++) {
                                                            updatePrevious[previousAddedProduct.length + n] = listOfAllNewTops[n];
                                                            allTops[dummyTops.length + previousAddedProduct.length + n] = listOfAllNewTops[n];
                                                        }
                                                        Top.setListOfNewTops(updatePrevious);
                                                        Top.setAllTops(allTops);

                                                    } else {
                                                        Products[] allTops = new Products[dummyTops.length + numOfTop];
                                                        for (int d = 0; d < dummyTops.length; d++) {
                                                            allTops[d] = dummyTops[d];
                                                        }
                                                        for (int n = 0; n < listOfAllNewTops.length; n++) {
                                                            allTops[dummyTops.length + n] = listOfAllNewTops[n];
                                                        }
                                                        Top.setListOfNewTops(listOfAllNewTops);
                                                        Top.setAllTops(allTops);
                                                    }

                                                    //! Showing all added product details.
                                                    Products[] allProducts = Top.getAllTops();
                                                    for (int j = 0; j < allProducts.length; j++) {
                                                        System.out.println("Product " + (j + 1) + ":\t" + allProducts[j].getProductName() + "\t" + allProducts[j].getProductId() + "\t" + allProducts[j].getProductCost());
                                                    }

                                                }
                                            }

                                        } else if (category == 2) {
                                            System.out.println("Number of Women Jeans want to add");
                                            int numOfWomenJean = scans.nextInt();

                                            Products[] listOfAllNewWomenJeans = new Products[numOfWomenJean];

                                            //! Adding new WomenJean in All WomenJeans.
                                            for (int i = 0; i < numOfWomenJean; i++) {
                                                Products newWomenJeans = new Products();
                                                scans.nextLine();
                                                System.out.println("Enter WomenJean name");
                                                String WomenJeanName = scans.nextLine();
                                                newWomenJeans.setProductName(WomenJeanName);
                                                System.out.println("Enter WomenJean ID");
                                                String WomenJeanId = scans.nextLine();
                                                newWomenJeans.setProductId(WomenJeanId);
                                                System.out.println("Enter WomenJean cost");
                                                int WomenJeanCost = scans.nextInt();
                                                newWomenJeans.setProductCost(WomenJeanCost);

                                                listOfAllNewWomenJeans[i] = newWomenJeans;

                                                //! After adding last product ==> All product saving in Products array.
                                                if (i == numOfWomenJean - 1) {
                                                    Products[] dummyWomenJeans = WomenJean.getListOfDummyWomenJeans();
                                                    Products[] previousAddedProduct = WomenJean.getListOfNewWomenJeans();

                                                    if (previousAddedProduct != null) {
                                                        Products[] updatePrevious = new Products[previousAddedProduct.length + numOfWomenJean];
                                                        Products[] allWomenJeans = new Products[previousAddedProduct.length + dummyWomenJeans.length + numOfWomenJean];

                                                        for (int d = 0; d < dummyWomenJeans.length; d++) {
                                                            allWomenJeans[d] = dummyWomenJeans[d];
                                                        }
                                                        for (int p = 0; p < previousAddedProduct.length; p++) {
                                                            updatePrevious[p] = previousAddedProduct[p];
                                                            allWomenJeans[dummyWomenJeans.length + p] = previousAddedProduct[p];
                                                        }
                                                        for (int n = 0; n < listOfAllNewWomenJeans.length; n++) {
                                                            updatePrevious[previousAddedProduct.length + n] = listOfAllNewWomenJeans[n];
                                                            allWomenJeans[dummyWomenJeans.length + previousAddedProduct.length + n] = listOfAllNewWomenJeans[n];
                                                        }
                                                        WomenJean.setListOfNewWomenJeans(updatePrevious);
                                                        WomenJean.setAllWomenJeans(allWomenJeans);

                                                    } else {
                                                        Products[] allWomenJeans = new Products[dummyWomenJeans.length + numOfWomenJean];
                                                        for (int d = 0; d < dummyWomenJeans.length; d++) {
                                                            allWomenJeans[d] = dummyWomenJeans[d];
                                                        }
                                                        for (int n = 0; n < listOfAllNewWomenJeans.length; n++) {
                                                            allWomenJeans[dummyWomenJeans.length + n] = listOfAllNewWomenJeans[n];
                                                        }
                                                        WomenJean.setListOfNewWomenJeans(listOfAllNewWomenJeans);
                                                        WomenJean.setAllWomenJeans(allWomenJeans);
                                                    }

                                                    //! Showing all added product details.
                                                    Products[] allProducts = WomenJean.getAllWomenJeans();
                                                    for (int j = 0; j < allProducts.length; j++) {
                                                        System.out.println("Product " + (j + 1) + ":\t" + allProducts[j].getProductName() + "\t" + allProducts[j].getProductId() + "\t" + allProducts[j].getProductCost());
                                                    }

                                                }
                                            }

                                        } else if (category == 3) {
                                            System.out.println("Number of Kurtis want to add");
                                            int numOfKurti = scans.nextInt();

                                            Products[] listOfAllNewKurtis = new Products[numOfKurti];

                                            //! Adding new Kurti in All Kurtis.
                                            for (int i = 0; i < numOfKurti; i++) {
                                                Products newKurtis = new Products();
                                                scans.nextLine();
                                                System.out.println("Enter Kurti name");
                                                String KurtiName = scans.nextLine();
                                                newKurtis.setProductName(KurtiName);
                                                System.out.println("Enter Kurti ID");
                                                String KurtiId = scans.nextLine();
                                                newKurtis.setProductId(KurtiId);
                                                System.out.println("Enter Kurti cost");
                                                int KurtiCost = scans.nextInt();
                                                newKurtis.setProductCost(KurtiCost);

                                                listOfAllNewKurtis[i] = newKurtis;

                                                //! After adding last product ==> All product saving in Products array.
                                                if (i == numOfKurti - 1) {
                                                    Products[] dummyKurtis = Kurti.getListOfDummyKurtis();
                                                    Products[] previousAddedProduct = Kurti.getListOfNewKurtis();

                                                    if (previousAddedProduct != null) {
                                                        Products[] updatePrevious = new Products[previousAddedProduct.length + numOfKurti];
                                                        Products[] allKurtis = new Products[previousAddedProduct.length + dummyKurtis.length + numOfKurti];

                                                        for (int d = 0; d < dummyKurtis.length; d++) {
                                                            allKurtis[d] = dummyKurtis[d];
                                                        }
                                                        for (int p = 0; p < previousAddedProduct.length; p++) {
                                                            updatePrevious[p] = previousAddedProduct[p];
                                                            allKurtis[dummyKurtis.length + p] = previousAddedProduct[p];
                                                        }
                                                        for (int n = 0; n < listOfAllNewKurtis.length; n++) {
                                                            updatePrevious[previousAddedProduct.length + n] = listOfAllNewKurtis[n];
                                                            allKurtis[dummyKurtis.length + previousAddedProduct.length + n] = listOfAllNewKurtis[n];
                                                        }
                                                        Kurti.setListOfNewKurtis(updatePrevious);
                                                        Kurti.setAllKurtis(allKurtis);

                                                    } else {
                                                        Products[] allKurtis = new Products[dummyKurtis.length + numOfKurti];
                                                        for (int d = 0; d < dummyKurtis.length; d++) {
                                                            allKurtis[d] = dummyKurtis[d];
                                                        }
                                                        for (int n = 0; n < listOfAllNewKurtis.length; n++) {
                                                            allKurtis[dummyKurtis.length + n] = listOfAllNewKurtis[n];
                                                        }
                                                        Kurti.setListOfNewKurtis(listOfAllNewKurtis);
                                                        Kurti.setAllKurtis(allKurtis);
                                                    }

                                                    //! Showing all added product details.
                                                    Products[] allProducts = Kurti.getAllKurtis();
                                                    for (int j = 0; j < allProducts.length; j++) {
                                                        System.out.println("Product " + (j + 1) + ":\t" + allProducts[j].getProductName() + "\t" + allProducts[j].getProductId() + "\t" + allProducts[j].getProductCost());
                                                    }

                                                }
                                            }

                                        } else if (category == 4) {
                                            subCategory = false;
                                        } else System.out.println("Error:\tPlease select valid category.");
                                    }
                                } else if (choicePerson == 3) {
                                    boolean subCategory = true;
                                    while (subCategory) {
                                        Category[] kids = subCategorys[2].getKidCategory();
                                        for (int i = 0; i < kids.length; i++) {
                                            System.out.print((i + 1) + ". " + kids[i].getCategoryName() + "\t");
                                        }
                                        System.out.println("\t3.Back");
                                        int category = scans.nextInt();
                                        if (category == 1) {
                                            System.out.println("Number of Kids Jeans want to add");
                                            int numOfKidsJean = scans.nextInt();

                                            Products[] listOfAllNewKidsJeans = new Products[numOfKidsJean];

                                            //! Adding new KidsJean in All KidsJeans.
                                            for (int i = 0; i < numOfKidsJean; i++) {
                                                Products newKidsJeans = new Products();
                                                scans.nextLine();
                                                System.out.println("Enter KidsJean name");
                                                String KidsJeanName = scans.nextLine();
                                                newKidsJeans.setProductName(KidsJeanName);
                                                System.out.println("Enter KidsJean ID");
                                                String KidsJeanId = scans.nextLine();
                                                newKidsJeans.setProductId(KidsJeanId);
                                                System.out.println("Enter KidsJean cost");
                                                int KidsJeanCost = scans.nextInt();
                                                newKidsJeans.setProductCost(KidsJeanCost);

                                                listOfAllNewKidsJeans[i] = newKidsJeans;

                                                //! After adding last product ==> All product saving in Products array.
                                                if (i == numOfKidsJean - 1) {
                                                    Products[] dummyKidsJeans = KidsJean.getListOfDummyKidsJeans();
                                                    Products[] previousAddedProduct = KidsJean.getListOfNewKidsJeans();

                                                    if (previousAddedProduct != null) {
                                                        Products[] updatePrevious = new Products[previousAddedProduct.length + numOfKidsJean];
                                                        Products[] allKidsJeans = new Products[previousAddedProduct.length + dummyKidsJeans.length + numOfKidsJean];

                                                        for (int d = 0; d < dummyKidsJeans.length; d++) {
                                                            allKidsJeans[d] = dummyKidsJeans[d];
                                                        }
                                                        for (int p = 0; p < previousAddedProduct.length; p++) {
                                                            updatePrevious[p] = previousAddedProduct[p];
                                                            allKidsJeans[dummyKidsJeans.length + p] = previousAddedProduct[p];
                                                        }
                                                        for (int n = 0; n < listOfAllNewKidsJeans.length; n++) {
                                                            updatePrevious[previousAddedProduct.length + n] = listOfAllNewKidsJeans[n];
                                                            allKidsJeans[dummyKidsJeans.length + previousAddedProduct.length + n] = listOfAllNewKidsJeans[n];
                                                        }
                                                        KidsJean.setListOfNewKidsJeans(updatePrevious);
                                                        KidsJean.setAllKidsJeans(allKidsJeans);

                                                    } else {
                                                        Products[] allKidsJeans = new Products[dummyKidsJeans.length + numOfKidsJean];
                                                        for (int d = 0; d < dummyKidsJeans.length; d++) {
                                                            allKidsJeans[d] = dummyKidsJeans[d];
                                                        }
                                                        for (int n = 0; n < listOfAllNewKidsJeans.length; n++) {
                                                            allKidsJeans[dummyKidsJeans.length + n] = listOfAllNewKidsJeans[n];
                                                        }
                                                        KidsJean.setListOfNewKidsJeans(listOfAllNewKidsJeans);
                                                        KidsJean.setAllKidsJeans(allKidsJeans);
                                                    }

                                                    //! Showing all added product details.
                                                    Products[] allProducts = KidsJean.getAllKidsJeans();
                                                    for (int j = 0; j < allProducts.length; j++) {
                                                        System.out.println("Product " + (j + 1) + ":\t" + allProducts[j].getProductName() + "\t" + allProducts[j].getProductId() + "\t" + allProducts[j].getProductCost());
                                                    }

                                                }
                                            }
                                        } else if (category == 2) {
                                            System.out.println("Number of Kids TShirt want to add");
                                            int numOfKidsTShirt = scans.nextInt();

                                            Products[] listOfAllNewKidsTShirts = new Products[numOfKidsTShirt];

                                            //! Adding new KidsTShirt in All KidsTShirts.
                                            for (int i = 0; i < numOfKidsTShirt; i++) {
                                                Products newKidsTShirts = new Products();
                                                scans.nextLine();
                                                System.out.println("Enter KidsTShirt name");
                                                String KidsTShirtName = scans.nextLine();
                                                newKidsTShirts.setProductName(KidsTShirtName);
                                                System.out.println("Enter KidsTShirt ID");
                                                String KidsTShirtId = scans.nextLine();
                                                newKidsTShirts.setProductId(KidsTShirtId);
                                                System.out.println("Enter KidsTShirt cost");
                                                int KidsTShirtCost = scans.nextInt();
                                                newKidsTShirts.setProductCost(KidsTShirtCost);

                                                listOfAllNewKidsTShirts[i] = newKidsTShirts;

                                                //! After adding last product ==> All product saving in Products array.
                                                if (i == numOfKidsTShirt - 1) {
                                                    Products[] dummyKidsTShirts = KidsTShirt.getListOfDummyKidsTShirt();
                                                    Products[] previousAddedProduct = KidsTShirt.getListOfNewKidsTShirt();

                                                    if (previousAddedProduct != null) {
                                                        Products[] updatePrevious = new Products[previousAddedProduct.length + numOfKidsTShirt];
                                                        Products[] allKidsTShirts = new Products[previousAddedProduct.length + dummyKidsTShirts.length + numOfKidsTShirt];

                                                        for (int d = 0; d < dummyKidsTShirts.length; d++) {
                                                            allKidsTShirts[d] = dummyKidsTShirts[d];
                                                        }
                                                        for (int p = 0; p < previousAddedProduct.length; p++) {
                                                            updatePrevious[p] = previousAddedProduct[p];
                                                            allKidsTShirts[dummyKidsTShirts.length + p] = previousAddedProduct[p];
                                                        }
                                                        for (int n = 0; n < listOfAllNewKidsTShirts.length; n++) {
                                                            updatePrevious[previousAddedProduct.length + n] = listOfAllNewKidsTShirts[n];
                                                            allKidsTShirts[dummyKidsTShirts.length + previousAddedProduct.length + n] = listOfAllNewKidsTShirts[n];
                                                        }
                                                        KidsTShirt.setListOfNewKidsTShirt(updatePrevious);
                                                        KidsTShirt.setAllKidsTShirt(allKidsTShirts);

                                                    } else {
                                                        Products[] allKidsTShirts = new Products[dummyKidsTShirts.length + numOfKidsTShirt];
                                                        for (int d = 0; d < dummyKidsTShirts.length; d++) {
                                                            allKidsTShirts[d] = dummyKidsTShirts[d];
                                                        }
                                                        for (int n = 0; n < listOfAllNewKidsTShirts.length; n++) {
                                                            allKidsTShirts[dummyKidsTShirts.length + n] = listOfAllNewKidsTShirts[n];
                                                        }
                                                        KidsTShirt.setListOfNewKidsTShirt(listOfAllNewKidsTShirts);
                                                        KidsTShirt.setAllKidsTShirt(allKidsTShirts);
                                                    }

                                                    //! Showing all added product details.
                                                    Products[] allProducts = KidsTShirt.getAllKidsTShirt();
                                                    for (int j = 0; j < allProducts.length; j++) {
                                                        System.out.println("Product " + (j + 1) + ":\t" + allProducts[j].getProductName() + "\t" + allProducts[j].getProductId() + "\t" + allProducts[j].getProductCost());
                                                    }
                                                }
                                            }
                                        } else if (category == 3) {
                                            subCategory = false;
                                        } else System.out.println("Error:\tPlease select valid category.");
                                    }
                                } else if (choicePerson == 4) {
                                    System.out.println("Going back to Category");
                                } else System.out.println("Error:\tEnter valid choice!");


                            } else if (wantedTo == 5) {
                                addingNewProduct = false;
                            } else System.out.println("Wrong input!");
                        }
                    } else if (choice.equals("N")) {
                        System.out.println("Ok! That's fine for now.");
                        modifySeller = false;
                    } else if (choice.equals("Q")) {
                        System.out.println("Thank you visiting seller page.");
                        modifySeller = false;
                        users = false;
                    } else System.out.println("Wrong input provided!\n");
                }


            } else if (userEntry == 3) {
                System.out.println("You are Administrator");
                if (publishRequest) {
                    System.out.println("Seller want to publish some products. Please check and approved the Products.");
                    Products[] sellerProducts = seller.getPublishProduct();
                    for (int i = 0; i < sellerProducts.length; i++) {
                        System.out.println("Product " + (i + 1) + ":\t" + sellerProducts[i].getProductName() + "\t" + sellerProducts[i].getProductId() + "\t" + sellerProducts[i].getProductCost());
                    }
                    System.out.println("Do you want to remove any Products?\tY/N");
                    scans.nextLine();
                    String changeProduct = scans.nextLine();
                    if (changeProduct.equals("Y")) {
                        System.out.println("Enter product id");
                        String productId = scans.nextLine();
                        for (int i = 0; i < sellerProducts.length; i++) {
                            if (productId.equals(sellerProducts[i].getProductId())) {
                                System.out.println("Your selected product :" + sellerProducts[i].getProductName());
                                //! Removing product and set all product for publishing...
                                Products[] revisedProducts = new Products[sellerProducts.length - 1];

                                for (int j = i; j < sellerProducts.length - 1; j++) {
                                    sellerProducts[j] = sellerProducts[j + 1];
                                }
                                for (int k = 0; k < revisedProducts.length; k++) {
                                    revisedProducts[k] = sellerProducts[k];
                                }
                                if (revisedProducts.length != 0) {

                                    newCatalogue.setListOfAllProducts(revisedProducts);

                                    System.out.println("Product deleted and approved successful...");
                                } else {
                                    newCatalogue.setListOfAllProducts(null);
                                }
                            }
                        }

                    } else if (changeProduct.equals("N")) {
                        System.out.println("Do you want to approved the Products?\tY/N");
                        String approve = scans.nextLine();
                        if (approve.equals("Y")) {
                            newCatalogue.setListOfAllProducts(sellerProducts);
                            System.out.println("Approval of all Product is done.");
                        } else if (approve.equals("N")) {
                            System.out.println("Approval is canceled.");
                        } else System.out.println("Error: Wrong input.");
                    } else System.out.println("Error: Wrong input.");
                    publishRequest = false;
                } else if (!publishRequest) {
                    System.out.println("No product is ready to publish");
                }
            } else {
                System.out.println("Invalid User Entry! Please try again.");
            }
        }
    }
}