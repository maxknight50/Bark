package bark;

import static bark.VolunteerStatus.conn;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import oracle.jdbc.pool.OracleDataSource;
import java.io.*; 
import java.util.*; 
import tables.*;


public class EventsRetry extends Login1 {

    Home home;
    VolunteerHome1 volHome;

    TableView<Event> yourTable = new TableView<>();
    TableView<Event> dailyTable = new TableView<>();
    TableView<Event> pastTable = new TableView<>();
    TableView<Event> hostedTable = new TableView<>();

    ObservableList<Event> dailyData = FXCollections.observableArrayList();
    ObservableList<Event> pastData = FXCollections.observableArrayList();
    ObservableList<Event> yourData = FXCollections.observableArrayList();
    ObservableList<Event> hostedData = FXCollections.observableArrayList();
    
    ArrayList<Event> dailyList = new ArrayList<>(); 
    ArrayList<Event> pastList = new ArrayList<>(); 
    ArrayList<Event> yourList = new ArrayList<>(); 

    //Create the columns for each table
    TableColumn yourName = new TableColumn("Event");
    TableColumn yourDescription = new TableColumn("Description");
    TableColumn yourDate = new TableColumn("Event Date");
    TableColumn yourTime = new TableColumn("Time");
    TableColumn yourLocation = new TableColumn("Location");

    TableColumn dailyName = new TableColumn("Event");
    TableColumn dailyDescription = new TableColumn("Description"); 
    TableColumn dailyDate = new TableColumn("Event Date");
    TableColumn dailyTime = new TableColumn("Time");
    TableColumn dailyLocation = new TableColumn("Location");

    TableColumn pastName = new TableColumn("Event");
    TableColumn pastDescription = new TableColumn("Description");
    TableColumn pastDate = new TableColumn("Event Date");
    TableColumn pastTime = new TableColumn("Time");
    TableColumn pastLocation = new TableColumn("Location");
    
    TableColumn hostedName = new TableColumn("Event");
    TableColumn hostedDescription = new TableColumn("Description");
    TableColumn hostedDate = new TableColumn("Event Date");
    TableColumn hostedTime = new TableColumn("Time");
    TableColumn hostedLocation = new TableColumn("Location");
    
    Button yourAdd = new Button("Add");
    Button yourModify = new Button("Modify");
    Button dailyAdd = new Button("Add");
    Button dailyModify = new Button("Modify"); 
    Button pastAdd = new Button("Add");
    Button pastModify = new Button("Modify");
    Button hostedAdd = new Button("Add");
    Button hostedModify = new Button("Modify");
    
    // GridPane associated for each tab
    GridPane dailyOverall = new GridPane();
    GridPane dailyTablePane = new GridPane();
    GridPane dailyButtons = new GridPane();

    GridPane yourOverall = new GridPane();
    GridPane yourTablePane = new GridPane();
    GridPane yourButtons = new GridPane();

    GridPane pastOverall = new GridPane();
    GridPane pastTablePane = new GridPane();
    GridPane pastButtons = new GridPane();

    GridPane hostedOverall = new GridPane();
    GridPane hostedTablePane = new GridPane();
    GridPane hostedButtons = new GridPane();

    // Tab creation
    Tab tab1 = new Tab("Your Events");
    Tab tab2 = new Tab("Bark-Hosted");
    Tab tab3 = new Tab("Daily Events");
    Tab tab4 = new Tab("Past Events");

    GridPane eventsPane = new GridPane();
    TabPane tabPane = new TabPane();

    EventsRetry(VolunteerHome1 vol) throws SQLException {
        this.volHome = vol;

        setTables();

        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(eventsPane, 1000, 550);
        tabPane.setMaxWidth(eventsPane.getWidth());

        dailyTable.setMinWidth(primaryScene.getWidth() - dailyButtons.getWidth());
        pastTable.setMinWidth(primaryScene.getWidth() - pastButtons.getWidth());
        yourTable.setMinWidth(primaryScene.getWidth() - pastButtons.getWidth());
        hostedTable.setMinWidth(primaryScene.getWidth() - pastButtons.getWidth());

        HBox.setHgrow(tabPane, Priority.ALWAYS);
        HBox.setHgrow(dailyTable, Priority.ALWAYS);
        VBox.setVgrow(dailyTable, Priority.ALWAYS);
        HBox.setHgrow(pastTable, Priority.ALWAYS);
        HBox.setHgrow(yourTable, Priority.ALWAYS);
        HBox.setHgrow(hostedTable, Priority.ALWAYS);

        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Events Menu");
        primaryStage.show();

        tabPane.setMinHeight(primaryScene.getHeight());
        tabPane.setMinWidth(primaryScene.getWidth());
    }

