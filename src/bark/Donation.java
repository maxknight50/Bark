
package bark;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 *
 * 
 */
public class Donation extends Login1 {
    Home home;
    
    Button backBtn = new Button("Back");
    Label donationTitleLbl = new Label("Donations");
    Label donationIDLbl = new Label("Donation ID");
    ComboBox<String> donationIDCb = new ComboBox();
    Label donationAmntLbl = new Label("Donation Amount");
    TextField donationAmntTxt = new TextField();
    Label donationNameLbl = new Label("Name of Donator");
    TextField donationNameTxt = new TextField();
    Label donationDateLbl = new Label("Date of Donation");
    TextField donationDateTxt = new TextField();
    Button submitBtn = new Button("Submit");
    
    GridPane donationPane = new GridPane();
    
    public Donation(Home home) {
        this.home = home;
        paneSettings(donationPane);
        
        
        donationPane.add(backBtn, 0, 0);
        donationPane.add(donationTitleLbl, 1, 0);
        donationPane.add(donationIDLbl, 1, 1);
        donationPane.add(donationIDCb, 2, 1);
        donationPane.add(donationAmntLbl, 1, 2);
        donationPane.add(donationAmntTxt, 1, 3);
        donationPane.add(donationNameLbl, 1, 4);
        donationPane.add(donationNameTxt, 1, 5);
        donationPane.add(donationDateLbl, 1, 6);
        donationPane.add(donationDateTxt, 1, 7);
        donationPane.add(submitBtn, 2, 8);
        
        
        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(donationPane, 600, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Donations");
        primaryStage.show();
    }
}
