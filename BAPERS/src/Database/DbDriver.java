package Database;

import Customer.CustomerAccount;
import Discount.*;
import JobTasks.Job;
import JobTasks.Task;
import JobTasks.TasksJobs;
import Discount.FlexibleDiscountPlan;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;



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
    public static final String COLUMN_STAFF_ID = "STAFF_ID";
    public static final String COLUMN_STAFF_NAME = "STAFF_NAME";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_STAFF_PASSWORD = "STAFF_PASSWORD";
    public static final String COLUMN_STAFF_ADDRESS = "STAFF_ADDRESS";
    public static final String COLUMN_STAFF_ROLE = "STAFF_ROLE";
    public static final String COLUMN_STAFF_PHONE_NUMBER = "STAFF_PHONE_NUMBER";
    //Create Tasks table variables.
    public static final String TABLE_TASKS_AVAILABLE = "TASKS_AVAILABLE";
    public static final String COLUMN_TASK_ID = "TASK_ID";
    public static final String COLUMN_TASK_DESCRIPTION = "TASK_DESCRIPTION";
    public static final String COLUMN_TASK_PRICE = "TASK_PRICE";
    public static final String COLUMN_TASK_DURATION = "TASK_DURATION";
    //Create Jobs table variables.
    public static final String TABLE_JOBS = "JOBS";
    public static final String COLUMN_JOB_ID = "JOB_ID";
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

    //Create Department table variables.
    public static final String TABLE_DEPARTMENT = "DEPARTMENT";
    public static final String COLUMN_DEPARTMENT_ID = "DEPARTMENT_ID";
    public static final String COLUMN_LOCATION = "LOCATION";
    //Create Tasks available jobs table variables.
    public static final String TABLE_TASKS_AVAILABLE_JOBS = "TASK_AVAILABLE_JOBS";
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
//Establish DB Connection.
    private static final DBConnection conn = new DBConnection();


    public static final String QUERY_CUSTOMER = "SELECT * FROM " +
            TABLE_CUSTOMER_ACCOUNT + " WHERE " + COLUMN_PHONE_NUMBER + " = ?";
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
            COLUMN_STAFF_ID_START + ',' + COLUMN_TOTAL_PRICE + ")" + "values (?,?,?,?,?,?,?)";

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
    public static final String insertTasksJobs = "insert into " + TABLE_TASKS_AVAILABLE_JOBS + "(" + COLUMN_TASK_ID + ',' +
            COLUMN_JOB_ID + ")" + "values (?,?)";
    public static final String insertPayment = "insert into " + TABLE_PAYMENT_HISTORY + "(" + COLUMN_JOB_ID + ',' + COLUMN_ACCOUNT_NUMBER + ',' + COLUMN_CASH_OR_CARD + ',' +
            COLUMN_CARD_TYPE + ',' + COLUMN_EXPIRY_DATE + ',' + COLUMN_LAST_4_DIGITS + ',' + COLUMN_AMOUNT +
            ")" + "values (?,?,?,?,?,?,?)";
    public static final String insertStaff = "insert into " + TABLE_STAFF_ACCOUNT + "(" + COLUMN_STAFF_NAME + ',' +
            COLUMN_USER_NAME + ',' + COLUMN_STAFF_PASSWORD + ',' + COLUMN_STAFF_ADDRESS + ',' + COLUMN_STAFF_ROLE + ',' + COLUMN_STAFF_PHONE_NUMBER + ")" +
            "values (?,?,?,?,?,?)";

//    private static PreparedStatement queryCustomer;
//    private static PreparedStatement insertIntoCustomer;
//    private static PreparedStatement insertIntoJob;
//    private static PreparedStatement insertIntoDiscount;
//    private static PreparedStatement insertIntoVariable;
//    private static PreparedStatement insertIntoFlexible;
//    private static PreparedStatement insertIntoFixed;
//    private static PreparedStatement insertIntoDepartment;
//    private static PreparedStatement insertIntoTasks;
//    private static PreparedStatement insertIntoTasksAvailableJobs;
//    private static PreparedStatement insertIntoPayments;
//    private static PreparedStatement insertIntoStaff;
//    private static PreparedStatement queryDepartment;
//    private static PreparedStatement queryPayments;
//    private static PreparedStatement queryStaffAccount;
//    private static PreparedStatement queryTasks;

