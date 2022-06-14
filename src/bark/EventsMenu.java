
package bark;

import static bark.VolunteerStatus.conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList; 
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import java.util.Date; 
import oracle.jdbc.pool.OracleDataSource;
import tables.Event;
import tables.EventHistory;

public class EventsMenu extends Login1 {
    Home home;
    
    TableView<String> eventTable = new TableView<>();
    TableView<Event> eventTable2 = new TableView<>();
    TableView<Event> eventTable3 = new TableView<>();
    
    ObservableList<String> tableData = FXCollections.observableArrayList();
 
    
    //Create the columsn for each table
    TableColumn dailyEvent = new TableColumn("Event");
    TableColumn dailyDate = new TableColumn("Event Date");
    TableColumn dailyDuration = new TableColumn("Event Duration");
    TableColumn pastEvent = new TableColumn("Event");
    TableColumn pastMax = new TableColumn("Max Volunteers");
    TableColumn pastAssigned = new TableColumn("Assigned Volunteers");
    TableColumn pastDate = new TableColumn("Event Date");
    TableColumn yourName = new TableColumn("Event Name"); 
    TableColumn yourDate = new TableColumn("Event Date");
    TableColumn yourDuration = new TableColumn("Duration");
    TableColumn yourDistance = new TableColumn("Distance");
    TableColumn yourCategory = new TableColumn("Category");
     
    // Tab 1 Controls
    
    // Tab 2 Controls
    
    // Tab 3 Controls
    
    // GridPane associated for each tab
    GridPane tPane1 = new GridPane();
    GridPane tPane2 = new GridPane();
    GridPane tPane3 = new GridPane();
    
    // Tab creation
    Tab tab1 = new Tab("Daily Events");
    Tab tab2 = new Tab("Past Events");
    Tab tab3 = new Tab("Your Events");
    
    GridPane eventsPane = new GridPane();
    TabPane tabPane = new TabPane();
    
    EventsMenu(Home home) {
        this.home = home;
        
        eventsPane.add(tabPane, 0, 1);
        
        tab1.setContent(tPane1);
        tab2.setContent(tPane2);
        tab3.setContent(tPane3);
        tabPane.getTabs().addAll(tab1, tab2, tab3);
        //TabPane tabClosingPolicy="UNAVAILABLE";
        tab1.setClosable(false);
        tab2.setClosable(false);
        tab3.setClosable(false);
        
        // Add the tables to the tabs
        tPane1.add(eventTable, 0, 0);
        tPane2.add(eventTable2, 0, 0);
        tPane3.add(eventTable3, 0, 0);
        
        
        //Set the cell values for eventTable (Daily Events)
        dailyEvent.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));
        dailyDate.setCellValueFactory(new PropertyValueFactory<Event, Date>("eventDate"));
        dailyDuration.setCellValueFactory(new PropertyValueFactory<Event, String>("eventTime"));
        pastEvent.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));
        pastMax.setCellValueFactory(new PropertyValueFactory<Event, Integer>("maxVolunteers"));
        //pastAssigned.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));
        pastDate.setCellValueFactory(new PropertyValueFactory<Event, Date>("eventDate"));
        yourName.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));
        yourDate.setCellValueFactory(new PropertyValueFactory<Event, Date>("eventDate"));
        yourDuration.setCellValueFactory(new PropertyValueFactory<Event, String>("eventTime"));
        yourDistance.setCellValueFactory(new PropertyValueFactory<EventHistory, Integer>("miles_Driven"));
        yourCategory.setCellValueFactory(new PropertyValueFactory<Event, String>("eventType"));

        eventTable.getColumns().addAll(dailyEvent, dailyDate, dailyDuration); 
        eventTable2.getColumns().addAll(pastEvent, pastMax, pastDate); 
        eventTable3.getColumns().addAll(yourName, yourDate, yourDuration, yourDistance, yourCategory); 
        
        //Query the database and populate the tables
        sendDBCommand("SELECT E.eventID, eventType, eventName, maxVolunteers, eventDate, eventTime, EH.Miles_Driven\n" +
                        "FROM Event E\n" +
                        "INNER JOIN EventHistory EH ON EH.EventID = E.EventID;");

        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(eventsPane, 600, 450);
        
        eventTable.setMinWidth(primaryScene.getWidth()); 
        eventTable2.setMinWidth(primaryScene.getWidth()); 
        eventTable3.setMinWidth(primaryScene.getWidth()); 
        
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Events Menu");
        primaryStage.show();  
        tabPane.setMinHeight(primaryScene.getHeight());
        tabPane.setMinWidth(primaryScene.getWidth());
        
        
    }
    
    public void sendDBCommand(String sqlQuery) {
        String URL = "jdbc:oracle:thin:@localhost:1521:XE";
        String userID = "javauser";
        String userPASS = "javapass";
        OracleDataSource ds;

        // You can comment this line out when your program is finished
        System.out.println(sqlQuery);

        try {
            ds = new OracleDataSource();
            ds.setURL(URL);
            conn = ds.getConnection(userID, userPASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sqlQuery); // Sends the Query to the DB
            System.out.println("RESULT SET: " + rs); 

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

}
