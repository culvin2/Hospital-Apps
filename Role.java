import java.util.*;

public class Role 
{
    public String ID;
    public String Name;
    public Integer Salary;

    public ArrayList<Staff> staffs = new ArrayList<>();

    public Role(String roleId, String rolename, Integer rolesalary)
    {
        ID = roleId;
        Name = rolename;
        Salary = rolesalary;
    }

    public void addStaff(Staff staff) 
    {
        staffs.add(staff);
    }

    public void updateSalary(int salary) 
    {
        Salary = salary;
    }
}
