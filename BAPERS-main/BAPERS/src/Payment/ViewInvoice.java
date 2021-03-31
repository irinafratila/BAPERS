package Payment;


import BapersControl.Main;
import Database.DbDriver;
import JobTasks.Job;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class ViewInvoice {

    private Main m;

    private static int id;

    public ViewInvoice(){
        this.m = new Main();
       // this.id = id;

    }



    public void cancel() throws IOException {
        m.changeScene("dashboard.fxml");
    }

    public void view() throws IOException {
        try {
            //constructor of file class having file as argument
            Job searchedJob = DbDriver.searchJobJustCreated();
            int x = searchedJob.getJobId();
            File file = new File("/Users/tobiadewunmi/Desktop/BAPERS-main/Invoice"+x+".txt");
            if(!Desktop.isDesktopSupported()){
                System.out.println("not supported");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            //if found
            if(file.exists()) {
                //open file
                desktop.open(file);
            }

        }catch(IOException e) {
            e.printStackTrace();
        }

    }



}