package BapersControl;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NoAccess implements Initializable {

    private BapersControl.Main m;

    @FXML
    private Label noAccessLabel;

    public NoAccess(){
        this.m = new BapersControl.Main();
    }

    public void cancel() throws IOException {
        m.changeScene("dashboard.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        noAccessLabel.setText("Sorry your account doesn't have acces to this feature " + BapersControl.currentLoginSession.getUsername());
    }
}
