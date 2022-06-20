package tables;

import java.util.Date;


public class Volunteer {

    int volunteerID;
    String volFirst;
    String volLast;
    String volAddress;
    Date dateOfBirth;
    String volInfo;
    String volEmail;
    String volPhone;
    Double totalHours;
    String status;
    String username;
    String password;
    int schedID;

    public Volunteer(int volunteerID, String volFirst, String volLast, String volAddress, Date dateOfBirth, String volInfo, String volEmail, String volPhone, Double totalHours, String status, String username, String password, int schedID) {
        this.volunteerID = volunteerID;
        this.volFirst = volFirst;
        this.volLast = volLast;
        this.volAddress = volAddress;
        this.dateOfBirth = dateOfBirth;
        this.volInfo = volInfo;
        this.volEmail = volEmail;
        this.volPhone = volPhone;
        this.totalHours = totalHours;
        this.status = status;
        this.username = username;
        this.password = password;
        this.schedID = schedID;
    }
    


    public int getVolunteerID() {
        return volunteerID;
    }

    public void setVolunteerID(int volunteerID) {
        this.volunteerID = volunteerID;
    }

    public String getVolFirst() {
        return volFirst;
    }

    public void setVolFirst(String volFirst) {
        this.volFirst = volFirst;
    }

    public String getVolLast() {
        return volLast;
    }

    public void setVolLast(String volLast) {
        this.volLast = volLast;
    }

    public String getVolAddress() {
        return volAddress;
    }

    public void setVolAddress(String volAddress) {
        this.volAddress = volAddress;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getVolInfo() {
        return volInfo;
    }

    public void setVolInfo(String volInfo) {
        this.volInfo = volInfo;
    }

    public String getVolEmail() {
        return volEmail;
    }

    public void setVolEmail(String volEmail) {
        this.volEmail = volEmail;
    }

    public String getVolPhone() {
        return volPhone;
    }

    public void setVolPhone(String volPhone) {
        this.volPhone = volPhone;
    }

    public Double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Double totalHours) {
        this.totalHours = totalHours;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSchedID() {
        return schedID;
    }

    public void setSchedID(int schedID) {
        this.schedID = schedID;
    }

}