package BapersControl;

import Database.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class tempCustomerSession {


    private Main m;
    private DBConnection conn;
    private Connection connDB;
    private static String id,customerName,title,firstName,lastName,address,city,postCode,email,number,customerType,discountID;


    public tempCustomerSession(String id){
        this.m = new Main();
        this.conn = new DBConnection();
        this.connDB = conn.getConnection();
        this.id = id;
        this.customerName = "";
        this.title = "";
        this.firstName = "";
        this.lastName = "";
        this.address = "";
        this.city = "";
        this.postCode = "";
        this.email = "";
        this.number = "";
        this.customerType ="";
        this.discountID = "";
        getCustomerDetails(id);


    }

    public void getCustomerDetails(String id){
//        String userName = username;
        String query = "SELECT * FROM Customer_ACCOUNT WHERE ACCOUNT_NUMBER = '" + id + "';";

        System.out.println(id + "first check temp");
        try {
            Statement statement = connDB.createStatement();
            ResultSet result = statement.executeQuery(query);

            if (result.next()){
//                id = result.getString(1);
//                setId(id);
                customerName = result.getString(2);
                setCustomerName(customerName);
                title = result.getString(3);
                setTitle(title);
                firstName = result.getString(4);
                setFirstName(firstName);
                lastName = result.getString(5);
                setLastName(lastName);
                address = result.getString(6);
                setAddress(address);
                city = result.getString(7);
                setCity(city);
                postCode = result.getString(8);
                setPostCode(postCode);
                number = result.getString(9);
                setNumber(number);
                email = result.getString(10);
                setEmail(email);
                customerType = result.getString(11);
                setCustomerType(customerType);
                discountID = result.getString(12);
                setDiscountID(discountID);

                System.out.println(id +  customerName +title   + firstName + lastName + address + city + postCode+
                        email + number + customerType + discountID);
            }else{
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void setId(String id) { tempCustomerSession.id = id; }

    public static String getId() { return id; }

    public static void setCustomerName(String customerName) {
        tempCustomerSession.customerName = customerName;
    }

    public static String getCustomerName() {
        return customerName;
    }

    public static void setTitle(String title) { tempCustomerSession.title = title; }

    public static String getTitle() { return title; }

    public static void setFirstName(String firstName) {
        tempCustomerSession.firstName = firstName;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setLastName(String lastName) {
        tempCustomerSession.lastName = lastName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setAddress(String address) {
        tempCustomerSession.address = address;
    }

    public static String getAddress() {
        return address;
    }

    public static void setCity(String city) {
        tempCustomerSession.city = city;
    }

    public static String getCity() {
        return city;
    }

    public static void setPostCode(String postCode) {
        tempCustomerSession.postCode = postCode;
    }

    public static String getPostCode() {
        return postCode;
    }

    public static void setEmail(String email) {
        tempCustomerSession.email = email;
    }

    public static String getEmail() {
        return email;
    }

    public static void setNumber(String number) {
        tempCustomerSession.number = number;
    }

    public static String getNumber() {
        return number;
    }

    public static void setCustomerType(String customerType) {
        tempCustomerSession.customerType = customerType;
    }

    public static String getCustomerType() {
        return customerType;
    }

    public static String getDiscountID() {
        return discountID;
    }

    public static void setDiscountID(String discountID) {
        tempCustomerSession.discountID = discountID;
    }
}
