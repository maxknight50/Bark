/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bark;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

// Displays individual animal info from the database
public class AnimalInfo extends Login1{
    
    Home home;
    
    Label topLbl = new Label("Animal Information");
    Label idLbl = new Label("Animal ID");
    Label nameLbl = new Label("Name");
    Label speciesLbl = new Label("Species");
    Label ageLbl = new Label("Age");
    Label medicalLbl = new Label("Medical History");
    Label feedingLbl = new Label("Feeding Needs");
    Label eventIdLbl = new Label ("Event ID");
    Label volIdLbl = new Label ("Volunteer ID");
    Label vetHistory = new Label ("Vet History");
    ComboBox<String> vetHistoryCB = new ComboBox();
    
    TextField idTxt = new TextField();
    TextField nameTxt = new TextField();
    TextField speciesTxt = new TextField();
    TextField ageTxt = new TextField();
    TextField medicalTxt = new TextField();
    TextField feedingTxt = new TextField();
    TextField eventIdTxt = new TextField();
    TextField volIdTxt = new TextField();
    
    Button backBtn = new Button("Back");
    
    Image paw = new Image("file:paw.jpg");
    ImageView viewPaw = new ImageView(paw);
    
    GridPane pane = new GridPane();
    
    AnimalInfo(Home home) {
        this.home = home;
        paneSettings(pane);
        
        pane.add(backBtn, 0, 0);
        pane.add(topLbl, 1, 1);
        pane.add(idLbl, 0, 2);
        pane.add(idTxt, 1, 2);
        pane.add(nameLbl, 0, 3);
        pane.add(nameTxt, 1, 3);
        pane.add(speciesLbl, 0, 4);
        pane.add(speciesTxt, 1, 4);
        pane.add(ageLbl, 0, 5);
        pane.add(ageTxt, 1, 5);
        pane.add(medicalLbl, 0, 6);
        pane.add(medicalTxt, 1, 6);
        pane.add(feedingLbl, 0, 7);
        pane.add(feedingTxt, 1, 7);
        pane.add(eventIdLbl, 0, 8);
        pane.add(eventIdTxt, 1, 8);
        pane.add(volIdLbl, 0, 9);
        pane.add(volIdTxt, 1, 9);
        pane.add(vetHistory,0,10);
        pane.add(vetHistoryCB,1,10);
        
        viewPaw.setFitHeight(50);
        viewPaw.setFitWidth(50);
        viewPaw.setX(100);
        viewPaw.setY(150);
        pane.add(viewPaw, 0, 10);

        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(pane, 600, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Animal Information");
        primaryStage.show();
        
        backBtn.setOnAction(e -> {
            primaryStage.close();
        });
    }
    
}
