package tables;

import java.util.Date; 

public class Event {
    public int eventID;
    public String eventType; 
    public String eventName; 
    public String eventDescription; 
    public int maxVolunteers;
    public String eventDate; 
    public String eventTime; 
    public String eventLocation; 
    public String eventCategory;
    public String eventStatus;
    
    //public static int eventCount = 0; 

    public Event(int eventID, String eventType, String eventName, String eventDescription, int maxVolunteers, String eventDate, String eventTime, String eventLocation, String eventCategory, String eventStatus) {
        this.eventID = eventID;
        this.eventType = eventType;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.maxVolunteers = maxVolunteers;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventLocation = eventLocation;
        this.eventCategory = eventCategory;
        this.eventStatus = eventStatus;
    }
    
    public Event(int id, String name, String category, String description, String date, String time, String location, String status){
        this.eventID = id; 
        this.eventName = name; 
        this.eventType = category; 
        this.eventDescription = description; 
        this.eventDate = date; 
        this.eventTime = time; 
        this.eventLocation = location; 
        this.eventStatus = status; 
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public int getMaxVolunteers() {
        return maxVolunteers;
    }

    public void setMaxVolunteers(int maxVolunteers) {
        this.maxVolunteers = maxVolunteers;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }
    
}
