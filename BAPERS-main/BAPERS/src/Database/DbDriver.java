package Database;

import Customer.CustomerAccount;
import Discount.*;
import JobTasks.Job;
import JobTasks.Task;
import JobTasks.TasksJobs;
import Discount.FlexibleDiscountPlan;
import Reports.IndividualPerformanceReport;
import Reports.Invoice;
import Reports.SummaryReport;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * @author Muhammad Masum Miah
 */

public class DbDriver {

    //Create Discount table variables.
    public static final String TABLE_DISCOUNT = "DISCOUNT";
    public static final String COLUMN_DISCOUNT_ID = "DISCOUNT_ID";
    public static final String COLUMN_DISCOUNT_TYPE = "DISCOUNT_TYPE";

    public static final String TABLE_FIXED = "FIXED";
    public static final String COLUMN_FIXED_ID = "FIXED_ID";
    public static final String COLUMN_FIXED_RATE = "FIXED_RATE";

    public static final String TABLE_FLEXIBLE = "FLEXIBLE";
    public static final String COLUMN_FLEXI_ID = "FLEXI_ID";
    public static final String COLUMN_FLEXI_RATE = "FLEXI_RATE";

    public static final String TABLE_VARIABLE = "VARIABLE";
    public static final String COLUMN_VAR_ID = "VAR_ID";
    public static final String COLUMN_RANGE = "COST_RANGE";
    public static final String COLUMN_VARIABLE_RATE = "VARIABLE_RATE";
    //Create Customer Account table variables.
    public static final String TABLE_CUSTOMER_ACCOUNT = "CUSTOMER_ACCOUNT";
    public static final String COLUMN_ACCOUNT_NUMBER = "ACCOUNT_NUMBER";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CONTACT_TITLE = "CONTACT_TITLE";
    public static final String COLUMN_CONTACT_FIRST_NAME = "CONTACT_FIRST_NAME";
    public static final String COLUMN_CONTACT_LAST_NAME = "CONTACT_LAST_NAME";
    public static final String COLUMN_ADDRESS = "ADDRESS";
    public static final String COLUMN_POSTCODE = "POSTCODE";
    public static final String COLUMN_CITY = "CITY";
    public static final String COLUMN_PHONE_NUMBER = "PHONE_NUMBER";
    public static final String COLUMN_EMAIL_ADDRESS = "EMAIL_ADDRESS";
    public static final String COLUMN_CUSTOMER_TYPE = "CUSTOMER_TYPE";
    //Create Staff account table variables.
    public static final String TABLE_STAFF_ACCOUNT = "STAFF_ACCOUNT";
    public static final String COLUMN_STAFF_ID = "STAFFID";
    public static final String COLUMN_STAFF_NAME = "STAFF_NAME";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_STAFF_PASSWORD = "STAFF_PASSWORD";
    public static final String COLUMN_STAFF_ADDRESS = "STAFF_ADDRESS";
    public static final String COLUMN_STAFF_ROLE = "STAFF_ROLE";
    public static final String COLUMN_STAFF_PHONE_NUMBER = "STAFF_PHONE_NUMBER";
    //Create Tasks table variables.
    public static final String TABLE_TASKS_AVAILABLE = "TASKS_AVAILABLE";
    public static final String COLUMN_TASK_ID = "TASKID";
    public static final String COLUMN_TASK_DESCRIPTION = "TASK_DESCRIPTION";
    public static final String COLUMN_TASK_PRICE = "TASK_PRICE";
    public static final String COLUMN_TASK_DURATION = "TASK_DURATION";
    //Create Jobs table variables.
    public static final String TABLE_JOBS = "JOBS";
    public static final String COLUMN_JOB_ID = "JOBID";
    public static final String COLUMN_START_TIME = "START_TIME";
    public static final String COLUMN_HOURS_TO_COMPLETE = "HOURS_TO_COMPLETE";
    public static final String COLUMN_TOTAL_PRICE = "TOTAL_PRICE";
    public static final String COLUMN_PRIORITY = "PRIORITY";
    public static final String COLUMN_CURRENT_STATUS = "STATUS";
    public static final String COLUMN_SPECIAL_INSTRUCTIONS = "SPECIAL_INSTRUCTIONS";
    public static final String COLUMN_JOB_DEADLINE = "DEADLINE";
    public static final String COLUMN_COMPLETE_TIME = "COMPLETE_TIME";
    public static final String COLUMN_STAFF_ID_START = "STAFF_ID_START";
    public static final String COLUMN_STAFF_ID_COMPLETE = "STAFF_ID_COMPLETE";
    public static final String COLUMN_JOB_IS_OVERDUE = "JOB_IS_OVERDUE";
    public static final String COLUMN_QUANTITY = "QUANTITY";
    //Create Department table variables.
    public static final String TABLE_DEPARTMENT = "DEPARTMENT";
    public static final String COLUMN_DEPARTMENT_ID = "DEPARTMENT_ID";
    public static final String COLUMN_LOCATION = "LOCATION";
    //Create Tasks available jobs table variables.
    public static final String TABLE_JOB_TASKS = "TASK_AVAILABLE_JOBS";
    public static final String COLUMN_TASK_STATUS = "TASK_STATUS";
    public static final String COLUMN_TASK_TIME_TAKEN = "TASK_TIME_TAKEN";
    public static final String COLUMN_TASK_START_TIME = "TASK_START_TIME";
    public static final String COLUMN_TASK_COMPLETE_TIME = "TASK_COMPLETE_TIME";
    public static final String COLUMN_TASK_SHIFT_TIME = "TASK_SHIFT_TIME";
    public static final String COLUMN_TASK_IS_COMPLETE = "TASK_IS_COMPLETE";
    public static final String COLUMN_TASK_IS_OVERDUE = "TASK_IS_OVERDUE";
    public static final String COLUMN_JOB_TASK_ID = "JOB_TASK_ID";
    //Create Payments history table variables.
    public static final String TABLE_PAYMENT_HISTORY = "PAYMENT_HISTORY";
    public static final String COLUMN_PAYMENT_ID = "PAYMENT_ID";
    public static final String COLUMN_CASH_OR_CARD = "CASH_OR_CARD";
    public static final String COLUMN_CARD_TYPE = "CARD_TYPE";
    public static final String COLUMN_EXPIRY_DATE = "EXPIRY_DATE";
    public static final String COLUMN_LAST_4_DIGITS = "LAST_4_DIGITS";
    public static final String COLUMN_AMOUNT = "AMOUNT";


    //Establish DB Connection.
    private static final DBConnection conn = new DBConnection();

    //Query statements to check  existence before inserting.
    //Have placed a '?' placeholder to avoid SQL injection attacks.
    public static final String QUERY_CUSTOMER = "SELECT * FROM " +
            TABLE_CUSTOMER_ACCOUNT + " WHERE " + COLUMN_PHONE_NUMBER + " = ?";

    public static final String QUERY_CUSTOMER_FOR_UPDATE = "SELECT * FROM " +
            TABLE_CUSTOMER_ACCOUNT + " WHERE " + COLUMN_ACCOUNT_NUMBER + " = ?";

    public static final String QUERY_DEPARTMENT = "SELECT * FROM " +
            TABLE_DEPARTMENT + " WHERE " + COLUMN_LOCATION + " = ?";

    public static final String QUERY_TASKS = "SELECT * FROM " +
            TABLE_TASKS_AVAILABLE + " WHERE " + COLUMN_TASK_DESCRIPTION + " = ?";

    public static final String QUERY_STAFF_ACCOUNT = "SELECT * FROM " +
            TABLE_STAFF_ACCOUNT + " WHERE " + COLUMN_USER_NAME + " = ?";

    public static final String QUERY_PAYMENTS = "SELECT * FROM " +
            TABLE_PAYMENT_HISTORY + " WHERE " + COLUMN_JOB_ID + " = ?";

    public static final String INSERT_CUSTOMER = "insert into " + TABLE_CUSTOMER_ACCOUNT + "(" + COLUMN_CUSTOMER_NAME + ',' +
            COLUMN_CONTACT_TITLE + ',' + COLUMN_CONTACT_FIRST_NAME + ',' + COLUMN_CONTACT_LAST_NAME + ',' +
            COLUMN_ADDRESS + ',' + COLUMN_CITY + ',' + (COLUMN_POSTCODE) + ',' + COLUMN_EMAIL_ADDRESS + ',' +
            COLUMN_PHONE_NUMBER + ',' + COLUMN_CUSTOMER_TYPE +
            ")" + "values (?,?,?,?,?,?,?,?,?,?)";

    public static final String insertJob = "insert into " + TABLE_JOBS + "(" + COLUMN_ACCOUNT_NUMBER + ',' + COLUMN_PRIORITY +
            ',' + COLUMN_SPECIAL_INSTRUCTIONS + ',' + COLUMN_START_TIME + ',' + COLUMN_JOB_DEADLINE + ',' +
            COLUMN_STAFF_ID_START + ',' + COLUMN_TOTAL_PRICE + ',' + COLUMN_JOB_IS_OVERDUE + ',' + COLUMN_QUANTITY +")" + "values (?,?,?,?,?,?,?,?,?)";

    private static final String insertDiscount = "Insert into " + TABLE_DISCOUNT + "(" + COLUMN_DISCOUNT_TYPE +
            ")" + "values (?)";

    public static final String insertVariable = "Insert into " + TABLE_VARIABLE + "(" + COLUMN_VARIABLE_RATE + "," +
            COLUMN_DISCOUNT_ID + "," + COLUMN_TASK_ID + ")" + "values (?,?,?)";

    public static final String insertFixed = "Insert into " + TABLE_FIXED + "(" + COLUMN_FIXED_RATE + "," +
            COLUMN_DISCOUNT_ID + ")" + "values (?,?)";

    public static final String insertFlexible = "Insert into " + TABLE_FLEXIBLE + "(" + COLUMN_FLEXI_RATE + "," + COLUMN_DISCOUNT_ID +
            "," + COLUMN_RANGE + ")" + "values (?,?,?)";

    public static final String insertDepartment = "Insert into " + TABLE_DEPARTMENT + "(" + COLUMN_LOCATION + ")" +
            "values (?)";

    public static final String insertTask = "insert into " + TABLE_TASKS_AVAILABLE + "(" + COLUMN_TASK_DESCRIPTION + ',' +
            COLUMN_DEPARTMENT_ID + ',' + COLUMN_TASK_PRICE + ',' + COLUMN_TASK_DURATION + ")" + "values (?,?,?,?)";

    public static final String insertTasksJobs = "insert into " + TABLE_JOB_TASKS + "(" + COLUMN_TASK_ID + ',' +
            COLUMN_JOB_ID + ")" + "values (?,?)";

    public static final String insertPayment = "insert into " + TABLE_PAYMENT_HISTORY + "(" + COLUMN_JOB_ID + ',' + COLUMN_ACCOUNT_NUMBER + ',' + COLUMN_CASH_OR_CARD + ',' +
            COLUMN_CARD_TYPE + ',' + COLUMN_EXPIRY_DATE + ',' + COLUMN_LAST_4_DIGITS + ',' + COLUMN_AMOUNT +
            ")" + "values (?,?,?,?,?,?,?)";

