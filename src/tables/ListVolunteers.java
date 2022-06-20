package tables;

import java.util.Date;

public class ListVolunteers {
   int volunteerID;
   String volFirst;
   String volLast;
   String volAddress;
   Date dateOfBirth;
   String volEmail;
   String volPhone;
   Double totalHours;
   String status;
   public static int volunteerCount = 0; 
   
   public ListVolunteers(int id, String first, String last, String address, Date date, String email, String phone, Double totalHours, String status){
       this.volunteerID = id; 
       this.volFirst = first; 
       this.volLast = last; 
       this.volAddress = address; 
       this.dateOfBirth = date; 
       this.volEmail = email; 
       this.volPhone = phone; 
       this.totalHours = totalHours; 
       this.status = status; 
       volunteerCount++; 
   }
   public ListVolunteers(int id, String first, String last, String address, String email, String phone, Double hours){
        this.volunteerID = id; 
        this.volFirst = first;
        this.volLast = last; 
        this.volAddress = address; 
        this.volEmail = email; 
        this.volPhone = phone; 
        this.totalHours = hours; 
        volunteerCount++; 
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
}
