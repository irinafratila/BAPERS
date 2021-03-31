package Reports;

import BapersControl.Main;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class ViewCustomerReport {

    private Main m;

    private static int id;

public ViewCustomerReport(){
    this.m = new Main();
    this.id = 0;

}

    public void cancel() throws IOException {
        m.changeScene("dashboard.fxml");
    }

    public void view() throws IOException {
        try {
            //constructor of file class having file as argument
            File file = new File("/Users/tobiadewunmi/Desktop/BAPERS-main/CustomerReport"+BapersControl.tempCustomerSession.getId()+".txt");
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

