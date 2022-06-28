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

        System.out.print("Hello. I am in volunteer home");
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
                // System.out.println("FOR TABLE 1: " + rs.getString("eventName") + " " + rs.getString("eventDate") + " " + rs.getString("eventTime"));
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
    }

    private void addButtons() {
        // Your table
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
        Button yourAdd = new Button("Add");
        Button yourDelete = new Button("Delete");
        Button yourModify = new Button("Modify");

        // Daily table
        Label nameLbl2 = new Label("Event:");
        TextField nameTxt2 = new TextField();
        Label dateLbl2 = new Label("Date:");
        TextField dateTxt2 = new TextField();
        Label timeLbl2 = new Label("Time:");
        TextField timeTxt2 = new TextField();
        Label distanceLbl2 = new Label("Distance:");
        TextField distanceTxt2 = new TextField();
        Label catLbl2 = new Label("Category:");
        TextField catTxt2 = new TextField();
        Button add2 = new Button("Add");
        Button delete2 = new Button("Delete");
        Button modify2 = new Button("Modify");
        Button dailyAdd = new Button("Add");
        Button dailyDelete = new Button("Delete");
        Button dailyModify = new Button("Modify");

        // Past table
        Label nameLbl3 = new Label("Event:");
        TextField nameTxt3 = new TextField();
        Label dateLbl3 = new Label("Date:");
        TextField dateTxt3 = new TextField();
        Label timeLbl3 = new Label("Time:");
        TextField timeTxt3 = new TextField();
        Label distanceLbl3 = new Label("Distance:");
        TextField distanceTxt3 = new TextField();
        Label catLbl3 = new Label("Category:");
        TextField catTxt3 = new TextField();
        Button pastAdd = new Button("Add");
        Button pastDelete = new Button("Delete");
        Button pastModify = new Button("Modify");

        // Hosted table
        Label nameLbl4 = new Label("Event:");
        TextField nameTxt4 = new TextField();
        Label dateLbl4 = new Label("Date:");
        TextField dateTxt4 = new TextField();
        Label timeLbl4 = new Label("Time:");
        TextField timeTxt4 = new TextField();
        Label distanceLbl4 = new Label("Distance:");
        TextField distanceTxt4 = new TextField();
        Label catLbl4 = new Label("Category:");
        TextField catTxt4 = new TextField();
        Button hostedAdd = new Button("Add");
        Button hostedDelete = new Button("Delete");
        Button hostedModify = new Button("Modify");

        yourButtons.add(nameLbl, 0, 1);
        yourButtons.add(nameTxt, 1, 1);
        yourButtons.add(dateLbl, 0, 2);
        yourButtons.add(dateTxt, 1, 2);
        yourButtons.add(timeLbl, 0, 3);
        yourButtons.add(timeTxt, 1, 3);
        yourButtons.add(distanceLbl, 0, 4);
        yourButtons.add(distanceTxt, 1, 4);
        yourButtons.add(yourAdd, 0, 5);
        yourButtons.add(yourDelete, 1, 6);
        yourButtons.add(yourModify, 1, 5);

        yourOverall.add(yourButtons, 0, 0);
        yourOverall.add(yourTablePane, 1, 0);

        pastButtons.add(nameLbl3, 0, 1);
        pastButtons.add(nameTxt3, 1, 1);
        pastButtons.add(dateLbl3, 0, 2);
        pastButtons.add(dateTxt3, 1, 2);
        pastButtons.add(timeLbl3, 0, 3);
        pastButtons.add(timeTxt3, 1, 3);
        pastButtons.add(distanceLbl3, 0, 4);
        pastButtons.add(distanceTxt3, 1, 4);
        pastButtons.add(pastAdd, 0, 5);
        pastButtons.add(pastDelete, 1, 6);
        pastButtons.add(pastModify, 1, 5);

        pastOverall.add(pastButtons, 0, 0);
        pastOverall.add(pastTablePane, 1, 0);

        dailyButtons.add(nameLbl2, 0, 1);
        dailyButtons.add(nameTxt2, 1, 1);
        dailyButtons.add(dateLbl2, 0, 2);
        dailyButtons.add(dateTxt2, 1, 2);
        dailyButtons.add(timeLbl2, 0, 3);
        dailyButtons.add(timeTxt2, 1, 3);
        dailyButtons.add(distanceLbl2, 0, 4);
        dailyButtons.add(distanceTxt2, 1, 4);
        dailyButtons.add(dailyAdd, 0, 5);
        dailyButtons.add(dailyDelete, 1, 6);
        dailyButtons.add(dailyModify, 1, 5);

        dailyOverall.add(dailyButtons, 0, 0);
        dailyOverall.add(dailyTablePane, 1, 0);

        hostedButtons.add(nameLbl4, 0, 1);
        hostedButtons.add(nameTxt4, 1, 1);
        hostedButtons.add(dateLbl4, 0, 2);
        hostedButtons.add(dateTxt4, 1, 2);
        hostedButtons.add(timeLbl4, 0, 3);
        hostedButtons.add(timeTxt4, 1, 3);
        hostedButtons.add(distanceLbl4, 0, 4);
        hostedButtons.add(distanceTxt4, 1, 4);
        hostedButtons.add(hostedAdd, 0, 5);
        hostedButtons.add(hostedDelete, 1, 6);
        hostedButtons.add(hostedModify, 1, 5);

        hostedOverall.add(hostedButtons, 0, 0);
        hostedOverall.add(hostedTablePane, 1, 0);

        hostedButtons.setMinWidth(300);
        dailyButtons.setMinWidth(300);
        yourButtons.setMinWidth(300);
        pastButtons.setMinWidth(300);

//        hostedTablePane.setMinWidth(300);
//        dailyTablePane.setMinWidth(300);
//        yourTablePane.setMinWidth(300);
//        pastTablePane.setMinWidth(300);
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
