package bark;

import static bark.VolunteerHome1.conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

public class ScheduleAvailability extends Login1 {

    Home home;
    VolunteerHome1 volHome;
    Login1 login;

    ComboBox<String> daysCombo = new ComboBox();
    ComboBox<String> scheduleCombo = new ComboBox();
    Button backBtn = new Button("Back");
    Label ScheduleTitleLbl = new Label("Schedule Availability");
    Label dayofWeek = new Label("Day of Week");
    Label time = new Label("Shift");
    Button check = new Button("Check Availability");
    Button schedule = new Button("Schedule");
    Button remove = new Button("Remove from Schedule");
    Label availableLbl = new Label("Volunteers available:");
    Label scheduledLbl = new Label("Volunteers currently scheduled:");
    ListView available = new ListView();
    ListView currentlyScheduled = new ListView();

//    Label MondayLbl = new Label("Monday");
//    Label TuesdayLbl = new Label("Tuesday");
//    ComboBox<String> scheduleIDCb2 = new ComboBox();
//    Label WednesdayLbl = new Label("Wednesday");
//    ComboBox<String> scheduleIDCb3 = new ComboBox();
//    Label ThursdayLbl = new Label("Thursday");
//    ComboBox<String> scheduleIDCb4 = new ComboBox();
//    Label FridayLbl = new Label("Friday");
//    ComboBox<String> scheduleIDCb5 = new ComboBox();
//    Label SaturdayLbl = new Label("Saturday");
//    ComboBox<String> scheduleIDCb6 = new ComboBox();
//    Label SundayLbl = new Label("Sunday");
//    ComboBox<String> scheduleIDCb7 = new ComboBox();
    GridPane schedulePane = new GridPane();

    String selectedDay = "";
    String selectedTime = "";
    int scheduleNum = 0;
    int schedID = 0;
    int volID = 0;

    Image paw = new Image("file:paw.jpg");
    ImageView viewPaw = new ImageView(paw);

