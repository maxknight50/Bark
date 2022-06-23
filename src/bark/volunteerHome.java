package bark;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class volunteerHome extends Home {
    Login1 login; // Create Login1 object
    Label barkTitle;
    Button eventsBtn = new Button("View Events");
    //Button reviewApplication = new Button("Review Applications"); // Admin only
    Button volunteerInfoBtn = new Button("View Your Information");
    Button assignSpecialBtn = new Button("Assign Specialization");
    Button volunteerReportBtn = new Button("View Volunteer Report"); // Admin only
    Button checkoutBtn = new Button("Check Out");
    //Button donationBtn = new Button("Donations");
    //Button animalInfoBtn = new Button("Animal Information"); // Admin only?
    //Button scheduleBtn = new Button("Schedule Availability");   
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

    volunteerHome(Login1 login) {
        super(login);
        
        barkTitle = new Label("Welcome to BARK, " + login.name + "!"); // Get name identified in login
        
        paneSettings(homePane);
        
        homePane.add(barkTitle, 0, 0);
        homePane.add(eventsBtn, 0, 1);
        //homePane.add(reviewApplication, 0, 2);
        homePane.add(volunteerInfoBtn, 0, 3);
        homePane.add(assignSpecialBtn, 0, 4);
        homePane.add(volunteerReportBtn, 0, 5);
        //homePane.add(donationBtn, 0, 6);
        //homePane.add(animalInfoBtn, 0, 7);
        //homePane.add(scheduleBtn, 0, 8);
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
        Scene primaryScene = new Scene(mainPane, 900, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("BARK Volunteer Home");
        primaryStage.show();

        // Check out button
        checkoutBtn.setOnAction(e -> {
            //primaryStage.setScene(Checkout.primaryScene);
            Checkout volChkOut = new Checkout(this);
        });
        
        // Volunteer summary button
        volunteerInfoBtn.setOnAction(e -> {
            VolunteerStatus myInfo = new VolunteerStatus(this);
        });
        
        // Assign Specialization btn
        assignSpecialBtn.setOnAction(e -> {
            AssignSpecialization assign1 = new AssignSpecialization(this);
        });

        // Events Menu button
        eventsBtn.setOnAction(e -> {
            //EventsRetry em = new EventsRetry(this);
        });
        
        //Logout Button
        logoutBtn.setOnAction(e -> {
            Logout volLogout = new Logout(this);
        });

    }

    public void getPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
