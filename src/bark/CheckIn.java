package bark;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 *
 */
public class CheckIn {

    Home home;
    GridPane checkin = new GridPane();
    Label txt = new Label("Would you like to check in and start your hour count?");
    Button check = new Button("Check In");

    CheckIn(Home home) {
        this.home = home;

        checkin.add(txt, 0, 0);
        checkin.add(check, 0, 1);
        checkin.setAlignment(Pos.CENTER);

        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(checkin, 400, 300);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Check In");
        primaryStage.show();
    }

}
