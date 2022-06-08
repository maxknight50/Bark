package tables;

import java.util.Date; 

public class WorkHistory {
   public Date workDate; 
   public String workTime; 
   public int volID; 
   
   public WorkHistory(){
       Date workDate; 
       String workTime = "";
       int volID = 0; 
   }
   
   public WorkHistory(Date date, String time, int id){
       this.workDate = date; 
       this.workTime = time; 
       this.volID = id; 
   }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public int getVolID() {
        return volID;
    }

    public void setVolID(int volID) {
        this.volID = volID;
    }
}
