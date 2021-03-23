package Admin;

public class Alert {
    int alertID;
    static int count = 0;
    String message;
    boolean seen = false;

    public Alert(String M) {
        message = M;
        count++;
        alertID = count;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

}
