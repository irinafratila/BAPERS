
package Reports;

import BapersControl.Main;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ViewBiplReport {

    private Main m;

    public ViewBiplReport(){
        this.m = new Main();

    }

    public void cancel() throws IOException {
        m.changeScene("dashboard.fxml");
    }

    public void view() throws IOException {

        String From = "2021-03-27";
        String To = "2021-03-29";


        try {
            //constructor of file class having file as argument
            File file = new File("/Users/tobiadewunmi/Desktop/BAPERS-main/SummaryReport" + From + "_" + To + ".txt");
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