package JobTasks;

public class CustomerAccount {
    private String name;
    private String phoneNumber;
    private String email;
    private Boolean valuedCustomer;

    public CustomerAccount(String name, String phoneNumber, String email, Boolean valuedCustomer) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.valuedCustomer = valuedCustomer;
    }
    public boolean makePayment(double amount){return true;}
    public void createJob(){}
    public void addTask(){}
    public void getJobs(){}
    public void upgradeCustomer(){
        if(!valuedCustomer){
            valuedCustomer = true;
        }
    }

}
