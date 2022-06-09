
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
    
    Button backLbl = new Button("Back");
    Label titleLbl = new Label("Check Out");
    Label currentHrsLbl = new Label("Your current hours: ");
    TextField currentHrsTxt = new TextField();
    Label usernameLbl = new Label("Username");
    TextField usernameTxt = new TextField();
    Label passwordLbl = new Label("Password");
    TextField passwordTxt = new TextField();
    Label volIdLbl = new Label("Volunteer ID");
    TextField volIdTxt = new TextField();
    Button checkoutBtn = new Button("CHECK OUT");
    
    
    GridPane checkoutPane = new GridPane();
    
    
    Checkout(Home home) {
        this.home = home;
        paneSettings(checkoutPane);
        
        // add nodes
        //checkoutPane.add(backLbl, 0, 0);
        checkoutPane.add(titleLbl, 0, 0);
        checkoutPane.add(currentHrsLbl, 0, 1);
        checkoutPane.add(currentHrsTxt, 1, 1);
        checkoutPane.add(usernameLbl, 0, 2);
        checkoutPane.add(usernameTxt, 0, 3);
        checkoutPane.add(passwordLbl, 0, 4);
        checkoutPane.add(passwordTxt, 0, 5);
        checkoutPane.add(volIdLbl, 0, 6);
        checkoutPane.add(volIdTxt, 0, 7);
        checkoutPane.add(checkoutBtn, 1, 8);
        

        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(checkoutPane, 600, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("BARK Checkout");
        primaryStage.show();
        
        backLbl.setOnAction(e -> {
            primaryStage.close(); 
        });
    }
    
    
}
