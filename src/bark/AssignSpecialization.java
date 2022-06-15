package bark;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * Assign Specialization screen
 */
public class AssignSpecialization extends Login1 {

    Home home;

    // add title and back button
    Label volIdLbl = new Label("Volunteer ID");
    ComboBox<String> volIdCb = new ComboBox();
    Label specialMenuLbl = new Label("Specialization Menu");
    ComboBox<String> specialMenuCb;
    Label currentSpecialLbl = new Label("Current Specializations: ");
    ListView currentList = new ListView();
    Button add = new Button("Add");
    Button delete = new Button("Delete");

    GridPane specialPane = new GridPane();

    AssignSpecialization(Home home) {
        this.home = home;
        paneSettings(specialPane);

        String specialization[] = {"Feeding", "Enclosure Care", "Adopter Relations", "Event Volunteer", "Training", "Fundraising"};
        specialMenuCb = new ComboBox(FXCollections.observableArrayList(specialization));

        // add nodes to pane
        specialPane.add(volIdLbl, 0, 0);
        specialPane.add(volIdCb, 0, 1);
        specialPane.add(specialMenuLbl, 0, 2);
        specialPane.add(specialMenuCb, 0, 3);
        specialPane.add(currentSpecialLbl, 0, 4);
        specialPane.add(currentList, 0, 5);
        specialPane.add(add, 0, 6);
        specialPane.add(delete, 1, 6);

        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(specialPane, 600, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Specialization Assignment");
        primaryStage.show();
    }
}
