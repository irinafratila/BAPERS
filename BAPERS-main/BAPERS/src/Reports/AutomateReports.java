package Reports;

import Database.DBBackupRestore;
import Database.DbDriver;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;

public class AutomateReports extends Thread{
    private String from, to;
    private Timestamp current, lastWeek;
    DBBackupRestore backup = new DBBackupRestore();

    @Override
    public void run() {
        super.run();
        while(true){
            current = new Timestamp(System.currentTimeMillis());
            Date dateTo = new Date(current.getTime());
            to = dateTo.toString();
            // generate for one week
            lastWeek = Timestamp.from(current.toInstant().minus(168, ChronoUnit.HOURS));
            Date dateFrom = new Date(lastWeek.getTime());
            from = dateFrom.toString();
            try {
                DbDriver.generateStaffReport();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //Backup the database on a weekly basis.
            backup.dbBackup("root","TeaM27TeaM","bapers");
            try {
                DbDriver.generateSummaryReport(from,to);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {

                //will generate weekly.
                Thread.sleep(604800000);
//                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }
}