
package tables;

import java.util.Date;

/**
 *
 * @author coolm
 */
public class Volunteer {
    
    int employeeID;
    String empName;
    String empAddress;
    Date dateofHire;
    String empPhone;
    int salary;
    int loginID;
    
    public Volunteer(int id, String name, String address, Date hire, String phone, int salary, int loginID) {
        this.employeeID = id;
        this.empName = name;
        this.empAddress = address;
        this.dateofHire = hire;
        this.empPhone = phone;
        this.salary = salary;
        this.loginID = loginID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public Date getDateofHire() {
        return dateofHire;
    }

    public void setDateofHire(Date dateofHire) {
        this.dateofHire = dateofHire;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getLoginID() {
        return loginID;
    }

    public void setLoginID(int loginID) {
        this.loginID = loginID;
    }
    
}
