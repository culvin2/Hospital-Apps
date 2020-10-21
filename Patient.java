import java.util.*;

public class Patient 
{
    public String ID;
    public String Name;
    public String Email;
    public String Address;
    public String Phone;
    public String Password;

    public ArrayList<ScheduleCheckup> checkup = new ArrayList<>();

    public Patient(String patientID, String patienName, String patietnEmail, String patienAddress, String patientPhone, String patientPassword)
    {
        ID = patientID;
        Name = patienName;
        Email = patietnEmail;
        Address = patienAddress;
        Phone = patientPhone;
        Password = patientPassword;
    }

    public boolean VerifyLogin(String email, String password)
    {
        if(email.equals(Email) && password.equals(Password))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public ArrayList<ScheduleCheckup> getScheduleCheckup() 
    {
        return checkup;
    }

    public void MakeAppointment(Staff choosenDoctor, ScheduleDoctor scheduelDoctor, String ID) 
    {
        ScheduleCheckup scheduel = new ScheduleCheckup(ID, scheduelDoctor.Date, scheduelDoctor.Time);
        checkup.add(scheduel);
        choosenDoctor.scheduleCheckUps.add(scheduel);
        System.out.println("New Appointment Added.");
    }
}
