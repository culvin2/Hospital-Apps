import java.util.*;

public class ScheduleCheckup 
{
    public String ID;
    public String Date;
    public String Time;

    public Patient patient;
    public Staff staff;
    public Payment payment;

    public ScheduleCheckup(String checkupID, String checkupDate, String checkupTime)
    {
        ID = checkupID;
        Date = checkupDate;
        Time = checkupTime;
    }

    public void setPayment(Payment pay) 
    {
        payment = pay;
    }

    public Payment getPayment() 
    {
        return payment;
    }
}