    ArrayList<Integer> sIDScheduled = new ArrayList<>();
    ArrayList<Integer> sIDAvailable = new ArrayList<>();

    
    public ScheduleAvailability(VolunteerHome1 volHome) {
        this.volHome = volHome;
        paneSettings(schedulePane);

        Label titleLbl = new Label("Set Your Availability:");
        Label shift1 = new Label("8 AM to 4 PM");
        Label shift2 = new Label("4 PM to 12 AM");
        Label shift3 = new Label("12 AM to 8 AM");
        String availablilty[] = {"Available", ""};
        ComboBox available1 = new ComboBox(FXCollections.observableArrayList(availablilty));
        ComboBox available2 = new ComboBox(FXCollections.observableArrayList(availablilty));
        ComboBox available3 = new ComboBox(FXCollections.observableArrayList(availablilty));

        daysCombo.getItems().add("Monday");
        daysCombo.getItems().add("Tuesday");
        daysCombo.getItems().add("Wednesday");
        daysCombo.getItems().add("Thursday");
        daysCombo.getItems().add("Friday");
        daysCombo.getItems().add("Saturday");
        daysCombo.getItems().add("Sunday");

        daysCombo.setOnAction(e -> {
            available1.setValue("");
            available2.setValue("");
            available3.setValue("");
            System.out.println(volHome.loginid);

            try {
                sendDBCommand("select volunteer.volID, vol_firstName, vol_lastName, shift_time.schedule_id, shift_time.dayofweek, shift_time.shift_id "
                        + "from shift_time full outer join schedule on shift_time.schedule_ID = schedule.schedule_ID "
                        + "full outer join volunteer on schedule.volID = volunteer.volID "
                        + "where dayofweek = '" + daysCombo.getValue() + "' "
                        + "AND volunteer.volID = " + volHome.loginid);

                if (rs.next()) {
                    schedID = rs.getInt("schedule_ID");
                    
                    System.out.println(rs.getInt("shift_id"));
                    switch (rs.getInt("shift_id")) {
                        case 1:
                            available1.setValue("Available");
                            break;
                        case 2:
                            available2.setValue("Available");
                            break;
                        case 3:
                            available3.setValue("Available");
                            break;
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(ScheduleAvailability.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        available1.setOnAction(e -> {
            if (available1.getValue().equals("Available")) {
                sendDBCommand("INSERT INTO SHIFT_TIME(dayOfWeek,schedule_ID,shift_ID, startTime,endTime,scheduled) "
                        + "VALUES('" + daysCombo.getValue() + "', " + schedID + ", 1, '8 AM to 4 PM', 'no'");
            }
        });
        available2.setOnAction(e -> {
            //4 PM to 12 AM
        });
        available3.setOnAction(e -> {
            //12 AM to 8 AM
        });

        schedulePane.add(backBtn, 0, 0);
        schedulePane.add(titleLbl, 1, 0);
        schedulePane.add(dayofWeek, 1, 1);
        schedulePane.add(time, 2, 1);
        schedulePane.add(daysCombo, 1, 2);
        schedulePane.add(shift1, 2, 2);
        schedulePane.add(shift2, 2, 3);
        schedulePane.add(shift3, 2, 4);
        schedulePane.add(available1, 3, 2);
        schedulePane.add(available2, 3, 3);
        schedulePane.add(available3, 3, 4);

        viewPaw.setFitHeight(50);
        viewPaw.setFitWidth(50);
        viewPaw.setX(100);
        viewPaw.setY(150);
        schedulePane.add(viewPaw, 3, 6);

        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(schedulePane, 700, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Schedule Availability");
        primaryStage.show();

    }

    public ScheduleAvailability(Home home) {
        this.home = home;
        paneSettings(schedulePane);

        schedulePane.add(backBtn, 0, 0);
        schedulePane.add(ScheduleTitleLbl, 1, 0);
        schedulePane.add(dayofWeek, 1, 1);
        schedulePane.add(time, 2, 1);
        schedulePane.add(daysCombo, 1, 2);
        schedulePane.add(scheduleCombo, 2, 2);
        schedulePane.add(check, 3, 2);
        schedulePane.add(availableLbl, 1, 3, 2, 1);
        schedulePane.add(available, 1, 4, 2, 1);
        schedulePane.add(scheduledLbl, 3, 3, 2, 1);
        schedulePane.add(currentlyScheduled, 3, 4, 2, 1);
        currentlyScheduled.setMinWidth(available.getWidth());
        schedulePane.add(schedule, 1, 5);
        schedulePane.add(remove, 3, 5);

        viewPaw.setFitHeight(50);
        viewPaw.setFitWidth(50);
        viewPaw.setX(100);
        viewPaw.setY(150);
        schedulePane.add(viewPaw, 3, 6);

        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(schedulePane, 700, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Schedule Availability");
        primaryStage.show();

        daysCombo.getItems().add("Monday");
        daysCombo.getItems().add("Tuesday");
        daysCombo.getItems().add("Wednesday");
        daysCombo.getItems().add("Thursday");
        daysCombo.getItems().add("Friday");
        daysCombo.getItems().add("Saturday");
        daysCombo.getItems().add("Sunday");

        scheduleCombo.getItems().add("12 AM to 8 AM");
        scheduleCombo.getItems().add("8 AM to 4 PM");
        scheduleCombo.getItems().add("4 PM to 12 AM");

        check.setOnAction(e -> {
            selectedDay = daysCombo.getSelectionModel().getSelectedItem();
            selectedTime = scheduleCombo.getSelectionModel().getSelectedItem();
            if (selectedTime.equals("8 AM to 4 PM")) {
                scheduleNum = 1;
            } else if (selectedTime.equals("4 PM to 12 AM")) {
                scheduleNum = 2;
            } else if (selectedTime.equals("12 AM to 8 AM")) {
                scheduleNum = 3;
            }

            // Place all available but not scheduled volunteers into left list
            sendDBCommand("select volunteer.volID, vol_firstName, vol_lastName, shift_time.schedule_id, shift_time.dayofweek, shift_time.shift_id "
                    + "from shift_time full outer join schedule on shift_time.schedule_ID = schedule.schedule_ID "
                    + "full outer join volunteer on schedule.volID = volunteer.volID where dayofweek = '" + selectedDay + "' AND scheduled = 'no'");
            try {
                while (rs.next()) {
                    if (rs.getInt("shift_ID") == scheduleNum) {
                        String fullName = rs.getString("vol_firstName") + " " + rs.getString("vol_lastname");
                        schedID = rs.getInt("schedule_ID"); // Store the schedule ID for when we update
                        available.getItems().add(fullName);
                        sIDAvailable.add(schedID); // Add the schedule ID to the array
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(ScheduleAvailability.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Place all scheduled volunteers into right list
            sendDBCommand("select vol_firstName, vol_lastName, shift_time.schedule_id, shift_time.dayofweek, shift_time.shift_id "
                    + "from shift_time full outer join schedule on shift_time.schedule_ID = schedule.schedule_ID "
                    + "full outer join volunteer on schedule.volID = volunteer.volID where dayofweek = '" + selectedDay + "' AND scheduled = 'yes'");
            try {
                while (rs.next()) {
                    if (rs.getInt("shift_ID") == scheduleNum) {
                        String fullName = rs.getString("vol_firstName") + " " + rs.getString("vol_lastname");
                        currentlyScheduled.getItems().add(fullName);
                        schedID = rs.getInt("schedule_id");
                        sIDScheduled.add(schedID);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(ScheduleAvailability.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // Schedule button
        schedule.setOnAction(e -> {
            int index = available.getSelectionModel().getSelectedIndex(); // Saves index
            int scheduled = sIDAvailable.get(index); // Gets the schedule id of the selected volunteer
            currentlyScheduled.getItems().add(available.getSelectionModel().getSelectedItem()); // Gets selected volunteer and adds to scheduled

            sIDScheduled.add(scheduled); // Adding the schedule number from available to the scheduled array
            sIDAvailable.remove(index); // Removing the schedule ID from available array

            available.getItems().remove(index); // Removes the volunteer from the available list
            available.getSelectionModel().clearSelection(); // Clears selection

            selectedDay = daysCombo.getSelectionModel().getSelectedItem();

            try {
                sendDBCommand("update SHIFT_TIME SET scheduled = 'yes' WHERE "
                        + "shift_time.dayofweek = '" + selectedDay + "' "
                        + "AND shift_time.shift_ID = " + scheduleNum + " "
                        + "AND shift_time.schedule_ID = " + scheduled + "");
            } catch (Exception ex) {
                Logger.getLogger(ScheduleAvailability.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        remove.setOnAction(e -> {
            int index = currentlyScheduled.getSelectionModel().getSelectedIndex(); // Get selected index
            int scheduled = sIDScheduled.get(index); // Get the id within the index
            available.getItems().add(currentlyScheduled.getSelectionModel().getSelectedItem()); // Add volunteer back to available

            sIDAvailable.add(scheduled); // Adding the schedule number from scheduled to available array
            sIDScheduled.remove(index); // Removing the schedule ID from scheduled array

            currentlyScheduled.getItems().remove(index); // Remove from currently scheduled
            currentlyScheduled.getSelectionModel().clearSelection();

            selectedDay = daysCombo.getSelectionModel().getSelectedItem();

            // Change the scheduled status to no within the database
            try {
                sendDBCommand("update SHIFT_TIME SET scheduled = 'no' WHERE "
                        + "shift_time.dayofweek = '" + selectedDay + "' "
                        + "AND shift_time.shift_ID = " + scheduleNum + " "
                        + "AND shift_time.schedule_ID = " + scheduled + "");
            } catch (Exception ex) {
                Logger.getLogger(ScheduleAvailability.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        backBtn.setOnAction(e -> {
            primaryStage.close();
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

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
