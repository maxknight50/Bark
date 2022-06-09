
package bark;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EventsMenu extends Login1 {
    Home home;
    
    // Tab 1 Controls
    
    // Tab 2 Controls
    
    // Tab 3 Controls
    
    
    
    GridPane eventsPane = new GridPane();
    TabPane tabPane = new TabPane();
    EventsMenu(Home home) {
        this.home = home;
        
        
        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(eventsPane, 600, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Events Menu");
        primaryStage.show();        
    }

}
