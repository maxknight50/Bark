package bark;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * Assign Specialization screen
 */
public class AssignSpecialization extends Login1 {

    Home home;

    // add title and back button
    Label volNameLbl = new Label("Volunteer Name");
    TextField volNameTxt = new TextField();
    Label specialMenuLbl = new Label("Specialization Menu");
    ComboBox<String> specialMenuCb;
    Label currentSpecialLbl = new Label("Current Specializations: ");
    ListView currentList = new ListView();
    Button add = new Button("Add");
    Button delete = new Button("Delete");
    
    Image paw = new Image("file:paw.jpg");
    ImageView viewPaw = new ImageView(paw);

    GridPane specialPane = new GridPane();

    AssignSpecialization(Home home) {
        this.home = home;
        paneSettings(specialPane);

        String specialization[] = {"Feeding", "Enclosure Care", "Adopter Relations", "Event Volunteer", "Training", "Fundraising"};
        specialMenuCb = new ComboBox(FXCollections.observableArrayList(specialization));

        // add nodes to pane
        specialPane.add(volNameLbl, 0, 0);
        specialPane.add(volNameTxt, 0, 1);
        specialPane.add(specialMenuLbl, 0, 2);
        specialPane.add(specialMenuCb, 0, 3);
        specialPane.add(currentSpecialLbl, 0, 4);
        specialPane.add(currentList, 0, 5, 3, 2);
        specialPane.add(add, 0, 7);
        specialPane.add(delete, 1, 7);
        currentList.setPrefWidth(150);
        currentList.setPrefHeight(200);
        currentList.setMinSize(200.0, Control.USE_PREF_SIZE);
        currentList.setMaxSize(200.0, Control.USE_PREF_SIZE);
        
        viewPaw.setFitHeight(50);
        viewPaw.setFitWidth(50);
        viewPaw.setX(100);
        viewPaw.setY(150);
        specialPane.add(viewPaw, 2, 7);

        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(specialPane, 700, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Specialization Assignment");
        primaryStage.show();
    }
}
