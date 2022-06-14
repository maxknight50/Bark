package tables;

import java.util.Date;

/**
 *
 * @author coolm
 */
public class Volunteer {

    int volunteerID;
    String volFirst;
    String volLast;
    String volAddress;
    Date dateofHire;
    String empPhone;
    int salary;
    int loginID;

    public Volunteer(int id, String first, String last, String address, Date hire, String phone, int salary, int loginID) {
        this.volunteerID = id;
        this.volFirst = first;
        this.volLast = last;
        this.volAddress = address;
        this.dateofHire = hire;
        this.empPhone = phone;
        this.salary = salary;
        this.loginID = loginID;
    }

    public int getEmployeeID() {
        return volunteerID;
    }

    public void setEmployeeID(int employeeID) {
        this.volunteerID = employeeID;
    }

    public String getFirst() {
        return volFirst;
    }

    public void setEmFirst(String first) {
        this.volFirst = first;
    }

    public String getLast() {
        return volFirst;
    }

    public void setLast(String first) {
        this.volFirst = first;
    }

    public String getEmpAddress() {
        return volAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.volAddress = empAddress;
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
