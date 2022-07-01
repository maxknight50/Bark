package bark;

import static java.lang.Math.abs;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.pool.OracleDataSource;


// Admin login comes through Home
public class Home extends Login1 {

    Statement stmt;
    static Connection conn;
    ResultSet rs;

    Login1 login; // Create Login1 object
    Label barkTitle;
    Button eventsBtn = new Button("View Events");
    Button reviewApplication = new Button("Review Applications");
    Button volunteerInfoBtn = new Button("View Your Information");
    Button assignSpecialBtn = new Button("Assign Specialization");
    Button volunteerReportBtn = new Button("View Volunteer Report");
    Button checkoutBtn = new Button("Check Out");
    Button donationBtn = new Button("Donations");
    Button animalInfoBtn = new Button("Animal Information");
    Button scheduleBtn = new Button("Schedule Availability");
    Button logoutBtn = new Button("Logout");
    Button checkIn = new Button("Check In");

    Label scrnTitleLbl = new Label("Social Home");

    Image paw = new Image("file:paw.jpg");
    ImageView viewPaw = new ImageView(paw);

    public double timeStored;
    String user;
    double hour;
    double minute;
    double timeCheckedIn;
    double hour2;
    double minute2;
    double totalCumHours;
    public static double cumHours;
    DecimalFormat df = new DecimalFormat("#.##");
    
    int loginid = 0;
    int schedid = 0;

    GridPane homePane = new GridPane();
    GridPane socialPane = new GridPane();
    GridPane mainPane = new GridPane();

    Home(Login1 login) {

        System.out.println(login.name);
        loginid = login.id;
        //schedid = login.schedid;
        barkTitle = new Label("Welcome to BARK, " + login.name + "!"); // Get name identified in login
        this.login = login;
        paneSettings(homePane);

        homePane.add(barkTitle, 0, 0);
        homePane.add(checkIn, 0, 1);
        homePane.add(reviewApplication, 0, 2);
        homePane.add(volunteerInfoBtn, 0, 3);
        homePane.add(eventsBtn, 0, 4);
        homePane.add(assignSpecialBtn, 0, 5);
        homePane.add(volunteerReportBtn, 0, 6);
        homePane.add(donationBtn, 0, 7);
        homePane.add(animalInfoBtn, 0, 8);
        homePane.add(scheduleBtn, 0, 9);
        
        homePane.add(logoutBtn, 0, 11);
        paneSettings(socialPane);
        socialPane.add(scrnTitleLbl, 0, 1);

        viewPaw.setFitHeight(50);
        viewPaw.setFitWidth(50);
        viewPaw.setX(100);
        viewPaw.setY(150);
        socialPane.add(viewPaw, 0, 0);

        mainPane.add(homePane, 0, 0);
        socialPane.setAlignment(Pos.TOP_CENTER);
        mainPane.add(socialPane, 1, 0);

        if (login.isAdmin == true) { // Only if the user is an admin does this get called
            populateSocial();
        }

        Stage primaryStage1 = login.primaryStage;
        Scene primaryScene = new Scene(mainPane, 900, 550);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("BARK Home");
        primaryStage.show();

        checkIn.setOnAction(e -> {
            LocalDateTime now = LocalDateTime.now();
            hour = now.getHour(); // Gets the current hour
            minute = now.getMinute(); // Gets the current minute
            timeStored = minute + (hour * 60); 
            try {
                String update = "UPDATE Volunteer SET timeCheckedIn = " + timeStored + " WHERE username = '" + login.user + "'";
                sendDBCommand(update);
                Label replace = new Label("You are checked in!"); // Replaces the check in button
                checkIn.setVisible(false);
                homePane.add(replace, 0, 1);
                homePane.add(checkoutBtn, 0, 10); // Adds the check out button
            } catch (Exception E) {
                System.out.println(E);
            }

        });

        checkoutBtn.setOnAction(e -> {
            LocalDateTime now = LocalDateTime.now();
//            System.out.println(df.format(now));
            hour2 = now.getHour();
            minute2 = now.getMinute();
            System.out.println(hour2 + ":" + minute2);
            //sendDBCommand(getTime);
            String getTime = "select * from Volunteer where username = '" + login.user + "'";
            sendDBCommand(getTime);
            try {
                while (rs.next()) {
                    timeCheckedIn = rs.getDouble("timeCheckedIn");
                    totalCumHours = rs.getInt("cumulativeHours");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + e.toString());
            }
            System.out.println("Checked In: " + timeCheckedIn);

            double checkedOut = (hour2 * 60) + minute2;
            System.out.println("Checked Out: " + checkedOut);
            cumHours = ((hour2 * 60) + minute2) - (timeCheckedIn);
            cumHours = cumHours / 60;
            System.out.println("Total time: " + df.format(cumHours));

            totalCumHours += cumHours;
            System.out.println("total cumulative hours: " + totalCumHours);
            String updateCumHours = "UPDATE Volunteer SET cumulativeHours = " + df.format(totalCumHours) + " WHERE username = '" + login.user + "'";
            sendDBCommand(updateCumHours);
            String delete = "UPDATE Volunteer SET timeCheckedIn = null WHERE username = '" + login.user + "'";
            sendDBCommand(delete);
            Checkout chkOut = new Checkout(this);
        });

        // Home button
        assignSpecialBtn.setOnAction(e -> {
            AssignSpecialization special1 = new AssignSpecialization(this);
        });

        // Volunteer summary button
        volunteerInfoBtn.setOnAction(e -> {
            VolunteerStatus sum = new VolunteerStatus(this);
        });

        // Volunteer List button
        volunteerReportBtn.setOnAction(e -> {
            VolunteerList vList = new VolunteerList(this);
        });

        // Events Menu button
        eventsBtn.setOnAction(e -> {
            try {
                EventsRetry em = new EventsRetry(this);
            } catch (SQLException ex) {
                System.out.println("SQL Exception! " + ex);
            }
        });

        // Donations Button
        donationBtn.setOnAction(e -> {
            DonationList don = new DonationList(this);
        });

        // Animal Info Button
        animalInfoBtn.setOnAction(e -> {
            AnimalList anim = new AnimalList(this);
        });

        // Animal Info Button
        reviewApplication.setOnAction(e -> {
            ReviewApplication app = new ReviewApplication(this);
        });
        //Schedule Button
        scheduleBtn.setOnAction(e -> {
            ScheduleAvailability sa = new ScheduleAvailability(this);
        });

        //Logout Button
        logoutBtn.setOnAction(e -> {
            Logout lo = new Logout(this);
        });

//        checkIn.setOnAction(e -> {
//            CheckIn ci = new CheckIn(this);
//        });
    }
    
    // Populates the social home screen with completed events
    public void populateSocial() {
        String join = "SELECT volunteer.volID, volunteer.vol_firstName, volunteer.vol_lastName, event.eventName, event.eventDate, event.eventType FROM Volunteer "
                + "INNER JOIN EVENTHISTORY ON volunteer.volID = eventhistory.volID INNER JOIN event ON eventhistory.eventID = event.eventID where eventStatus = 'completed'";
        sendDBCommand(join);
        int iter = 4; // Sets where the first row will be placed
        try {
            while (rs.next()) {
                rs.getString("vol_firstName");
                Label temp1 = new Label(rs.getString("eventdate") + ": " + rs.getString("vol_firstname") + " " + rs.getString("vol_lastname") + " completed " + rs.getString("eventname"));
                socialPane.add(temp1, 0, iter);
                iter++; // Updates the iterator to move it down one
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleAvailability.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public double getCumHours() {
        return cumHours;
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