    EventsRetry(Home home) throws SQLException {
        this.home = home;

        System.out.print("Hello. I am in admin home");
        setTables();

        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(eventsPane, 1000, 550);
        tabPane.setMaxWidth(eventsPane.getWidth());

        dailyTable.setMinWidth(primaryScene.getWidth() - dailyButtons.getWidth());
        pastTable.setMinWidth(primaryScene.getWidth() - pastButtons.getWidth());
        yourTable.setMinWidth(primaryScene.getWidth() - pastButtons.getWidth());
        hostedTable.setMinWidth(primaryScene.getWidth() - pastButtons.getWidth());

        HBox.setHgrow(tabPane, Priority.ALWAYS);
        HBox.setHgrow(dailyTable, Priority.ALWAYS);
        VBox.setVgrow(dailyTable, Priority.ALWAYS);
        HBox.setHgrow(pastTable, Priority.ALWAYS);
        HBox.setHgrow(yourTable, Priority.ALWAYS);
        HBox.setHgrow(hostedTable, Priority.ALWAYS);

        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Events Menu");
        primaryStage.show();

        tabPane.setMinHeight(primaryScene.getHeight());
        tabPane.setMinWidth(primaryScene.getWidth());
    }

    public void setTables() {
        yourTable.setItems(yourData);
        dailyTable.setItems(dailyData);
        pastTable.setItems(pastData);
        hostedTable.setItems(hostedData);

        eventsPane.add(tabPane, 0, 1);

        addButtons();

        tab1.setContent(yourOverall);
        tab2.setContent(hostedOverall);
        tab3.setContent(dailyOverall);
        tab4.setContent(pastOverall);
        tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);

        tab1.setClosable(false);
        tab2.setClosable(false);
        tab3.setClosable(false);
        tab4.setClosable(false);

        // Add the tables to the tabs
        dailyTablePane.add(dailyTable, 0, 0);
        hostedTablePane.add(hostedTable, 0, 0);
        yourTablePane.add(yourTable, 0, 0);
        pastTablePane.add(pastTable, 0, 0);

