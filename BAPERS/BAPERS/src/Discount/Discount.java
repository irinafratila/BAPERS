package Discount;

import Customer.CustomerAccount;
import Database.DbDriver;

import java.util.Scanner;

public class Discount {
    private int discountId;
    private String description;

    public Discount(int discountId, String description) {
        this.discountId = discountId;
        this.description = description;
    }

    public double calculatePrice() {
        return 0;
    }

    public int getDiscountId() {
        return discountId;
    }


    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void applyFixedDiscount(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter Customer id");
        int searchedId = sc.nextInt();
        CustomerAccount searchedCustomer = DbDriver.searchCustomer(searchedId);
        searchedCustomer.updateCustomerType("valuable","fixed");
    }

    public void applyFlexiDiscount(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter Customer id");
        int searchedId = sc.nextInt();
        CustomerAccount searchedCustomer = DbDriver.searchCustomer(searchedId);
        searchedCustomer.updateCustomerType("valuable","flexible");
    }

    public void applyVarDiscount(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter Customer id");
        int searchedId = sc.nextInt();
        CustomerAccount searchedCustomer = DbDriver.searchCustomer(searchedId);
        searchedCustomer.updateCustomerType("valuable","variable");
    }


}