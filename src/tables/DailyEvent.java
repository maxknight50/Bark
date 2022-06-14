package tables;

import java.util.Date; 

public class DailyEvent {
    String eventName; 
    Date eventDate; 
    String eventTime; 
    
    public DailyEvent(String name, Date date, String time){
        this.eventName = name; 
        this.eventDate = date; 
        this.eventTime = time; 
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }
    
    public String toString(){
        return "Name: " + eventName + " Date: " + eventDate + " Time: " + eventTime; 
    }
}
