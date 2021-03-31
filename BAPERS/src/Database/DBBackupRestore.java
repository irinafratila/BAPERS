package Database;

import java.sql.Date;
import java.sql.Timestamp;

public class DBBackupRestore {

    public  void dbBackup(String dbUser, String dbPass, String dbName) {
        Timestamp current = new Timestamp(System.currentTimeMillis());
        Date date = new Date(current.getTime());
        String d = date.toString();
        String savePath = "/Users/masum/Desktop/BAPERS/dbBackup"+d+".sql";
        String executeCmd = ("/usr/local/mysql/bin/mysqldump -u " + dbUser + " -p" + dbPass + "  --databases " + dbName + " -r " + savePath);
        try {
            Process p = Runtime.getRuntime().exec(executeCmd);
            int processComplete = p.waitFor();
            if (processComplete == 0) {
                System.out.println("Backup Created Success");
            } else {
                System.out.println("Backup Unsuccessful");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
////        dbBackup("root","helloworld12","bapers");
//        restoreDB("bapers","root","helloworld12","2021-03-31");


//    }


    public  void restoreDB(String dbName, String dbUserName, String dbPassword, String date){
        String source = "/Users/masum/Desktop/BAPERS/dbBackup"+date+".sql";


        String[] executeCmd = new String[]{"mysql", "--user=" + dbUserName, "--password=" + dbPassword, dbName,"-e", " source "+source};
        Process runtimeProcess;
        try {
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                System.out.println("Backup restored successfully");

            }
            else{
                System.out.println("Could not restore the backup");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
