//
//package bark;
//
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.GridPane;
//import javafx.stage.Stage;
//
///**
// *
// * 
// */
//public class CreateAccount extends Login1 {
//    Login1 login;
//    
//    Label thankLbl = new Label("Thanks for submitting your application! Please create your account");
//    Label createLbl = new Label("Create Account");
//    Label usernameLbl = new Label("Create Username: ");
//    Label passwordLbl = new Label("Create Password: ");
//    
//    TextField usernameTxt = new TextField();
//    TextField passwordTxt = new TextField();
//    
//    Button createBtn = new Button("Create Account");
//    
//    Image paw = new Image("file:paw.jpg");
//    ImageView viewPaw = new ImageView(paw);
//    
//    GridPane createPane = new GridPane();
//    
//    public CreateAccount(Login1 login) {
//        this.login = login;
//        paneSettings(createPane);
//        
//        createPane.add(thankLbl, 0, 0);
//        createPane.add(createLbl, 0, 1);
//        createPane.add(usernameLbl, 0, 2);
//        createPane.add(passwordLbl, 0, 3);
//        createPane.add(usernameTxt, 1, 2);
//        createPane.add(passwordTxt, 1, 3);
//        createPane.add(passwordTxt, 0, 4);
//        
//        
//        viewPaw.setFitHeight(50);
//        viewPaw.setFitWidth(50);
//        viewPaw.setX(100);
//        viewPaw.setY(150);
//        createPane.add(viewPaw, 1, 5);
//        
//        Stage primaryStage = new Stage();
//        Scene primaryScene = new Scene(createPane, 700, 700);
//        primaryStage.setScene(primaryScene);
//        primaryStage.setTitle("Create Account");
//        primaryStage.show();
//    }
//    
//}
