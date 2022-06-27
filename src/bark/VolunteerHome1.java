package bark;

import static java.lang.Math.abs;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

public class VolunteerHome1 extends Home {

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

    Label scrnTitleLbl = new Label("Social Home");

    Label dateLbl = new Label("Date");
    Label takeOut = new Label("06/01/22");
    Label takeOut3 = new Label("05/28/22");

    Label descLbl = new Label("Description");
    Label takeOut2 = new Label("John Smith completed 20 hours of training. Congratulations!");
    Label takeOut4 = new Label("Elizabeth Ley joined BARK!");

    Image paw = new Image("file:paw.jpg");
    ImageView viewPaw = new ImageView(paw);

    double hour;
    double minute;
    double hour2;
    double minute2;
    double totalMinute;
    public double cumHours;
    DecimalFormat df = new DecimalFormat("#.##");

    GridPane homePane = new GridPane();
    GridPane socialPane = new GridPane();
    GridPane mainPane = new GridPane();

    VolunteerHome1(Login1 login) {
        super(login);

        barkTitle = new Label("Welcome to BARK, " + login.name + "!"); // Get name identified in login

        paneSettings(homePane);

        homePane.add(barkTitle, 0, 0);
        homePane.add(checkIn, 0, 1);
        homePane.add(volunteerInfoBtn, 0, 2);
        homePane.add(eventsBtn, 0, 3);
        homePane.add(assignSpecialBtn, 0, 4);
        homePane.add(animalInfoBtn, 0, 5);
        //homePane.add(scheduleBtn, 0, 8);
        homePane.add(checkoutBtn, 0, 6);
        homePane.add(logoutBtn, 0, 7);

        paneSettings(socialPane);
        socialPane.add(scrnTitleLbl, 0, 0);
        socialPane.add(dateLbl, 0, 1);
        socialPane.add(takeOut, 0, 3);
        socialPane.add(descLbl, 2, 1);
        socialPane.add(takeOut2, 2, 3);
        socialPane.add(takeOut3, 0, 5);
        socialPane.add(takeOut4, 2, 5);

        viewPaw.setFitHeight(50);
        viewPaw.setFitWidth(50);
        viewPaw.setX(100);
        viewPaw.setY(150);
        homePane.add(viewPaw, 0, 12);

        hour = login.getHour();
        minute = login.getMinute();

        mainPane.add(homePane, 0, 0);
        socialPane.setAlignment(Pos.TOP_CENTER);
        mainPane.add(socialPane, 1, 0);
        Stage primaryStage1 = login.primaryStage;
        Scene primaryScene = new Scene(mainPane, 900, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("BARK Volunteer Home");
        primaryStage.show();

        // Check out button
        checkoutBtn.setOnAction(e -> {
            //primaryStage.setScene(Checkout.primaryScene);
            LocalDateTime now = LocalDateTime.now();
            System.out.println(dtf.format(now));
            hour2 = now.getHour();
            minute2 = now.getMinute();
            totalMinute = totalMinute = (abs(minute2 - minute) * (1.66666666667) / 100);
            cumHours = cumHours + totalMinute;
            System.out.println("Total time: " + df.format(cumHours));
            VolunteerCheckout volChkOut = new VolunteerCheckout(this);
        });

        // Volunteer summary button
        volunteerInfoBtn.setOnAction(e -> {
            VolunteerStatus myInfo = new VolunteerStatus(this);
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

    public double getCumHours() {
        return cumHours;
    }

    public void getPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
