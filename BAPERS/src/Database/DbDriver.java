package Database;


import java.sql.*;

public class DbDriver {

    public static void main(String[] args) {
String url = "jdbc:mysql://localhost:3306/BAPERS";
String user = "root";
String password = "helloworld12";
        try {
            Connection conn = DriverManager.getConnection(url,user, password);
            Statement statement = conn.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS Discount \n" +
                    "(\n" +
                    "Discount_ID integer NOT NULL,\n" +
                    "Type_ID integer NOT NULL,\n" +
                    "PRIMARY KEY (Discount_ID)\n" +
                    ")");

            statement.execute("CREATE TABLE IF NOT EXISTS Fixed \n" +
                    "(\n" +
                    "Fixed_ID integer NOT NULL,\n" +
                    "Discount_ID integer NOT NULL UNIQUE,\n" +
                    "Fixed_Rate decimal (4,2),\n" +
                    "PRIMARY KEY (Fixed_ID),\n" +
                    "FOREIGN KEY (Discount_ID) REFERENCES Discount(Discount_ID)\n" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS Flexible \n" +
                    "(\n" +
                    "Flexi_ID integer NOT NULL,\n" +
                    "Discount_ID integer NOT NULL UNIQUE,\n" +
                    "PRIMARY KEY (Flexi_ID),\n" +
                    "FOREIGN KEY (Discount_ID) REFERENCES Discount(Discount_ID)\n" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS Variable \n" +
                    "(\n" +
                    "Var_ID integer NOT NULL,\n" +
                    "Discount_ID integer NOT NULL UNIQUE,\n" +
                    "PRIMARY KEY (Var_ID),\n" +
                    "FOREIGN KEY (Discount_ID) REFERENCES Discount(Discount_ID) \n" +
                    ")");

            statement.execute("CREATE TABLE IF NOT EXISTS CUSTOMER_ACCOUNT(\n" +
                    " AccountNumber int  AUTO_INCREMENT NOT NULL,\n" +
                    " CustomerName varchar(255),\n" +
                    " ContactName varchar(255),\n" +
                    " CustomerAddress varchar(255),\n" +
                    " CustomerPhoneNumber varchar(15),\n" +
                    " CustomerDiscountType varchar(50) default 'no discount',\n" +
                    " CustomerDiscountRate int default 0,\n" +
                    " CustomerType varchar(20) default 'normal',\n" +
                    " Discount_ID integer ,\n" +
                    "PRIMARY KEY (AccountNumber),\n" +
                    "FOREIGN KEY (Discount_ID) REFERENCES DISCOUNT(DISCOUNT_ID)\n" +
                    ")");
            statement.execute( "CREATE TABLE IF NOT EXISTS STAFF_ACCOUNT(\n" +
                    " StaffID int  AUTO_INCREMENT NOT NULL,\n" +
                    " StaffName varchar(100),\n" +
                    " UserName varchar(100),\n" +
                    " Password varchar(255),\n" +
                    " StaffAddress varchar(255),\n" +
                    " StaffRole varchar(30),\n" +
                    " StaffPhoneNumber varchar(15),\n" +
                    "PRIMARY KEY (StaffID)\n" +
                    "  )");
            statement.execute(   "CREATE TABLE IF NOT EXISTS TASKS_AVAILABLE(\n" +
                    " TaskID int AUTO_INCREMENT NOT NULL,\n" +
                    " TaskDescription varchar(255),\n" +
                    " TaskLocation varchar(50),\n" +
                    " TaskPrice float,\n" +
                    "TaskDuration varchar(50),\n" +
                    "PRIMARY KEY (TaskID)\n" +
                    ")");
            statement.execute(   "CREATE TABLE IF NOT EXISTS JOBS (\n" +
                    "JobID int AUTO_INCREMENT NOT NULL,\n" +
                    "AccountNumber int,\n" +
                    "JobUrgency varchar(20),\n" +
                    "ArrivalTime varchar(255),\n" +
                    "ArrivalDate varchar(255),\n" +
                    "HoursToComplete int,\n" +
                    "TotalPrice float,\n" +
                    "PRIMARY KEY (JobID),\n" +
                    "FOREIGN KEY(AccountNumber) REFERENCES\n" +
                    "CUSTOMER_ACCOUNT(AccountNumber)\n" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS TASKS_AVAILABLE_JOBS (\n" +
                    "TaskID int NOT NULL,\n" +
                    "JobID int NOT NULL,\n" +
                    "StaffID int NOT NULL,\n" +
                    "TaskStatus varchar(255),\n" +
                    "DiscountRate varchar(255),\n" +
                    "TaskTimeTaken int,\n" +
                    "TaskDateStarted varchar(255),\n" +
                    "TaskTimeStarted varchar(255),\n" +
                    "FOREIGN KEY(TaskID) REFERENCES TASKS_AVAILABLE(TaskID),\n" +
                    "FOREIGN KEY(JobID) REFERENCES JOBS(JobID),\n" +
                    "FOREIGN KEY(StaffID) REFERENCES STAFF_ACCOUNT(StaffID)\n" +
                    ")");
            statement.execute( "CREATE TABLE IF NOT EXISTS PAYMENT_HISTORY(\n" +
                    "PaymentID int AUTO_INCREMENT,\n" +
                    "JobID int,\n" +
                    "AccountNumber int,\n" +
                    "CashOrCard varchar(10),\n" +
                    "CardType varchar(10),\n" +
                    "ExpiryDate varchar(15),\n" +
                    "Last4Digits int,\n" +
                    "AmountPaid int,\n" +
                    "PRIMARY KEY (PaymentID),\n" +
                    "FOREIGN KEY (JobID) REFERENCES JOBS(JobID),\n" +
                    "FOREIGN KEY (AccountNumber) REFERENCES\n" +
                    "CUSTOMER_ACCOUNT(AccountNumber)\n" +
                    ")");





        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
