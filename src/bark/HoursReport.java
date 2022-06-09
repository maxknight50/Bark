package bark;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class HoursReport extends Login1 {
    Home home;
    
    Button backLbl = new Button("Back");
    Label idLabel = new Label("Volunteer ID:");
    ComboBox idBox = new ComboBox(); 
    Label hoursLabel = new Label("# of Hours:");
    TextField hoursField = new TextField(); 
    Label milesLabel = new Label("Miles Driven: ");
    TextField milesField = new TextField();
    Label departureLabel = new Label("Departure Time: ");
    TextField departureField = new TextField();
    Label arrivalLabel = new Label("Arrival Time: ");
    TextField arrivalField = new TextField();
    Label categoryLabel = new Label("Activity Category:");
    TextField categoryField = new TextField();
    

    GridPane hoursPane = new GridPane();
    
    HoursReport(Login1 login) {
        this.home = home;
        paneSettings(hoursPane);
        
        hoursPane.add(backLbl, 0, 0);
        hoursPane.add(idLabel, 0, 1); 
        hoursPane.add(idBox, 1, 1); 
        hoursPane.add(hoursLabel, 0, 2);
        hoursPane.add(hoursField, 1, 2);
        hoursPane.add(milesLabel, 0, 3);
        hoursPane.add(milesField, 1, 3);
        hoursPane.add(departureLabel, 0, 4);
        hoursPane.add(departureField, 1, 4);
        hoursPane.add(arrivalLabel, 0, 5);
        hoursPane.add(arrivalField, 1, 5);
        hoursPane.add(categoryLabel, 0, 6);
        hoursPane.add(categoryField, 1, 6);


        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(hoursPane, 600, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Volunteer and Hours Report");
        primaryStage.show();
        
        backLbl.setOnAction(e -> {
            primaryStage.close(); 
        });
    }
    
    
}
