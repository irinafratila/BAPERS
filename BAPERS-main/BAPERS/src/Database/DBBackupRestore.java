package Database;

import java.sql.Date;
import java.sql.Timestamp;

public class DBBackupRestore {

    public static void dbBackup(String dbUser, String dbPass, String dbName) {
        Timestamp current = new Timestamp(System.currentTimeMillis());
        Date date = new Date(current.getTime());
        String d = date.toString();
        String savePath = "/Users/tobiadewunmi/Desktop/BAPERS-main/dbBackup"+d+".sql";
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

    public static void main(String[] args) {
        dbBackup("root","TeaM27TeaM","bapers");
         restoreDB("bapers","root","TeaM27TeaM","2021-03-31");


    }


    public static void restoreDB(String dbName, String dbUserName, String dbPassword, String date){
        String source = "/Users/tobiadewunmi/Desktop/BAPERS-main/dbBackup"+date+".sql";


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