package Database;


import java.sql.*;

public class DbDriver {

    //Tables
    public static final String TABLE_DISCOUNT = "DISCOUNT";
    public static final String COLUMN_DISCOUNT_ID ="DISCOUNT_ID";
    public static final String COLUMN_TYPE_ID = "TYPE_ID";

    public static final String TABLE_FIXED = "FIXED";
    public static final String COLUMN_FIXED_ID =" FIXED_ID";
    public static final String COLUMN_FIXED_RATE = "FIXED_RATE";

    public static final String TABLE_FLEXIBLE = "FLEXIBLE";
    public static final String COLUMN_FLEXI_ID ="FLEXI_ID";
    
    public static final String TABLE_VARIABLE = "VARIABLE";
    public static final String COLUMN_VAR_ID = "VAR_ID";

    public static final String TABLE_CUSTOMER_ACCOUNT = "CUSTOMER_ACCOUNT";
    public static final String COLUMN_ACCOUNT_NUMBER = "ACCOUNT_NUMBER";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CONTACT_NAME = "CONTACT_NAME";
    public static final String COLUMN_ADDRESS = "ADDRESS";
    public static final String COLUMN_PHONE_NUMBER ="PHONE_NUMBER";
    public static final String COLUMN_DISCOUNT_TYPE = "DISCOUNT_TYPE";
    public static final String COLUMN_DISCOUNT_RATE = "DISCOUNT_RATE";
    public static final String COLUMN_CUSTOMER_TYPE = "CUSTOMER_TYPE";


    public static final String TABLE_STAFF_ACCOUNT = "STAFF_ACCOUNT";
    public static final String COLUMN_STAFF_ID = "STAFF_ID";
    public static final String COLUMN_STAFF_NAME = "STAFF_NAME";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_PASSWORD = "PASSWORD";
    public static final String COLUMN_STAFF_ADDRESS = "STAFF_ADDRESS";
    public static final String COLUMN_STAFF_ROLE = "STAFF_ROLE";
    public static final String COLUMN_STAFF_PHONE_NUMBER ="STAFF_PHONE_NUMBER";



    public static final String TABLE_TASKS_AVAILABLE = "TASKS_AVAILABLE";
    public static final String COLUMN_TASK_ID ="TASK_ID";
    public static final String COLUMN_TASK_DESCRIPTION = "TASK_DESCRIPTION";
    public static final String COLUMN_TASK_LOCATION ="TASK_LOCATION";
    public static final String COLUMN_TASK_PRICE = "TSK_PRICE";
    public static final String COLUMN_TASK_DURATION = "TASK_DURATION";



    public static final String TABLE_JOBS = "JOBS";
    public static final String COLUMN_JOB_ID ="JOB_ID";
    public static final String COLUMN_JOB_URGENCY = "JOB_URGENCY";
    public static final String COLUMN_START_TIME = "START_TIME";
    public static final String COLUMN_HOURS_TO_COMPLETE ="HOURS_TO_COMPLETE";
    public static final String COLUMN_TOTAL_PRICE = "TOTAL_PRICE";

    public static final String TABLE_TASKS_AVAILABLE_JOBS = "TASK_AVAILABLE_JOBS";
    public static final String COLUMN_TASK_STATUS ="TASK_STATUS";
    public static final String COLUMN_TASK_TIME_TAKEN = "TASK_TIME_TAKEN";
    public static final String COLUMN_TASK_START_TIME = "TASK_START_TIME";






    public static final String TABLE_PAYMENT_HISTORY = "PAYMENT_HISTORY";
    public static final String COLUMN_PAYMENT_ID = "PAYMENT_ID";
    public static final String COLUMN_CASH_OR_CARD = "CASH_OR_CARD";
    public static final String COLUMN_CARD_TYPE ="CARD_TYPE";
    public static final String COLUMN_EXPIRY_DATE ="EXPIRY_DATE";
    public static final String COLUMN_LAST_4_DIGITS = "LAST_4_DIGITS";
    public static final String COLUMN_AMOUNT ="AMOUNT";




    //Table_1 attributes




