
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
    
    // GridPane associated for each tab
    GridPane tPane1 = new GridPane();
    GridPane tPane2 = new GridPane();
    GridPane tPane3 = new GridPane();
    
    // Tab creation
    Tab tab1 = new Tab("Daily Events");
    Tab tab2 = new Tab("Past Events");
    Tab tab3 = new Tab("Your Events");
    
    GridPane eventsPane = new GridPane();
    TabPane tabPane = new TabPane();
    EventsMenu(Home home) {
        this.home = home;
        
        eventsPane.add(tabPane, 0, 1);
        
        
        tab1.setContent(tPane1);
        tab2.setContent(tPane2);
        tab3.setContent(tPane3);
        tabPane.getTabs().addAll(tab1, tab2, tab3);
        //TabPane tabClosingPolicy="UNAVAILABLE";
        tab1.setClosable(false);
        tab2.setClosable(false);
        tab3.setClosable(false);
        
        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(eventsPane, 600, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Events Menu");
        primaryStage.show();  
        tabPane.setMinHeight(primaryScene.getHeight());
        tabPane.setMinWidth(primaryScene.getWidth());
    }

}
