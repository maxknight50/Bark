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
import oracle.jdbc.pool.OracleDataSource;
import tables.*;

public class EventsRetry extends Login1 {

    Home home;

    TableView<Event> yourTable = new TableView<>();
    TableView<Event> dailyTable = new TableView<>();
    TableView<Event> pastTable = new TableView<>();
    TableView<Event> hostedTable = new TableView<>();

    ObservableList<Event> dailyData = FXCollections.observableArrayList();
    ObservableList<Event> pastData = FXCollections.observableArrayList();
    ObservableList<Event> yourData = FXCollections.observableArrayList();
    ObservableList<Event> hostedData = FXCollections.observableArrayList();

    //Create the columns for each table
    TableColumn yourName = new TableColumn("Event Name");
    TableColumn yourDate = new TableColumn("Event Date");
    TableColumn yourDuration = new TableColumn("Time");
    TableColumn yourDistance = new TableColumn("Distance");
    TableColumn yourCategory = new TableColumn("Category");

    TableColumn dailyEvent = new TableColumn("Event");
    TableColumn dailyDate = new TableColumn("Event Date");
    TableColumn dailyDuration = new TableColumn("Event Time");

    TableColumn hostedEvent = new TableColumn("Event");
    TableColumn hostedDate = new TableColumn("Event Date");
    TableColumn hostedDuration = new TableColumn("Event Time");
    TableColumn hostedVol = new TableColumn("Max Volunteers");

    TableColumn pastEvent = new TableColumn("Event");
    TableColumn pastMax = new TableColumn("Max Volunteers");
    TableColumn pastAssigned = new TableColumn("Assigned Volunteers");
    TableColumn pastDate = new TableColumn("Event Date");

    // GridPane associated for each tab
    GridPane dailyTablePane = new GridPane();
    GridPane hostedTablePane = new GridPane();

    GridPane yourOverall = new GridPane();
    GridPane yourTablePane = new GridPane();
    GridPane yourButtons = new GridPane();
    GridPane pastTablePane = new GridPane();
    GridPane dailyButtons = new GridPane();
    GridPane hostedButtons = new GridPane();
    GridPane pastButtons = new GridPane();

    Button test = new Button("Hello");
    Label nameLbl = new Label("Event:");
    TextField nameTxt = new TextField();
    Label dateLbl = new Label("Date:");
    TextField dateTxt = new TextField();
    Label timeLbl = new Label("Time:");
    TextField timeTxt = new TextField();
    Label distanceLbl = new Label("Distance:");
    TextField distanceTxt = new TextField();
    Label catLbl = new Label("Category:");
    TextField catTxt = new TextField();

    // Tab creation
    Tab tab1 = new Tab("Your Events");
    Tab tab2 = new Tab("Bark-Hosted");
    Tab tab3 = new Tab("Daily Events");
    Tab tab4 = new Tab("Past Events");

    GridPane eventsPane = new GridPane();
    TabPane tabPane = new TabPane();

    EventsRetry(Home home) throws SQLException {
        this.home = home;

        yourTable.setItems(yourData);
        dailyTable.setItems(dailyData);
        pastTable.setItems(pastData);
        hostedTable.setItems(hostedData);

        eventsPane.add(tabPane, 0, 1);

        yourButtons.add(nameLbl, 0, 1);
        yourButtons.add(nameTxt, 1, 1);
        yourButtons.add(dateLbl, 0, 2);
        yourButtons.add(dateTxt, 1, 2);
        yourButtons.add(timeLbl, 0, 3);
        yourButtons.add(timeTxt, 1, 3);
        yourButtons.add(distanceLbl, 0, 4);
        yourButtons.add(distanceTxt, 1, 4);

        yourOverall.add(yourButtons, 0, 0);
        yourOverall.add(yourTablePane, 1, 0);

        tab1.setContent(yourOverall);
        tab2.setContent(hostedTablePane);
        tab3.setContent(dailyTablePane);
        tab4.setContent(pastTablePane);
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

        hostedEvent.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));
        hostedVol.setCellValueFactory(new PropertyValueFactory<Event, Integer>("maxVolunteers"));
        hostedDate.setCellValueFactory(new PropertyValueFactory<Event, Date>("eventDate"));
        hostedDuration.setCellValueFactory(new PropertyValueFactory<Event, String>("eventTime"));

        dailyTable.getColumns().addAll(dailyEvent, dailyDate, dailyDuration);
        pastTable.getColumns().addAll(pastEvent, pastMax, pastDate);
        yourTable.getColumns().addAll(yourName, yourDate, yourDuration, yourDistance, yourCategory);
        hostedTable.getColumns().addAll(hostedEvent, hostedVol, hostedDate, hostedDuration);

//        dailyTable.setItems(table1Data);
//        eventTable2.setItems(table2Data);
//        eventTable3.setItems(table3Data);
        //Query the database and populate the tables
//        sendDBCommand("SELECT E.eventID, eventType, eventName, eventDescription, maxVolunteers, eventDate, eventTime, eventLocation, eventCategory, eventStatus, EH.Miles_Driven "
//                + "FROM Event E INNER JOIN EventHistory EH ON EH.EventID = E.EventID WHERE eventCategory = 'Daily event'"");
        try {
            // Display daily events
            sendDBCommand("SELECT E.eventID, eventType, eventName, eventDescription, maxVolunteers, eventDate, eventTime, eventLocation, eventCategory, eventStatus "
                    + "FROM Event E WHERE eventCategory = 'Daily event' AND eventStatus != 'completed'");
            while (rs.next()) {
                dailyData.add(new Event(rs.getInt("eventID"), rs.getString("eventType"), rs.getString("eventName"), rs.getString("eventDescription"),
                        rs.getInt("maxVolunteers"), rs.getDate("eventDate"), rs.getString("eventTime"), rs.getString("eventLocation"), rs.getString("eventCategory"),
                        rs.getString("eventStatus")));
                System.out.println("FOR TABLE 1: " + rs.getString("eventName") + " " + rs.getString("eventDate") + " " + rs.getString("eventTime"));
            }

            // Display completed events
            sendDBCommand("SELECT E.eventID, eventType, eventName, eventDescription, maxVolunteers, eventDate, eventTime, eventLocation, eventCategory, eventStatus "
                    + "FROM Event E WHERE eventStatus = 'completed'");
            while (rs.next()) {
                pastData.add(new Event(rs.getInt("eventID"), rs.getString("eventType"), rs.getString("eventName"), rs.getString("eventDescription"),
                        rs.getInt("maxVolunteers"), rs.getDate("eventDate"), rs.getString("eventTime"), rs.getString("eventLocation"), rs.getString("eventCategory"),
                        rs.getString("eventStatus")));
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

        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(eventsPane, 600, 450);

        dailyTable.setMinWidth(primaryScene.getWidth());
        pastTable.setMinWidth(primaryScene.getWidth());
        yourTable.setMinWidth(primaryScene.getWidth());
        hostedTable.setMinWidth(primaryScene.getWidth());

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