    public static void main(String[] args) {

        try {
            DBConnection conn = new DBConnection();
            Statement statement = conn.getConnection().createStatement();
//            statement.execute("drop table if exists " + TABLE_10);
//            statement.execute("drop table if exists " + TABLE_9);
//            statement.execute("drop table if exists " + TABLE_8);
//
//
//            statement.execute("drop table if exists " + TABLE_7);
//            statement.execute("drop table if exists " + TABLE_6);
//            statement.execute("drop table if exists " + TABLE_5);
//            statement.execute("drop table if exists " + TABLE_4);
//            statement.execute("drop table if exists " + TABLE_3);
//            statement.execute("drop table if exists " + TABLE_2);
//            statement.execute("drop table if exists " + TABLE_1);
////
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_DISCOUNT + " \n" +
                    "(\n" +
                    COLUMN_DISCOUNT_ID +" integer NOT NULL,\n" +
                    COLUMN_TYPE_ID +" integer NOT NULL,\n" +
                    "PRIMARY KEY (" + COLUMN_DISCOUNT_ID + ")\n" +
                    ")");

            statement.execute("CREATE TABLE IF NOT EXISTS " +  TABLE_FIXED + " \n" +
                    "(\n" +
                    COLUMN_FIXED_ID +" integer NOT NULL,\n" +
                    COLUMN_DISCOUNT_ID +" integer NOT NULL UNIQUE,\n" +
                    COLUMN_FIXED_RATE + " decimal(4,2),\n" +
                    "PRIMARY KEY ("+COLUMN_FIXED_ID +"),\n" +
                    "FOREIGN KEY (" + COLUMN_DISCOUNT_ID + ") REFERENCES " +TABLE_DISCOUNT +"(" + COLUMN_DISCOUNT_ID +")\n" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_FLEXIBLE + "\n" +
                    "(\n" +
                    COLUMN_FLEXI_ID + " integer NOT NULL,\n" +
                    COLUMN_DISCOUNT_ID + " integer NOT NULL UNIQUE,\n" +
                    "PRIMARY KEY (" + COLUMN_FLEXI_ID + "),\n" +
                    "FOREIGN KEY (" + COLUMN_DISCOUNT_ID + ") REFERENCES " +TABLE_DISCOUNT +"(" + COLUMN_DISCOUNT_ID +")\n" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS "+ TABLE_VARIABLE +" \n" +
                    "(\n" +
                     COLUMN_VAR_ID + " integer NOT NULL,\n" +
                    COLUMN_DISCOUNT_ID +" integer NOT NULL UNIQUE,\n" +
                    "PRIMARY KEY (" + COLUMN_VAR_ID + "),\n" +
                    "FOREIGN KEY (" + COLUMN_DISCOUNT_ID +") REFERENCES " +TABLE_DISCOUNT +"("+ COLUMN_DISCOUNT_ID +") \n" +
                    ")");

            statement.execute("CREATE TABLE IF NOT EXISTS "+ TABLE_CUSTOMER_ACCOUNT +"(\n" +
                    COLUMN_ACCOUNT_NUMBER + "  int  AUTO_INCREMENT NOT NULL,\n" +
                    COLUMN_CUSTOMER_NAME + " varchar(255),\n" +
                    COLUMN_CONTACT_NAME +" varchar(255),\n" +
                    COLUMN_ADDRESS +"  varchar(255),\n" +
                    COLUMN_PHONE_NUMBER +"  varchar(15),\n" +
                    COLUMN_DISCOUNT_TYPE +"  varchar(50) default 'no discount',\n" +
                    COLUMN_DISCOUNT_RATE + "  int default 0,\n" +
                    COLUMN_CUSTOMER_TYPE +" varchar(20) default 'normal',\n" +
                    COLUMN_DISCOUNT_ID +" integer ,\n" +
                    "PRIMARY KEY (" + COLUMN_ACCOUNT_NUMBER +"),\n" +
                    "FOREIGN KEY ("+ COLUMN_DISCOUNT_ID +") REFERENCES " +TABLE_DISCOUNT +"(" + COLUMN_DISCOUNT_ID +")\n" +
                    ")");

            statement.execute( "CREATE TABLE IF NOT EXISTS "+ TABLE_STAFF_ACCOUNT +"(\n" +
                    COLUMN_STAFF_ID + "  int  AUTO_INCREMENT NOT NULL,\n" +
                    COLUMN_STAFF_NAME +"  varchar(100),\n" +
                    COLUMN_USER_NAME + "  varchar(100),\n" +
                    COLUMN_PASSWORD +"  varchar(255),\n" +
                    COLUMN_STAFF_ADDRESS +"  varchar(255),\n" +
                    COLUMN_STAFF_ROLE + "  varchar(30),\n" +
                    COLUMN_STAFF_PHONE_NUMBER + "  varchar(15),\n" +
                    "PRIMARY KEY ("+ COLUMN_STAFF_ID +")\n" +
                    "  )");

            statement.execute(   "CREATE TABLE IF NOT EXISTS "+ TABLE_TASKS_AVAILABLE +"(\n" +
                    COLUMN_TASK_ID +"  int AUTO_INCREMENT NOT NULL,\n" +
                    COLUMN_TASK_DESCRIPTION +"  varchar(255),\n" +
                    COLUMN_TASK_LOCATION +"  varchar(50),\n" +
                    COLUMN_TASK_PRICE +"  float,\n" +
                    COLUMN_TASK_DURATION +" varchar(50),\n" +
                    "PRIMARY KEY (" + COLUMN_TASK_ID +")\n" +
                    ")");
            statement.execute(   "CREATE TABLE IF NOT EXISTS "+ TABLE_JOBS +" (\n" +
                    COLUMN_JOB_ID +" int AUTO_INCREMENT NOT NULL,\n" +
                    COLUMN_ACCOUNT_NUMBER +" int,\n" +
                    COLUMN_JOB_URGENCY +" varchar(20),\n" +
                    COLUMN_START_TIME +" TIMESTAMP,\n" +

                    COLUMN_HOURS_TO_COMPLETE +" int,\n" +
                    COLUMN_TOTAL_PRICE +" float,\n" +
                    "PRIMARY KEY (" + COLUMN_JOB_ID +"),\n" +
                    "FOREIGN KEY(" +COLUMN_ACCOUNT_NUMBER + ") REFERENCES\n" +
                     TABLE_CUSTOMER_ACCOUNT +"(" +COLUMN_ACCOUNT_NUMBER + ")\n" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS "+ TABLE_TASKS_AVAILABLE_JOBS +" (\n" +
                    COLUMN_TASK_ID +" int NOT NULL,\n" +
                    COLUMN_JOB_ID +" int NOT NULL,\n" +
                    COLUMN_STAFF_ID +" int NOT NULL,\n" +
                    COLUMN_TASK_STATUS + " varchar(255),\n" +
                    COLUMN_DISCOUNT_RATE +" varchar(255),\n" +
                    COLUMN_TASK_TIME_TAKEN +" float,\n" +
                    COLUMN_TASK_START_TIME +" TIMESTAMP,\n" +

                    "FOREIGN KEY(" + COLUMN_TASK_ID + ") REFERENCES " + TABLE_TASKS_AVAILABLE +"("+COLUMN_TASK_ID+"),\n" +
                    "FOREIGN KEY(" + COLUMN_JOB_ID +") REFERENCES " + TABLE_JOBS +"("+ COLUMN_JOB_ID +"),\n" +
                    "FOREIGN KEY("+ COLUMN_STAFF_ID+") REFERENCES " + TABLE_STAFF_ACCOUNT +"("+ COLUMN_STAFF_ID +")\n" +
                    ")");


            statement.execute( "CREATE TABLE IF NOT EXISTS "+ TABLE_PAYMENT_HISTORY +"(\n" +
                    COLUMN_PAYMENT_ID +" int NOT NULL AUTO_INCREMENT,   \n" +
                    COLUMN_JOB_ID +" int,\n" +
                    COLUMN_ACCOUNT_NUMBER+" int,\n" +
                    COLUMN_CASH_OR_CARD +" varchar(10),\n" +
                    COLUMN_CARD_TYPE+" varchar(10),\n" +
                    COLUMN_EXPIRY_DATE+" varchar(15),\n" +
                    COLUMN_LAST_4_DIGITS+" int,\n" +
                    COLUMN_AMOUNT + " int,\n" +
                    "PRIMARY KEY ("+COLUMN_PAYMENT_ID+"),\n" +
                    "FOREIGN KEY ("+COLUMN_JOB_ID +") REFERENCES " + TABLE_JOBS +"("+COLUMN_JOB_ID+"),\n" +
                    "FOREIGN KEY ("+COLUMN_ACCOUNT_NUMBER+") REFERENCES\n" +
                    TABLE_CUSTOMER_ACCOUNT +"("+COLUMN_ACCOUNT_NUMBER+")\n" +
                    ")");







            String sql = "SELECT * FROM "+ TABLE_STAFF_ACCOUNT ;
            ResultSet result = statement.executeQuery(sql);

            while (result.next()){
                String staffName = result.getString(2);
                System.out.println(staffName);
            }



                System.out.println("Connected");
                result.close();
                statement.close();
                if(conn != null)
                conn.getConnection().close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
