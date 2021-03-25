package Database;



import Customer.CustomerAccount;

import Discount.*;
import JobTasks.Job;
import JobTasks.Task;
import JobTasks.TasksJobs;
import Discount.FlexibleDiscountPlan;
import javafx.collections.ObservableList;

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
    public static final String COLUMN_STAFF_ID = "STAFFID";
    public static final String COLUMN_STAFF_NAME = "STAFF_NAME";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_PASSWORD = "PASSWORD";
    public static final String COLUMN_STAFF_ADDRESS = "STAFF_ADDRESS";
    public static final String COLUMN_STAFF_ROLE = "STAFF_ROLE";
    public static final String COLUMN_STAFF_PHONE_NUMBER = "STAFF_PHONE_NUMBER";
    //Create Tasks table variables.
    public static final String TABLE_TASKS_AVAILABLE = "TASKS_AVAILABLE";
    public static final String COLUMN_TASK_ID = "TASKID";
    public static final String COLUMN_TASK_DESCRIPTION = "TASK_DESCRIPTION";
    public static final String COLUMN_TASK_PRICE = "TSK_PRICE";
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
    private static final DBConnection conn = new DBConnection();


    public static void main(String[] args) {

        //By putting statement  in the parenthesis, there is no need to close statement at the end.
        try  {
            Statement statement = conn.getConnection().createStatement();
            // For testing purposes, delete all tables before running code.
            statement.execute("SET FOREIGN_KEY_CHECKS=0");
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
            insertStaffAccount("hfhf", "dd", "ds", "dd", "ddd", "242323");
            insertStaffAccount("tobs", "tobs", "123", "home", "Office Manager", "242323");
            insertDiscount("No discount");

            //Insert into customer account.
            insertCustomer("City, University of London", "Prof", "David", "Rhind", "Northampton Square", "London", "EC1V0HB", "David.Rhind@city.ac.uk", "02070408000", "valuable");
            insertCustomer("AirVia Ltd", "Mr", "Boris", "Berezovsky", "12 Bond Street", "London", "WC1V8HU", "Boris.B@yahoo.com", "02073218523", "valuable");
            insertCustomer("InfoPharma Ltd", "Mr", "ALex", "White", "25 Bond Street", "London", "WC1V 8LS", "Alex.Wright@infopharma.com", "02073218001", "valuable");
            insertCustomer("Hello Magazine", "Ms", "Sarah", "Brocklehurst", "12 Charter Street", "London", "W1 8NS", "Sarah.Brocklehurst@hello.com", "02034567809", "valuable");
            insertCustomer("Ms Eva Bauyer", "Ms", "Eva", "Bauyer", "1, Liverpool Street", "London", "EC2V 8NS", "eva.bauyer@gmail.com", "02085558989", "valuable");
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

//            insertFixedDiscount(3,2);
            insertVariableDiscount(2,1,1);
//            insertFlexibleDiscount(2,3,1000);


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
                float price = results.getFloat(COLUMN_TOTAL_PRICE);

                Job job = new Job(jobId,accountNumber,priority,instructions,status,start,deadline,completeTime,hours,staffIdStart,price,staffIdComplete);
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
             ResultSet results = statement.executeQuery("SELECT * from " + TABLE_TASKS_AVAILABLE)
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
    public static List<Discount> queryDiscounts() {
        try (Statement statement = conn.getConnection().createStatement();
             ResultSet results = statement.executeQuery("SELECT * from " + TABLE_DISCOUNT)
        ) {
            List<Discount> discounts = new LinkedList<>();
            while (results.next()) {
                int discountId = results.getInt(COLUMN_DISCOUNT_ID);
                String description = results.getString(COLUMN_DISCOUNT_TYPE);
                Discount discount = new Discount(discountId,description);
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

                VariableDiscountPlan variableDiscountPlan = new VariableDiscountPlan(variableId,discountId,taskId,rate);
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

                FixedDiscountPlan fixedDiscountPlan = new FixedDiscountPlan(rate,discountId,fixedId);
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
                String isOverdue= results.getString(COLUMN_TASK_IS_OVERDUE);
                TasksJobs task = new TasksJobs(jobTaskId,taskId,jobId,staffId,status,time,startTime,completeTime,shiftTime,isComplete,isOverdue);
                tasks.add(task);
            }
            return tasks;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    //Insert statement to enter data into individual tables  into the database.
    public static void insertDiscount(String discount) {
        try (Statement statement = conn.getConnection().createStatement()) {
            String sb1 = "Insert into " + TABLE_DISCOUNT +
                    "(" +
                    COLUMN_DISCOUNT_TYPE +
                    ")" +
                    "values ('" +
                    discount +
                    "')";
            statement.execute(sb1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertVariableDiscount(double rate, int dId, int tId) {
        try (Statement statement = conn.getConnection().createStatement()) {
            String sb1 = "Insert into " + TABLE_VARIABLE +
                    "(" +
                    COLUMN_VARIABLE_RATE +
                    "," +
                    COLUMN_DISCOUNT_ID +
                    "," +
                    COLUMN_TASK_ID +
                    ")" +
                    "values (" +
                    rate +
                    "," +
                    dId +
                    "," +
                    tId +
                    ")";
            statement.execute(sb1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertFixedDiscount(double rate, int Did) {
        try (Statement statement = conn.getConnection().createStatement()) {
            String sb1 = "Insert into " + TABLE_FIXED +
                    "(" +
                    COLUMN_FIXED_RATE +
                    "," +
                    COLUMN_DISCOUNT_ID +
                    ")" +
                    "values (" +
                    rate +
                    "," +
                    Did +
                    ")";
            statement.execute(sb1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertFlexibleDiscount(double rate, int Did, float range) {
        try (Statement statement = conn.getConnection().createStatement()) {
            String sb1 = "Insert into " + TABLE_FLEXIBLE +
                    "(" +
                    COLUMN_FLEXI_RATE +
                    "," +
                    COLUMN_DISCOUNT_ID +
                    "," +
                    COLUMN_RANGE +
                    ")" +
                    "values (" +
                    rate +
                    "," +
                    Did +
                    ",'" +
                    range +
                    "')";
            statement.execute(sb1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertDepartment(String location) {
        try (Statement statement = conn.getConnection().createStatement()) {
            String sb1 = "Insert into " + TABLE_DEPARTMENT +
                    "(" +
                    COLUMN_LOCATION +
                    ")" +
                    "values ('" +
                    location +
                    "')";
            statement.execute(sb1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertTasks(String desc, int did, float price, int duration) {
        try (Statement statement = conn.getConnection().createStatement()) {
            String sb1 = "insert into " + TABLE_TASKS_AVAILABLE +
                    "(" +
                    COLUMN_TASK_DESCRIPTION +
                    ',' +
                    COLUMN_DEPARTMENT_ID +
                    ',' +
                    COLUMN_TASK_PRICE +
                    ',' +
                    COLUMN_TASK_DURATION +
                    ")" +
                    "values ('" +
                    desc +
                    "', " +
                    did +
                    ", " +
                    price +
                    ", " +
                    duration +
                    ")";
            statement.execute(sb1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertCustomer(String cName, String title, String firstName, String lastName, String address, String City, String postcode, String email, String phone, String type) {
        try (Statement statement = conn.getConnection().createStatement()) {
//            Discount discount = searchDiscount();
//            int discountId= discount.getDiscountId()+1;
//            insertDiscount("no discount");
            String sb1 = "insert into " + TABLE_CUSTOMER_ACCOUNT +
                    "(" +
                    COLUMN_CUSTOMER_NAME +
                    ',' +
                    COLUMN_CONTACT_TITLE +
                    ',' +
                    COLUMN_CONTACT_FIRST_NAME +
                    ',' +
                    COLUMN_CONTACT_LAST_NAME +
                    ',' +
                    COLUMN_ADDRESS +
                    ',' +
                    COLUMN_CITY +
                    ',' +
                    (COLUMN_POSTCODE) +
                    ',' +
                    COLUMN_EMAIL_ADDRESS +
                    ',' +
                    COLUMN_PHONE_NUMBER +
                    ',' +
                    COLUMN_CUSTOMER_TYPE +
                    ")" +
                    "values ('" +
                    cName +
                    "', '" +
                    title +
                    "', '" +
                    firstName +
                    "', '" +
                    lastName +
                    "', '" +
                    address +
                    "', '" +
                    City +
                    "', '" +
                    postcode +
                    "', '" +
                    email +
                    "', '" +
                    phone +
                    "', '" +
                    type +
                    "')";
            statement.execute(sb1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void insertJob(int accountNumber, int priority, String instructions, String start, String deadline, int staffId, double price) {
        try (Statement statement = conn.getConnection().createStatement()) {
            String sb1 = "insert into " + TABLE_JOBS +
                    "(" +
                    COLUMN_ACCOUNT_NUMBER +
                    ',' +
                    COLUMN_PRIORITY +
                    ',' +
                    COLUMN_SPECIAL_INSTRUCTIONS +
                    ',' +
                    COLUMN_START_TIME +
                    ',' +
                    COLUMN_JOB_DEADLINE +
                    ',' +
                    COLUMN_STAFF_ID_START +
                    ',' +
                    COLUMN_TOTAL_PRICE +
                    ")" +
                    "values (" +
                    accountNumber +
                    ", " +
                    priority +
                    ", '" +
                    instructions +
                    "', '" +
                    start +
                    "', '" +
                    deadline +
                    "', " +
                    staffId +
                    ", " +
                    price +
                    ")";
            statement.execute(sb1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertPaymentHistory( int jobId, int customerId, String cashOrCard, String cardType,String expiry, int lastDigits,float amount) {
        try (Statement statement = conn.getConnection().createStatement()) {
            String sb1 = "insert into " + TABLE_PAYMENT_HISTORY +
                    "(" +
                    COLUMN_JOB_ID +
                    ',' +
                    COLUMN_ACCOUNT_NUMBER +
                    ',' +
                    COLUMN_CASH_OR_CARD +
                    ',' +
                    COLUMN_CARD_TYPE +
                    ',' +
                    COLUMN_EXPIRY_DATE +
                    ',' +
                    COLUMN_LAST_4_DIGITS +
                    ',' +
                    COLUMN_AMOUNT +
                    ")" +
                    "values (" +
                    jobId +
                    ", " +
                    customerId +
                    ", '" +
                    cashOrCard +
                    "', '" +
                    cardType +
                    "', '" +
                    expiry +
                    "', " +
                    lastDigits +
                    ", " +
                    amount +
                    ")";
            statement.execute(sb1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertTasksAvailableJobs(int taskId, int jobId ) {
        try (Statement statement = conn.getConnection().createStatement()) {
            String sb1 = "insert into " + TABLE_TASKS_AVAILABLE_JOBS +
                    "(" +
                    COLUMN_TASK_ID +
                    ',' +
                    COLUMN_JOB_ID +
                    ")" +
                    "values (" +
                    taskId +
                    ", " +
                    jobId +
                    ")";
            statement.execute(sb1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //TODO: Put all customer related CRUD here.
    //Update the database when changing the customer type or discount.
    public static void updateCustomerType(String isValuable, int discountId, int cId) {
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
    public static void updateStartTask(String status,String start,String shift, int id) {
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
    public static void updateCompleteTask(String status,String completeTime, String taskIsComplete,String taskIsOverdue, int timeTaken,int id) {
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
    public static void removeTasksByJob(int id) {
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
    public static Discount searchLastDiscountId() {
        List<Discount> discounts = queryDiscounts();
        if (discounts == null) {
            System.out.println("No Discounts");
            return null;
        }
        return discounts.get(discounts.size()-1);
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



    //This will return a job which is searched by id.
    public static Job searchJobs(int searchedJob){
        List<Job> jobs = queryJobs();
        if(jobs == null){
            System.out.println("No Jobs");
            return null;
        }
        for(Job j: jobs){
            if (j.getJobId() == searchedJob){
                return j;
            }
        }return null;
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
        List<Job> jobs = DbDriver.queryJobs();

        if (jobs == null) {
            System.out.println("No Jobs");
            return null;
        }
        return jobs;
    }

    public static void printOpenJobs() {
        List<Job> open = searchAllJobs();
        for (Job j : open) {
            if (j.getHours() == 0) {
                System.out.println(j.getJobId());
            }
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
                for (Task task : tasks)
                    if (task.getTaskId() == t.getTaskId()) {
                        remaining.add(task);
                    }
            }
        return remaining;

    }

    //Search through job tasks from the database.  with specific jobtasks id.
    public static TasksJobs searchTasksJobs(int id) {
        List<TasksJobs> jobs = queryTasksJobs();
        if (jobs == null) {
            System.out.println("No Tasks in this job");
            return null;
        } else {
            for (TasksJobs j : jobs) {
                if (j.getJobId() == id) {
                    return j;
                }
            }
            return null;
        }
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
        for (Task j : open) {
            System.out.println(j.getTaskId() + " " + j.getDescription());
        }
    }





    //Search for a discount
    public static Discount getDiscount(int discountId) {
        List<Discount> discount = queryDiscounts();
        for (Discount d : discount) {
            if (d.getDiscountId() == discountId) {
                return d;
            }
        }return null;
    }
    //helper method to get the last discount id which was used
    public static Discount getLastDiscountFromDB() {
        List<Discount> discounts = queryDiscounts();
        if (discounts == null) {
            System.out.println("No Discounts");
            return null;
        }
        return discounts.get(discounts.size()-1);
    }



    //added latest

    public static Boolean verifyLogin(String x, String y) {
        try (Statement statement = conn.getConnection().createStatement();) {
            StringBuilder sb = new StringBuilder("SELECT count(1) FROM ");
            sb.append(TABLE_STAFF_ACCOUNT);
            sb.append(" WHERE ");
            sb.append(COLUMN_USER_NAME);
            sb.append(" = '");
            sb.append(x);
            sb.append("' AND ");
            sb.append(COLUMN_PASSWORD);
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


    public static Boolean insertStaffAccount(String name, String userName, String password, String address, String role, String phone) {
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
            return true;

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
            sb.append(COLUMN_PASSWORD);
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

//    public static void print() {
//        try (Statement statement = conn.getConnection().createStatement();) {
//            statement.execute("SET FOREIGN_KEY_CHECKS=0");
//            StringBuilder sb = new StringBuilder("delete from ");
//            sb.append(TABLE_STAFF_ACCOUNT);
//            sb.append(" WHERE ");
//            sb.append(COLUMN_USER_NAME);
//            sb.append(" = '");
//            sb.append("username");
//            sb.append("' ");
//            String sb1 = sb.toString();
//            System.out.println(sb1);
//            statement.executeUpdate(sb1);
//            statement.execute("SET FOREIGN_KEY_CHECKS=1");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

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

                System.out.println(staff11 +" : "+ staff21 +" : "+ staff31 +" : "+ staff41 +" : "+ staff51 +" : "+ staff61+" : "+ staff71+" : " + staff81 +" : " + staff91 +" : " +staff101 +" : " + staff111);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}