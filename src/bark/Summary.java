
package bark;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Summary extends Login1 {
    Home home;
    
    Button backLbl = new Button("Back");
    Label firstNameLabel = new Label("First Name");
    TextField firstNameField = new TextField(); 
    Label lastNameLabel = new Label("Last Name: ");
    TextField lastNameField = new TextField();
    Label addressLabel = new Label("Address: ");
    TextField addressField = new TextField();
    Label emailLabel = new Label("E-Mail: ");
    TextField emailField = new TextField();
    Label phoneLabel = new Label("Phone Number:");
    TextField phoneField = new TextField();
    Label cumulativeHours = new Label("Cumulative Hours: ");
    TextField hoursField = new TextField(); 
    Label statusLabel = new Label("Status: ");
    TextField statusField = new TextField(); 
    Label availabilityLabel = new Label("Availability: ");
    TextField availabilityField = new TextField(); 
    
    
    Button checkoutBtn = new Button("CHECK OUT");
    
    
    GridPane summaryPane = new GridPane();
    
    
    Summary(Login1 login) {
        this.home = home;
        paneSettings(summaryPane);
        
        summaryPane.add(backLbl, 0, 0); 
        summaryPane.add(firstNameLabel, 0, 1);
        summaryPane.add(firstNameField, 1, 1);
        summaryPane.add(lastNameLabel, 0, 2);
        summaryPane.add(lastNameField, 1, 2);
        summaryPane.add(addressLabel, 0, 3);
        summaryPane.add(addressField, 1, 3);
        summaryPane.add(emailLabel, 0, 4);
        summaryPane.add(emailField, 1, 4);
        summaryPane.add(phoneLabel, 0, 5);
        summaryPane.add(phoneField, 1, 5);
        summaryPane.add(cumulativeHours, 0, 6);
        summaryPane.add(hoursField, 1, 6);
        summaryPane.add(statusLabel, 0, 7);
        summaryPane.add(statusField, 1, 7);
        summaryPane.add(availabilityLabel, 0, 8);
        summaryPane.add(availabilityField, 1, 8);



        

        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(summaryPane, 600, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Volunteer Status");
        primaryStage.show();
        
        backLbl.setOnAction(e -> {
            primaryStage.close(); 
        });
    }
    
    
}
