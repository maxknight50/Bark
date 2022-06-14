/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bark;

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 *
 * 
 */
public class SocialHomeScreen extends Login1 {
    Home home;
    
    Button backBtn = new Button("Back");
    Label scrnTitleLbl = new Label("Social Home Screen");
    Label dateLbl = new Label("Date");
    Label descLbl = new Label("Description");
    
    GridPane socialPane = new GridPane();
    
    SocialHomeScreen(Home home) {
        this.home = home;
        paneSettings(socialPane);
        
        socialPane.add(backBtn, 0, 0);
        socialPane.add(scrnTitleLbl, 1, 0);
        socialPane.add(dateLbl, 0, 1);
        socialPane.add(descLbl, 2, 1);
        
        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(socialPane, 600, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Social Home Screen");
        primaryStage.show();
        
        backBtn.setOnAction(e -> {
            primaryStage.close();
        });
 
    }
    
    @Override
    public void paneSettings(GridPane pane) {
        socialPane = pane;

        // Add spacing
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);
        pane.setAlignment(Pos.TOP_CENTER);
    }
    
}
