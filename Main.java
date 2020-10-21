import java.util.*;


public class Main 
{
    //Data Buffer
    private static ArrayList<Role> role = new ArrayList<>();
    private static ArrayList<Staff> staff = new ArrayList<>();
    private static ArrayList<Patient> patient = new ArrayList<>();
    private static ArrayList<PaymentType> paymentType = new ArrayList<>();

    Patient patientLogin = null;
    Staff stafflogin = null;
    boolean IsUserLogin = false;
    int LoginDetector;

    boolean Loginpatient(String mail, String pass)
    {
        for (Patient ptn : patient) 
        {
            if (ptn.VerifyLogin(mail, pass)) 
            {
                patientLogin = ptn;
                return true;
            }
        }
        return false;
    }

    boolean Loginstaff(String mail, String pass)
    {
        for (Staff sff : staff) 
        {
            if (sff.VerifyLogin(mail, pass)) 
            {
                stafflogin = sff;
                return true;
            }
        }
        return false;
    }
    
    void Loginpage(Integer type)
    {
        System.out.println("\n============");
        Scanner scanner = new Scanner(System.in);
        if(type == 3)
        {
            System.exit(0);
        }

        while (true) 
        {
            System.out.println("Input Email and Password");
            System.out.print("Email : ");
            String mail = scanner.nextLine();
            System.out.print("Password : ");
            String password = scanner.nextLine();

            switch(type)
            {
                case 1 :
                {
                    if (Loginpatient(mail, password)) 
                    {
                        IsUserLogin = true;
                        return;
                    }
                }
                break;
                case 2 :
                {
                    if (Loginstaff(mail, password)) 
                    {
                        IsUserLogin = true;
                        return;
                    }
                }
                break;
            }
        }
    }

    void Menupages(Integer type)
    {
        if(type == 1)
        {
            System.out.println("\n");
            System.out.println("Patient Name : " + patientLogin.Name);
            System.out.println("========================");
            System.out.println("1. Request Doctor Scheduel");
            System.out.println("2. Create Chekup Appointment");
            System.out.println("3. Pay Bill");
            System.out.println("4. Check Appointment");
            System.out.println("5. LogOut");
            System.out.println("Your Respond : ");
        }
        else
        {
            System.out.println("\n");
            System.out.println("Staff Name : " + stafflogin.Name);
            System.out.println("========================");
            System.out.println("1. Register Patient");
            System.out.println("2. Check Dokter Scheduel");
            System.out.println("3. Register New Scheduel");
            System.out.println("4. Check Appointment");
            System.out.println("5. LogOut");
            System.out.println("Your Respond : ");
        }
    }

    int SelectedDoctor() 
    {
        int choice = 0;
        System.out.println("\n");
        System.out.println("Doctor List");
        System.out.println("===========");
        int i = 0;
        for (Staff s : staff) 
        {
            System.out.println(++i + ". " + s.Name + " - " + s.StaffRole.Name);
        }
    
        Scanner scanner = new Scanner(System.in);
        do 
        {
            System.out.println("Choose Doctor :");
            choice = scanner.nextInt();
        } 
        while (choice < 1 || choice > staff.size());
        return choice;
    }

    ScheduleDoctor checkDoctorSchedule(boolean checkOnly, Staff doctor) 
    {
        System.out.println("\n");
        System.out.println("Available Schedeuel");
        System.out.println("===================");
        int i = 0;
        for (ScheduleDoctor schedule : doctor.getScheduleDoctors()) 
        {
            System.out.println(++i + ". " + schedule.Date + " @ " + schedule.Time);
        }

        if (checkOnly)
        {
            return null;
        }

        int choice;
        Scanner scanner = new Scanner(System.in);
        do 
        {
            System.out.println("Choose Schedule :");
            choice = scanner.nextInt();
        } 
        while (choice < 1 || choice > doctor.scheduleDoctors.size());
        return doctor.getScheduleDoctors().get(choice - 1);
    }

    void checkAppointment(Staff doctor)
    {
        System.out.println("\n");
        System.out.println("Doctor Appointment");
        System.out.println("===================");
        int i = 0;
        for (ScheduleCheckup schedule : doctor.getScheduleCheckup()) 
        {
            System.out.println(++i + ". " + schedule.Date + " @ " + schedule.Time);
        }
    }