    public static final String insertStaff = "insert into " + TABLE_STAFF_ACCOUNT + "(" + COLUMN_STAFF_NAME + ',' +
            COLUMN_USER_NAME + ',' + COLUMN_STAFF_PASSWORD + ',' + COLUMN_STAFF_ADDRESS + ',' + COLUMN_STAFF_ROLE + ',' + COLUMN_STAFF_PHONE_NUMBER + ")" +
            "values (?,?,?,?,?,?)";

    public static final String createIndividualReport = "SELECT " + TABLE_STAFF_ACCOUNT + "." + COLUMN_STAFF_ID + ", " + TABLE_STAFF_ACCOUNT + "." + COLUMN_STAFF_NAME +
            ", " + TABLE_STAFF_ACCOUNT + "." + COLUMN_STAFF_ROLE + " , " + TABLE_JOB_TASKS + "." + COLUMN_TASK_ID + ", " + TABLE_DEPARTMENT + "." + COLUMN_LOCATION + ", " +
            TABLE_JOB_TASKS + "." + COLUMN_TASK_START_TIME + ", " + TABLE_JOB_TASKS + "." + COLUMN_TASK_COMPLETE_TIME + ", " + TABLE_JOB_TASKS + "." +
            COLUMN_TASK_TIME_TAKEN +
            " FROM ((( " + TABLE_STAFF_ACCOUNT + "" +
            " INNER JOIN " + TABLE_JOB_TASKS + " ON " + TABLE_STAFF_ACCOUNT + "." + COLUMN_STAFF_ID + " = " + TABLE_JOB_TASKS + "." + COLUMN_STAFF_ID + ")" +
            " INNER JOIN " + TABLE_TASKS_AVAILABLE + " ON " + TABLE_JOB_TASKS + "." + COLUMN_TASK_ID + " = " + TABLE_TASKS_AVAILABLE + "." + COLUMN_TASK_ID + ")" +
            " INNER JOIN " + TABLE_DEPARTMENT + " ON " + TABLE_TASKS_AVAILABLE + "." + COLUMN_DEPARTMENT_ID + " = " + TABLE_DEPARTMENT + "." + COLUMN_DEPARTMENT_ID + ")";

    public static final String createInvoice = "SELECT " + TABLE_CUSTOMER_ACCOUNT + "." + COLUMN_ACCOUNT_NUMBER + ", " + TABLE_CUSTOMER_ACCOUNT + "." + COLUMN_CUSTOMER_NAME + ", " +
            "concat(" + TABLE_CUSTOMER_ACCOUNT + "." + COLUMN_CONTACT_TITLE + " , " + "' ' ," + TABLE_CUSTOMER_ACCOUNT + "." + COLUMN_CONTACT_FIRST_NAME + ", " + "' ' ," + TABLE_CUSTOMER_ACCOUNT + "." + COLUMN_CONTACT_LAST_NAME + ") AS CONTACT, " +
            "concat(" + TABLE_CUSTOMER_ACCOUNT + "." + COLUMN_ADDRESS + ", " + "' ' , " + TABLE_CUSTOMER_ACCOUNT + "." + COLUMN_CITY + ", " + "' ' ," + TABLE_CUSTOMER_ACCOUNT + "." + COLUMN_POSTCODE + ") AS ADDRESS, " +
            TABLE_CUSTOMER_ACCOUNT + "." + COLUMN_PHONE_NUMBER + ", " + TABLE_JOBS + "." + COLUMN_JOB_ID + ", " + TABLE_JOBS + "." + COLUMN_START_TIME + ", " +
            TABLE_TASKS_AVAILABLE + "." + COLUMN_TASK_ID + ", " + TABLE_TASKS_AVAILABLE + "." + COLUMN_TASK_DESCRIPTION + ", " + TABLE_TASKS_AVAILABLE + "." + COLUMN_TASK_PRICE + ", " + TABLE_JOBS + "." + COLUMN_TOTAL_PRICE +
            " FROM ((( " + TABLE_CUSTOMER_ACCOUNT + "" +
            " INNER JOIN " + TABLE_JOBS + " ON " + TABLE_CUSTOMER_ACCOUNT + "." + COLUMN_ACCOUNT_NUMBER + " = " + TABLE_JOBS + "." + COLUMN_ACCOUNT_NUMBER + ")" +
            " INNER JOIN " + TABLE_JOB_TASKS + " ON " + TABLE_JOBS + "." + COLUMN_JOB_ID + " = " + TABLE_JOB_TASKS + "." + COLUMN_JOB_ID + ")" +
            " INNER JOIN " + TABLE_TASKS_AVAILABLE + " ON " + TABLE_JOB_TASKS + "." + COLUMN_TASK_ID + " = " + TABLE_TASKS_AVAILABLE + "." + COLUMN_TASK_ID + ")" +
            " WHERE " + TABLE_JOBS + "." + COLUMN_JOB_ID + " = " + "?  ";

    public static final String upgradeCustomer = "UPDATE " + TABLE_CUSTOMER_ACCOUNT +
            " SET " + COLUMN_CUSTOMER_TYPE + " = " + "?" + " , " + COLUMN_DISCOUNT_ID + " = " +
            "?"+ " WHERE " + COLUMN_ACCOUNT_NUMBER + " = " + "? ";


    public static final String createIndividualStaffReport = "SELECT " + TABLE_STAFF_ACCOUNT + "." + COLUMN_STAFF_ID + ", " + TABLE_STAFF_ACCOUNT + "." + COLUMN_STAFF_NAME +
            ", " + TABLE_STAFF_ACCOUNT + "." + COLUMN_STAFF_ROLE + " , " + TABLE_JOB_TASKS + "." + COLUMN_TASK_ID + ", " + TABLE_DEPARTMENT + "." + COLUMN_LOCATION + ", " +
            TABLE_JOB_TASKS + "." + COLUMN_TASK_START_TIME + ", " + TABLE_JOB_TASKS + "." + COLUMN_TASK_COMPLETE_TIME + ", " + TABLE_JOB_TASKS + "." +
            COLUMN_TASK_TIME_TAKEN +
            " FROM ((( " + TABLE_STAFF_ACCOUNT + "" +
            " INNER JOIN " + TABLE_JOB_TASKS + " ON " + TABLE_STAFF_ACCOUNT + "." + COLUMN_STAFF_ID + " = " + TABLE_JOB_TASKS + "." + COLUMN_STAFF_ID + ")" +
            " INNER JOIN " + TABLE_TASKS_AVAILABLE + " ON " + TABLE_JOB_TASKS + "." + COLUMN_TASK_ID + " = " + TABLE_TASKS_AVAILABLE + "." + COLUMN_TASK_ID + ")" +
            " INNER JOIN " + TABLE_DEPARTMENT + " ON " + TABLE_TASKS_AVAILABLE + "." + COLUMN_DEPARTMENT_ID + " = " + TABLE_DEPARTMENT + "." + COLUMN_DEPARTMENT_ID + ")" +
            " WHERE " + TABLE_STAFF_ACCOUNT +"." + COLUMN_STAFF_ID + " = ? ";


