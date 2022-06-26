package bark;

import java.text.DecimalFormat;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * 
 */
public class VolunteerCheckout extends Login1 {
    Login1 login;
    Home home;
    VolunteerHome vHome;
    DecimalFormat df = new DecimalFormat("#.##");
    
    //Button backBtn = new Button("Back");
    Label titleLbl = new Label("Check Out");
    Label currentHrsLbl;
    Label currentHrsDisplayLbl = new Label();
    Label usernameLbl = new Label("Username");
    TextField usernameTxt = new TextField();
    Label passwordLbl = new Label("Password");
    TextField passwordTxt = new TextField();
//    Label volIdLbl = new Label("Volunteer ID");
//    TextField volIdTxt = new TextField();
    Button checkoutBtn = new Button("CHECK OUT");
    
    Image paw = new Image("file:paw.jpg");
    ImageView viewPaw = new ImageView(paw);
    
    GridPane checkoutPane = new GridPane();
    
    VolunteerCheckout(VolunteerHome vhome) {
        this.vHome = vhome;
        paneSettings(checkoutPane);
        System.out.println("Test volunteer: " + df.format(vhome.cumHours));
        currentHrsLbl = new Label("Your current hours: " + df.format(vhome.cumHours));
        
        // add nodes
        //checkoutPane.add(backBtn, 0, 0);
        checkoutPane.add(titleLbl, 1, 0);
        checkoutPane.add(currentHrsLbl, 1, 1);
        checkoutPane.add(currentHrsDisplayLbl, 2, 1);
        checkoutPane.add(usernameLbl, 1, 2);
        checkoutPane.add(usernameTxt, 1, 3);
        checkoutPane.add(passwordLbl, 1, 4);
        checkoutPane.add(passwordTxt, 1, 5);
//        checkoutPane.add(volIdLbl, 1, 6);
//        checkoutPane.add(volIdTxt, 1, 7);
        checkoutPane.add(checkoutBtn, 1, 8);
        
        viewPaw.setFitHeight(50);
        viewPaw.setFitWidth(50);
        viewPaw.setX(100);
        viewPaw.setY(150);
        checkoutPane.add(viewPaw, 2, 9);
        
        Stage primaryStage = vhome.primaryStage;
        Scene primaryScene = new Scene(checkoutPane, 600, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("BARK Checkout");
        primaryStage.show();
    }
}