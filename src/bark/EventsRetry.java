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

    TableView<Event> yourDailyTable = new TableView<>();
    TableView<Event> yourHostedTable = new TableView<>();
    TableView<Event> dailyTable = new TableView<>();
    TableView<Event> pastTable = new TableView<>();
    TableView<Event> hostedTable = new TableView<>();

    ObservableList<Event> yourDailyData = FXCollections.observableArrayList();
    ObservableList<Event> yourHostedData = FXCollections.observableArrayList();
    ObservableList<Event> dailyData = FXCollections.observableArrayList();
    ObservableList<Event> pastData = FXCollections.observableArrayList();
    ObservableList<Event> hostedData = FXCollections.observableArrayList();

    ArrayList<Event> yourDailyList = new ArrayList<>();
    ArrayList<Event> yourHostedList = new ArrayList<>();
    ArrayList<Event> dailyList = new ArrayList<>();
    ArrayList<Event> pastList = new ArrayList<>();
    ArrayList<Event> hostedList = new ArrayList<>();

    //Create the columns for each table
    TableColumn yourDailyID = new TableColumn("ID");
    TableColumn yourDailyName = new TableColumn("Event");
    TableColumn yourDailyDescription = new TableColumn("Description");
    TableColumn yourDailyDate = new TableColumn("Event Date");
    TableColumn yourDailyTime = new TableColumn("Time");
    TableColumn yourDailyLocation = new TableColumn("Location");

    TableColumn yourHostedID = new TableColumn("ID");
    TableColumn yourHostedName = new TableColumn("Event");
    TableColumn yourHostedDescription = new TableColumn("Description");
    TableColumn yourHostedDate = new TableColumn("Event Date");
    TableColumn yourHostedTime = new TableColumn("Time");
    TableColumn yourHostedLocation = new TableColumn("Location");

    TableColumn dailyID = new TableColumn("ID");
    TableColumn dailyName = new TableColumn("Event");
    TableColumn dailyDescription = new TableColumn("Description");
    TableColumn dailyDate = new TableColumn("Event Date");
    TableColumn dailyTime = new TableColumn("Time");
    TableColumn dailyLocation = new TableColumn("Location");

    TableColumn pastID = new TableColumn("ID");
    TableColumn pastName = new TableColumn("Event");
    TableColumn pastDescription = new TableColumn("Description");
    TableColumn pastDate = new TableColumn("Event Date");
    TableColumn pastTime = new TableColumn("Time");
    TableColumn pastLocation = new TableColumn("Location");

    TableColumn hostedID = new TableColumn("ID");
    TableColumn hostedName = new TableColumn("Event");
    TableColumn hostedDescription = new TableColumn("Description");
    TableColumn hostedDate = new TableColumn("Event Date");
    TableColumn hostedTime = new TableColumn("Time");
    TableColumn hostedLocation = new TableColumn("Location");

    Button yourUnregister = new Button("Unregister");
    Button yourHostedPopulate = new Button("<-- Select and Populate");
    Button yourDailyPopulate = new Button("<-- Select and Populate");
    Button dailyAdd = new Button("Add");
    Button dailyModify = new Button("Modify");
    Button dailyPopulate = new Button("<-- Select and Populate");
    Button pastModify = new Button("Modify");
    Button pastPopulate = new Button("<-- Select and Populate");
    Button hostedAdd = new Button("Add");
    Button hostedModify = new Button("Modify");
    Button hostedPopulate = new Button("<-- Select and Populate");
    Button dailyComplete = new Button("Event Completed");
    Button hostedComplete = new Button("Event Completed");
    Button dailySignup = new Button("Sign Up");
    Button hostedSignup = new Button("Sign up");
    Label hostedMessage = new Label("");
    Label dailyMessage = new Label("");
    Label yourDailyLbl = new Label("Your Daily Tasks");
    Label yourHostedLbl = new Label("Your Registered Events");

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
    int id = 0;
    int check = 0;

    EventsRetry(VolunteerHome1 vol) throws SQLException {
        this.volHome = vol;
        id = vol.loginid;
        setTables();

        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(eventsPane, 1000, 550);
        tabPane.setMaxWidth(eventsPane.getWidth());

        dailyTable.setMinWidth(primaryScene.getWidth() - dailyButtons.getWidth());
        pastTable.setMinWidth(primaryScene.getWidth() - pastButtons.getWidth());
        yourDailyTable.setMinWidth(primaryScene.getWidth() - pastButtons.getWidth());
        yourHostedTable.setMinWidth(primaryScene.getWidth() - pastButtons.getWidth());
        hostedTable.setMinWidth(primaryScene.getWidth() - pastButtons.getWidth());

        HBox.setHgrow(tabPane, Priority.ALWAYS);
        HBox.setHgrow(dailyTable, Priority.ALWAYS);
        VBox.setVgrow(dailyTable, Priority.ALWAYS);
        HBox.setHgrow(pastTable, Priority.ALWAYS);
        HBox.setHgrow(yourDailyTable, Priority.ALWAYS);
        HBox.setHgrow(yourHostedTable, Priority.ALWAYS);
        VBox.setVgrow(yourHostedTable, Priority.ALWAYS);
        HBox.setHgrow(hostedTable, Priority.ALWAYS);

        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Events Menu");
        primaryStage.show();

        tabPane.setMinHeight(primaryScene.getHeight());
        tabPane.setMinWidth(primaryScene.getWidth());
    }

    EventsRetry(Home home) throws SQLException {
        this.home = home;
        //Retrieve the ID of the user who is logged in
        id = home.loginid;
        setTables();

        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(eventsPane, 1000, 550);
        tabPane.setMaxWidth(eventsPane.getWidth());

        dailyTable.setMinWidth(primaryScene.getWidth() - dailyButtons.getWidth());
        pastTable.setMinWidth(primaryScene.getWidth() - pastButtons.getWidth());
        yourDailyTable.setMinWidth(primaryScene.getWidth() - pastButtons.getWidth());
        yourHostedTable.setMinWidth(primaryScene.getWidth() - pastButtons.getWidth());
        hostedTable.setMinWidth(primaryScene.getWidth() - pastButtons.getWidth());

        HBox.setHgrow(tabPane, Priority.ALWAYS);
        HBox.setHgrow(dailyTable, Priority.ALWAYS);
        VBox.setVgrow(dailyTable, Priority.ALWAYS);
        HBox.setHgrow(pastTable, Priority.ALWAYS);
        HBox.setHgrow(yourDailyTable, Priority.ALWAYS);
        HBox.setHgrow(hostedTable, Priority.ALWAYS);

        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Events Menu");
        primaryStage.show();

        tabPane.setMinHeight(primaryScene.getHeight());
        tabPane.setMinWidth(primaryScene.getWidth());
    }

    public void setTables() {
        yourDailyTable.setItems(yourDailyData);
        yourHostedTable.setItems(yourHostedData);
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
        yourTablePane.add(yourDailyTable, 0, 1);
        yourTablePane.add(yourHostedTable, 0, 5);
        pastTablePane.add(pastTable, 0, 0);

        //Set the cell values for eventTable (Daily Events)
        dailyID.setCellValueFactory(new PropertyValueFactory<Event, Integer>("eventID"));
        dailyName.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));
        dailyDescription.setCellValueFactory(new PropertyValueFactory<Event, String>("eventDescription"));
        dailyDate.setCellValueFactory(new PropertyValueFactory<Event, Date>("eventDate"));
        dailyTime.setCellValueFactory(new PropertyValueFactory<Event, String>("eventTime"));
        dailyLocation.setCellValueFactory(new PropertyValueFactory<Event, String>("eventLocation"));

        pastID.setCellValueFactory(new PropertyValueFactory<Event, Integer>("eventID"));
        pastName.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));
        pastDescription.setCellValueFactory(new PropertyValueFactory<Event, String>("eventDescription"));
        pastDate.setCellValueFactory(new PropertyValueFactory<Event, Date>("eventDate"));
        pastTime.setCellValueFactory(new PropertyValueFactory<Event, String>("eventTime"));
        pastLocation.setCellValueFactory(new PropertyValueFactory<Event, String>("eventLocation"));

        yourDailyID.setCellValueFactory(new PropertyValueFactory<Event, Integer>("eventID"));
        yourDailyName.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));
        yourDailyDescription.setCellValueFactory(new PropertyValueFactory<Event, String>("eventDescription"));
        yourDailyDate.setCellValueFactory(new PropertyValueFactory<Event, Date>("eventDate"));
        yourDailyTime.setCellValueFactory(new PropertyValueFactory<Event, String>("eventTime"));
        yourDailyLocation.setCellValueFactory(new PropertyValueFactory<Event, String>("eventLocation"));

        yourHostedID.setCellValueFactory(new PropertyValueFactory<Event, Integer>("eventID"));
        yourHostedName.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));
        yourHostedDescription.setCellValueFactory(new PropertyValueFactory<Event, String>("eventDescription"));
        yourHostedDate.setCellValueFactory(new PropertyValueFactory<Event, Date>("eventDate"));
        yourHostedTime.setCellValueFactory(new PropertyValueFactory<Event, String>("eventTime"));
        yourHostedLocation.setCellValueFactory(new PropertyValueFactory<Event, String>("eventLocation"));

        hostedID.setCellValueFactory(new PropertyValueFactory<Event, Integer>("eventID"));
        hostedName.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));
        hostedDescription.setCellValueFactory(new PropertyValueFactory<Event, String>("eventDescription"));
        hostedDate.setCellValueFactory(new PropertyValueFactory<Event, Date>("eventDate"));
        hostedTime.setCellValueFactory(new PropertyValueFactory<Event, String>("eventTime"));
        hostedLocation.setCellValueFactory(new PropertyValueFactory<Event, String>("eventLocation"));
        dailyTable.getColumns().addAll(dailyID, dailyName, dailyDescription, dailyDate, dailyTime, dailyLocation);
        pastTable.getColumns().addAll(pastID, pastName, pastDescription, pastDate, pastTime, pastLocation);
        yourDailyTable.getColumns().addAll(yourDailyID, yourDailyName, yourDailyDescription, yourDailyDate, yourDailyTime, yourDailyLocation);
        yourHostedTable.getColumns().addAll(yourHostedID, yourHostedName, yourHostedDescription, yourHostedDate, yourHostedTime, yourHostedLocation);
        hostedTable.getColumns().addAll(hostedID, hostedName, hostedDescription, hostedDate, hostedTime, hostedLocation);

        try {
            // Display daily events
            sendDBCommand("SELECT E.eventID, eventType, eventName, eventDescription, maxVolunteers, eventDate, eventTime, eventLocation, eventCategory, eventStatus "
                    + "FROM Event E WHERE eventCategory = 'Daily event' AND eventStatus != 'completed'");
            while (rs.next()) {
                dailyList.add(new Event(rs.getInt("eventID"), rs.getString("eventType"), rs.getString("eventName"), rs.getString("eventDescription"),
                        rs.getInt("maxVolunteers"), rs.getString("eventDate"), rs.getString("eventTime"), rs.getString("eventLocation"), rs.getString("eventCategory"),
                        rs.getString("eventStatus")));
            }
            for (Event x : dailyList) {
                dailyData.add(x);
            }

            // Display completed events
            sendDBCommand("SELECT E.eventID, eventType, eventName, eventDescription, maxVolunteers, eventDate, eventTime, eventLocation, eventCategory, eventStatus "
                    + "FROM Event E WHERE eventStatus = 'completed'");
            while (rs.next()) {
                pastList.add(new Event(rs.getInt("eventID"), rs.getString("eventType"), rs.getString("eventName"), rs.getString("eventDescription"),
                        rs.getInt("maxVolunteers"), rs.getString("eventDate"), rs.getString("eventTime"), rs.getString("eventLocation"), rs.getString("eventCategory"),
                        rs.getString("eventStatus")));
            }
            for (Event x : pastList) {
                pastData.add(x);
            }

            // Display BARK-hosted events
            sendDBCommand("SELECT E.eventID, eventType, eventName, eventDescription, maxVolunteers, eventDate, eventTime, eventLocation, eventCategory, eventStatus "
                    + "FROM Event E WHERE eventCategory = 'BARK hosted' AND eventStatus != 'completed'");
            while (rs.next()) {
                hostedList.add(new Event(rs.getInt("eventID"), rs.getString("eventType"), rs.getString("eventName"), rs.getString("eventDescription"),
                        rs.getInt("maxVolunteers"), rs.getString("eventDate"), rs.getString("eventTime"), rs.getString("eventLocation"), rs.getString("eventCategory"),
                        rs.getString("eventStatus")));
            }
            for (Event x : hostedList) {
                hostedData.add(x);
            }
            String query1 = "SELECT e.eventID, eventName, eventType, eventDescription, eventDate, eventTime, eventLocation, eh.volID, eventStatus FROM eventHistory eh "
                    + "INNER JOIN event e ON e.eventID = eh.eventID WHERE volID = " + id + " AND eventCategory = 'Daily event'";
            sendDBCommand(query1);
            //Add events to the user's event list where their id is matched to an event in the database
            yourDailyList.clear();
            try {
                while (rs.next()) {
                    yourDailyList.add(new Event(rs.getInt("eventID"), rs.getString("eventName"), rs.getString("eventType"), rs.getString("eventDescription"), rs.getString("eventDate"), rs.getString("eventTime"), rs.getString("eventLocation"), rs.getString("eventStatus")));
                }
            } catch (Exception ex) {
                System.out.println("Exception! " + ex);
            }
            for (Event ex : yourDailyList) {
                yourDailyData.add(ex);
            }

            String query2 = "SELECT e.eventID, eventName, eventType, eventDescription, eventDate, eventTime, eventLocation, eh.volID, eventStatus FROM eventHistory eh "
                    + "INNER JOIN event e ON  e.eventID = eh.eventID WHERE volID = " + id + " AND eventCategory = 'BARK hosted'";
            sendDBCommand(query2);
            //Add events to the user's event list where their id is matched to an event in the database
            yourHostedList.clear();
            try {
                while (rs.next()) {
                    yourHostedList.add(new Event(rs.getInt("eventID"), rs.getString("eventName"), rs.getString("eventType"), rs.getString("eventDescription"), rs.getString("eventDate"), rs.getString("eventTime"), rs.getString("eventLocation"), rs.getString("eventStatus")));
                }
            } catch (Exception ex) {
                System.out.println("Exception! " + ex);
            }
            for (Event ex : yourHostedList) {
                yourHostedData.add(ex);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void addButtons() {
        // Your table
        Label yourID = new Label("ID: ");
        Label yourName = new Label("Name:");
        Label yourCategory = new Label("Category");
        Label yourDescription = new Label("Description");
        Label yourDate = new Label("Date");
        Label yourTime = new Label("Time");
        Label yourLocation = new Label("Location");
        Label yourIdField = new Label("");
        TextField yourNameField = new TextField();
        TextField yourCatField = new TextField();
        TextField yourDescField = new TextField();
        TextField yourDateField = new TextField();
        TextField yourTimeField = new TextField();
        TextField yourLocationField = new TextField();
        // Daily table
        Label dailyID = new Label("ID: ");
        Label dailyName = new Label("Name:");
        Label dailyCategory = new Label("Category");
        Label dailyDescription = new Label("Description");
        Label dailyDate = new Label("Date");
        Label dailyTime = new Label("Time");
        Label dailyLocation = new Label("Location");
        Label dailyIdField = new Label("");
        TextField dailyNameField = new TextField();
        TextField dailyCatField = new TextField();
        TextField dailyDescField = new TextField();
        TextField dailyDateField = new TextField();
        TextField dailyTimeField = new TextField();
        TextField dailyLocationField = new TextField();
        // Past table
        Label pastID = new Label("ID: ");
        Label pastName = new Label("Name:");
        Label pastCategory = new Label("Category");
        Label pastDescription = new Label("Description");
        Label pastDate = new Label("Date");
        Label pastTime = new Label("Time");
        Label pastLocation = new Label("Location");
        Label pastIdField = new Label("");
        TextField pastNameField = new TextField();
        TextField pastCatField = new TextField();
        TextField pastDescField = new TextField();
        TextField pastDateField = new TextField();
        TextField pastTimeField = new TextField();
        TextField pastLocationField = new TextField();
        // Hosted table
        Label hostedID = new Label("ID: ");
        Label hostedName = new Label("Name:");
        Label hostedCategory = new Label("Category");
        Label hostedDescription = new Label("Description");
        Label hostedDate = new Label("Date");
        Label hostedTime = new Label("Time");
        Label hostedLocation = new Label("Location");
        Label hostedIdField = new Label("");
        TextField hostedNameField = new TextField();
        TextField hostedCatField = new TextField();
        TextField hostedDescField = new TextField();
        TextField hostedDateField = new TextField();
        TextField hostedTimeField = new TextField();
        TextField hostedLocationField = new TextField();

        // Add all buttons for your table
        yourButtons.add(yourID, 0, 1);
        yourButtons.add(yourIdField, 1, 1);
        yourButtons.add(yourName, 0, 2);
        yourButtons.add(yourNameField, 1, 2);
        yourButtons.add(yourCategory, 0, 3);
        yourButtons.add(yourCatField, 1, 3);
        yourButtons.add(yourDescription, 0, 4);
        yourButtons.add(yourDescField, 1, 4);
        yourButtons.add(yourDate, 0, 5);
        yourButtons.add(yourDateField, 1, 5);
        yourButtons.add(yourTime, 0, 6);
        yourButtons.add(yourTimeField, 1, 6);
        yourButtons.add(yourLocation, 0, 7);
        yourButtons.add(yourLocationField, 1, 7);
        yourButtons.add(yourUnregister, 1, 8);
        yourTablePane.add(yourDailyPopulate, 0, 2);
        yourTablePane.add(yourHostedPopulate, 0, 6);
        yourTablePane.add(yourDailyLbl, 0, 0);
        yourTablePane.add(yourHostedLbl, 0, 3);

        yourOverall.add(yourButtons, 0, 0);
        yourOverall.add(yourTablePane, 1, 0);

        // Add all buttons for past table
        pastButtons.add(pastID, 0, 1);
        pastButtons.add(pastIdField, 1, 1);
        pastButtons.add(pastName, 0, 2);
        pastButtons.add(pastNameField, 1, 2);
        pastButtons.add(pastCategory, 0, 3);
        pastButtons.add(pastCatField, 1, 3);
        pastButtons.add(pastDescription, 0, 4);
        pastButtons.add(pastDescField, 1, 4);
        pastButtons.add(pastDate, 0, 5);
        pastButtons.add(pastDateField, 1, 5);
        pastButtons.add(pastTime, 0, 6);
        pastButtons.add(pastTimeField, 1, 6);
        pastButtons.add(pastLocation, 0, 7);
        pastButtons.add(pastLocationField, 1, 7);
        pastButtons.add(pastModify, 1, 8);
        pastTablePane.add(pastPopulate, 0, 1);

        pastOverall.add(pastButtons, 0, 0);
        pastOverall.add(pastTablePane, 1, 0);

        // Add all buttons for daily table
        dailyButtons.add(dailyID, 0, 1);
        dailyButtons.add(dailyIdField, 1, 1);
        dailyButtons.add(dailyName, 0, 2);
        dailyButtons.add(dailyNameField, 1, 2);
        dailyButtons.add(dailyCategory, 0, 3);
        dailyButtons.add(dailyCatField, 1, 3);
        dailyButtons.add(dailyDescription, 0, 4);
        dailyButtons.add(dailyDescField, 1, 4);
        dailyButtons.add(dailyDate, 0, 5);
        dailyButtons.add(dailyDateField, 1, 5);
        dailyButtons.add(dailyTime, 0, 6);
        dailyButtons.add(dailyTimeField, 1, 6);
        dailyButtons.add(dailyLocation, 0, 7);
        dailyButtons.add(dailyLocationField, 1, 7);
        dailyButtons.add(dailyAdd, 0, 8);
        dailyButtons.add(dailyModify, 1, 8);
        dailyTablePane.add(dailyPopulate, 0, 1);
        dailyButtons.add(dailyComplete, 1, 10);
        dailyButtons.add(dailySignup, 0, 10);

        dailyOverall.add(dailyButtons, 0, 0);
        dailyOverall.add(dailyTablePane, 1, 0);

        // Add all buttons for hosted table
        hostedButtons.add(hostedID, 0, 1);
        hostedButtons.add(hostedIdField, 1, 1);
        hostedButtons.add(hostedName, 0, 2);
        hostedButtons.add(hostedNameField, 1, 2);
        hostedButtons.add(hostedCategory, 0, 3);
        hostedButtons.add(hostedCatField, 1, 3);
        hostedButtons.add(hostedDescription, 0, 4);
        hostedButtons.add(hostedDescField, 1, 4);
        hostedButtons.add(hostedDate, 0, 5);
        hostedButtons.add(hostedDateField, 1, 5);
        hostedButtons.add(hostedTime, 0, 6);
        hostedButtons.add(hostedTimeField, 1, 6);
        hostedButtons.add(hostedLocation, 0, 7);
        hostedButtons.add(hostedLocationField, 1, 7);
        hostedButtons.add(hostedAdd, 0, 8);
        hostedButtons.add(hostedModify, 1, 8);
        hostedButtons.add(hostedComplete, 1, 10);
        hostedButtons.add(hostedSignup, 0, 10);
        hostedTablePane.add(hostedPopulate, 0, 1);
        hostedTablePane.add(hostedMessage, 0, 2);

        hostedOverall.add(hostedButtons, 0, 0);
        hostedOverall.add(hostedTablePane, 1, 0);

        hostedButtons.setMinWidth(300);
        dailyButtons.setMinWidth(300);
        yourButtons.setMinWidth(300);
        pastButtons.setMinWidth(300);

        //Action handler to modify past events
        pastModify.setOnAction(e -> {
            int newID = Integer.valueOf(pastIdField.getText());
            String newName = pastNameField.getText();
            String newCategory = pastCatField.getText();
            String newDescription = pastDescField.getText();
            String newDate = pastDateField.getText();
            String newTime = pastTimeField.getText();
            String newLocation = pastLocationField.getText();

            String query = "UPDATE EVENT SET eventID = " + newID + ", eventName = '" + newName + "', eventCategory = '" + newCategory + "', eventDescription = '" + newDescription + "', eventDate = '" + newDate + "', eventTime = '" + newTime + "', eventLocation = '" + newLocation + "' WHERE eventID = " + newID + "";

            sendDBCommand(query);
            for (int i = 0; i < pastList.size(); i++) {
                if (pastList.get(i).getEventID() == newID) {
                    pastList.get(i).setEventName(newName);
                    pastList.get(i).setEventType(newCategory);
                    pastList.get(i).setEventDescription(newDescription);
                    pastList.get(i).setEventDate(newDate);
                    pastList.get(i).setEventTime(newTime);
                    pastList.get(i).setEventLocation(newLocation);
                }
            }
            pastData.clear();
            for (Event a : pastList) {
                pastData.add(a);
            }
        });
        //Action handler to populate the textfields from a past event
        pastPopulate.setOnAction(e -> {
            pastIdField.setText(pastTable.getSelectionModel().getSelectedItem().getEventID() + "");
            pastNameField.setText(pastTable.getSelectionModel().getSelectedItem().getEventName());
            pastCatField.setText(pastTable.getSelectionModel().getSelectedItem().getEventType());
            pastDescField.setText(pastTable.getSelectionModel().getSelectedItem().getEventDescription());
            pastDateField.setText(pastTable.getSelectionModel().getSelectedItem().getEventDate());
            pastTimeField.setText(pastTable.getSelectionModel().getSelectedItem().getEventTime());
            pastLocationField.setText(pastTable.getSelectionModel().getSelectedItem().getEventLocation());
        });
        //Action handler to add an event to daily events
        dailyAdd.setOnAction(e -> {
            int largest = 0;
            try {
                String q = "SELECT * FROM EVENT";
                sendDBCommand(q);
                try {
                    while (rs.next()) {
                        largest = rs.getInt("eventID");
                        while (rs.next()) {
                            int store = rs.getInt("eventID");
                            if (store > largest) {
                                largest = store;
                            }
                        }
                    }
                } catch (Exception m) {
                    System.out.println("Exception finding the largest! " + m);
                }
                int newID = largest + 1;
                String newName = dailyNameField.getText();
                String newCategory = dailyCatField.getText();
                String newDescription = dailyDescField.getText();
                String newDate = dailyDateField.getText();
                String newTime = dailyTimeField.getText();
                String newLocation = dailyLocationField.getText();
                String category = "Daily event";
                String newStatus = "upcoming";

                Event newEvent = new Event(newID, newName, newCategory, newDescription, newDate, newTime, newLocation, newStatus);
                dailyList.add(newEvent);

                String query = "INSERT INTO EVENT(eventID, eventName, eventType, eventDescription, eventDate, eventTime, eventLocation, eventCategory, eventStatus) VALUES (" + newID + ", '" + newName + "', '" + newCategory + "', '" + newDescription + "', '" + newDate + "', '" + newTime + "', '" + newLocation + "', '" + category + "', '" + newStatus + "')";
                sendDBCommand(query);

                dailyData.clear();
                for (Event x : dailyList) {
                    dailyData.add(x);
                }
            } catch (Exception ex) {
                System.out.println("Error! " + ex);
            }
        });
        //Action handler to modify a daily event
        dailyModify.setOnAction(e -> {
            int newID = Integer.valueOf(dailyIdField.getText());
            String newName = dailyNameField.getText();
            String newCategory = dailyCatField.getText();
            String newDescription = dailyDescField.getText();
            String newDate = dailyDateField.getText();
            String newTime = dailyTimeField.getText();
            String newLocation = dailyLocationField.getText();

            String query = "UPDATE EVENT SET eventID = " + newID + ", eventName = '" + newName + "', eventCategory = '" + newCategory + "', eventDescription = '" + newDescription + "', eventDate = '" + newDate + "', eventTime = '" + newTime + "', eventLocation = '" + newLocation + "' WHERE eventID = " + newID + "";

            sendDBCommand(query);
            for (int i = 0; i < dailyList.size(); i++) {
                if (dailyList.get(i).getEventID() == newID) {
                    dailyList.get(i).setEventName(newName);
                    dailyList.get(i).setEventCategory(newCategory);
                    dailyList.get(i).setEventDescription(newDescription);
                    dailyList.get(i).setEventDate(newDate);
                    dailyList.get(i).setEventTime(newTime);
                    dailyList.get(i).setEventLocation(newLocation);
                }
            }
            dailyData.clear();
            for (Event a : dailyList) {
                dailyData.add(a);
            }
        });
        //Action handler to populate textfields from the information of a daily event 
        dailyPopulate.setOnAction(e -> {
            dailyIdField.setText(dailyTable.getSelectionModel().getSelectedItem().getEventID() + "");
            dailyNameField.setText(dailyTable.getSelectionModel().getSelectedItem().getEventName());
            dailyCatField.setText(dailyTable.getSelectionModel().getSelectedItem().getEventCategory());
            dailyDescField.setText(dailyTable.getSelectionModel().getSelectedItem().getEventDescription());
            dailyDateField.setText(dailyTable.getSelectionModel().getSelectedItem().getEventDate());
            dailyTimeField.setText(dailyTable.getSelectionModel().getSelectedItem().getEventTime());
            dailyLocationField.setText(dailyTable.getSelectionModel().getSelectedItem().getEventLocation());
        });
        //Action handler to populate textfields from the information of a bark hosted event 
        hostedPopulate.setOnAction(e -> {
            hostedIdField.setText(hostedTable.getSelectionModel().getSelectedItem().getEventID() + "");
            hostedNameField.setText(hostedTable.getSelectionModel().getSelectedItem().getEventName());
            hostedCatField.setText(hostedTable.getSelectionModel().getSelectedItem().getEventType());
            hostedDescField.setText(hostedTable.getSelectionModel().getSelectedItem().getEventDescription());
            hostedDateField.setText(hostedTable.getSelectionModel().getSelectedItem().getEventDate());
            hostedTimeField.setText(hostedTable.getSelectionModel().getSelectedItem().getEventTime());
            hostedLocationField.setText(hostedTable.getSelectionModel().getSelectedItem().getEventLocation());
        });
        yourDailyPopulate.setOnAction(e -> {
            yourIdField.setText(yourDailyTable.getSelectionModel().getSelectedItem().getEventID() + "");
            yourNameField.setText(yourDailyTable.getSelectionModel().getSelectedItem().getEventName());
            yourCatField.setText(yourDailyTable.getSelectionModel().getSelectedItem().getEventType());
            yourDescField.setText(yourDailyTable.getSelectionModel().getSelectedItem().getEventDescription());
            yourDateField.setText(yourDailyTable.getSelectionModel().getSelectedItem().getEventDate());
            yourTimeField.setText(yourDailyTable.getSelectionModel().getSelectedItem().getEventTime());
            yourLocationField.setText(yourDailyTable.getSelectionModel().getSelectedItem().getEventLocation());
            check = 1; // If the data is from daily table, mark it as 1
        });
        yourHostedPopulate.setOnAction(e -> {
            yourIdField.setText(yourHostedTable.getSelectionModel().getSelectedItem().getEventID() + "");
            yourNameField.setText(yourHostedTable.getSelectionModel().getSelectedItem().getEventName());
            yourCatField.setText(yourHostedTable.getSelectionModel().getSelectedItem().getEventType());
            yourDescField.setText(yourHostedTable.getSelectionModel().getSelectedItem().getEventDescription());
            yourDateField.setText(yourHostedTable.getSelectionModel().getSelectedItem().getEventDate());
            yourTimeField.setText(yourHostedTable.getSelectionModel().getSelectedItem().getEventTime());
            yourLocationField.setText(yourHostedTable.getSelectionModel().getSelectedItem().getEventLocation());
            check = 2; // If the data is from hosted table, mark it as 2
        });
        yourUnregister.setOnAction(e -> {
            if (check == 1) {
                int eventid = yourDailyTable.getSelectionModel().getSelectedItem().getEventID();
                String eventName = yourDailyTable.getSelectionModel().getSelectedItem().getEventName();
                String eventType = yourDailyTable.getSelectionModel().getSelectedItem().getEventType();
                String eventDescription = yourDailyTable.getSelectionModel().getSelectedItem().getEventDescription();
                String eventDate = yourDailyTable.getSelectionModel().getSelectedItem().getEventDate();
                String eventTime = yourDailyTable.getSelectionModel().getSelectedItem().getEventTime();
                String eventLocation = yourDailyTable.getSelectionModel().getSelectedItem().getEventLocation();
                String eventStatus = yourDailyTable.getSelectionModel().getSelectedItem().getEventStatus();
                yourDailyList.remove(new Event(eventid, eventName, eventType, eventDescription, eventDate, eventTime, eventLocation, eventStatus));
                String query = "DELETE from EVENTHISTORY where volID = " + id + " AND eventID = " + eventid + "";
                sendDBCommand(query);
                int index = yourDailyTable.getSelectionModel().getSelectedIndex();
//                yourDailyTable.getItems().remove(index);
                yourDailyData.remove(index);
//                for (Event x : yourDailyList) {
//                    yourDailyData.remove(x);
//                }
            }
            if (check == 2) {
                int eventid = yourHostedTable.getSelectionModel().getSelectedItem().getEventID();
                String eventName = yourHostedTable.getSelectionModel().getSelectedItem().getEventName();
                String eventType = yourHostedTable.getSelectionModel().getSelectedItem().getEventType();
                String eventDescription = yourHostedTable.getSelectionModel().getSelectedItem().getEventDescription();
                String eventDate = yourHostedTable.getSelectionModel().getSelectedItem().getEventDate();
                String eventTime = yourHostedTable.getSelectionModel().getSelectedItem().getEventTime();
                String eventLocation = yourHostedTable.getSelectionModel().getSelectedItem().getEventLocation();
                String eventStatus = yourHostedTable.getSelectionModel().getSelectedItem().getEventStatus();
                yourHostedList.remove(new Event(eventid, eventName, eventType, eventDescription, eventDate, eventTime, eventLocation, eventStatus));
                String query = "DELETE from EVENTHISTORY where volID = " + id + " AND eventID = " + eventid + "";
                sendDBCommand(query);
                yourHostedTable.getItems().remove(yourHostedTable.getSelectionModel().getSelectedIndex());
                for (Event x : yourHostedList) {
                    yourHostedData.remove(x);
                }
            }
        });

        //Action handler to add a bark hosted event
        hostedAdd.setOnAction(e -> {
            int largest = 0;
            try {
                String q = "SELECT * FROM EVENT";
                sendDBCommand(q);
                try {
                    while (rs.next()) {
                        largest = rs.getInt("eventID");
                        while (rs.next()) {
                            int store = rs.getInt("eventID");
                            if (store > largest) {
                                largest = store;
                            }
                        }
                    }
                } catch (Exception m) {
                    System.out.println("Exception finding the largest! " + m);
                }
                int newID = largest + 1;
                String newName = hostedNameField.getText();
                String newCategory = hostedCatField.getText();
                String newDescription = hostedDescField.getText();
                String newDate = hostedDateField.getText();
                String newTime = hostedTimeField.getText();
                String newLocation = hostedLocationField.getText();
                String category = "BARK hosted";
                String newStatus = "upcoming";

                Event newEvent = new Event(newID, newName, newCategory, newDescription, newDate, newTime, newLocation, newStatus);
                hostedList.add(newEvent);

                String query = "INSERT INTO EVENT(eventID, eventName, eventType, eventDescription, eventDate, eventTime, eventLocation, eventCategory, eventStatus) VALUES (" + newID + ", '" + newName + "', '" + newCategory + "', '" + newDescription + "', '" + newDate + "', '" + newTime + "', '" + newLocation + "', '" + category + "', '" + newStatus + "')";
                sendDBCommand(query);

                hostedData.clear();
                for (Event x : hostedList) {
                    hostedData.add(x);
                }
            } catch (Exception ex) {
                System.out.println("Error! " + ex);
            }
        });
        //Action handler to modify a bark hosted event
        hostedModify.setOnAction(e -> {
            int newID = Integer.valueOf(hostedIdField.getText());
            String newName = hostedNameField.getText();
            String newCategory = hostedCatField.getText();
            String newDescription = hostedDescField.getText();
            String newDate = hostedDateField.getText();
            String newTime = hostedTimeField.getText();
            String newLocation = hostedLocationField.getText();

            String query = "UPDATE EVENT SET eventID = " + newID + ", eventName = '" + newName + "', eventCategory = '" + newCategory + "', eventDescription = '" + newDescription + "', eventDate = '" + newDate + "', eventTime = '" + newTime + "', eventLocation = '" + newLocation + "' WHERE eventID = " + newID + "";

            sendDBCommand(query);
            for (int i = 0; i < hostedList.size(); i++) {
                if (hostedList.get(i).getEventID() == newID) {
                    hostedList.get(i).setEventName(newName);
                    hostedList.get(i).setEventCategory(newCategory);
                    hostedList.get(i).setEventDescription(newDescription);
                    hostedList.get(i).setEventDate(newDate);
                    hostedList.get(i).setEventTime(newTime);
                    hostedList.get(i).setEventLocation(newLocation);
                }
            }
            hostedData.clear();
            for (Event a : hostedList) {
                hostedData.add(a);
            }
        });
        //Action handler to mark a daily event as having been completed 
        dailyComplete.setOnAction(e -> {
            int id = dailyTable.getSelectionModel().getSelectedItem().getEventID();
            String query = "UPDATE EVENT SET eventStatus = 'completed' WHERE eventID = " + id;
            int index = dailyTable.getSelectionModel().getSelectedIndex();
            dailyList.get(index).setEventStatus("complete");
            sendDBCommand(query);
            pastList.add(dailyList.get(index));
            dailyList.remove(index);
            dailyData.clear();
            for (Event x : dailyList) {
                dailyData.add(x);
            }
            pastData.clear();
            for (Event x : pastList) {
                pastData.add(x);
            }
        });
        //Action handler to mark a bark hosted event as having been completed 
        hostedComplete.setOnAction(e -> {
            int id = hostedTable.getSelectionModel().getSelectedItem().getEventID();
            String query = "UPDATE EVENT SET eventStatus = 'completed' WHERE eventID = " + id;
            int index = hostedTable.getSelectionModel().getSelectedIndex();
            hostedList.get(index).setEventStatus("complete");
            sendDBCommand(query);
            pastList.add(hostedList.get(index));
            hostedList.remove(index);
            hostedData.clear();
            for (Event x : hostedList) {
                hostedData.add(x);
            }
            pastData.clear();
            for (Event x : pastList) {
                pastData.add(x);
            }
        });
        //Action handler for sign up of daily events
        dailySignup.setOnAction(e -> {
            int eventid = dailyTable.getSelectionModel().getSelectedItem().getEventID();
            String eventName = dailyTable.getSelectionModel().getSelectedItem().getEventName();
            String eventType = dailyTable.getSelectionModel().getSelectedItem().getEventType();
            String eventDescription = dailyTable.getSelectionModel().getSelectedItem().getEventDescription();
            String eventDate = dailyTable.getSelectionModel().getSelectedItem().getEventDate();
            String eventTime = dailyTable.getSelectionModel().getSelectedItem().getEventTime();
            String eventLocation = dailyTable.getSelectionModel().getSelectedItem().getEventLocation();
            String eventStatus = dailyTable.getSelectionModel().getSelectedItem().getEventStatus();
            yourDailyList.add(new Event(eventid, eventName, eventType, eventDescription, eventDate, eventTime, eventLocation, eventStatus));
            String query = "INSERT INTO EVENTHISTORY(volID, eventID) VALUES (" + id + ", " + eventid + ")";
            sendDBCommand(query);
            yourDailyData.clear();
            for (Event x : yourDailyList) {
                yourDailyData.add(x);
            }
        });
        //Action handler for sign up of bark hosted events
        hostedSignup.setOnAction(e -> {
            int eventid = hostedTable.getSelectionModel().getSelectedItem().getEventID();
            String eventName = hostedTable.getSelectionModel().getSelectedItem().getEventName();
            String eventType = hostedTable.getSelectionModel().getSelectedItem().getEventType();
            String eventDescription = hostedTable.getSelectionModel().getSelectedItem().getEventDescription();
            String eventDate = hostedTable.getSelectionModel().getSelectedItem().getEventDate();
            String eventTime = hostedTable.getSelectionModel().getSelectedItem().getEventTime();
            String eventLocation = hostedTable.getSelectionModel().getSelectedItem().getEventLocation();
            String eventStatus = hostedTable.getSelectionModel().getSelectedItem().getEventStatus();

            try {
                String query = "INSERT INTO EVENTHISTORY(volID, eventID) VALUES (" + id + ", " + eventid + ")";
                sendDBCommand(query);
                yourHostedList.add(new Event(eventid, eventName, eventType, eventDescription, eventDate, eventTime, eventLocation, eventStatus));
            } catch (Exception ex) {
                hostedMessage.setText("Error in signing up");
            }

            yourHostedData.clear();
            for (Event x : yourHostedList) {
                yourHostedData.add(x);
            }
        });
    }

    public void sendDBCommand(String sqlQuery) {
        String URL = "jdbc:oracle:thin:@localhost:1521:XE";
        String userID = "javauser";
        String userPASS = "javapass";
        OracleDataSource ds;

        try {
            ds = new OracleDataSource();
            ds.setURL(URL);
            conn = ds.getConnection(userID, userPASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sqlQuery); // Sends the Query to the DB

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

}
