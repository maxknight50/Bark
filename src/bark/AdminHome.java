//package bark;
//
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.*;
//import javafx.stage.Stage;
//
//public class AdminHome extends Login1 {
//
//    Login1 login;
//    Label barkTitle = new Label("Welcome to BARK - Admin");
//    Button eventsBtn = new Button("View Events");
//    Button socialHomeScreenBtn = new Button("View Social Home Screen");
//    Button volunteerInfoBtn = new Button("View Volunteer Information");
//    Button assignSpecialBtn = new Button("Assign Specialization");
//    Button volunteerReportBtn = new Button("View Volunteer Report");
//    Button checkoutBtn = new Button("Check Out");
//    Button donationBtn = new Button("Donations");
//    Button animalInfoBtn = new Button("Animal List");
//
//    GridPane homePane = new GridPane();
//    Stage primaryStage = new Stage();
//
//    AdminHome(Login1 login) {
//        this.login = login;
//        paneSettings(homePane);
//        homePane.add(barkTitle, 0, 0);
//        homePane.add(eventsBtn, 0, 1);
//        homePane.add(socialHomeScreenBtn, 0, 2);
//        homePane.add(volunteerInfoBtn, 0, 3);
//        homePane.add(assignSpecialBtn, 0, 4);
//        homePane.add(volunteerReportBtn, 0, 5);
//
//        homePane.add(donationBtn, 0, 7);
//        homePane.add(animalInfoBtn, 0, 8);
//        homePane.add(checkoutBtn, 0, 6);
//
//        Scene primaryScene = new Scene(homePane, 600, 450);
//        primaryStage.setScene(primaryScene);
//        primaryStage.setTitle("BARK Home");
//        primaryStage.show();
//
//        // Check out button
//        checkoutBtn.setOnAction(e -> {
//            //primaryStage.setScene(Checkout.primaryScene);
//            Checkout chkOut = new Checkout(this);
//        });
//
//        // Home button
//        assignSpecialBtn.setOnAction(e -> {
//            AssignSpecialization special1 = new AssignSpecialization(this);
//        });
//
//        // Volunteer summary button
//        volunteerInfoBtn.setOnAction(e -> {
//            Summary sum = new Summary(this);
//        });
//
//        // Volunteer Status button
//        volunteerReportBtn.setOnAction(e -> {
//            VolunteerStatus status = new VolunteerStatus(this);
//        });
//
//        // Events Menu button
//        eventsBtn.setOnAction(e -> {
//            EventsMenu em = new EventsMenu(this);
//        });
//
//        // Donations Button
//        donationBtn.setOnAction(e -> {
//            Donation don = new Donation(this);
//        });
//
//        // Animal Info Button
//        animalInfoBtn.setOnAction(e -> {
//            AnimalInfo anim = new AnimalInfo(this);
//        });
//
//        // Animal Info Button
//        socialHomeScreenBtn.setOnAction(e -> {
//            SocialHomeScreen soc = new SocialHomeScreen(this);
//        });
//
//    }
//
//    public void getPrimaryStage(Stage primaryStage) {
//        this.primaryStage = primaryStage;
//    }
//
//}
