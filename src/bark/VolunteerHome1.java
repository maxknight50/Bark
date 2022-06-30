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

public class VolunteerHome1 extends Home {
    Home home;
    Statement stmt;
    static Connection conn;
    ResultSet rs;

    Login1 login; // Create Login1 object
    Label barkTitle;
    Button checkIn = new Button("Check In");
    Button volunteerInfoBtn = new Button("View Your Information");
    Button eventsBtn = new Button("View Events");
    Button assignSpecialBtn = new Button("Assign Specialization");
    Button animalInfoBtn = new Button("Animal Information"); // Admin only?
    //Button scheduleBtn = new Button("Schedule Availability");  
    Button checkoutBtn = new Button("Check Out");
    Button logoutBtn = new Button("Logout");
    Button viewAvailability = new Button("View Your Schedule");

    Label scrnTitleLbl = new Label("Social Home");

    Label dateLbl = new Label("Date");

    Label descLbl = new Label("Description");
    Label Lbl = new Label("Description");
    
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
    public double cumHours;
    DecimalFormat df = new DecimalFormat("#.##");
    public int loginid = 0;
    int schedid = 0;

    GridPane homePane = new GridPane();
    GridPane socialPane = new GridPane();
    GridPane mainPane = new GridPane();

    VolunteerHome1(Login1 login) {
        super(login);
        this.home = home;
        barkTitle = new Label("Welcome to BARK, " + login.name + "!"); // Get name identified in login
        loginid = login.id;
        this.login = login;
  //      schedid = login.schedid;
        paneSettings(homePane);

        homePane.add(barkTitle, 0, 0);
        homePane.add(checkIn, 0, 1);
        homePane.add(volunteerInfoBtn, 0, 2);
        homePane.add(viewAvailability, 0, 3);
        homePane.add(eventsBtn, 0, 4);
        homePane.add(assignSpecialBtn, 0, 5);
        homePane.add(animalInfoBtn, 0, 6);
        homePane.add(checkoutBtn, 0, 7);
        homePane.add(logoutBtn, 0, 8);

        paneSettings(socialPane);
        //socialPane.add(scrnTitleLbl, 0, 1);
        //socialPane.add(dateLbl, 0, 2);
        //socialPane.add(descLbl, 2, 2);
        //socialPane.add(Lbl, 5, 6);

        viewPaw.setFitHeight(50);
        viewPaw.setFitWidth(50);
        viewPaw.setX(100);
        viewPaw.setY(150);
        //socialPane.add(viewPaw, 0, 0);

        mainPane.add(homePane, 0, 0);
        socialPane.setAlignment(Pos.TOP_CENTER);
        
        
        mainPane.add(socialPane, 1, 0);
        
        
        //populateSocial1();
        
        Stage primaryStage1 = login.primaryStage;
        Scene primaryScene = new Scene(mainPane, 900, 550);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("BARK Volunteer Home");
        primaryStage.show();

        checkIn.setOnAction(e -> {
            LocalDateTime now = LocalDateTime.now();
//            System.out.println(df.format(now));
            hour = now.getHour();
            minute = now.getMinute();
            System.out.println(hour + ":" + minute);
            timeStored = minute + (hour * 60);
            String update = "UPDATE Volunteer SET timeCheckedIn = " + timeStored + " WHERE username = '" + login.user + "'";
            sendDBCommand(update);

        });
        // Check out button
        checkoutBtn.setOnAction(e -> {
            //primaryStage.setScene(Checkout.primaryScene);
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
                    totalCumHours = rs.getDouble("cumulativeHours");
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
            VolunteerCheckout volChkOut = new VolunteerCheckout(this);
        });

        // Volunteer summary button
        volunteerInfoBtn.setOnAction(e -> {
            VolunteerStatus myInfo = new VolunteerStatus(this);
        });

        viewAvailability.setOnAction(e -> {
            ScheduleAvailability myInfo = new ScheduleAvailability(this);
        });

        // Assign Specialization btn
        assignSpecialBtn.setOnAction(e -> {
            AssignSpecialization assign1 = new AssignSpecialization(this);
        });

        // Assign Specialization btn
        animalInfoBtn.setOnAction(e -> {
            AnimalList al = new AnimalList(this);
        });

        // Events Menu button
        eventsBtn.setOnAction(e -> {
            try {
                EventsRetry em = new EventsRetry(this);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        });

        //Logout Button
        logoutBtn.setOnAction(e -> {
            Logout volLogout = new Logout(this);
        });

    }
    
    public void populateSocial1() {
        String join = "SELECT volunteer.volID, volunteer.vol_firstName, volunteer.vol_lastName, event.eventName, event.eventDate, event.eventType FROM Volunteer " +
                "INNER JOIN EVENTHISTORY ON volunteer.volID = eventhistory.volID INNER JOIN event ON eventhistory.eventID = event.eventID";
        sendDBCommand(join);
        int iter = 4;
        Label lbl = new Label("hello");
        //socialPane.add(lbl, 2, 3);
        try {
            while (rs.next()) {
                Label temp1 = new Label(rs.getString("vol_firstname") + " " + rs.getString("vol_lastname") + " completed " + rs.getString("eventname") + " on " + rs.getString("eventdate"));
                socialPane.add(temp1, 2, iter);
                iter++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleAvailability.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }    

    public double getCumHours() {
        return cumHours;
    }

    public void getPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
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