        //Set the cell values for eventTable (Daily Events)
        dailyName.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));
        dailyDescription.setCellValueFactory(new PropertyValueFactory<Event, String>("eventDescription"));
        dailyDate.setCellValueFactory(new PropertyValueFactory<Event, Date>("eventDate"));
        dailyTime.setCellValueFactory(new PropertyValueFactory<Event, String>("eventTime"));
        dailyLocation.setCellValueFactory(new PropertyValueFactory<Event, String>("eventLocation"));

        pastName.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));
        pastDescription.setCellValueFactory(new PropertyValueFactory<Event, String>("eventDescription"));
        pastDate.setCellValueFactory(new PropertyValueFactory<Event, Date>("eventDate"));
        pastTime.setCellValueFactory(new PropertyValueFactory<Event, String>("eventTime"));
        pastLocation.setCellValueFactory(new PropertyValueFactory<Event, String>("eventLocation"));

        yourName.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));
        yourDescription.setCellValueFactory(new PropertyValueFactory<Event, String>("eventDescription"));
        yourDate.setCellValueFactory(new PropertyValueFactory<Event, Date>("eventDate"));
        yourTime.setCellValueFactory(new PropertyValueFactory<Event, String>("eventTime"));
        yourLocation.setCellValueFactory(new PropertyValueFactory<Event, String>("eventLocation"));
        
        hostedName.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));
        hostedDescription.setCellValueFactory(new PropertyValueFactory<Event, String>("eventDescription"));
        hostedDate.setCellValueFactory(new PropertyValueFactory<Event, Date>("eventDate"));
        hostedTime.setCellValueFactory(new PropertyValueFactory<Event, String>("eventTime"));
        hostedLocation.setCellValueFactory(new PropertyValueFactory<Event, String>("eventLocation"));

        dailyTable.getColumns().addAll(dailyName, dailyDescription, dailyDate, dailyTime, dailyLocation);
        pastTable.getColumns().addAll(pastName, pastDescription, pastDate, pastTime, pastLocation);
        yourTable.getColumns().addAll(yourName, yourDescription, yourDate, yourTime, yourLocation);
        hostedTable.getColumns().addAll(hostedName, hostedDescription, hostedDate, hostedTime, hostedLocation);
        
         try {
            // Display daily events
            sendDBCommand("SELECT E.eventID, eventType, eventName, eventDescription, maxVolunteers, eventDate, eventTime, eventLocation, eventCategory, eventStatus "
                    + "FROM Event E WHERE eventCategory = 'Daily event' AND eventStatus != 'completed'");
            while (rs.next()) {
                dailyList.add(new Event(rs.getInt("eventID"), rs.getString("eventType"), rs.getString("eventName"), rs.getString("eventDescription"),
                        rs.getInt("maxVolunteers"), rs.getDate("eventDate"), rs.getString("eventTime"), rs.getString("eventLocation"), rs.getString("eventCategory"),
                        rs.getString("eventStatus")));
            }
            for(Event x : dailyList){
                dailyData.add(x); 
            }

            // Display completed events
            sendDBCommand("SELECT E.eventID, eventType, eventName, eventDescription, maxVolunteers, eventDate, eventTime, eventLocation, eventCategory, eventStatus "
                    + "FROM Event E WHERE eventStatus = 'completed'");
            while (rs.next()) {
                pastList.add(new Event(rs.getInt("eventID"), rs.getString("eventType"), rs.getString("eventName"), rs.getString("eventDescription"),
                        rs.getInt("maxVolunteers"), rs.getDate("eventDate"), rs.getString("eventTime"), rs.getString("eventLocation"), rs.getString("eventCategory"),
                        rs.getString("eventStatus")));
            }
            for(Event x : pastList){
                pastData.add(x); 
            }

            // Display BARK-hosted events
            sendDBCommand("SELECT E.eventID, eventType, eventName, eventDescription, maxVolunteers, eventDate, eventTime, eventLocation, eventCategory, eventStatus "
                    + "FROM Event E WHERE eventCategory = 'BARK hosted' AND eventStatus != 'completed'");
            while (rs.next()) {
                hostedData.add(new Event(rs.getInt("eventID"), rs.getString("eventType"), rs.getString("eventName"), rs.getString("eventDescription"),
                        rs.getInt("maxVolunteers"), rs.getDate("eventDate"), rs.getString("eventTime"), rs.getString("eventLocation"), rs.getString("eventCategory"),
                        rs.getString("eventStatus")));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void addButtons() {
        // Your table
        Label yourName = new Label("Name:");
        Label yourCategory = new Label("Category"); 
        Label yourDescription = new Label("Description");
        Label yourDate = new Label("Date");
        Label yourLocation = new Label("Location");
        TextField yourNameField = new TextField(); 
        TextField yourCatField = new TextField(); 
        TextField yourDescField = new TextField(); 
        TextField yourDateField = new TextField(); 
        TextField yourLocationField = new TextField();

        // Daily table
        Label dailyName = new Label("Name:");
        Label dailyCategory = new Label("Category"); 
        Label dailyDescription = new Label("Description");
        Label dailyDate = new Label("Date");
        Label dailyLocation = new Label("Location");
        TextField dailyNameField = new TextField(); 
        TextField dailyCatField = new TextField(); 
        TextField dailyDescField = new TextField(); 
        TextField dailyDateField = new TextField(); 
        TextField dailyLocationField = new TextField(); 

        // Past table
        Label pastName = new Label("Name:");
        Label pastCategory = new Label("Category"); 
        Label pastDescription = new Label("Description");
        Label pastDate = new Label("Date");
        Label pastLocation = new Label("Location");
        TextField pastNameField = new TextField(); 
        TextField pastCatField = new TextField(); 
        TextField pastDescField = new TextField(); 
        TextField pastDateField = new TextField(); 
        TextField pastLocationField = new TextField(); 

        // Hosted table
        Label hostedName = new Label("Name:");
        Label hostedCategory = new Label("Category"); 
        Label hostedDescription = new Label("Description");
        Label hostedDate = new Label("Date");
        Label hostedLocation = new Label("Location");
        TextField hostedNameField = new TextField(); 
        TextField hostedCatField = new TextField(); 
        TextField hostedDescField = new TextField(); 
        TextField hostedDateField = new TextField(); 
        TextField hostedLocationField = new TextField(); 
        

        yourButtons.add(yourName, 0, 1);
        yourButtons.add(yourNameField, 1, 1);
        yourButtons.add(yourCategory, 0, 2);
        yourButtons.add(yourCatField, 1, 2);
        yourButtons.add(yourDescription, 0, 3);
        yourButtons.add(yourDescField, 1, 3);
        yourButtons.add(yourDate, 0, 4);
        yourButtons.add(yourDateField, 1, 4);
        yourButtons.add(yourLocation, 0, 5);
        yourButtons.add(yourLocationField, 1, 5);
        yourButtons.add(yourAdd, 0, 6); 
        yourButtons.add(yourModify, 1, 6); 

        yourOverall.add(yourButtons, 0, 0);
        yourOverall.add(yourTablePane, 1, 0);

        pastButtons.add(pastName, 0, 1);
        pastButtons.add(pastNameField, 1, 1);
        pastButtons.add(pastCategory, 0, 2);
        pastButtons.add(pastCatField, 1, 2);
        pastButtons.add(pastDescription, 0, 3);
        pastButtons.add(pastDescField, 1, 3);
        pastButtons.add(pastDate, 0, 4);
        pastButtons.add(pastDateField, 1, 4);
        pastButtons.add(pastLocation, 0, 5);
        pastButtons.add(pastLocationField, 1, 5);
        pastButtons.add(pastAdd, 0, 6); 
        pastButtons.add(pastModify, 1, 6); 

        pastOverall.add(pastButtons, 0, 0);
        pastOverall.add(pastTablePane, 1, 0);

        dailyButtons.add(dailyName, 0, 1);
        dailyButtons.add(dailyNameField, 1, 1);
        dailyButtons.add(dailyCategory, 0, 2);
        dailyButtons.add(dailyCatField, 1, 2);
        dailyButtons.add(dailyDescription, 0, 3);
        dailyButtons.add(dailyDescField, 1, 3);
        dailyButtons.add(dailyDate, 0, 4);
        dailyButtons.add(dailyDateField, 1, 4);
        dailyButtons.add(dailyLocation, 0, 5);
        dailyButtons.add(dailyLocationField, 1, 5);
        dailyButtons.add(dailyAdd, 0, 6); 
        dailyButtons.add(dailyModify, 1, 6); 

        dailyOverall.add(dailyButtons, 0, 0);
        dailyOverall.add(dailyTablePane, 1, 0);

        hostedButtons.add(hostedName, 0, 1);
        hostedButtons.add(hostedNameField, 1, 1);
        hostedButtons.add(hostedCategory, 0, 2);
        hostedButtons.add(hostedCatField, 1, 2);
        hostedButtons.add(hostedDescription, 0, 3);
        hostedButtons.add(hostedDescField, 1, 3);
        hostedButtons.add(hostedDate, 0, 4);
        hostedButtons.add(hostedDateField, 1, 4);
        hostedButtons.add(hostedLocation, 0, 5);
        hostedButtons.add(hostedLocationField, 1, 5);
        hostedButtons.add(hostedAdd, 0, 6); 
        hostedButtons.add(hostedModify, 1, 6); 

        hostedOverall.add(hostedButtons, 0, 0);
        hostedOverall.add(hostedTablePane, 1, 0);

        hostedButtons.setMinWidth(300);
        dailyButtons.setMinWidth(300);
        yourButtons.setMinWidth(300);
        pastButtons.setMinWidth(300);

        yourAdd.setOnAction(e -> {
            System.out.println("Your add button clicked");
        });

        yourModify.setOnAction(e -> {
            System.out.println("Your modify button clicked");
        });
        pastAdd.setOnAction(e -> {
            System.out.println("Past add button clicked");
        });

        pastModify.setOnAction(e -> {
            System.out.println("Past modify button clicked");
        });
        dailyAdd.setOnAction(e -> {
            System.out.println("Daily add button clicked");
        });

        dailyModify.setOnAction(e -> {
            System.out.println("Daily modify button clicked");
            
        });
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
            //       System.out.println("RESULT SET: " + rs);

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

}
