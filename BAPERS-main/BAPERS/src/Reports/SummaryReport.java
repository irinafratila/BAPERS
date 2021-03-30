package Reports;

import java.sql.Date;

//bipl report
public class SummaryReport {
    private Date date;
    private String location;
    private double totalTime;

    public SummaryReport(Date date, String location, double totalTime) {
        this.date = date;
        this.location = location;
        this.totalTime = totalTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }
}
