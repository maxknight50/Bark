//THIS IS A COMMENT #2
// HELLO
// Josh's comment test
package bark;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.*;
import oracle.jdbc.pool.*;
import java.util.*;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;

/**
 *
 *
 */
public class BarkApplication extends BarkLogin {

    BarkLogin login;

    // FX Controls - Labels
    //Label title = new Label("Application"); ***Find JAVAFX Node/control that isnt Label for title
    Label description1 = new Label("Welcome! Thank you for your interest in BARK");
    Label description2 = new Label("Please contact us at 504-394-3001 with any questions.");
    Label fNameLbl = new Label("First Name");
    Label lNameLbl = new Label("Last Name");
    Label addressLbl = new Label("Address");
    Label infoLbl = new Label("Information");
    Label emailLbl = new Label("Email");
    Label phoneLbl = new Label("Phone");
    Label expLbl = new Label("Experience");

    // FX Controls - Textfields, comboboxes, etc.
    TextField fNameTxt = new TextField();
    TextField lNameTxt = new TextField();
    TextField addressTxt = new TextField();
    TextField infoTxt = new TextField();
    TextField emailTxt = new TextField();
    TextField phoneTxt = new TextField();
    ComboBox<String> expCb = new ComboBox();
    Button submitBtn = new Button("Submit");

    GridPane pane1 = new GridPane();

    BarkApplication(BarkLogin login) {

        this.login = login;
        paneSettings(pane1);
        //pane1.add(title, 1,0);
        pane1.add(description1, 1, 1);
        pane1.add(description2, 1, 2);
        pane1.add(fNameLbl, 0, 3);
        pane1.add(fNameTxt, 1, 3);
        pane1.add(lNameLbl, 0, 4);
        pane1.add(lNameTxt, 1, 4);
        pane1.add(infoLbl, 0, 5);
        pane1.add(infoTxt, 1, 5);
        pane1.add(emailLbl, 0, 6);
        pane1.add(emailTxt, 1, 6);
        pane1.add(expLbl, 0, 7);
        pane1.add(expCb, 1, 7);
        pane1.add(submitBtn, 1, 8);

        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(pane1, 600, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("BARK Application");
        primaryStage.show();
    }

}
