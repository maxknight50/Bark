package bark;

import java.text.DecimalFormat;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 *
 */
public class Checkout extends Login1 {

    VolunteerHome1 volHome;
    Home home;
    VolunteerHome1 vHome;
    DecimalFormat df = new DecimalFormat("#.##");

    Label titleLbl = new Label("Check Out");
    Label currentHrsLbl;
    Label currentHrsDisplayLbl = new Label();
    Button checkoutBtn = new Button("CHECK OUT");

    Image paw = new Image("file:paw.jpg");
    ImageView viewPaw = new ImageView(paw);

    GridPane checkoutPane = new GridPane();

    Checkout(Home home) { // For admin login
        this.home = home;
        paneSettings(checkoutPane);
        System.out.println("Test admin: " + df.format(home.cumHours));
        currentHrsLbl = new Label("Your current hours: " + df.format(home.cumHours));

        // add nodes
        checkoutPane.add(titleLbl, 1, 0);
        checkoutPane.add(currentHrsLbl, 1, 1);
        checkoutPane.add(currentHrsDisplayLbl, 2, 1);
        checkoutPane.add(checkoutBtn, 1, 8);

        viewPaw.setFitHeight(50);
        viewPaw.setFitWidth(50);
        viewPaw.setX(100);
        viewPaw.setY(150);
        checkoutPane.add(viewPaw, 2, 9);

        Stage primaryStage = home.primaryStage;
        Scene primaryScene = new Scene(checkoutPane, 600, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("BARK Checkout");
        primaryStage.show();

        checkoutBtn.setOnAction(e -> {
            primaryStage.close();
        });

    }
    
        Checkout(VolunteerHome1 volHome) { // For regular login
        this.volHome = volHome;
        paneSettings(checkoutPane);
        System.out.println("Test admin: " + df.format(home.cumHours));
        currentHrsLbl = new Label("Your current hours: " + df.format(home.cumHours));

        // add nodes
        checkoutPane.add(titleLbl, 1, 0);
        checkoutPane.add(currentHrsLbl, 1, 1);
        checkoutPane.add(currentHrsDisplayLbl, 2, 1);
        checkoutPane.add(checkoutBtn, 1, 8);

        viewPaw.setFitHeight(50);
        viewPaw.setFitWidth(50);
        viewPaw.setX(100);
        viewPaw.setY(150);
        checkoutPane.add(viewPaw, 2, 9);

        Stage primaryStage = home.primaryStage;
        Scene primaryScene = new Scene(checkoutPane, 600, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("BARK Checkout");
        primaryStage.show();

        checkoutBtn.setOnAction(e -> {
            primaryStage.close();
        });

    }

}