//
//
//    public void close() {
//        try {
//
//            if (insertIntoCustomer != null) {
//                insertIntoCustomer.close();
//            }
//
//            if (queryCustomer != null) {
//                queryCustomer.close();
//            }
//
//
//            if (conn != null) {
//                conn.close();
//            }
//        } catch (SQLException e) {
//            System.out.println("Couldn't close connection: " + e.getMessage());
//        }
//    }

    public static void main(String[] args) throws SQLException {


        //By putting statement  in the parenthesis, there is no need to close statement at the end.
        try {


            Statement statement = conn.getConnection().createStatement();
            // For testing purposes, delete all tables before running code.
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_PAYMENT_HISTORY);
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_TASKS_AVAILABLE_JOBS);
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_JOBS);
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_VARIABLE);
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_TASKS_AVAILABLE);
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_DEPARTMENT);
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_STAFF_ACCOUNT);
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_CUSTOMER_ACCOUNT);
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_FLEXIBLE);
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_FIXED);
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_DISCOUNT);

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
                    COLUMN_START_TIME + " varchar(50),\n" +
                    COLUMN_JOB_DEADLINE + " varchar(50),\n" +
                    COLUMN_COMPLETE_TIME + " varchar(50),\n" +
                    COLUMN_HOURS_TO_COMPLETE + " int default 0,\n" +
                    COLUMN_STAFF_ID_START + " int,\n" +
                    COLUMN_STAFF_ID_COMPLETE + " int,\n" +
                    COLUMN_TOTAL_PRICE + " float,\n" +
                    "PRIMARY KEY (" + COLUMN_JOB_ID + "),\n" +
                    "FOREIGN KEY(" + COLUMN_ACCOUNT_NUMBER + ") REFERENCES\n" +
                    TABLE_CUSTOMER_ACCOUNT + "(" + COLUMN_ACCOUNT_NUMBER + "),\n" +
                    "FOREIGN KEY(" + COLUMN_STAFF_ID_START + ") REFERENCES\n" +
                    TABLE_STAFF_ACCOUNT + "(" + COLUMN_STAFF_ID + "),\n" +
                    "FOREIGN KEY(" + COLUMN_STAFF_ID_COMPLETE + ") REFERENCES\n" +
                    TABLE_STAFF_ACCOUNT + "(" + COLUMN_STAFF_ID + ")\n" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_TASKS_AVAILABLE_JOBS + " (\n" +
                    COLUMN_JOB_TASK_ID + " int NOT NULL auto_increment,\n" +
                    COLUMN_TASK_ID + " int NOT NULL,\n" +
                    COLUMN_JOB_ID + " int NOT NULL,\n" +
                    COLUMN_STAFF_ID + " int default 1 ,\n" +
                    COLUMN_TASK_STATUS + " varchar(255) default 'Ready to process',\n" +
                    COLUMN_TASK_TIME_TAKEN + " float,\n" +
                    COLUMN_TASK_START_TIME + " varchar(255),\n" +
                    COLUMN_TASK_COMPLETE_TIME + " varchar(255),\n" +
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
                    COLUMN_LAST_4_DIGITS + " int,\n" +
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
            int a = insertStaffAccount("hfhf", "dd", "ds", "dd", "ddd", "242323");
            int as = insertDiscount("No discount");

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
            insertTasks("Use of large copy camera",1, 19.00,120);
            insertTasks("Black and white film processing" ,2, 49.50,60);
            insertTasks("Bag up" ,3,6.00F,30);
            insertTasks("Colour film processing" ,2,80.00,90);
            insertTasks("Colour Transparency Processing",2,110.30,180);
            insertTasks("Use of small copy camera",1, 8.50,75);
            insertTasks("Mount transparencies",3,55.50,45);

