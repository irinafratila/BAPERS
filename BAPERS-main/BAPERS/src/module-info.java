module BAPERS {
    // this is used to import necessary packages? idk what they are called
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.sql;
    requires java.desktop;
    //requires mysql.conncetor.java;

    //requires kotlin.stdlib;
    // opens bapers control as that is the main/ start of all use cases
    opens Admin;
    opens BapersControl;
    opens Database;
    opens Customer;
    opens Discount;
    opens JobTasks;
    opens Reports;
    opens Alerts;
    opens Payment;
}