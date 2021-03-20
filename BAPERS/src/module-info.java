
module BAPERS {
    // this is used to import necessary packages? idk what they are called
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.sql;
    //requires mysql.conncetor.java;

    //requires kotlin.stdlib;
    // opens bapers control as that is the main/ start of all use cases
    opens BapersControl;
    opens JobTasks;

}