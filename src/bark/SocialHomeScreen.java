/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bark;

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 *
 * 
 */
public class SocialHomeScreen extends Login1 {
    Home home;
    
    Button backBtn = new Button("Back");
    Label scrnTitleLbl = new Label("Social Home");
    Label dateLbl = new Label("Date");
    
    Label takeOut = new Label("06/01/22");
    Label takeOut3 = new Label("05/28/22");
    
    Label descLbl = new Label("Description");
    
    Label takeOut2 = new Label("John Smith completed 20 hours of training. Congratulations!");
    Label takeOut4 = new Label("Elizabeth Ley joined BARK!");
    
    Image paw = new Image("file:paw.jpg");
    ImageView viewPaw = new ImageView(paw);
    
    GridPane socialPane = new GridPane();
    
    SocialHomeScreen(Home home) {
        this.home = home;
        paneSettings(socialPane);
        
        socialPane.add(backBtn, 0, 0);
        socialPane.add(scrnTitleLbl, 0, 1);
        socialPane.add(dateLbl, 0, 2);
        socialPane.add(takeOut, 0, 3);
        socialPane.add(descLbl, 2, 2);
        socialPane.add(takeOut2, 2, 3);
        socialPane.add(takeOut3, 0, 4);
        socialPane.add(takeOut4, 2, 4);
        
        viewPaw.setFitHeight(50);
        viewPaw.setFitWidth(50);
        viewPaw.setX(100);
        viewPaw.setY(150);
        socialPane.add(viewPaw, 4, 0);
        
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
