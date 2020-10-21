import java.util.*;

public class Staff 
{
    public String ID;
    public String Name;
    public String Email;
    public String Password;
    public String Address;
    public String WorkTime;

    public Role StaffRole;
    public ArrayList<ScheduleDoctor> scheduleDoctors = new ArrayList<>();
    public ArrayList<ScheduleCheckup> scheduleCheckUps = new ArrayList<>();

    public Staff(String staffID, String staffName, String staffEmail, String staffPassword, String staffAddress, String workTime, Role roles)
    {
        ID = staffID;
        Name = staffName;
        Email = staffEmail;
        Password = staffPassword;
        Address = staffAddress;
        WorkTime = workTime;

        setRole(roles);
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

    public Patient RegisPatient(String Id)
    {
        System.out.println("Insert data for patient");
        System.out.println("========================");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name : ");
        String name = scanner.nextLine();
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Address : ");
        String address = scanner.nextLine();
        System.out.print("Phone : ");
        String phone = scanner.nextLine();
        String password;
        do 
        {
            System.out.print("Password : ");
            password = scanner.nextLine();
        } 
        while (password.length() == 0);

        Patient patient = new Patient(Id, name, email, address, phone, password);

        return patient;
    }

    public void setRole(Role role) 
    {
        StaffRole = role;
    }

    public Role getRole() 
    {
        return StaffRole;
    }

    public ArrayList<ScheduleDoctor> getScheduleDoctors() 
    {
        return scheduleDoctors;
    }

    public ArrayList<ScheduleCheckup> getScheduleCheckup() 
    {
        return scheduleCheckUps;
    }

    public void addScheduelDoctor(String Id) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n");
        System.out.println("Register new Doctor Schedule");
        System.out.println("============================");
        System.out.print("Insert Date [dd/mm/yyyy] : ");
        String date = scanner.nextLine(); 
        System.out.print("Insert Time [hh.mm] : ");
        String time = scanner.nextLine();
        System.out.print("New Doctor schedule successfully Added.");

        ScheduleDoctor newScheduel = new ScheduleDoctor(Id, date, time);

        scheduleDoctors.add(newScheduel);
    }
}
