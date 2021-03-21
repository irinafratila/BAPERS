package Admin;

public class Admin_ImplClass implements I_Admin{

    @Override
    public void addUserAccount() {
        User tempUser = new User();
        //Code to add tempUser to the database
    }

//    @Override
//    public void addUserAccount(String N, String U, String P) {
//        User tempUser = new User(N, U, P);
//        //Code to add tempUser to the database
//    }

    @Override
    public String getUserAccount(int id) {
        //Code to look through database and pull user with matching ID
        return null;
    }

    @Override
    public void removeUserAccount(int id) {
        //Code to look through database and remove user with matching ID from it
    }

    @Override
    public void addAlert(String M) {
        Alert tempAlert = new Alert(M);
    }

    @Override
    public void removeAlert() {

    }

    @Override
    public Alert getAlert() {
        return null;
    }
    public Alert getAlert(int ID) {
        return null;
    }
    public Alert getAlert(User user) {
        return null;
    }
    /*multiple get alerts, find all alerts for one user, find alert ID, search message text for
    a specific string*/
}
