package tables;

import java.util.Date; 

public class YourEvent {
    String eventName; 
    int maxVolunteers; 
    Date eventDate; 
    String eventTime; 
    int milesDriven; 
    String eventType; 
    
    public YourEvent(String name, int max, Date date, String time, int miles, String type){
        this.eventName = name; 
        this.maxVolunteers = max; 
        this.eventDate = date; 
        this.eventTime = time; 
        this.milesDriven = miles; 
        this.eventType = type; 
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getMaxVolunteers() {
        return maxVolunteers;
    }

    public void setMaxVolunteers(int maxVolunteers) {
        this.maxVolunteers = maxVolunteers;
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

    public int getMilesDriven() {
        return milesDriven;
    }

    public void setMilesDriven(int milesDriven) {
        this.milesDriven = milesDriven;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String toString() {
        return "Name: " + eventName + ", Max Volunteers:" + maxVolunteers + ", Event Date:" + eventDate + ", Time: " + eventTime + ", Miles Driven:" + milesDriven + " Event Type: " + eventType;
    }

   
}
