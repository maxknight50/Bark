
package bark;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * 
 */
public class Checkout extends Login1 {
    Home home;
    
    Button backBtn = new Button("Back");
    Label titleLbl = new Label("Check Out");
    Label currentHrsLbl = new Label("Your current hours: ");
    Label currentHrsDisplayLbl = new Label();
    Label usernameLbl = new Label("Username");
    TextField usernameTxt = new TextField();
    Label passwordLbl = new Label("Password");
    TextField passwordTxt = new TextField();
//    Label volIdLbl = new Label("Volunteer ID");
//    TextField volIdTxt = new TextField();
    Button checkoutBtn = new Button("CHECK OUT");
    
    GridPane checkoutPane = new GridPane();
    
    Checkout(Home home) {
        this.home = home;
        paneSettings(checkoutPane);
        
        // add nodes
        checkoutPane.add(backBtn, 0, 0);
        checkoutPane.add(titleLbl, 1, 0);
        checkoutPane.add(currentHrsLbl, 1, 1);
        checkoutPane.add(currentHrsDisplayLbl, 2, 1);
        checkoutPane.add(usernameLbl, 1, 2);
        checkoutPane.add(usernameTxt, 1, 3);
        checkoutPane.add(passwordLbl, 1, 4);
        checkoutPane.add(passwordTxt, 1, 5);
//        checkoutPane.add(volIdLbl, 1, 6);
//        checkoutPane.add(volIdTxt, 1, 7);
        checkoutPane.add(checkoutBtn, 2, 8);
        
        Stage primaryStage = home.primaryStage;
        Scene primaryScene = new Scene(checkoutPane, 600, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("BARK Checkout");
        primaryStage.show();
        
        backBtn.setOnAction(e -> {
            Home backHome = new Home(home);
            primaryStage.close();
        });
    }
    
    
}