//            insertFixedDiscount(3,2);
//            insertVariableDiscount(2,1,1);
//            insertFlexibleDiscount(2,3,1000);
//

            insertJob(1, 2, "yes", "2021-03-21 19:03:48.539", "2021-03-21 19:03:48.539", 1, 6.6F);
            System.out.println("Connected to Database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Below are a set of query methods to access data from the database.
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
                String start = results.getString(COLUMN_START_TIME);
                String deadline = results.getString(COLUMN_JOB_DEADLINE);
                String completeTime = results.getString(COLUMN_COMPLETE_TIME);
                int hours = results.getInt(COLUMN_HOURS_TO_COMPLETE);
                int staffIdStart = results.getInt(COLUMN_STAFF_ID_START);
                int staffIdComplete = results.getInt(COLUMN_STAFF_ID_COMPLETE);
                double price = results.getDouble(COLUMN_TOTAL_PRICE);

                Job job = new Job(jobId, accountNumber, priority, instructions, status, start, deadline, completeTime, hours, staffIdStart, price, staffIdComplete);
                jobs.add(job);
            }
            return jobs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
//    public static void queryInvoice() {
//        try (Statement statement = conn.createStatement();
//             String sql = " select c.account_number, c.customer_name,concat(c.contact_title , '  ' , c.contact_first_name ,' ', c.contact_last_name) as Contact ,
// concat(c.address , ' ' , c.city,' ', c.postcode) as Address ,c.phone_number,jobs.job_id, jobs.start_time,t.Task_id, t.task_price, jobs.total_price,
//                concat(c.contact_title , ' ' , c.contact_first_name ,' ', c.contact_last_name) as Contact
//        from (((((customer_account  c
//                inner join discount  d on c.discount_id = d.discount_id )
//        inner join variable  v on d.discount_id = v.discount_id)
//        inner join tasks_available as t on v.task_id = t.task_id)
//        inner join task_available_jobs  taj on t.task_Id = taj.task_id)
//        inner join jobs on taj.job_id = jobs.job_Id)
//        where jobs.job_id =(select max(jobs.job_id) from jobs)"
//
//        ResultSet results = statement.executeQuery(
//
//        ) {
////            List<Job> jobs = new LinkedList<>();
////            while (results.next()) {
////                int jobId = results.getInt(COLUMN_JOB_ID);
////                int accountNumber = results.getInt(COLUMN_ACCOUNT_NUMBER);
////                int priority = results.getInt(COLUMN_PRIORITY);
////                String status = results.getString(COLUMN_CURRENT_STATUS);
////                String instructions = results.getString(COLUMN_SPECIAL_INSTRUCTIONS);
////                String start = results.getString(COLUMN_START_TIME);
////                String deadline = results.getString(COLUMN_JOB_DEADLINE);
////                String completeTime = results.getString(COLUMN_COMPLETE_TIME);
////                int hours = results.getInt(COLUMN_HOURS_TO_COMPLETE);
////                int staffIdStart = results.getInt(COLUMN_STAFF_ID_START);
////                int staffIdComplete = results.getInt(COLUMN_STAFF_ID_COMPLETE);
////                double price = results.getDouble(COLUMN_TOTAL_PRICE);
////
////                Job job = new Job(jobId,accountNumber,priority,instructions,status,start,deadline,completeTime,hours,staffIdStart,price,staffIdComplete);
////                jobs.add(job);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

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

    public static List<TasksJobs> queryTasksJobs() {
        try (Statement statement = conn.getConnection().createStatement();
             ResultSet results = statement.executeQuery("SELECT * from " + TABLE_TASKS_AVAILABLE_JOBS)
        ) {
            List<TasksJobs> tasks = new LinkedList<>();
            while (results.next()) {
                int jobTaskId = results.getInt(COLUMN_JOB_TASK_ID);
                int taskId = results.getInt(COLUMN_TASK_ID);
                int jobId = results.getInt(COLUMN_JOB_ID);
                int staffId = results.getInt(COLUMN_STAFF_ID);
                String status = results.getString(COLUMN_TASK_STATUS);
                int time = results.getInt(COLUMN_TASK_TIME_TAKEN);
                String startTime = results.getString(COLUMN_TASK_START_TIME);
                String completeTime = results.getString(COLUMN_TASK_COMPLETE_TIME);
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

//    Insert statement to enter data into individual tables  into the database.

    //Insert statement to enter data into individual tables  into the database.
    public static int insertCustomer(String cName, String title, String firstName, String lastName, String address, String City, String postcode, String email, String phone, String type) throws SQLException {
        try (PreparedStatement insertIntoCustomer = conn.getConnection().prepareStatement(INSERT_CUSTOMER, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement queryCustomer = conn.getConnection().prepareStatement(QUERY_CUSTOMER)) {
            queryCustomer.setString(1, phone);
            ResultSet results = queryCustomer.executeQuery();
            if (results.next()) {
                return results.getInt(1);
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
                    throw new SQLException("Couldn't insert artist!");
                }
                ResultSet generatedKeys = insertIntoCustomer.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Couldn't get _id for customer");
                }
            }
        }
    }


    public static int insertJob(int accountNumber, int priority, String instructions, String start, String deadline, int staffId, double price) throws SQLException {
        try (PreparedStatement insertIntoJob = conn.getConnection().prepareStatement(insertJob, Statement.RETURN_GENERATED_KEYS);
        ) {

            // Insert job
            insertIntoJob.setInt(1, accountNumber);
            insertIntoJob.setInt(2, priority);
            insertIntoJob.setString(3, instructions);
            insertIntoJob.setString(4, start);
            insertIntoJob.setString(5, deadline);
            insertIntoJob.setInt(6, staffId);
            insertIntoJob.setDouble(7, price);

            int affectedRows = insertIntoJob.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert job");
            }
            ResultSet generatedKeys = insertIntoJob.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for job");
            }
        }
    }

    public static int insertDiscount(String type) throws SQLException {
        try (PreparedStatement insertIntoDiscount = conn.getConnection().prepareStatement(insertDiscount, Statement.RETURN_GENERATED_KEYS);
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

    public static int insertVariable(double rate, int dId, int tId) throws SQLException {
        try (PreparedStatement insertIntoVariable = conn.getConnection().prepareStatement(insertVariable, Statement.RETURN_GENERATED_KEYS)) {

            // Insert variable discount
            insertIntoVariable.setDouble(1, rate);
            insertIntoVariable.setInt(2, dId);
            insertIntoVariable.setInt(3, tId);

            int affectedRows = insertIntoVariable.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert variable discount");
            }
            ResultSet generatedKeys = insertIntoVariable.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for variable discount");
            }
        }
    }

    public static int insertFixed(double rate, int dId) throws SQLException {
        try (PreparedStatement insertIntoFixed = conn.getConnection().prepareStatement(insertVariable, Statement.RETURN_GENERATED_KEYS)) {

            // Insert fixed discount
            insertIntoFixed.setDouble(1, rate);
            insertIntoFixed.setInt(2, dId);


            int affectedRows = insertIntoFixed.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert fixed discount");
            }
            ResultSet generatedKeys = insertIntoFixed.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for fixed discount");
            }
        }
    }

    public static int insertFlexible(double rate, int dId, double range) throws SQLException {
        try (PreparedStatement insertIntoFlexible = conn.getConnection().prepareStatement(insertFlexible, Statement.RETURN_GENERATED_KEYS);
            ) {
            // Insert variable discount
            insertIntoFlexible.setDouble(1, rate);
            insertIntoFlexible.setInt(2, dId);
            insertIntoFlexible.setDouble(3, range);


            int affectedRows = insertIntoFlexible.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert flexible discount");
            }
            ResultSet generatedKeys = insertIntoFlexible.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for flexible discount");
            }
        }

    }

    public static int insertDepartment(String location) throws SQLException {
        try (PreparedStatement insertIntoDepartment = conn.getConnection().prepareStatement(insertDepartment, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement queryDepartment = conn.getConnection().prepareStatement(QUERY_DEPARTMENT)) {
            queryDepartment.setString(1, location);
            ResultSet results = queryDepartment.executeQuery();
            if (results.next()) {
                return results.getInt(1);
            } else {
                // Insert variable discount
                insertIntoDepartment.setString(1, location);

                int affectedRows = insertIntoDepartment.executeUpdate();

                if (affectedRows != 1) {
                    throw new SQLException("Couldn't insert Department");
                }
                ResultSet generatedKeys = insertIntoDepartment.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Couldn't get _id for Department");
                }
            }
        }
    }


    public static int insertTasks(String desc, int did, double price, int duration) throws SQLException {
        try (PreparedStatement insertIntoTasks = conn.getConnection().prepareStatement(insertTask, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement queryTasks = conn.getConnection().prepareStatement(QUERY_TASKS)) {
            queryTasks.setString(1, desc);
            ResultSet results = queryTasks.executeQuery();
            if (results.next()) {
                return results.getInt(1);
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
                ResultSet generatedKeys = insertIntoTasks.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Couldn't get _id for task");
                }
            }
        }
    }

    public static int insertStaffAccount(String name, String userName, String password, String address, String
            role, String phone) throws SQLException {
        try (PreparedStatement queryStaffAccount = conn.getConnection().prepareStatement((QUERY_STAFF_ACCOUNT));
             PreparedStatement insertIntoStaff = conn.getConnection().prepareStatement(insertStaff, Statement.RETURN_GENERATED_KEYS)) {

            queryStaffAccount.setString(1, userName);
            ResultSet results = queryStaffAccount.executeQuery();
            if (results.next()) {
                return results.getInt(1);
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
                ResultSet generatedKeys = insertIntoStaff.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Couldn't get _id for staff account");
                }
            }
        }
    }


    public static int insertPaymentHistory(int jobId, int customerId, String cashOrCard, String cardType, String expiry, int lastDigits, double amount) throws SQLException {
        try(PreparedStatement insertIntoPayments = conn.getConnection().prepareStatement(insertPayment, Statement.RETURN_GENERATED_KEYS);
        PreparedStatement queryPayments = conn.getConnection().prepareStatement(QUERY_PAYMENTS)){

        queryPayments.setInt(1, jobId);
        ResultSet results = queryPayments.executeQuery();
        if (results.next()) {
            return results.getInt(1);
        } else {
            // Insert payment history
            insertIntoPayments.setInt(1, jobId);
            insertIntoPayments.setInt(2, customerId);
            insertIntoPayments.setString(3, cashOrCard);
            insertIntoPayments.setString(4, cardType);
            insertIntoPayments.setString(5, expiry);
            insertIntoPayments.setInt(6, lastDigits);
            insertIntoPayments.setDouble(7, amount);

            int affectedRows = insertIntoPayments.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert payment");
            }
            ResultSet generatedKeys = insertIntoPayments.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id payment");
            }
        }
    }
    }


    public static int insertTasksAvailableJobs(int taskId, int jobId) throws SQLException {
        try (PreparedStatement insertIntoTasksAvailableJobs = conn.getConnection().prepareStatement(insertTasksJobs, Statement.RETURN_GENERATED_KEYS)) {


            // Insert job tasks
            insertIntoTasksAvailableJobs.setInt(1, taskId);
            insertIntoTasksAvailableJobs.setInt(2, jobId);


            int affectedRows = insertIntoTasksAvailableJobs.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert job task");
            }
            ResultSet generatedKeys = insertIntoTasksAvailableJobs.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id job tasks");
            }
        }
    }


            //TODO: Put all customer related CRUD here.
            //Update the database when changing the customer type or discount.
            public static void updateCustomerType (String isValuable,int discountId, int cId){
                try (Statement statement = conn.getConnection().createStatement()) {
                    String sb1 = "UPDATE " + TABLE_CUSTOMER_ACCOUNT +
                            " SET " +
                            COLUMN_CUSTOMER_TYPE +
                            " = '" +
                            isValuable +
                            "', " +
                            COLUMN_DISCOUNT_ID +
                            " = " +
                            discountId +
                            " WHERE " +
                            COLUMN_ACCOUNT_NUMBER +
                            " = " +
                            cId;
                    System.out.println(sb1);
                    statement.execute(sb1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


            //TODO: Put all tasks related CRUD here.
            //Update the database after starting a task
            public static void updateStartTask (String status, String start, String shift,int id){
                try (Statement statement = conn.getConnection().createStatement()) {
                    String sb1 = "UPDATE " + TABLE_TASKS_AVAILABLE_JOBS +
                            " SET " +
                            COLUMN_TASK_STATUS +
                            " = '" +
                            status +
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
            public static void updateCompleteTask (String status, String completeTime, String taskIsComplete, String
            taskIsOverdue,int timeTaken, int id){
                try (Statement statement = conn.getConnection().createStatement()) {
                    String sb1 = "UPDATE " + TABLE_TASKS_AVAILABLE_JOBS +
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
            public static void removeTasksByJob ( int id){
                try (Statement statement = conn.getConnection().createStatement()) {
                    String sb1 = "delete from " + TABLE_TASKS_AVAILABLE_JOBS +
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
            //delete a job from the database.
            public static void removeJob ( int id){
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
            public static void updateJobPrice ( double price, int id){
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
            public static void searchOpenJobs () {
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
            //Delete a task which the company no longer provide.
            public static void removeTasks ( int id){
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

            public static void updateDiscount (String desc,int id){
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


            //This will return a job which is searched by id.
            public static Job searchJobs ( int searchedJob){
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
            //Return the last job on the database, this is in order to access the last job id.
            public static Job searchJobJustCreated () {
                List<Job> jobs = queryJobs();
                if (jobs == null) {
                    System.out.println("No Jobs");
                    return null;
                }
                return jobs.get(jobs.size() - 1);
            }
            //Search and print open jobs.
            public static List<Job> searchAllJobs () {
                List<Job> jobs = queryJobs();
                if (jobs == null) {
                    System.out.println("No Jobs");
                    return null;
                }
                return jobs;
            }

            //    helper method to check all open jobs
            public static List<Job> getOpenJobs () {
                List<Job> jobs = searchAllJobs();
                List<Job> openJobs = new LinkedList<>();
                if (jobs != null) {
                    for (Job j : jobs) {
                        if (j.getCompleteTime() == null) {
                            openJobs.add(j);
                        }
                    }
                }
                if (openJobs.size() > 0) {
                    return openJobs;
                }
                return null;
            }
            public static void printJobs (List < Job > jobs) {
                for (Job j : jobs) {
                    System.out.println(j.getJobId());
                }
            }


            //    search for a customer by id
            public static CustomerAccount searchCustomer ( int searchedCustomer){
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


            // search for a task by id.
            public static Task searchTask ( int taskId){
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


            //helper method to search and print all tasks in a particular job by selecting a job id.
            public static List<Task> searchTasksJobsToPrint ( int jobId){
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
            public static TasksJobs searchTasksJobs ( int id){
                List<TasksJobs> jobs = queryTasksJobs();
                if (jobs == null) {
                    System.out.println("No Tasks in this job");
                } else {
                    for (TasksJobs j : jobs) {
                        if (j.getJobId() == id) {
                            return j;
                        }
                    }
                }
                return null;
            }
            public static List<TasksJobs> getAllTaskInfoOnAJob ( int jobId){
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
            public static void printOpenTasks ( int jobId){
                List<Task> open = searchTasksJobsToPrint(jobId);
                if (open == null) {
                    System.out.println("No Tasks");
                } else {
                    for (Task j : open) {
                        System.out.println(j.getTaskId() + " " + j.getDescription());
                    }
                }
            }


            //Search for a discount
            public static Discount getDiscount ( int discountId){
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

            public static Discount searchLastDiscountId () {
                List<Discount> discounts = queryDiscounts();
                if (discounts == null) {
                    System.out.println("No Discounts");
                    return null;
                }
                return discounts.get(discounts.size() - 1);
            }

            public static void insertInvoice () {
            }


    }
