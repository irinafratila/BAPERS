package Database;


import Admin.User;
import Customer.CustomerAccount;
import Discount.Discount;
import JobTasks.Job;
import JobTasks.Task;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DbDriver {

    //Create Discount table variables.
    public static final String TABLE_DISCOUNT = "DISCOUNT";
    public static final String COLUMN_DISCOUNT_ID = "DISCOUNT_ID";
    public static final String COLUMN_DISCOUNT_TYPE = "DISCOUNT_TYPE";

    public static final String TABLE_FIXED = "FIXED";
    public static final String COLUMN_FIXED_ID = " FIXED_ID";
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
    public static final String COLUMN_PASSWORD = "PASSWORD";
    public static final String COLUMN_STAFF_ADDRESS = "STAFF_ADDRESS";
    public static final String COLUMN_STAFF_ROLE = "STAFF_ROLE";
    public static final String COLUMN_STAFF_PHONE_NUMBER = "STAFF_PHONE_NUMBER";
    //Create Tasks table variables.
    public static final String TABLE_TASKS_AVAILABLE = "TASKS_AVAILABLE";
    public static final String COLUMN_TASK_ID = "TASK_ID";
    public static final String COLUMN_TASK_DESCRIPTION = "TASK_DESCRIPTION";
    public static final String COLUMN_TASK_PRICE = "TSK_PRICE";
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
    private static DBConnection conn = new DBConnection();


    public static void main(String[] args) {

        ResultSet results;
        //By putting statement  in the paranthesis, there is no need to close statement at the end.
        try  {
            Statement statement = conn.getConnection().createStatement();
            // For testing purposes, delete all tables before running code.
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_PAYMENT_HISTORY);
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_TASKS_AVAILABLE_JOBS);
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_JOBS);
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_TASKS_AVAILABLE);
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_DEPARTMENT);
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_STAFF_ACCOUNT);
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_CUSTOMER_ACCOUNT);
            statement.execute("DROP TABLE IF EXISTS  " + TABLE_VARIABLE);
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
                    COLUMN_DISCOUNT_ID + " integer NOT NULL UNIQUE,\n" +
                    COLUMN_FIXED_RATE + " decimal(4,2),\n" +
                    "PRIMARY KEY (" + COLUMN_FIXED_ID + "),\n" +
                    "FOREIGN KEY (" + COLUMN_DISCOUNT_ID + ") REFERENCES " + TABLE_DISCOUNT + "(" + COLUMN_DISCOUNT_ID + ")\n" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_FLEXIBLE + "\n" +
                    "(\n" +
                    COLUMN_FLEXI_ID + " integer NOT NULL auto_increment,\n" +
                    COLUMN_FLEXI_RATE + " integer NOT NULL,\n" +
                    COLUMN_DISCOUNT_ID + " integer NOT NULL UNIQUE,\n" +
                    "PRIMARY KEY (" + COLUMN_FLEXI_ID + "),\n" +
                    "FOREIGN KEY (" + COLUMN_DISCOUNT_ID + ") REFERENCES " + TABLE_DISCOUNT + "(" + COLUMN_DISCOUNT_ID + ")\n" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_VARIABLE + " \n" +
                    "(\n" +
                    COLUMN_VAR_ID + " integer NOT NULL auto_increment,\n" +
                    COLUMN_DISCOUNT_ID + " integer NOT NULL UNIQUE,\n" +
                    COLUMN_RANGE + " varchar(50) NOT NULL UNIQUE,\n" +
                    COLUMN_VARIABLE_RATE + " integer NOT NULL ,\n" +
                    "PRIMARY KEY (" + COLUMN_VAR_ID + "),\n" +
                    "FOREIGN KEY (" + COLUMN_DISCOUNT_ID + ") REFERENCES " + TABLE_DISCOUNT + "(" + COLUMN_DISCOUNT_ID + ") \n" +
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
                    COLUMN_DISCOUNT_ID + " integer default 0,\n" +
                    "PRIMARY KEY (" + COLUMN_ACCOUNT_NUMBER + "),\n" +
                    "FOREIGN KEY (" + COLUMN_DISCOUNT_ID + ") REFERENCES " + TABLE_DISCOUNT + "(" + COLUMN_DISCOUNT_ID + ")\n" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_STAFF_ACCOUNT + "(\n" +
                    COLUMN_STAFF_ID + "  int  AUTO_INCREMENT NOT NULL,\n" +
                    COLUMN_STAFF_NAME + "  varchar(100),\n" +
                    COLUMN_USER_NAME + "  varchar(100),\n" +
                    COLUMN_PASSWORD + "  varchar(255),\n" +
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
                    COLUMN_LAST_4_DIGITS + " int,\n" +
                    COLUMN_AMOUNT + " float,\n" +
                    "PRIMARY KEY (" + COLUMN_PAYMENT_ID + "),\n" +
                    "FOREIGN KEY (" + COLUMN_JOB_ID + ") REFERENCES " + TABLE_JOBS + "(" + COLUMN_JOB_ID + "),\n" +
                    "FOREIGN KEY (" + COLUMN_ACCOUNT_NUMBER + ") REFERENCES\n" +
                    TABLE_CUSTOMER_ACCOUNT + "(" + COLUMN_ACCOUNT_NUMBER + ")\n" +
                    ")");

            insertStaffAccount("hfhf", "dd", "ds", "dd", "ddd", "242323");
            insertDiscount("FLEXI");
            insertDiscount("FIXED");
            insertDiscount("VARIABLE");
            //Insert into customer account.
            insertCustomer("City, University of London", "Prof", "David", "Rhind", "Northampton Square", "London", "EC1V0HB", "David.Rhind@city.ac.uk", "02070408000", "valuable", 1);
            insertCustomer("AirVia Ltd", "Mr", "Boris", "Berezovsky", "12 Bond Street", "London", "WC1V8HU", "Boris.B@yahoo.com", "02073218523", "valuable", 2);
            insertCustomer("InfoPharma Ltd", "Mr", "ALex", "White", "25 Bond Street", "London", "WC1V 8LS", "Alex.Wright@infopharma.com", "02073218001", "valuable", 3);
            insertCustomer("Hello Magazine", "Ms", "Sarah", "Brocklehurst", "12 Charter Street", "London", "W1 8NS", "Sarah.Brocklehurst@hello.com", "02034567809", "valuable", 3);
            insertCustomer("Ms Eva Bauyer", "Ms", "Eva", "Bauyer", "1, Liverpool Street", "London", "EC2V 8NS", "eva.bauyer@gmail.com", "02085558989", "valuable", 1);
            /*
            //Testing joins
            statement.execute(" insert into DISCOUNT(DISCOUNT_ID,DISCOUNT_TYPE) values(1,'FLEXI')");
            statement.execute(" insert into DISCOUNT(DISCOUNT_ID,DISCOUNT_TYPE) values(2,'FLEXI')");
            statement.execute("insert into FLEXIBLE(FLEXI_ID,FLEXI_RATE,DISCOUNT_ID)values(1,3,1);");
            statement.execute("insert into FLEXIBLE(FLEXI_ID,FLEXI_RATE,DISCOUNT_ID)values(2,5,2);");
            */

            //Fill up Departments table;
            insertDepartment("COPY ROOM");
            insertDepartment("DEVELOPMENT ROOM");
            insertDepartment("FINISHING ROOM");



            //Insert into TASKS_AVAILABLE.
            insertTasks("Use of large copy camera",1, 19.00F,120);
            insertTasks("Black and white film processing" ,2, 49.50F,60);
            insertTasks("Bag up" ,3,6.00F,30);
            insertTasks("Colour film processing" ,2,80.00F,90);
            insertTasks("Colour Transparecy Processing",2,110.30F,180);
            insertTasks("Use of small copy camera",1, 8.50F,75);
            insertTasks("Mount transparencies",3,55.50F,45);






           insertJob(1, 2, "yes", "2021-03-21 19:03:48.539", "2021-03-21 19:03:48.539", 1, 6.6F);
            System.out.println("Connected to Database!");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Testing sql querys from the database.
    public static List<CustomerAccount> queryCustomers() {

        try (Statement statement = conn.getConnection().createStatement();
             ResultSet results = statement.executeQuery("SELECT * from " + TABLE_CUSTOMER_ACCOUNT);
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
                Boolean isValuable;
                if (results.getString(COLUMN_CUSTOMER_TYPE).equals("valuable")) {
                    isValuable = true;
                } else {
                    isValuable = false;
                }
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
//
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
//
//                User staff = new User(staffId,name,userName,password,address,role,phoneNumber);
//                users.add(staff);
//            }
//            return users;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//
//    }

    public static List<Job> queryJobs() {

        try (Statement statement = conn.getConnection().createStatement();
             ResultSet results = statement.executeQuery("SELECT * from " + TABLE_JOBS);
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
                String completeTine = results.getString(COLUMN_COMPLETE_TIME);
                int hours = results.getInt(COLUMN_HOURS_TO_COMPLETE);
                int staffIdStart = results.getInt(COLUMN_STAFF_ID_START);
                int staffIdComplete = results.getInt(COLUMN_STAFF_ID_COMPLETE);
                float price = results.getFloat(COLUMN_TOTAL_PRICE);



                Job job = new Job(jobId,accountNumber,priority,instructions,status,start,deadline,completeTine,hours,staffIdStart,price,staffIdComplete);
                jobs.add(job);
            }
            return jobs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }
    public static List<Task> queryTasks() {

        try (Statement statement = conn.getConnection().createStatement();
             ResultSet results = statement.executeQuery("SELECT * from " + TABLE_TASKS_AVAILABLE);
        ) {
            List<Task> tasks = new LinkedList<>();
            while (results.next()) {
                int taskId = results.getInt(COLUMN_TASK_ID);
                String description = results.getString(COLUMN_TASK_DESCRIPTION);
                int departmentId = results.getInt(COLUMN_DEPARTMENT_ID);
                float taskPrice = results.getFloat(COLUMN_TASK_PRICE);
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
    //Insert into the database.
    public static void insertDiscount(String discount) {
        try (Statement statement = conn.getConnection().createStatement();) {
            StringBuilder sb = new StringBuilder("Insert into ");
            sb.append(TABLE_DISCOUNT);
            sb.append("(");
            sb.append(COLUMN_DISCOUNT_TYPE);
            sb.append(")");
            sb.append("values ('");
            sb.append(discount);
            sb.append("')");
            String sb1 = sb.toString();
            statement.execute(sb1);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static void insertDepartment(String location) {
        try (Statement statement = conn.getConnection().createStatement();) {
            StringBuilder sb = new StringBuilder("Insert into ");
            sb.append(TABLE_DEPARTMENT);
            sb.append("(");
            sb.append(COLUMN_LOCATION);
            sb.append(")");
            sb.append("values ('");
            sb.append(location);
            sb.append("')");
            String sb1 = sb.toString();
            statement.execute(sb1);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    public static void insertTasks(String desc, int did, float price, int duration) {
        try (Statement statement = conn.getConnection().createStatement();) {
            StringBuilder sb = new StringBuilder("insert into ");
            sb.append(TABLE_TASKS_AVAILABLE);
            sb.append("(");
            sb.append(COLUMN_TASK_DESCRIPTION);
            sb.append(',');
            sb.append(COLUMN_DEPARTMENT_ID);
            sb.append(',');
            sb.append(COLUMN_TASK_PRICE);
            sb.append(',');
            sb.append(COLUMN_TASK_DURATION);

            sb.append(")");
            sb.append("values ('");
            sb.append(desc);
            sb.append("', ");
            sb.append(did);
            sb.append(", ");
            sb.append(price);
            sb.append(", ");
            sb.append(duration);

            sb.append(")");
            String sb1 = sb.toString();
            statement.execute(sb1);

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    public static void insertCustomer(String cName, String title, String firstName, String lastName, String address, String City, String postcode, String email, String phone, String type, int discountId) {
        try (Statement statement = conn.getConnection().createStatement();) {
            StringBuilder sb = new StringBuilder("insert into ");
            sb.append(TABLE_CUSTOMER_ACCOUNT);
            sb.append("(");
            sb.append(COLUMN_CUSTOMER_NAME);
            sb.append(',');
            sb.append(COLUMN_CONTACT_TITLE);
            sb.append(',');
            sb.append(COLUMN_CONTACT_FIRST_NAME);
            sb.append(',');
            sb.append(COLUMN_CONTACT_LAST_NAME);
            sb.append(',');
            sb.append(COLUMN_ADDRESS);
            sb.append(',');
            sb.append(COLUMN_CITY);
            sb.append(',');
            sb.append((COLUMN_POSTCODE));
            sb.append(',');
            sb.append(COLUMN_EMAIL_ADDRESS);
            sb.append(',');
            sb.append(COLUMN_PHONE_NUMBER);
            sb.append(',');
            sb.append(COLUMN_CUSTOMER_TYPE);
            sb.append(',');
            sb.append((COLUMN_DISCOUNT_ID));
            sb.append(")");
            sb.append("values ('");
            sb.append(cName);
            sb.append("', '");
            sb.append(title);
            sb.append("', '");
            sb.append(firstName);
            sb.append("', '");
            sb.append(lastName);
            sb.append("', '");
            sb.append(address);
            sb.append("', '");
            sb.append(City);
            sb.append("', '");
            sb.append(postcode);
            sb.append("', '");
            sb.append(email);
            sb.append("', '");
            sb.append(phone);
            sb.append("', '");
            sb.append(type);
            sb.append("', ");
            sb.append(discountId);
            sb.append(")");
            String sb1 = sb.toString();
            statement.execute(sb1);

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    public static void insertStaffAccount(String name,String userName,String password,String address,String role,String phone) {
        try (Statement statement = conn.getConnection().createStatement();) {
            StringBuilder sb = new StringBuilder("insert into ");
            sb.append(TABLE_STAFF_ACCOUNT);
            sb.append("(");
            sb.append(COLUMN_STAFF_NAME);
            sb.append(',');
            sb.append(COLUMN_USER_NAME);
            sb.append(',');
            sb.append(COLUMN_PASSWORD);
            sb.append(',');
            sb.append(COLUMN_STAFF_ADDRESS);
            sb.append(',');
            sb.append(COLUMN_STAFF_ROLE);
            sb.append(',');
            sb.append(COLUMN_STAFF_PHONE_NUMBER);
            sb.append(")");
            sb.append("values ('");
            sb.append(name);
            sb.append("', '");
            sb.append(userName);
            sb.append("', '");
            sb.append(password);
            sb.append("', '");
            sb.append(address);
            sb.append("', '");
            sb.append(role);
            sb.append("', '");
            sb.append(phone);
            sb.append("')");
            String sb1 = sb.toString();
            statement.execute(sb1);

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    public static void insertJob(int accountNumber, int priority, String instructions, String start, String deadline, int staffId, float price) {
        try (Statement statement = conn.getConnection().createStatement();) {
            StringBuilder sb = new StringBuilder("insert into ");
            sb.append(TABLE_JOBS);
            sb.append("(");
            sb.append(COLUMN_ACCOUNT_NUMBER);
            sb.append(',');
            sb.append(COLUMN_PRIORITY);
            sb.append(',');
            sb.append(COLUMN_SPECIAL_INSTRUCTIONS);
            sb.append(',');
            sb.append(COLUMN_START_TIME);
            sb.append(',');
            sb.append(COLUMN_JOB_DEADLINE);
            sb.append(',');
            sb.append(COLUMN_STAFF_ID_START);
            sb.append(',');
            sb.append(COLUMN_TOTAL_PRICE);
            sb.append(")");
            sb.append("values (");
            sb.append(accountNumber);
            sb.append(", ");
            sb.append(priority);
            sb.append(", '");
            sb.append(instructions);
            sb.append("', '");
            sb.append(start);
            sb.append("', '");
            sb.append(deadline);
            sb.append("', ");
            sb.append(staffId);
            sb.append(", ");
            sb.append(price);
            sb.append(")");
            String sb1 = sb.toString();
            statement.execute(sb1);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static void insertPaymentHistory( int jobId, int customerId, String cashOrCard, String cardType,String expiry, int lastDigits,float amount) {
        try (Statement statement = conn.getConnection().createStatement();) {
            StringBuilder sb = new StringBuilder("insert into ");
            sb.append(TABLE_PAYMENT_HISTORY);
            sb.append("(");
            sb.append(COLUMN_JOB_ID);
            sb.append(',');
            sb.append(COLUMN_ACCOUNT_NUMBER);
            sb.append(',');
            sb.append(COLUMN_CASH_OR_CARD);
            sb.append(',');
            sb.append(COLUMN_CARD_TYPE);
            sb.append(',');
            sb.append(COLUMN_EXPIRY_DATE);
            sb.append(',');
            sb.append(COLUMN_LAST_4_DIGITS);
            sb.append(',');
            sb.append(COLUMN_AMOUNT);
            sb.append(")");
            sb.append("values (");
            sb.append(jobId);
            sb.append(", ");
            sb.append(customerId);
            sb.append(", '");
            sb.append(cashOrCard);
            sb.append(", '");
            sb.append(cardType);
            sb.append("', '");
            sb.append(expiry);
            sb.append("', ");
            sb.append(lastDigits);
            sb.append(", ");
            sb.append(amount);
            sb.append(")");
            String sb1 = sb.toString();
            statement.execute(sb1);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static void insertTasksAvailableJobs(int taskId, int jobId, int staffId) {
        try (Statement statement = conn.getConnection().createStatement();) {
            StringBuilder sb = new StringBuilder("insert into ");
            sb.append(TABLE_TASKS_AVAILABLE_JOBS);
            sb.append("(");
            sb.append(COLUMN_TASK_ID);
            sb.append(',');
            sb.append(COLUMN_JOB_ID);
            sb.append(',');
            sb.append(COLUMN_STAFF_ID);
            sb.append(")");
            sb.append("values (");
            sb.append(taskId);
            sb.append(", ");
            sb.append(jobId);
            sb.append(", ");
            sb.append(staffId);
            sb.append(")");
            String sb1 = sb.toString();
            statement.execute(sb1);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    //TODO: Create inserts for discount types.

    //Update the database when changing the customer type or discount.

    public static void updateCustomerType(String isValuable, int discountId, int cId) {
        try (Statement statement = conn.getConnection().createStatement();) {
            StringBuilder sb = new StringBuilder("UPDATE ");
            sb.append(TABLE_CUSTOMER_ACCOUNT);
            sb.append(" SET ");
            sb.append(COLUMN_CUSTOMER_TYPE);
            sb.append(" = '");
            sb.append(isValuable);
            sb.append("', ");
            sb.append(COLUMN_DISCOUNT_ID);
            sb.append(" = ");
            sb.append(discountId);
            sb.append(" WHERE ");
            sb.append(COLUMN_ACCOUNT_NUMBER);
            sb.append(" = ");
            sb.append(cId);
            String sb1 = sb.toString();
            System.out.println(sb1);
            statement.execute(sb1);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }


}
