/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bark;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class VolunteerStatus extends Login1{
    
    Home home;
    
    Label topLbl = new Label("Volunteer Status");
    Label fNameLbl = new Label("First Name");
    Label lNameLbl = new Label("Last Name");
    Label addressLbl = new Label("Address");
    Label emailLbl = new Label("E-Mail");
    Label phoneLbl = new Label("Phone");
    Label cumHrsLbl = new Label("Cumulative Hours");
    Label statusLbl = new Label("Status");
    Label availLbl = new Label("Availability");
    
    TextField fNameTxt = new TextField();
    TextField lNameTxt = new TextField();
    TextField addressTxt = new TextField();
    TextField emailTxt = new TextField();
    TextField phoneTxt = new TextField();
    TextField cumHrsTxt = new TextField();
    TextField availTxt = new TextField();
    
    ComboBox<String> statusBox = new ComboBox<>();
    
    Button backBtn = new Button("Back");
   
    GridPane pane = new GridPane();
    
    VolunteerStatus(Home home) {
        
        this.home = home;
        paneSettings(pane);
        
        pane.add(backBtn, 0, 0);
        pane.add(topLbl, 1, 1);
        pane.add(fNameLbl, 0, 2);
        pane.add(fNameTxt, 1, 2);
        pane.add(lNameLbl, 0, 3);
        pane.add(lNameTxt, 1, 3);
        pane.add(addressLbl, 0, 4);
        pane.add(addressTxt, 1, 4);
        pane.add(emailLbl, 0, 5);
        pane.add(emailTxt, 1, 5);
        pane.add(phoneLbl, 0, 6);
        pane.add(phoneTxt, 1, 6);
        pane.add(cumHrsLbl, 0, 7);
        pane.add(cumHrsTxt, 1, 7);
        pane.add(statusLbl, 0, 8);
        pane.add(statusBox, 1, 8);
        pane.add(availLbl, 0, 9);
        pane.add(availTxt, 1, 9);
        
        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(pane, 600, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Volunteer Status");
        primaryStage.show();
        
    }
}
    