    void PaymentMethod()
    {
        System.out.println("\n");
        System.out.println("Payment Bill");
        System.out.println("===============");
        int i = 0;
        for(ScheduleCheckup checkUp : patientLogin.checkup) 
        {
            System.out.print(++i + ". " + checkUp.Date + " @ " + checkUp.Time);

            if (checkUp.payment == null) 
            {
                System.out.println(" Bill Unpaid");
            }
            else 
            {
                System.out.println(" Bill Already Paid");
                return;
            }
        }

        if (i == 0) 
        {
            System.out.println("There is no pending bills.");
            return;
        }

        ScheduleCheckup checkUp;
        Scanner choosebill = new Scanner(System.in);
        do 
        {
            int bill = choosebill.nextInt();
            checkUp = patientLogin.checkup.get(bill - 1);
        } 
        while (checkUp.payment != null);

        Scanner inputAmmount = new Scanner(System.in);
        System.out.print("Payment amount : ");
        int price = inputAmmount.nextInt();

        i = 0;
        for (PaymentType type : paymentType) 
        {
            System.out.println(++i + ". " + type.Name);
        }
        
        Scanner choosetype = new Scanner(System.in);
        int type = 0;
        do 
        {
            System.out.println("Choose Payment Type : ");
            type = choosetype.nextInt();
        } 
        while (type < 1 || type > paymentType.size());

        PaymentType Types = paymentType.get(type - 1);
        Payment payment = new Payment("1", new Date().toString(), price);

        payment.setPaymentType(Types);
        checkUp.setPayment(payment);
        System.out.println("Payment Succeed.");
    }

    public static void main(String[] args) 
    {
        Main program = new Main();
        int choice;

        //generate data
        role.add(new Role("1", "Admin", 3000000));
        role.add(new Role("2", "Spesialis Jantung", 18000000));
        role.add(new Role("3", "Umum", 3500000));
        role.add(new Role("4", "Spesialis THT", 4000000));
        role.add(new Role("5", "Spesialis Penyakit Kulit", 5000000));
        role.add(new Role("6", "Spesialis Gigi", 4500000));

        paymentType.add(new PaymentType("1", "Cash"));
        paymentType.add(new PaymentType("2", "Credit card"));
        paymentType.add(new PaymentType("3", "Debit card"));

        patient.add(new Patient("1", "Jhonny", "Jhonny@gmail.com", "Jakarta", "08333321", "admin123"));
        patient.add(new Patient("2", "Angle", "Angle@gmail.com", "Jakarta", "08123451", "admin123"));
        patient.add(new Patient("3", "Jane", "Jane@gmail.com", "Bandung", "08543212", "admin123"));
        patient.add(new Patient("4", "Tony", "Tony@gmail.com", "Bandung", "08676542", "admin123"));

        staff.add(new Staff("1", "Calvin", "Calvin@gmail.com", "Admin123", "Bandung", "4 jam", role.get(2)));
        staff.add(new Staff("1", "Martinez", "Martinez@gmail.com", "Admin123", "Bandung", "4 jam", role.get(3)));
        staff.add(new Staff("1", "Monty", "Monty@gmail.com", "Admin123", "Bandung", "5 jam", role.get(4)));
        staff.add(new Staff("1", "Stark", "Stark@gmail.com", "Admin123", "Bandung", "4 jam", role.get(5)));

        while(true)
        {
            if(!program.IsUserLogin)
            {
                do
                {
                    System.out.println("\n");
                    System.out.println("Login as: ");
                    System.out.println("=========");
                    System.out.println("1. Patient");
                    System.out.println("2. Staff");
                    System.out.println("3. Exit");
                    System.out.print("Input your Respond : ");

                    Scanner scanner = new Scanner(System.in);
                    program.LoginDetector = scanner.nextInt();
                }
                while (program.LoginDetector < 1 || program.LoginDetector > 3);
                program.Loginpage(program.LoginDetector);
            }


            Scanner input = new Scanner(System.in);
            do 
            {
                program.Menupages(program.LoginDetector);
                choice = input.nextInt();
            } 
            while (choice < 1 || choice > 5);
            
            switch(choice)
            {
                case 1:
                {
                    if(program.LoginDetector == 1)
                    {
                        program.checkDoctorSchedule(true, staff.get(program.SelectedDoctor()-1));
                    }
                    else
                    {
                        patient.add(program.stafflogin.RegisPatient(String.valueOf(patient.size()+1)));
                    }
                }
                break;
                case 2:
                {
                    if(program.LoginDetector == 1)
                    {
                        Staff doctor;
                        int doctorselected = program.SelectedDoctor()-1;
                        ScheduleDoctor schedule = program.checkDoctorSchedule(false, staff.get(doctorselected));
                        doctor = staff.get(doctorselected);
                        program.patientLogin.MakeAppointment(doctor, schedule , String.valueOf(doctor.scheduleDoctors.size() + 1));
                    }
                    else
                        program.checkDoctorSchedule(true, staff.get(program.SelectedDoctor()-1));
                }
                break;
                case 3:
                {
                    if(program.LoginDetector == 1)
                        program.PaymentMethod();
                    else
                        program.stafflogin.addScheduelDoctor(String.valueOf(program.stafflogin.scheduleDoctors.size()+1));
                }
                break;
                case 4:
                {
                    if(program.LoginDetector == 1)
                        program.checkAppointment(staff.get(program.SelectedDoctor()-1));
                    else
                        program.checkAppointment(staff.get(program.SelectedDoctor()-1));
                }
                break;
                case 5:
                {
                    program.IsUserLogin = false;
                    program.patientLogin = null;
                    program.stafflogin = null;
                }
                break;
            }
        }

    }
}
