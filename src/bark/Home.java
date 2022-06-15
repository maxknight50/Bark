package bark;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Home extends Login1 {

    Login1 login; // Create Login1 object
    Label barkTitle;
    Button eventsBtn = new Button("View Events");
    Button socialHomeScreenBtn = new Button("View Social Home Screen");
    Button volunteerInfoBtn = new Button("View Volunteer Information");
    Button assignSpecialBtn = new Button("Assign Specialization");
    Button volunteerReportBtn = new Button("View Volunteer Report");
    Button checkoutBtn = new Button("Check Out");
    Button donationBtn = new Button("Donations");
    Button animalInfoBtn = new Button("Animal Information");
    Button scheduleBtn = new Button("Schedule Availability");

    GridPane homePane = new GridPane();

    Home(Login1 login) {
        
        System.out.println(login.name);
        barkTitle = new Label("Welcome to BARK, " + login.name + "!"); // Get name identified in login
        this.login = login;
        paneSettings(homePane);
        homePane.add(barkTitle, 0, 0);
        homePane.add(eventsBtn, 0, 1);
        homePane.add(socialHomeScreenBtn, 0, 2);
        homePane.add(volunteerInfoBtn, 0, 3);
        homePane.add(assignSpecialBtn, 0, 4);
        homePane.add(volunteerReportBtn, 0, 5);
        homePane.add(donationBtn, 0, 6);
        homePane.add(animalInfoBtn, 0, 7);
        homePane.add(scheduleBtn, 0, 8);
        homePane.add(checkoutBtn, 0, 9);

        Stage primaryStage1 = login.primaryStage;
        Scene primaryScene = new Scene(homePane, 600, 450);
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
            EventsMenu em = new EventsMenu(this);
        });

        // Donations Button
        donationBtn.setOnAction(e -> {
            DonationStatus don = new DonationStatus(this);
        });

        // Animal Info Button
        animalInfoBtn.setOnAction(e -> {
            AnimalList anim = new AnimalList(this);
        });

        // Animal Info Button
        socialHomeScreenBtn.setOnAction(e -> {
            SocialHomeScreen soc = new SocialHomeScreen(this);
        });
        //Schedule Button
        scheduleBtn.setOnAction(e -> {
            ScheduleAvailability sa = new ScheduleAvailability(this);
        });

    }

}
