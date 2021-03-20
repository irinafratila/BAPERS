package JobTasks;

import java.util.List;

public interface I_JobTasks {
    public void addVector();
    public void removeVector();
    public void addListOfJobs();
    public void removeListOfJobs();
    public List<Job> getListOfJobs();
    public void removeListOfCustomers();
    public void addListOfCustomers();
    public List<Job> getListOfCustomers();
    public void addJob(Job job);
    public void removeJob();
    public Job getJob(int jobId);
    public void addCustomerAccount();
    public void removeCustomerAccount();
    public void getCustomerAccount();
    public void addListOfTasks();
    public void removeListOfTasks();
    public Task getTask(int taskId);









}
