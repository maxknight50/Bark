

package bark;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


/**
 *
 * 
 */
public class Home extends Login1 {
    Login1 login;
    Label barkTitle = new Label("BARK");
    Button eventsBtn = new Button("View Events");
    Button socialHomeScreenBtn = new Button("View Social Home Screen");
    Button volunteerInfoBtn = new Button("View Volunteer Information");
    Button assignSpecialBtn = new Button("Assign Specialization");
    Button volunteerReportBtn = new Button("View Volunteer Report");
    Button checkoutBtn = new Button("Check Out");
    
    
    
    
    GridPane homePane = new GridPane();
    
    
    
    Home(Login1 login) {
        this.login = login;
        paneSettings(homePane);
        homePane.add(barkTitle, 0, 0);
        homePane.add(eventsBtn, 0, 1);
        homePane.add(socialHomeScreenBtn, 0, 2);
        homePane.add(volunteerInfoBtn, 0, 3);
        homePane.add(assignSpecialBtn, 0, 4);
        homePane.add(volunteerReportBtn, 0, 5);
        homePane.add(checkoutBtn, 0, 6);

        
        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(homePane, 600, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("BARK Home");
        primaryStage.show();
        
        // Check out button
        checkoutBtn.setOnAction(e -> {
            Checkout chkOut = new Checkout(this); 
        });
    }
    
}
