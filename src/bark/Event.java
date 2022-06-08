//Mark
package bark;

import java.util.Date; 

public class Event {
    public int eventID;
    public String eventType; 
    public String eventName; 
    public String eventDescription; 
    public int maxVolunteers;
    public Date eventDate; 
    public String eventTime; 
    public String eventLocation; 
    
    public static int eventCount = 0; 
    
    public Event(){
        this.eventID = eventCount + 1; 
        this.eventType = "";
        this.eventName = ""; 
        this.eventDescription = ""; 
        this.maxVolunteers = 0; 
       // this.eventDate - ???; 
        this.eventTime = ""; 
        this.eventLocation = ""; 

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

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public static int getEventCount() {
        return eventCount;
    }

    public static void setEventCount(int eventCount) {
        Event.eventCount = eventCount;
    }
}
