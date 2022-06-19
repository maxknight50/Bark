package bark;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ScheduleAvailability extends Login1 {

    Home home;

    Button backBtn = new Button("Back");
    Label ScheduleTitleLbl = new Label("Schedule Availibiltiy");
    Label MondayLbl = new Label("Monday");
    ComboBox<String> scheduleIDCb1 = new ComboBox();
    Label TuesdayLbl = new Label("Tuesday");
    ComboBox<String> scheduleIDCb2 = new ComboBox();
    Label WednesdayLbl = new Label("Wednesday");
    ComboBox<String> scheduleIDCb3 = new ComboBox();
    Label ThursdayLbl = new Label("Thursday");
    ComboBox<String> scheduleIDCb4 = new ComboBox();
    Label FridayLbl = new Label("Friday");
    ComboBox<String> scheduleIDCb5 = new ComboBox();
    Label SaturdayLbl = new Label("Saturday");
    ComboBox<String> scheduleIDCb6 = new ComboBox();
    Label SundayLbl = new Label("Sunday");
    ComboBox<String> scheduleIDCb7 = new ComboBox();

    GridPane schedulePane = new GridPane();

    Image paw = new Image("file:paw.jpg");
    ImageView viewPaw = new ImageView(paw);

    public ScheduleAvailability(Home home) {
        this.home = home;
        paneSettings(schedulePane);

        schedulePane.add(backBtn, 0, 0);
        schedulePane.add(ScheduleTitleLbl, 1, 0);
        schedulePane.add(MondayLbl, 1, 1);
        schedulePane.add(scheduleIDCb1, 2, 1);
        schedulePane.add(TuesdayLbl, 1, 2);
        schedulePane.add(scheduleIDCb2, 2, 2);
        schedulePane.add(WednesdayLbl, 1, 3);
        schedulePane.add(scheduleIDCb3, 2, 3);
        schedulePane.add(ThursdayLbl, 1, 4);
        schedulePane.add(scheduleIDCb4, 2, 4);
        schedulePane.add(FridayLbl, 1, 5);
        schedulePane.add(scheduleIDCb5, 2, 5);
        schedulePane.add(SaturdayLbl,1,6);
        schedulePane.add(scheduleIDCb6,2,6);
        schedulePane.add(SundayLbl,1,7);
        schedulePane.add(scheduleIDCb7,2,7);

        viewPaw.setFitHeight(50);
        viewPaw.setFitWidth(50);
        viewPaw.setX(100);
        viewPaw.setY(150);
        schedulePane.add(viewPaw, 3, 6);

        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(schedulePane, 600, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Schedule Availability");
        primaryStage.show();

        backBtn.setOnAction(e -> {
            primaryStage.close();
        });
    }
}
