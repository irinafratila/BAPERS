package JobTasks;

import java.util.List;

public interface I_JobTasks {
    void addVector();
    void removeVector();
    void addListOfJobs();
    void removeListOfJobs();
    List<Job> getListOfJobs();
    void removeListOfCustomers();
    void addListOfCustomers();
    List<Job> getListOfCustomers();
    void addJob(Job job);
    void removeJob();
    Job getJob(int jobId);
    void addCustomerAccount();
    void removeCustomerAccount();
    void getCustomerAccount();
    void addListOfTasks();
    void removeListOfTasks();
    Task getTask(int taskId);









}