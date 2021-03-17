package BapersControl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

// this is the class that is in control after user succesfully logs in
// it goes hand in hand with the afterLogin.xml file which is used to represent the gui
public class afterLogin {


    @FXML
    private Button button;

    public void userLogOut(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene("Login.fxml");
    }
}