    public static void main(String[] args) throws SQLException {


        //By putting statement  in the parenthesis, there is no need to close statement at the end.
        try (Statement statement = conn.getConnection().createStatement()) {


            // For testing purposes, delete all tables before running code.
            statement.execute("SET FOREIGN_KEY_CHECKS=0");
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_PAYMENT_HISTORY);
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_JOB_TASKS);
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_JOBS);
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_VARIABLE);
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_TASKS_AVAILABLE);
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_DEPARTMENT);
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_STAFF_ACCOUNT);
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_CUSTOMER_ACCOUNT);
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_FLEXIBLE);
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_FIXED);
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_DISCOUNT);
            statement.execute("SET FOREIGN_KEY_CHECKS=1");

            //Create individual tables
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_DISCOUNT + " \n" +
                    "(\n" +
                    COLUMN_DISCOUNT_ID + " integer NOT NULL auto_increment,\n" +
                    COLUMN_DISCOUNT_TYPE + " varchar(255) NOT NULL,\n" +
                    "PRIMARY KEY (" + COLUMN_DISCOUNT_ID + ")\n" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_FIXED + " \n" +
                    "(\n" +
                    COLUMN_FIXED_ID + " integer NOT NULL auto_increment,\n" +
                    COLUMN_DISCOUNT_ID + " integer NOT NULL ,\n" +
                    COLUMN_FIXED_RATE + " float,\n" +
                    "PRIMARY KEY (" + COLUMN_FIXED_ID + "),\n" +
                    "FOREIGN KEY (" + COLUMN_DISCOUNT_ID + ") REFERENCES " + TABLE_DISCOUNT + "(" + COLUMN_DISCOUNT_ID + ")\n" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_FLEXIBLE + "\n" +
                    "(\n" +
                    COLUMN_FLEXI_ID + " integer NOT NULL auto_increment,\n" +
                    COLUMN_FLEXI_RATE + " float NOT NULL,\n" +
                    COLUMN_RANGE + " float NOT NULL ,\n" +
                    COLUMN_DISCOUNT_ID + " integer NOT NULL ,\n" +
                    "PRIMARY KEY (" + COLUMN_FLEXI_ID + "),\n" +
                    "FOREIGN KEY (" + COLUMN_DISCOUNT_ID + ") REFERENCES " + TABLE_DISCOUNT + "(" + COLUMN_DISCOUNT_ID + ")\n" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CUSTOMER_ACCOUNT + "(\n" +
                    COLUMN_ACCOUNT_NUMBER + "  int  AUTO_INCREMENT NOT NULL,\n" +
                    COLUMN_CUSTOMER_NAME + " varchar(255),\n" +
                    COLUMN_CONTACT_TITLE + " varchar(10),\n" +
                    COLUMN_CONTACT_FIRST_NAME + " varchar(255),\n" +
                    COLUMN_CONTACT_LAST_NAME + " varchar(255),\n" +
                    COLUMN_ADDRESS + "  varchar(255),\n" +
                    COLUMN_POSTCODE + " varchar(10),\n" +
                    COLUMN_CITY + " varchar(30),\n" +
                    COLUMN_PHONE_NUMBER + "  varchar(15),\n" +
                    COLUMN_EMAIL_ADDRESS + " varchar(255),\n" +
                    COLUMN_CUSTOMER_TYPE + " varchar(20) default 'normal',\n" +
                    COLUMN_DISCOUNT_ID + " integer ,\n" +
                    "PRIMARY KEY (" + COLUMN_ACCOUNT_NUMBER + "),\n" +
                    "FOREIGN KEY (" + COLUMN_DISCOUNT_ID + ") REFERENCES " + TABLE_DISCOUNT + "(" + COLUMN_DISCOUNT_ID + ")\n" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_STAFF_ACCOUNT + "(\n" +
                    COLUMN_STAFF_ID + "  int  AUTO_INCREMENT NOT NULL,\n" +
                    COLUMN_STAFF_NAME + "  varchar(100),\n" +
                    COLUMN_USER_NAME + "  varchar(100),\n" +
                    COLUMN_STAFF_PASSWORD + "  varchar(255),\n" +
                    COLUMN_STAFF_ADDRESS + "  varchar(255),\n" +
                    COLUMN_STAFF_ROLE + "  varchar(30),\n" +
                    COLUMN_STAFF_PHONE_NUMBER + "  varchar(15),\n" +
                    "PRIMARY KEY (" + COLUMN_STAFF_ID + ")\n" +
                    "  )");
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_DEPARTMENT + "(\n" +
                    COLUMN_DEPARTMENT_ID + "  int AUTO_INCREMENT NOT NULL,\n" +
                    COLUMN_LOCATION + "  varchar(255),\n" +
                    "PRIMARY KEY (" + COLUMN_DEPARTMENT_ID + ")\n" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_TASKS_AVAILABLE + "(\n" +
                    COLUMN_TASK_ID + "  int AUTO_INCREMENT NOT NULL,\n" +
                    COLUMN_TASK_DESCRIPTION + "  varchar(255),\n" +
                    COLUMN_DEPARTMENT_ID + "  int ,\n" +
                    COLUMN_TASK_PRICE + "  float,\n" +
                    COLUMN_TASK_DURATION + " int ,\n" +
                    "PRIMARY KEY (" + COLUMN_TASK_ID + "),\n" +
                    "FOREIGN KEY(" + COLUMN_DEPARTMENT_ID + ") REFERENCES\n" +
                    TABLE_DEPARTMENT + "(" + COLUMN_DEPARTMENT_ID + ")\n" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_JOBS + " (\n" +
                    COLUMN_JOB_ID + " int AUTO_INCREMENT NOT NULL,\n" +
                    COLUMN_ACCOUNT_NUMBER + " int,\n" +
                    COLUMN_PRIORITY + " int,\n" +
                    COLUMN_CURRENT_STATUS + " varchar(255) default 'In Progress', \n" +
                    COLUMN_SPECIAL_INSTRUCTIONS + " varchar(255),\n" +
                    COLUMN_START_TIME + " TIMESTAMP,\n" +
                    COLUMN_JOB_DEADLINE + " TIMESTAMP,\n" +
                    COLUMN_COMPLETE_TIME + " TIMESTAMP,\n" +
                    COLUMN_HOURS_TO_COMPLETE + " int default 0,\n" +
                    COLUMN_STAFF_ID_START + " int,\n" +
                    COLUMN_STAFF_ID_COMPLETE + " int,\n" +
                    COLUMN_JOB_IS_OVERDUE + " varchar(20),\n" +
                    COLUMN_QUANTITY + " int,\n" +
                    COLUMN_TOTAL_PRICE + " float,\n" +
                    "PRIMARY KEY (" + COLUMN_JOB_ID + "),\n" +
                    "FOREIGN KEY(" + COLUMN_ACCOUNT_NUMBER + ") REFERENCES\n" +
                    TABLE_CUSTOMER_ACCOUNT + "(" + COLUMN_ACCOUNT_NUMBER + "),\n" +
                    "FOREIGN KEY(" + COLUMN_STAFF_ID_START + ") REFERENCES\n" +
                    TABLE_STAFF_ACCOUNT + "(" + COLUMN_STAFF_ID + "),\n" +
                    "FOREIGN KEY(" + COLUMN_STAFF_ID_COMPLETE + ") REFERENCES\n" +
                    TABLE_STAFF_ACCOUNT + "(" + COLUMN_STAFF_ID + ")\n" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_JOB_TASKS + " (\n" +
                    COLUMN_JOB_TASK_ID + " int NOT NULL auto_increment,\n" +
                    COLUMN_TASK_ID + " int NOT NULL,\n" +
                    COLUMN_JOB_ID + " int NOT NULL,\n" +
                    COLUMN_STAFF_ID + " int  ,\n" +
                    COLUMN_TASK_STATUS + " varchar(255) default 'Ready to process',\n" +
                    COLUMN_TASK_TIME_TAKEN + " float,\n" +
                    COLUMN_TASK_START_TIME + " TIMESTAMP,\n" +
                    COLUMN_TASK_COMPLETE_TIME + " TIMESTAMP,\n" +
                    COLUMN_TASK_SHIFT_TIME + " varchar(20),\n" +
                    COLUMN_TASK_IS_COMPLETE + " varchar(20),\n" +
                    COLUMN_TASK_IS_OVERDUE + " varchar(20), \n" +
                    "PRIMARY KEY (" + COLUMN_JOB_TASK_ID + "),\n" +
                    "FOREIGN KEY(" + COLUMN_TASK_ID + ") REFERENCES " + TABLE_TASKS_AVAILABLE + "(" + COLUMN_TASK_ID + "),\n" +
                    "FOREIGN KEY(" + COLUMN_JOB_ID + ") REFERENCES " + TABLE_JOBS + "(" + COLUMN_JOB_ID + "),\n" +
                    "FOREIGN KEY(" + COLUMN_STAFF_ID + ") REFERENCES " + TABLE_STAFF_ACCOUNT + "(" + COLUMN_STAFF_ID + ")\n" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_PAYMENT_HISTORY + "(\n" +
                    COLUMN_PAYMENT_ID + " int NOT NULL AUTO_INCREMENT,   \n" +
                    COLUMN_JOB_ID + " int,\n" +
                    COLUMN_ACCOUNT_NUMBER + " int,\n" +
                    COLUMN_CASH_OR_CARD + " varchar(10),\n" +
                    COLUMN_CARD_TYPE + " varchar(10),\n" +
                    COLUMN_EXPIRY_DATE + " varchar(15),\n" +
                    COLUMN_LAST_4_DIGITS + " varchar(4),\n" +
                    COLUMN_AMOUNT + " float,\n" +
                    "PRIMARY KEY (" + COLUMN_PAYMENT_ID + "),\n" +
                    "FOREIGN KEY (" + COLUMN_JOB_ID + ") REFERENCES " + TABLE_JOBS + "(" + COLUMN_JOB_ID + "),\n" +
                    "FOREIGN KEY (" + COLUMN_ACCOUNT_NUMBER + ") REFERENCES\n" +
                    TABLE_CUSTOMER_ACCOUNT + "(" + COLUMN_ACCOUNT_NUMBER + ")\n" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_VARIABLE + " \n" +
                    "(\n" +
                    COLUMN_VAR_ID + " integer NOT NULL auto_increment,\n" +
                    COLUMN_DISCOUNT_ID + " integer NOT NULL ,\n" +
                    COLUMN_TASK_ID + " integer NOT NULL ,\n" +
                    COLUMN_VARIABLE_RATE + " integer NOT NULL ,\n" +
                    "PRIMARY KEY (" + COLUMN_VAR_ID + "),\n" +
                    "FOREIGN KEY (" + COLUMN_TASK_ID + ") REFERENCES " + TABLE_TASKS_AVAILABLE + "(" + COLUMN_TASK_ID + "), \n" +
                    "FOREIGN KEY (" + COLUMN_DISCOUNT_ID + ") REFERENCES " + TABLE_DISCOUNT + "(" + COLUMN_DISCOUNT_ID + ") \n" +
                    ")");


            //Demo insert data.
            insertStaffAccount("Manager", "Manager", "Get_it_done", "Northampton Square", "Office manager", "07566638273");
            insertStaffAccount("Accountant", "Accountant", "Count_money", "Northampton Square", "Shift manager", "07566638273");
            insertStaffAccount("Clerk", "Clerk", "Paperwork", "Northampton Square", "Shift manager", "07566638273");
            insertStaffAccount("Hello", "Hello", "Hello_there", "Northampton Square", "Receptionist", "07566638273");
            insertStaffAccount("Development", "Development", "Lot_smell", "Northampton Square", "Technician", "07566638273");
            insertStaffAccount("Copy", "Copy", "Too_dark", "Northampton Square", "Technician", "07566638273");
            insertStaffAccount("Packer", "Packer", "Pack_it", "Northampton Square", "Technician", "07566638273");
            insertStaffAccount("Finish", "Finish", "Fine_touch", "Northampton Square", "Technician", "07566638273");


            //Insert into customer account.
            insertCustomer("City, University of London", "Prof", "David", "Rhind", "Northampton Square", "London", "EC1V0HB", "David.Rhind@city.ac.uk", "02070408000", "valuable");
            insertCustomer("AirVia Ltd", "Mr", "Boris", "Berezovsky", "12 Bond Street", "London", "WC1V8HU", "Boris.B@yahoo.com", "02073218523", "valuable");
            insertCustomer("InfoPharma Ltd", "Mr", "ALex", "White", "25 Bond Street", "London", "WC1V 8LS", "Alex.Wright@infopharma.com", "02073218001", "valuable");
            insertCustomer("Hello Magazine", "Ms", "Sarah", "Brocklehurst", "12 Charter Street", "London", "W1 8NS", "Sarah.Brocklehurst@hello.com", "02034567809", "valuable");
            insertCustomer("Ms Eva Bauyer", "Ms", "Eva", "Bauyer", "1, Liverpool Street", "London", "EC2V 8NS", "eva.bauyer@gmail.com", "02085558989", "valuable");


            //Fill up Departments table;
            insertDepartment("COPY ROOM");
            insertDepartment("DEVELOPMENT ROOM");
            insertDepartment("FINISHING ROOM");

            //Insert into TASKS_AVAILABLE.
            insertTasks("Use of large copy camera", 1, 19.00, 120);
            insertTasks("Black and white film processing", 2, 49.50, 60);
            insertTasks("Bag up", 3, 6.00F, 30);
            insertTasks("Colour film processing", 2, 80.00, 90);
            insertTasks("Colour Transparency Processing", 2, 110.30, 180);
            insertTasks("Use of small copy camera", 1, 8.50, 75);
            insertTasks("Mount transparencies", 3, 55.50, 45);


            int a = insertDiscount("no discount");
            System.out.println("discount id = " + a);

            System.out.println("Connected to Database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Below are all relavent methods needed for backend use.
    // Each table is categorised with it's inserts, querys, updates, where needed and search methods.


    // TODO:Update once admin class is complete.
//    public List<User> queryUser() {
//        try (Statement statement = conn.getConnection().createStatement();
//             ResultSet results = statement.executeQuery("SELECT * from " + TABLE_STAFF_ACCOUNT);
//        ) {
//            List<User> users = new LinkedList<>();
//            while (results.next()) {
//                int staffId = results.getString(COLUMN_STAFF_ID;
//                String name = results.getString(COLUMN_STAFF_NAME);
//                String userName = results.getString(COLUMN_USER_NAME);
//                String password = results.getString(COLUMN_PASSWORD);
//                String address = results.getString(COLUMN_STAFF_ADDRESS);
//                String role = results.getString(COLUMN_STAFF_ROLE);
//                String phoneNumber = results.getString(COLUMN_STAFF_PHONE_NUMBER);
//
//                User staff = new User(staffId,name,userName,password,address,role,phoneNumber);
//                users.add(staff);
//            }
//            return users;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }


    //Insert statement to enter data into individual tables  into the database.
    public static void insertCustomer(String cName, String title, String firstName, String lastName, String address, String City, String postcode, String email, String phone, String type) throws SQLException {
        try (PreparedStatement insertIntoCustomer = conn.getConnection().prepareStatement(INSERT_CUSTOMER, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement queryCustomer = conn.getConnection().prepareStatement(QUERY_CUSTOMER)) {
            queryCustomer.setString(1, phone);
            ResultSet results = queryCustomer.executeQuery();
            if (results.next()) {
                System.out.println("customer exists");
            } else {
                // Insert customer
                insertIntoCustomer.setString(1, cName);
                insertIntoCustomer.setString(2, title);
                insertIntoCustomer.setString(3, firstName);
                insertIntoCustomer.setString(4, lastName);
                insertIntoCustomer.setString(5, address);
                insertIntoCustomer.setString(6, City);
                insertIntoCustomer.setString(7, postcode);
                insertIntoCustomer.setString(8, email);
                insertIntoCustomer.setString(9, phone);
                insertIntoCustomer.setString(10, type);
                int affectedRows = insertIntoCustomer.executeUpdate();
                if (affectedRows != 1) {
                    throw new SQLException("Couldn't insert customer!");


                }
            }
        }
    }

    public static List<CustomerAccount> queryCustomers() {
        try (Statement statement = conn.getConnection().createStatement();
             ResultSet results = statement.executeQuery("SELECT * from " + TABLE_CUSTOMER_ACCOUNT)
        ) {
            List<CustomerAccount> customers = new LinkedList<>();
            while (results.next()) {
                String customer_name = results.getString(COLUMN_CUSTOMER_NAME);
                int customerId = results.getInt(COLUMN_ACCOUNT_NUMBER);
                String title = results.getString(COLUMN_CONTACT_TITLE);
                String firstName = results.getString(COLUMN_CONTACT_FIRST_NAME);
                String lastName = results.getString(COLUMN_CONTACT_LAST_NAME);
                String address = results.getString(COLUMN_ADDRESS);
                String postcode = results.getString(COLUMN_POSTCODE);
                String city = results.getString(COLUMN_CITY);
                String phoneNumber = results.getString(COLUMN_PHONE_NUMBER);
                String email = results.getString(COLUMN_EMAIL_ADDRESS);
                boolean isValuable;
                isValuable = results.getString(COLUMN_CUSTOMER_TYPE).equals("valuable");
                int discountId = results.getInt(COLUMN_DISCOUNT_ID);

                CustomerAccount customer = new CustomerAccount(customerId, customer_name, title, firstName, lastName, address, postcode, city, phoneNumber, email, isValuable, discountId);
                customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //    search for a customer by id
    public static CustomerAccount searchCustomer(int searchedCustomer) {
        List<CustomerAccount> customers = queryCustomers();
        if (customers == null) {
            System.out.println("No customers");
            return null;
        }
        for (CustomerAccount c : customers) {
            if (c.getCustomerId() == searchedCustomer) {
                return c;
            }
        }
        return null;
    }

    //TODO: Put all customer related CRUD here.
    //Update the database when changing the customer type or discount.
    public static void updateCustomerType(String isValuable, int discountId, int cId)throws SQLException {
        try (PreparedStatement updateCustomer = conn.getConnection().prepareStatement(upgradeCustomer);
             PreparedStatement queryCustomer = conn.getConnection().prepareStatement(QUERY_CUSTOMER_FOR_UPDATE)
        ) {

            queryCustomer.setInt(1, cId);
            ResultSet results = queryCustomer.executeQuery();
            if (results.next()) {
                System.out.println("customer exists");
                updateCustomer.setString(1,isValuable);
                updateCustomer.setInt(2,discountId);
                updateCustomer.setInt(3,cId);
                int affectedRows = updateCustomer.executeUpdate();
                if (affectedRows != 1) {
                    throw new SQLException("Couldn't insert customer!");
                }

            } else {
                System.out.println("Customer does not exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Job related code

    //TODO made change here

    public static void insertJob(String cName, String title, String firstName, String lastName, String address, String City, String postcode,
                                 String email, String phone, String type, int accountNumber, int priority, String instructions, Timestamp start,
                                 Timestamp deadline, int staffId, double price, String isOverdue, int quantity) {
        try (
                Connection connection = conn.getConnection();
                PreparedStatement insertIntoJob = connection.prepareStatement(insertJob)

        ) {
            connection.setAutoCommit(false);

            insertCustomer(cName, title, firstName, lastName, address, City, postcode, email, phone, type);

            // Insert job
            insertIntoJob.setInt(1, accountNumber);
            insertIntoJob.setInt(2, priority);
            insertIntoJob.setString(3, instructions);
            insertIntoJob.setTimestamp(4, start);
            insertIntoJob.setTimestamp(5, deadline);
            insertIntoJob.setInt(6, staffId);
            insertIntoJob.setDouble(7, price);
            insertIntoJob.setString(8, isOverdue);
            insertIntoJob.setInt(9,quantity);

            int affectedRows = insertIntoJob.executeUpdate();

            if (affectedRows == 1) {
                connection.commit();
            } else {
                throw new SQLException("The job insert failed.");

            }
        } catch (Exception e) {
            System.out.println("Insert job exception: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                conn.getConnection().rollback();
            } catch (SQLException e2) {
                System.out.println("Oh boy! Things are really bad! " + e2.getMessage());
            }
        } finally {
            try {
                System.out.println("Resetting default commit behavior");
                conn.getConnection().setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Couldn't reset auto-commit! " + e.getMessage());
            }

        }
    }
    //TODO made change here
    public static List<Job> queryJobs() {
        try (Statement statement = conn.getConnection().createStatement();
             ResultSet results = statement.executeQuery("SELECT * from " + TABLE_JOBS)
        ) {
            List<Job> jobs = new LinkedList<>();
            while (results.next()) {
                int jobId = results.getInt(COLUMN_JOB_ID);
                int accountNumber = results.getInt(COLUMN_ACCOUNT_NUMBER);
                int priority = results.getInt(COLUMN_PRIORITY);
                String status = results.getString(COLUMN_CURRENT_STATUS);
                String instructions = results.getString(COLUMN_SPECIAL_INSTRUCTIONS);
                Timestamp start = results.getTimestamp(COLUMN_START_TIME);
                Timestamp deadline = results.getTimestamp(COLUMN_JOB_DEADLINE);
                Timestamp completeTime = results.getTimestamp(COLUMN_COMPLETE_TIME);
                int hours = results.getInt(COLUMN_HOURS_TO_COMPLETE);
                int staffIdStart = results.getInt(COLUMN_STAFF_ID_START);
                int staffIdComplete = results.getInt(COLUMN_STAFF_ID_COMPLETE);
                double price = results.getDouble(COLUMN_TOTAL_PRICE);
                String isOverdue = results.getString(COLUMN_JOB_IS_OVERDUE);
                int quantity = results.getInt(COLUMN_QUANTITY);


                Job job = new Job(jobId, accountNumber, priority, instructions, status, start, deadline, completeTime, hours, staffIdStart, price, staffIdComplete, isOverdue, quantity);

                jobs.add(job);
            }
            return jobs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void updateCompleteJob(String status, Timestamp completeTime, double hours, int staffId, String isOverdue, int jobId) {
        try (Statement statement = conn.getConnection().createStatement()) {
            String sb1 = "UPDATE " + TABLE_JOBS +
                    " SET " +
                    COLUMN_CURRENT_STATUS +
                    " = '" +
                    status +
                    "', " +
                    COLUMN_COMPLETE_TIME +
                    " = '" +
                    completeTime +
                    "', " +
                    COLUMN_HOURS_TO_COMPLETE +
                    " = " +
                    hours +
                    ", " +
                    COLUMN_STAFF_ID_COMPLETE +
                    " = " +
                    staffId +
                    ", " +

                    COLUMN_JOB_IS_OVERDUE +
                    " = '" +
                    isOverdue +
                    "' WHERE " +
                    COLUMN_JOB_ID +
                    " = " +
                    jobId;
            System.out.println(sb1);
            statement.execute(sb1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Return the last job on the database, this is in order to access the last job id.
    public static Job searchJobJustCreated() {
        List<Job> jobs = queryJobs();
        if (jobs == null) {
            System.out.println("No Jobs");
            return null;
        }
        return jobs.get(jobs.size() - 1);
    }

    //Search and print open jobs.
    public static List<Job> searchAllJobs() {
        List<Job> jobs = queryJobs();
        if (jobs == null) {
            System.out.println("No Jobs");
            return null;
        }
        return jobs;
    }

    //    helper method to check all open jobs
    public static List<Job> getOpenJobs() {
        List<Job> jobs = searchAllJobs();
        List<Job> openJobs = new LinkedList<>();
        if (jobs != null) {
            for (Job j : jobs) {
                if (j.getCompleteTimeStamp() == null) {
                    openJobs.add(j);
                }
            }
        }
        if (openJobs.size() > 0) {
            return openJobs;
        }
        return null;
    }

    public static void printJobs(List<Job> jobs) {
        for (Job j : jobs) {
            System.out.println(j.getJobId());
        }
    }

    //This will return a job which is searched by id.
    public static Job searchJobs(int searchedJob) {
        List<Job> jobs = queryJobs();
        if (jobs == null) {
            System.out.println("No Jobs");
            return null;
        }
        for (Job j : jobs) {
            if (j.getJobId() == searchedJob) {
                return j;
            }
        }
        return null;
    }

    //delete a job from the database.
    public static void removeJob(int id) {
        try (Statement statement = conn.getConnection().createStatement()) {
            String sb1 = "delete from " + TABLE_JOBS +
                    " WHERE " +
                    COLUMN_JOB_ID +
                    " = " +
                    id;
            System.out.println(sb1);
            statement.execute(sb1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateJobPrice(double price, int id) {
        try (Statement statement = conn.getConnection().createStatement()) {
            String sb1 = "UPDATE " + TABLE_JOBS +
                    " SET " +
                    COLUMN_TOTAL_PRICE +
                    " = " +
                    price +
                    " WHERE " +
                    COLUMN_JOB_ID +
                    " = " +
                    id;
            System.out.println(sb1);
            statement.execute(sb1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Returns all the jobs which are currently incomplete
    public static void searchOpenJobs() {
        try (Statement statement = conn.getConnection().createStatement()) {
            String sb1 = "select * from  " + TABLE_JOBS +
                    " WHERE UPPER(" +
                    COLUMN_COMPLETE_TIME +
                    ") = UPPER('NULL')";
            System.out.println(sb1);
            statement.execute(sb1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Discounts related code
    public static int insertDiscount(String type) throws SQLException {
        try (PreparedStatement insertIntoDiscount = conn.getConnection().prepareStatement(insertDiscount, Statement.RETURN_GENERATED_KEYS)
        ) {

            // Insert discount

            insertIntoDiscount.setString(1, type);

            int affectedRows = insertIntoDiscount.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert Discount");
            }
            ResultSet generatedKeys = insertIntoDiscount.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for Discount");
            }
        }
    }

    public static void insertVariableDiscount(int discount, double rate, int tId) {
        try (Connection connection = conn.getConnection();
             PreparedStatement insertIntoVariable = connection.prepareStatement(insertVariable, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);



            // Insert variable discount
            insertIntoVariable.setDouble(1, rate);
            insertIntoVariable.setInt(2, discount);
            insertIntoVariable.setInt(3, tId);

            int affectedRows = insertIntoVariable.executeUpdate();
            if (affectedRows == 1) {
                connection.commit();
            } else {
                throw new SQLException("The discount insert failed.");

            }
        } catch (Exception e) {
            System.out.println("Insert job exception: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                conn.getConnection().rollback();
            } catch (SQLException e2) {
                System.out.println("Oh boy! Things are really bad! " + e2.getMessage());
            }
        } finally {
            try {
                System.out.println("Resetting default commit behavior");
                conn.getConnection().setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Couldn't reset auto-commit! " + e.getMessage());
            }

        }
    }

    public static void insertFixedDiscount(String type, double rate) throws SQLException {
        try (
                Connection connection = conn.getConnection();
                PreparedStatement insertIntoFixed = connection.prepareStatement(insertFixed)) {
            connection.setAutoCommit(false);

            int discount = insertDiscount(type);
            // Insert fixed discount
            insertIntoFixed.setDouble(1, rate);
            insertIntoFixed.setInt(2, discount);

            int affectedRows = insertIntoFixed.executeUpdate();
            if (affectedRows == 1) {
                connection.commit();
            } else {
                throw new SQLException("The discount insert failed.");

            }
        } catch (Exception e) {
            System.out.println("Insert job exception: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                conn.getConnection().rollback();
            } catch (SQLException e2) {
                System.out.println("Oh boy! Things are really bad! " + e2.getMessage());
            }
        } finally {
            try {
                System.out.println("Resetting default commit behavior");
                conn.getConnection().setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Couldn't reset auto-commit! " + e.getMessage());
            }

        }
    }

    public static void insertFlexibleDiscount(int discount, double rate, double range) {
        try (Connection connection = conn.getConnection();
             PreparedStatement insertIntoFlexible = connection.prepareStatement(insertFlexible)
        ) {
            connection.setAutoCommit(false);



            // Insert variable discount
            insertIntoFlexible.setDouble(1, rate);
            insertIntoFlexible.setInt(2, discount);
            insertIntoFlexible.setDouble(3, range);
            int affectedRows = insertIntoFlexible.executeUpdate();
            if (affectedRows == 1) {
                connection.commit();
            } else {
                throw new SQLException("The discount insert failed.");

            }
        } catch (Exception e) {
            System.out.println("Insert job exception: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                conn.getConnection().rollback();
            } catch (SQLException e2) {
                System.out.println("Oh boy! Things are really bad! " + e2.getMessage());
            }
        } finally {
            try {
                System.out.println("Resetting default commit behavior");
                conn.getConnection().setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Couldn't reset auto-commit! " + e.getMessage());
            }

        }
    }

    public static List<Discount> queryDiscounts() {
        try (Statement statement = conn.getConnection().createStatement();
             ResultSet results = statement.executeQuery("SELECT * from " + TABLE_DISCOUNT)
        ) {
            List<Discount> discounts = new LinkedList<>();
            while (results.next()) {
                int discountId = results.getInt(COLUMN_DISCOUNT_ID);
                String description = results.getString(COLUMN_DISCOUNT_TYPE);
                Discount discount = new Discount(discountId, description);
                discounts.add(discount);
            }
            return discounts;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<FlexibleDiscountPlan> queryFlexiDiscounts() {
        try (Statement statement = conn.getConnection().createStatement();
             ResultSet results = statement.executeQuery("SELECT * from " + TABLE_FLEXIBLE)
        ) {
            List<FlexibleDiscountPlan> discounts = new LinkedList<>();
            while (results.next()) {
                int flexiId = results.getInt(COLUMN_FLEXI_ID);
                int rate = results.getInt(COLUMN_FLEXI_RATE);
                int range = results.getInt(COLUMN_RANGE);
                int discountId = results.getInt(COLUMN_DISCOUNT_ID);
                FlexibleDiscountPlan flexibleDiscountPlan = new FlexibleDiscountPlan(discountId, range, rate, flexiId);
                discounts.add(flexibleDiscountPlan);
            }
            return discounts;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<VariableDiscountPlan> queryVariableDiscounts() {
        try (Statement statement = conn.getConnection().createStatement();
             ResultSet results = statement.executeQuery("SELECT * from " + TABLE_VARIABLE)
        ) {
            List<VariableDiscountPlan> discounts = new LinkedList<>();
            while (results.next()) {
                int variableId = results.getInt(COLUMN_VAR_ID);
                int rate = results.getInt(COLUMN_VARIABLE_RATE);
                int discountId = results.getInt(COLUMN_DISCOUNT_ID);
                int taskId = results.getInt(COLUMN_TASK_ID);

                VariableDiscountPlan variableDiscountPlan = new VariableDiscountPlan(variableId, discountId, taskId, rate);
                discounts.add(variableDiscountPlan);
            }
            return discounts;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<FixedDiscountPlan> queryFixedDiscounts() {
        try (Statement statement = conn.getConnection().createStatement();
             ResultSet results = statement.executeQuery("SELECT * from " + TABLE_FIXED)
        ) {
            List<FixedDiscountPlan> discounts = new LinkedList<>();
            while (results.next()) {
                int fixedId = results.getInt(COLUMN_FIXED_ID);
                int rate = results.getInt(COLUMN_FIXED_RATE);
                int discountId = results.getInt(COLUMN_DISCOUNT_ID);

                FixedDiscountPlan fixedDiscountPlan = new FixedDiscountPlan(rate, discountId, fixedId);
                discounts.add(fixedDiscountPlan);
            }
            return discounts;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Search for a discount
    public static Discount getDiscount(int discountId) {
        List<Discount> discount = queryDiscounts();
        if (discount == null) {
            System.out.println("No Tasks");
        } else {
            for (Discount d : discount) {
                if (d.getDiscountId() == discountId) {
                    return d;
                }
            }

        }
        return null;
    }
    //helper method to get the last discount id which was used

    public static Discount getLastDiscountFromDB() {
        List<Discount> discounts = queryDiscounts();
        if (discounts == null) {
            System.out.println("No Discounts");
            return null;
        }
        return discounts.get(discounts.size() - 1);
    }

    public static void updateDiscount(String desc, int id) {
        try (Statement statement = conn.getConnection().createStatement()) {
            String sb1 = "UPDATE " + TABLE_DISCOUNT +
                    " SET " +
                    COLUMN_DISCOUNT_TYPE +
                    " = " +
                    desc +
                    " WHERE " +
                    COLUMN_DISCOUNT_ID +
                    " = " +
                    id;
            System.out.println(sb1);
            statement.execute(sb1);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }




    //Department related code
    public static void insertDepartment(String location) throws SQLException {
        try (PreparedStatement insertIntoDepartment = conn.getConnection().prepareStatement(insertDepartment);
             PreparedStatement queryDepartment = conn.getConnection().prepareStatement(QUERY_DEPARTMENT)) {
            queryDepartment.setString(1, location);
            ResultSet results = queryDepartment.executeQuery();
            if (results.next()) {
                System.out.println("department already exists");
            } else {
                // Insert variable discount
                insertIntoDepartment.setString(1, location);

                int affectedRows = insertIntoDepartment.executeUpdate();

                if (affectedRows != 1) {
                    throw new SQLException("Couldn't insert Department");
                }
            }
        }
    }

    //    Tasks related code.
    public static void insertTasks(String desc, int did, double price, int duration) throws SQLException {
        try (PreparedStatement insertIntoTasks = conn.getConnection().prepareStatement(insertTask);
             PreparedStatement queryTasks = conn.getConnection().prepareStatement(QUERY_TASKS)) {
            queryTasks.setString(1, desc);
            ResultSet results = queryTasks.executeQuery();
            if (results.next()) {
                System.out.println("Task is available");
            } else {

                // Insert variable discount
                insertIntoTasks.setString(1, desc);
                insertIntoTasks.setInt(2, did);
                insertIntoTasks.setDouble(3, price);
                insertIntoTasks.setInt(4, duration);

                int affectedRows = insertIntoTasks.executeUpdate();

                if (affectedRows != 1) {
                    throw new SQLException("Couldn't insert Tasks");
                }


            }
        }
    }

    public static List<Task> queryTasks() {
        try (Statement statement = conn.getConnection().createStatement();
             ResultSet results = statement.executeQuery("SELECT * from " + TABLE_TASKS_AVAILABLE)
        ) {
            List<Task> tasks = new LinkedList<>();
            while (results.next()) {
                int taskId = results.getInt(COLUMN_TASK_ID);
                String description = results.getString(COLUMN_TASK_DESCRIPTION);
                int departmentId = results.getInt(COLUMN_DEPARTMENT_ID);
                double taskPrice = results.getDouble(COLUMN_TASK_PRICE);
                int duration = results.getInt(COLUMN_TASK_DURATION);

                Task task = new Task(taskId, description, departmentId, taskPrice, duration);
                tasks.add(task);
            }
            return tasks;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    // search for a task by id.
    public static Task searchTask(int taskId) {
        List<Task> tasks = queryTasks();
        if (tasks == null) {
            System.out.println("No Tasks available");
            return null;
        }
        for (Task t : tasks) {
            if (t.getTaskId() == taskId) {
                return t;
            }
        }
        return null;
    }

    //Delete a task which the company no longer provide.
    public static void removeTasks(int id) {
        try (Statement statement = conn.getConnection().createStatement()) {
            String sb1 = "delete from " + TABLE_TASKS_AVAILABLE +
                    " WHERE " +
                    COLUMN_TASK_ID +
                    " = " +
                    id;
            System.out.println(sb1);
            statement.execute(sb1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Job Tasks Related code
    public static void insertTasksAvailableJobs(int taskId, int jobId) throws SQLException {
        try (PreparedStatement insertIntoTasksAvailableJobs = conn.getConnection().prepareStatement(insertTasksJobs)) {


            // Insert job tasks
            insertIntoTasksAvailableJobs.setInt(1, taskId);
            insertIntoTasksAvailableJobs.setInt(2, jobId);


            int affectedRows = insertIntoTasksAvailableJobs.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert job task");
            }

        }
    }

    public static List<TasksJobs> queryTasksJobs() {
        try (Statement statement = conn.getConnection().createStatement();
             ResultSet results = statement.executeQuery("SELECT * from " + TABLE_JOB_TASKS)
        ) {
            List<TasksJobs> tasks = new LinkedList<>();
            while (results.next()) {
                int jobTaskId = results.getInt(COLUMN_JOB_TASK_ID);
                int taskId = results.getInt(COLUMN_TASK_ID);
                int jobId = results.getInt(COLUMN_JOB_ID);
                int staffId = results.getInt(COLUMN_STAFF_ID);
                String status = results.getString(COLUMN_TASK_STATUS);
                int time = results.getInt(COLUMN_TASK_TIME_TAKEN);
                Timestamp startTime = results.getTimestamp(COLUMN_TASK_START_TIME);
                Timestamp completeTime = results.getTimestamp(COLUMN_TASK_COMPLETE_TIME);
                String shiftTime = results.getString(COLUMN_TASK_SHIFT_TIME);
                String isComplete = results.getString(COLUMN_TASK_IS_COMPLETE);
                String isOverdue = results.getString(COLUMN_TASK_IS_OVERDUE);
                TasksJobs task = new TasksJobs(jobTaskId, taskId, jobId, staffId, status, time, startTime, completeTime, shiftTime, isComplete, isOverdue);
                tasks.add(task);
            }
            return tasks;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    //helper method to search and print all tasks in a particular job by selecting a job id.
    public static List<Task> searchTasksJobsToPrint(int jobId) {
        List<TasksJobs> jobs = queryTasksJobs();
        List<Task> tasks = queryTasks();
        List<Task> remaining = new LinkedList<>();
        if (jobs == null) {
            System.out.println("No Tasks");
            return null;
        }
        for (TasksJobs t : jobs)
            if (t.getJobId() == jobId) {
                if (tasks != null) {
                    for (Task task : tasks)
                        if (task.getTaskId() == t.getTaskId()) {
                            remaining.add(task);
                        }
                }
            }
        return remaining;

    }

    //Search through job tasks from the database.  with specific job-tasks id.
    public static TasksJobs searchTasksJobs(int jobTaskid) {
        List<TasksJobs> jobs = queryTasksJobs();
        if (jobs == null) {
            System.out.println("No Tasks in this job");
        } else {
            for (TasksJobs j : jobs) {
                if (j.getJobId() == jobTaskid) {
                    return j;
                }
            }
        }
        return null;
    }

    public static List<TasksJobs> getAllTaskInfoOnAJob(int jobId) {
        List<TasksJobs> jobs = queryTasksJobs();
        List<TasksJobs> remaining = new LinkedList<>();
        if (jobs == null) {
            System.out.println("No Tasks");
            return null;
        }
        for (TasksJobs t : jobs)
            if (t.getJobId() == jobId) {
                remaining.add(t);
            }
        return remaining;
    }

    public static void printOpenTasks(int jobId) {
        List<Task> open = searchTasksJobsToPrint(jobId);
        if (open == null) {
            System.out.println("No Tasks");
        } else {
            for (Task j : open) {
                System.out.println(j.getTaskId() + " " + j.getDescription());
            }
        }
    }

    //TODO: Put all tasks related CRUD here.
    //Update the database after starting a task
    public static void updateStartTask(String status, int staff_id, Timestamp start, String shift, int id) {
        try (Statement statement = conn.getConnection().createStatement()) {
            String sb1 = "UPDATE " + TABLE_JOB_TASKS +
                    " SET " +
                    COLUMN_TASK_STATUS +
                    " = '" +
                    status +
                    "', " +
                    COLUMN_STAFF_ID +
                    " = '" +
                    staff_id +
                    "', " +
                    COLUMN_TASK_START_TIME +
                    " = '" +
                    start +
                    "', " +
                    COLUMN_TASK_SHIFT_TIME +
                    " = '" +
                    shift +
                    "' WHERE " +
                    COLUMN_JOB_TASK_ID +
                    " = " +
                    id;
            System.out.println(sb1);
            statement.execute(sb1);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static void updateCompleteTask(String status, Timestamp completeTime, String taskIsComplete, String
            taskIsOverdue, int timeTaken, int id) {
        try (Statement statement = conn.getConnection().createStatement()) {
            String sb1 = "UPDATE " + TABLE_JOB_TASKS +
                    " SET " +
                    COLUMN_TASK_STATUS +
                    " = '" +
                    status +
                    "', " +
                    COLUMN_TASK_COMPLETE_TIME +
                    " = '" +
                    completeTime +
                    "', " +
                    COLUMN_TASK_IS_COMPLETE +
                    " = '" +
                    taskIsComplete +
                    "', " +
                    COLUMN_TASK_IS_OVERDUE +
                    " = '" +
                    taskIsOverdue +
                    "', " +
                    COLUMN_TASK_TIME_TAKEN +
                    " = " +
                    timeTaken +
                    " WHERE " +
                    COLUMN_JOB_TASK_ID +
                    " = " +
                    id;
            System.out.println(sb1);
            statement.execute(sb1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Amend a job by removing a certain task.
    public static void removeTasksByJob(int id) {
        try (Statement statement = conn.getConnection().createStatement()) {
            String sb1 = "delete from " + TABLE_JOB_TASKS +
                    " WHERE " +
                    COLUMN_JOB_ID +
                    " = " +
                    id;
            System.out.println(sb1);
            statement.execute(sb1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static Boolean insertStaffAccount(String name, String userName, String password, String address, String
            role, String phone) throws SQLException {
        try (PreparedStatement queryStaffAccount = conn.getConnection().prepareStatement((QUERY_STAFF_ACCOUNT));
             PreparedStatement insertIntoStaff = conn.getConnection().prepareStatement(insertStaff)) {

            queryStaffAccount.setString(1, userName);
            ResultSet results = queryStaffAccount.executeQuery();
            if (results.next()) {
                System.out.println("staff already exists");
            } else {
                // Insert staff_account
                insertIntoStaff.setString(1, name);
                insertIntoStaff.setString(2, userName);
                insertIntoStaff.setString(3, password);
                insertIntoStaff.setString(4, address);
                insertIntoStaff.setString(5, role);
                insertIntoStaff.setString(6, phone);

                int affectedRows = insertIntoStaff.executeUpdate();

                if (affectedRows != 1) {
                    throw new SQLException("Couldn't insert staff member");

                }
            }
        }
        return null;
    }


    public static void insertPaymentHistory(int jobId, int customerId, String cashOrCard, String cardType, String expiry, String lastDigits, double amount) throws SQLException {
        try (PreparedStatement insertIntoPayments = conn.getConnection().prepareStatement(insertPayment);
             PreparedStatement queryPayments = conn.getConnection().prepareStatement(QUERY_PAYMENTS)) {
            queryPayments.setInt(1, jobId);
            ResultSet results = queryPayments.executeQuery();
            if (results.next()) {
                System.out.println("already exists");
            } else {
                // Insert payment history
                insertIntoPayments.setInt(1, jobId);
                insertIntoPayments.setInt(2, customerId);
                insertIntoPayments.setString(3, cashOrCard);
                insertIntoPayments.setString(4, cardType);
                insertIntoPayments.setString(5, expiry);
                insertIntoPayments.setString(6, lastDigits);
                insertIntoPayments.setDouble(7, amount);

                int affectedRows = insertIntoPayments.executeUpdate();

                if (affectedRows != 1) {
                    throw new SQLException("Couldn't insert payment");
                }


            }
        }
    }

    //Invoice creation
    // takes in jobID
    public static void generateInvoice(int id) throws FileNotFoundException {

        PrintStream o = new PrintStream(new File("Invoice"+id+".txt"));
        System.setOut(o);

        List<Invoice> invoices = DbDriver.createInvoice(id);
        assert invoices != null;
        Invoice invoice1;
        invoice1 = invoices.get(0);
        System.out.println("customer id = " + invoice1.getCustomerId() + " \n" +
                "Customer Name: " + invoice1.getName() + "\n" +
                "Contact name: " + invoice1.getContact() + " \n" +
                "Address: " + invoice1.getPhoneNumber() + "\n" +
                "Phone Number: " + invoice1.getPhoneNumber() + " \n" +
                "Job Number: " + invoice1.getJobId() + "\n" +
                "1: Task ID: " + invoice1.getTaskId() + " \n" +
                "Task Description: " + invoice1.getDesc() + "\n" +
                " Task Price: " + invoice1.gettPrice());
        if (invoices.size() > 1) {
            for (int i = 1; i < invoices.size(); i++) {
                Invoice invoice = invoices.get(i);
                System.out.println(i + 1 + ": Task ID: " + invoice.getTaskId() + " \n"
                        + " Task Description: " + invoice.getDesc() + "\n" +
                        " Task Price: " + invoice.gettPrice());
                if (i == invoices.size() - 1) {
                    System.out.println(" Total Price after discount and VAT: " + invoices.get(i).getTotal());
                }
            }


        }
        o.close();
    }

    public static List<Invoice> createInvoice(int id) {
        try (PreparedStatement createIntoInvoice = conn.getConnection().prepareStatement(createInvoice)
        ) {
            createIntoInvoice.setInt(1,id);
            ResultSet results = createIntoInvoice.executeQuery();
            List<Invoice> invoices = new LinkedList<>();
            while (results.next()) {

                int customerId = results.getInt(1);
                String name = results.getString(2);
                String contact = results.getString(3);
                String address = results.getString(4);
                String phoneNumber = results.getString(5);
                String startTime = results.getString(7);
                int jobId = results.getInt(6);
                int taskId = results.getInt(8);
                String desc = results.getString(9);
                double tPrice = results.getDouble(10);
                double total = results.getDouble(11);

                Invoice invoice = new Invoice(customerId, name, contact, address, phoneNumber, startTime, jobId, taskId, desc, tPrice, total);
                invoices.add(invoice);
            }
            return invoices;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Individual Performance reports.
    public static void generateStaffReport() {
        List<IndividualPerformanceReport> reports = DbDriver.createStaffReports();
        int staffID;
        double totalTime = 0;
        double totalEffort = 0;
        int i = 0;
        Map<Integer, Double> totalTimeList = new HashMap<>();
        if (reports != null) {
            while (i < reports.size()) {
                staffID = reports.get(i).getStaff_id();
                IndividualPerformanceReport report = reports.get(i);
                if (i > 0) {
                    while (staffID == reports.get(i - 1).getStaff_id() && i < reports.size()) {
                        System.out.println("Staff ID:  = " + report.getStaff_id() + " \n" +
                                "Staff Name: " + report.getName() + "\n" +
                                "Role: " + report.getRole() + "\n" +
                                "Task Id: " + report.getTaskId() + " \n" +
                                "Department: " + report.getLocation() + "\n" +
                                "Start: " + report.getStartTime() + " \n" +
                                "Complete: " + report.getComplete() + "\n" +
                                "Time Taken: " + report.getTimeTaken() + " \n");
                        totalTime += reports.get(i).getTimeTaken();
                        i++;
                    }
                    if (i <= reports.size()) {
                        System.out.println("Total Time Spent: " + totalTime);
                        totalTimeList.put(reports.get(i - 1).getStaff_id(), totalTime);
                        if (i < reports.size())
                            totalTime = reports.get(i).getTimeTaken();
                    }
                } else {
                    System.out.println("Staff ID:  = " + report.getStaff_id() + " \n" +
                            "Staff Name: " + report.getName() + "\n" +
                            "Role: " + report.getRole() + "\n" +
                            "Task Id: " + report.getTaskId() + " \n" +
                            "Department: " + report.getLocation() + "\n" +
                            "Start: " + report.getStartTime() + " \n" +
                            "Complete: " + report.getComplete() + "\n" +
                            "Time Taken: " + report.getTimeTaken() + " \n");
                    totalTime += reports.get(i).getTimeTaken();
                    i++;
                }
            }
        }
        for (Map.Entry<Integer, Double> entry : totalTimeList.entrySet()) {
            totalEffort += entry.getValue();
            System.out.println("total effort: " + totalEffort);

        }
    }

    public static List<IndividualPerformanceReport> createStaffReports() {
        try (Statement statement = conn.getConnection().createStatement();
             ResultSet results = statement.executeQuery(createIndividualReport)
        ) {
            List<IndividualPerformanceReport> reports = new LinkedList<>();
            while (results.next()) {

                int staffId = results.getInt(1);
                String name = results.getString(2);
                String role = results.getString(3);
                int taskID = results.getInt(4);
                String location = results.getString(5);
                String startTime = results.getString(6);
                String completeTime = results.getString(7);
                double timeTaken = results.getInt(8);

                IndividualPerformanceReport report = new IndividualPerformanceReport(staffId, name, role, taskID, location, startTime, completeTime, timeTaken);
                reports.add(report);

            }
            return reports;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    //Individual Performance reports.
    // takes in staff id
    public static void generateIndividualStaffReport(int id) throws SQLException, FileNotFoundException {

        PrintStream o = new PrintStream(new File("StaffReport"+id+".txt"));
        System.setOut(o);

        List<IndividualPerformanceReport> reports = DbDriver.createIndividualStaffReports(id);
        int staffID;
        double totalTime = 0;
        double totalEffort = 0;
        int i = 0;
        Map<Integer, Double> totalTimeList = new HashMap<>();
        if (reports != null) {
            while (i < reports.size()) {
                staffID = reports.get(i).getStaff_id();
                IndividualPerformanceReport report = reports.get(i);
                if (i > 0) {
                    while (staffID == reports.get(i - 1).getStaff_id() && i < reports.size()) {
                        System.out.println("Staff ID:  = " + report.getStaff_id() + " \n" +
                                "Staff Name: " + report.getName() + "\n" +
                                "Role: " + report.getRole() + "\n" +
                                "Task Id: " + report.getTaskId() + " \n" +
                                "Department: " + report.getLocation() + "\n" +
                                "Start: " + report.getStartTime() + " \n" +
                                "Complete: " + report.getComplete() + "\n" +
                                "Time Taken: " + report.getTimeTaken() + " \n");
                        totalTime += reports.get(i).getTimeTaken();
                        i++;
                    }
                    if (i <= reports.size()) {
                        System.out.println("Total Time Spent: " + totalTime);
                        totalTimeList.put(reports.get(i - 1).getStaff_id(), totalTime);
                        if (i < reports.size())
                            totalTime = reports.get(i).getTimeTaken();
                    }
                } else {
                    System.out.println("Staff ID:  = " + report.getStaff_id() + " \n" +
                            "Staff Name: " + report.getName() + "\n" +
                            "Role: " + report.getRole() + "\n" +
                            "Task Id: " + report.getTaskId() + " \n" +
                            "Department: " + report.getLocation() + "\n" +
                            "Start: " + report.getStartTime() + " \n" +
                            "Complete: " + report.getComplete() + "\n" +
                            "Time Taken: " + report.getTimeTaken() + " \n");
                    totalTime += reports.get(i).getTimeTaken();
                    i++;

                }
            }
        }
        for (Map.Entry<Integer, Double> entry : totalTimeList.entrySet()) {
            totalEffort += entry.getValue();
            System.out.println("total effort: " + totalEffort);

        }
        o.close();
    }

    public static List<IndividualPerformanceReport> createIndividualStaffReports(int id) throws SQLException {
        try(PreparedStatement check = conn.getConnection().prepareStatement(createIndividualStaffReport)) {
            check.setInt(1, id);
            ResultSet results = check.executeQuery();
            if (results.next()) {
                List<IndividualPerformanceReport> reports = new LinkedList<>();
                while (results.next()) {

                    int staffId = results.getInt(1);
                    String name = results.getString(2);
                    String role = results.getString(3);
                    int taskID = results.getInt(4);
                    String location = results.getString(5);
                    String startTime = results.getString(6);
                    String completeTime = results.getString(7);
                    double timeTaken = results.getInt(8);

                    IndividualPerformanceReport report = new IndividualPerformanceReport(staffId, name, role, taskID, location, startTime, completeTime, timeTaken);
                    reports.add(report);


                }return reports;

            } else {
                System.out.println("Staff member is a bad team player, has not done any work.");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }return null;
    }



    public static List<SummaryReport> createSummaryReports(String From, String To) {
        Date d = Date.valueOf(From);
        Date d1 = Date.valueOf(To);
        try (Statement statement = conn.getConnection().createStatement();

             ResultSet results = statement.executeQuery("SELECT CAST((" + TABLE_JOB_TASKS + "." + COLUMN_TASK_START_TIME + ") AS DATE) AS DATE, " + TABLE_DEPARTMENT + "." + COLUMN_LOCATION +
                     " ,SUM(" + TABLE_JOB_TASKS + "." + COLUMN_TASK_TIME_TAKEN +
                     ") AS TOTAL_TIME " +
                     " FROM (( " + TABLE_JOB_TASKS + "" +
                     " INNER JOIN " + TABLE_TASKS_AVAILABLE + " ON " + TABLE_JOB_TASKS + "." + COLUMN_TASK_ID + " = " + TABLE_JOB_TASKS + "." + COLUMN_TASK_ID + ")" +
                     " INNER JOIN " + TABLE_DEPARTMENT + " ON " + TABLE_TASKS_AVAILABLE + "." + COLUMN_DEPARTMENT_ID + " = " + TABLE_DEPARTMENT + "." + COLUMN_DEPARTMENT_ID + ")" +
                     " WHERE CAST((" + TABLE_JOB_TASKS + "." + COLUMN_TASK_START_TIME + ") AS DATE) BETWEEN '" + d + "' AND '" + d1 + "'" +

                     " GROUP BY DATE, " + TABLE_DEPARTMENT + "." + COLUMN_LOCATION +
                     " ORDER BY " + TABLE_DEPARTMENT + "." + COLUMN_LOCATION)
        ) {
            List<SummaryReport> reports = new LinkedList<>();
            while (results.next()) {

                Date date = results.getDate(1);
                String location = results.getString(2);
                double totalTime = results.getDouble(3);


                SummaryReport report = new SummaryReport(date, location, totalTime);
                reports.add(report);

            }
            return reports;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Individual Performance reports.
    public static void generateSummaryReport(String From, String To) throws FileNotFoundException {

        PrintStream o = new PrintStream(new File("SummaryReport"+From +"_"+To+".txt"));
        System.setOut(o);

        List<SummaryReport> s = DbDriver.createSummaryReports(From, To);

        String location = null;

        double totalTimeForShift = 0;
        double totalHoursForAllJobs = 0;

        Map<String, Double> totalTimeList = new HashMap<>();
        if (s != null) {
            for (SummaryReport summaryReport : s) {
                if (summaryReport.getLocation().equalsIgnoreCase(location)) {
                    System.out.println(
                            "DATE: " + summaryReport.getDate() + " \n" +
                                    "LOCATION: " + summaryReport.getLocation() + "\n" +
                                    "TOTAL TIME: " + summaryReport.getTotalTime() + "\n");
                    location = summaryReport.getLocation();
                    totalTimeForShift += summaryReport.getTotalTime();

                } else if (!summaryReport.getLocation().equalsIgnoreCase(location)) {
                    System.out.println("Total Time Spent: " + totalTimeForShift + "\n");

                    totalTimeList.put(location, totalTimeForShift);
                    totalTimeForShift = summaryReport.getTotalTime();
                    location = summaryReport.getLocation();
                    System.out.println(
                            "DATE: " + summaryReport.getDate() + " \n" +
                                    "LOCATION: " + summaryReport.getLocation() + "\n" +
                                    "TOTAL TIME: " + summaryReport.getTotalTime() + "\n");

                }


            }
        }
        totalTimeList.put(location, totalTimeForShift);
        for (Map.Entry<String, Double> entry : totalTimeList.entrySet()) {
            totalHoursForAllJobs += entry.getValue();


        }
        System.out.println("total shift hours: " + totalHoursForAllJobs);
        o.close();

    }




    //    public static ObservableList<Task> queryTasksObservable() {
//        try (Statement statement = conn.getConnection().createStatement();
//             ResultSet results = statement.executeQuery("SELECT * from " + TABLE_TASKS_AVAILABLE)
//        ) {
//            List<Task> tasks = new LinkedList<>();
//            while (results.next()) {
//                int taskId = results.getInt(COLUMN_TASK_ID);
//                String description = results.getString(COLUMN_TASK_DESCRIPTION);
//                int departmentId = results.getInt(COLUMN_DEPARTMENT_ID);
//                float taskPrice = results.getFloat(COLUMN_TASK_PRICE);
//                int duration = results.getInt(COLUMN_TASK_DURATION);
//
//                Task task = new Task(taskId, description, departmentId, taskPrice, duration);
//                tasks.add(task);
//            }
//            return tasks;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }


    public static Boolean deleteTask(int x) {

        try (Statement statement = conn.getConnection().createStatement();) {
            StringBuilder sb = new StringBuilder("delete from ");
            sb.append(TABLE_TASKS_AVAILABLE);
            sb.append(" WHERE ");
            sb.append(COLUMN_TASK_ID);
            sb.append(" = '");
            sb.append(x);
            sb.append("' ");
            String sb1 = sb.toString();
            System.out.println(sb1);
            statement.executeUpdate(sb1);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //This will return a job which is searched by id.
    public static Boolean searchJob(int searchedJob){
        List<Job> jobs = queryJobs();
        if(jobs == null){
            System.out.println("No Jobs");
            return false;
        }
        for(Job j: jobs){
            if (j.getJobId() == searchedJob){
                return true;
            }
        }return false;
    }

    public static Boolean verifyLogin(String x, String y) {
        try (Statement statement = conn.getConnection().createStatement();) {
            StringBuilder sb = new StringBuilder("SELECT count(1) FROM ");
            sb.append(TABLE_STAFF_ACCOUNT);
            sb.append(" WHERE ");
            sb.append(COLUMN_USER_NAME);
            sb.append(" = '");
            sb.append(x);
            sb.append("' AND ");
            sb.append(COLUMN_STAFF_PASSWORD);
            sb.append(" = '");
            sb.append(y);
            sb.append("'");
            String sb1 = sb.toString();
            System.out.println("SELECT count(1) FROM STAFF_ACCOUNT WHERE USER_NAME = '" + "USERNAME" + "' AND PASSWORD ='" + "PASS" + "'");
            System.out.println(sb1);
            ResultSet result = statement.executeQuery(sb1);
            while (result.next()) {
                if (result.getInt(1) == 1) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    public static Boolean updateStaffAccount(String id, String name, String userName, String password, String address, String role, String phone) {
        try (Statement statement = conn.getConnection().createStatement();) {
            StringBuilder sb = new StringBuilder("UPDATE ");
            sb.append(TABLE_STAFF_ACCOUNT);
            sb.append(" SET ");
            sb.append(COLUMN_STAFF_NAME);
            sb.append(" = '");
            sb.append(name);
            sb.append("', ");
            sb.append(COLUMN_USER_NAME);
            sb.append(" = '");
            sb.append(userName);
            sb.append("', ");
            sb.append(COLUMN_STAFF_PASSWORD);
            sb.append(" = '");
            sb.append(password);
            sb.append("', ");
            sb.append(COLUMN_STAFF_ADDRESS);
            sb.append(" = '");
            sb.append(address);
            sb.append("', ");
            sb.append(COLUMN_STAFF_ROLE);
            sb.append(" = '");
            sb.append(role);
            sb.append("', ");
            sb.append(COLUMN_STAFF_PHONE_NUMBER);
            sb.append(" = '");
            sb.append(phone);
            sb.append("' WHERE ");
            sb.append(COLUMN_STAFF_ID);
            sb.append(" = ");
            sb.append(id);
            String sb1 = sb.toString();
            System.out.println(sb1);
            statement.execute(sb1);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean updateCustomerAccount(String id, String CustomerName, String Title,String FirstName,
                                                String LastName,String Address, String City, String PostCode, String
                                                        Email, String PhoneNumber) {
        try (Statement statement = conn.getConnection().createStatement();) {
            StringBuilder sb = new StringBuilder("UPDATE ");
            sb.append(TABLE_CUSTOMER_ACCOUNT);
            sb.append(" SET ");
            sb.append(COLUMN_CUSTOMER_NAME);
            sb.append(" = '");
            sb.append(CustomerName);
            sb.append("', ");
            sb.append(COLUMN_CONTACT_TITLE);
            sb.append(" = '");
            sb.append(Title);
            sb.append("', ");
            sb.append(COLUMN_CONTACT_FIRST_NAME);
            sb.append(" = '");
            sb.append(FirstName);
            sb.append("', ");
            sb.append(COLUMN_CONTACT_LAST_NAME);
            sb.append(" = '");
            sb.append(LastName);
            sb.append("', ");
            sb.append(COLUMN_ADDRESS);
            sb.append(" = '");
            sb.append(Address);
            sb.append("', ");
            sb.append(COLUMN_CITY);
            sb.append(" = '");
            sb.append(City);
            sb.append("', ");
            sb.append(COLUMN_POSTCODE);
            sb.append(" = '");
            sb.append(PostCode);
            sb.append("', ");
            sb.append(COLUMN_EMAIL_ADDRESS);
            sb.append(" = '");
            sb.append(Email);
            sb.append("', ");
            sb.append(COLUMN_PHONE_NUMBER);
            sb.append(" = '");
            sb.append(PhoneNumber);
            sb.append("' WHERE ");
            sb.append(COLUMN_ACCOUNT_NUMBER);
            sb.append(" = ");
            sb.append(id);
            String sb1 = sb.toString();
            System.out.println(sb1);
            statement.execute(sb1);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static Boolean deleteStaffAccount(String x) {

        try (Statement statement = conn.getConnection().createStatement();) {
            StringBuilder sb = new StringBuilder("delete from ");
            sb.append(TABLE_STAFF_ACCOUNT);
            sb.append(" WHERE ");
            sb.append(COLUMN_STAFF_ID);
            sb.append(" = '");
            sb.append(x);
            sb.append("' ");
            String sb1 = sb.toString();
            System.out.println(sb1);
            statement.executeUpdate(sb1);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Boolean searchStaffAccount(String x) {
        try (Statement statement = conn.getConnection().createStatement();) {
            StringBuilder sb = new StringBuilder("select count(1) from  ");
            sb.append(TABLE_STAFF_ACCOUNT);
            sb.append(" WHERE ");
            sb.append(COLUMN_STAFF_ID);
            sb.append(" = '");
            sb.append(x);
            sb.append("' ");
            String sb1 = sb.toString();
            System.out.println(sb1);
            ResultSet result = statement.executeQuery(sb1);
            while (result.next()) {
                if (result.getInt(1) == 1) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean searchCustomerAccount(String id) {
        List<CustomerAccount> customers = queryCustomers();
        if (customers == null) {
            System.out.println("No customers");
            return false;
        }
        for (CustomerAccount c : customers) {
            if (c.getCustomerId() == Integer.parseInt(id)) {
                return true;
            }
        }
        return false;
    }

    public static Boolean deleteCustomerAccount(String x) {

        try (Statement statement = conn.getConnection().createStatement();) {
            StringBuilder sb = new StringBuilder("delete from ");
            sb.append(TABLE_CUSTOMER_ACCOUNT);
            sb.append(" WHERE ");
            sb.append(COLUMN_ACCOUNT_NUMBER);
            sb.append(" = '");
            sb.append(x);
            sb.append("' ");
            String sb1 = sb.toString();
            System.out.println(sb1);
            statement.executeUpdate(sb1);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean removeFlexibleDiscount(double rate, int Did, float range) {

        try (Statement statement = conn.getConnection().createStatement();) {
            StringBuilder sb = new StringBuilder("delete from ");
            sb.append(TABLE_FLEXIBLE);
            sb.append(" WHERE ");
            sb.append(COLUMN_FLEXI_RATE);
            sb.append(" = ");
            sb.append(rate);
            sb.append(" AND ");
            sb.append(COLUMN_DISCOUNT_ID);
            sb.append(" = ");
            sb.append(Did);
            sb.append(" AND ");
            sb.append(COLUMN_RANGE);
            sb.append(" = ");
            sb.append(range);
           // sb.append("' ");
            String sb1 = sb.toString();
            System.out.println(sb1);
            statement.executeUpdate(sb1);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean removeVariableDiscount(double rate, int Did, int TaskId) {

        try (Statement statement = conn.getConnection().createStatement();) {
            StringBuilder sb = new StringBuilder("delete from ");
            sb.append(TABLE_VARIABLE);
            sb.append(" WHERE ");
            sb.append(COLUMN_VARIABLE_RATE);
            sb.append(" = ");
            sb.append(rate);
            sb.append(" And ");
            sb.append(COLUMN_DISCOUNT_ID);
            sb.append(" = ");
            sb.append(Did);
            sb.append(" AND ");
            sb.append(COLUMN_TASK_ID);
            sb.append(" = ");
            sb.append(TaskId);
//            sb.append(" AND ");
            String sb1 = sb.toString();
            System.out.println(sb1);
            statement.executeUpdate(sb1);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    //testing purposes to print out staff account and see how it changes with each action
    public static void printStaff() {
        try (Statement statement = conn.getConnection().createStatement();) {
            StringBuilder sb = new StringBuilder("select * from ");
            sb.append(TABLE_STAFF_ACCOUNT);

            String sb1 = sb.toString();
            System.out.println(sb1);
//            statement.executeUpdate(sb1);
            ResultSet result1 = statement.executeQuery(sb1);
            while (result1.next()) {
                String staff11 = result1.getString(1);
                String staff21 = result1.getString(2);
                String staff31 = result1.getString(3);
                String staff41 = result1.getString(4);
                String staff51 = result1.getString(5);
                String staff61 = result1.getString(6);
                System.out.println(staff11 +"\n"+ staff21 +"\n"+ staff31 +"\n"+ staff41 +"\n"+ staff51 +"\n"+ staff61+"\n"+"\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //testing purposes to print out customer account and see how it changes with each action
    public static void printCust() {
        try (Statement statement = conn.getConnection().createStatement();) {
            StringBuilder sb = new StringBuilder("select * from ");
            sb.append(TABLE_CUSTOMER_ACCOUNT);

            String sb1 = sb.toString();
            System.out.println(sb1);
//            statement.executeUpdate(sb1);
            ResultSet result1 = statement.executeQuery(sb1);
            while (result1.next()) {
                String staff11 = result1.getString(1);
                String staff21 = result1.getString(2);
                String staff31 = result1.getString(3);
                String staff41 = result1.getString(4);
                String staff51 = result1.getString(5);
                String staff61 = result1.getString(6);
                String staff71 = result1.getString(7);
                String staff81 = result1.getString(8);
                String staff91 = result1.getString(9);
                String staff101 = result1.getString(10);
                String staff111 = result1.getString(11);
                String staff112 = result1.getString(12);

                System.out.println(staff11 +" : "+ staff21 +" : "+ staff31 +" : "+ staff41 +" : "+ staff51 +" : "+ staff61+" : "+ staff71+" : " + staff81 +" : " + staff91 +" : " +staff101 +" : " + staff111+" : " + staff112);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //testing purposes to print out customer account and see how it changes with each action
    public static void printJobs() {
        try (Statement statement = conn.getConnection().createStatement();) {
            StringBuilder sb = new StringBuilder("select * from ");
            sb.append(TABLE_JOBS);

            String sb1 = sb.toString();
            System.out.println(sb1);
//            statement.executeUpdate(sb1);
            ResultSet result1 = statement.executeQuery(sb1);
            while (result1.next()) {
                String staff11 = result1.getString(1);
                String staff21 = result1.getString(2);
                String staff31 = result1.getString(3);
                String staff41 = result1.getString(4);
                String staff51 = result1.getString(5);
                String staff61 = result1.getString(6);
                String staff71 = result1.getString(7);
                String staff81 = result1.getString(8);
                String staff91 = result1.getString(9);
                String staff101 = result1.getString(10);
                String staff111 = result1.getString(11);
                String staff112 = result1.getString(12);

                System.out.println(staff11 +" : "+ staff21 +" : "+ staff31 +" : "+ staff41 +" : "+ staff51 +" : "+ staff61+" : "+ staff71+" : " + staff81 +" : " + staff91 +" : " +staff101 +" : " + staff111 +" : " + staff112);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //testing purposes to print out customer account and see how it changes with each action
    public static void printTasks() {
        try (Statement statement = conn.getConnection().createStatement();) {
            StringBuilder sb = new StringBuilder("select * from ");
            sb.append(TABLE_TASKS_AVAILABLE);

            String sb1 = sb.toString();
            System.out.println(sb1);
//            statement.executeUpdate(sb1);
            ResultSet result1 = statement.executeQuery(sb1);
            while (result1.next()) {
                String staff11 = result1.getString(1);
                String staff21 = result1.getString(2);
                String staff31 = result1.getString(3);
                String staff41 = result1.getString(4);
                String staff51 = result1.getString(5);


                System.out.println(staff11 +" : "+ staff21 +" : "+ staff31 +" : "+ staff41 +" : "+ staff51);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}











