package Alerts;

import Database.DbDriver;

public class AutomateAlerts extends Thread{
    @Override
    public void run() {
        super.run();

        while (true) {

            DbDriver.generateAlertPayment();
//            DbDriver.taskDeadline();
        try {
            //Will run checks again after 15 minutes.
            Thread.sleep(900000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }

    }
}
