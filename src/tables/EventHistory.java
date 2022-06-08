package tables;


public class EventHistory {
    public int volID; 
    public int eventID; 
    public int expenses; 
    public String event_Duration; 
    public int miles_Driven; 
    
    public EventHistory(){
        this.volID = 0; 
        this.eventID = 0; 
        this.expenses = 0; 
        this.event_Duration = "";
        this.miles_Driven = 0; 
    }
    
    public EventHistory(int id, int eventid, int expenses, String duration, int miles){
        this.volID = id;
        this.eventID = eventid; 
        this.expenses = expenses; 
        this.event_Duration = duration; 
        this.miles_Driven = miles; 
    }

    public int getVolID() {
        return volID;
    }

    public void setVolID(int volID) {
        this.volID = volID;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getExpenses() {
        return expenses;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }

    public String getEvent_Duration() {
        return event_Duration;
    }

    public void setEvent_Duration(String event_Duration) {
        this.event_Duration = event_Duration;
    }

    public int getMiles_Driven() {
        return miles_Driven;
    }

    public void setMiles_Driven(int miles_Driven) {
        this.miles_Driven = miles_Driven;
    }
}
