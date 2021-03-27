package Admin;

import javafx.scene.control.Alert;

public interface I_Admin {
    void addUserAccount();
//    void addUserAccount(String N, String U, String P);
    String getUserAccount(int id);
    void removeUserAccount(int id);
    void addAlert(String M);
    void removeAlert();
    Alert getAlert();
    //check with team if should be return type alert ot string (just return message of alert)
}