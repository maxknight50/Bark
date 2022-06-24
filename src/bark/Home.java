package bark;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.awt.Color;

public class Home extends Login1 {

    Login1 login; // Create Login1 object
    Label barkTitle;
    Button eventsBtn = new Button("View Events");
    Button reviewApplication = new Button("Review Applications"); // Admin only
    Button volunteerInfoBtn = new Button("View Your Information");
    Button assignSpecialBtn = new Button("Assign Specialization");
    Button volunteerReportBtn = new Button("View Volunteer Report"); // Admin only
    Button checkoutBtn = new Button("Check Out");
    Button donationBtn = new Button("Donations");
    Button animalInfoBtn = new Button("Animal Information"); // Admin only?
    Button scheduleBtn = new Button("Schedule Availability");
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

    GridPane homePane = new GridPane();
    GridPane socialPane = new GridPane();
    GridPane mainPane = new GridPane();

    Home(Login1 login) {

        System.out.println(login.name);
        barkTitle = new Label("Welcome to BARK, " + login.name + "!"); // Get name identified in login
        this.login = login;
        paneSettings(homePane);

        homePane.add(barkTitle, 0, 0);
        homePane.add(eventsBtn, 0, 1);
        homePane.add(reviewApplication, 0, 2);
        homePane.add(volunteerInfoBtn, 0, 3);
        homePane.add(assignSpecialBtn, 0, 4);
        homePane.add(volunteerReportBtn, 0, 5);
        homePane.add(donationBtn, 0, 6);
        homePane.add(animalInfoBtn, 0, 7);
        homePane.add(scheduleBtn, 0, 8);
        homePane.add(checkoutBtn, 0, 9);
        homePane.add(logoutBtn, 0, 10);
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

        mainPane.add(homePane, 0, 0);
        socialPane.setAlignment(Pos.TOP_CENTER);
        mainPane.add(socialPane, 1, 0);
        Stage primaryStage1 = login.primaryStage;
        Scene primaryScene = new Scene(mainPane, 900, 550);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("BARK Home");
        primaryStage.show();

        // Check out button
        checkoutBtn.setOnAction(e -> {
            //primaryStage.setScene(Checkout.primaryScene);
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

    }

}
