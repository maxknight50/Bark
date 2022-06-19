/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bark;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author joshr
 */
public class Logout extends Login1 {
    Home home;
    Label logoutLbl = new Label("Logout");
    Label msgLbl = new Label("You are now logged out!");
    //Button loginBtn = new Button("Back to Login");
    
    Image paw = new Image("file:paw.jpg");
    ImageView viewPaw = new ImageView(paw);
    
    GridPane logoutPane = new GridPane();
    
    Logout(Home home) {
        paneSettings(logoutPane);
        logoutPane.add(logoutLbl, 0, 0);
        logoutPane.add(msgLbl, 0, 1);
        //logoutPane.add(loginBtn, 0, 2);
        
        viewPaw.setFitHeight(50);
        viewPaw.setFitWidth(50);
        viewPaw.setX(100);
        viewPaw.setY(150);
        logoutPane.add(viewPaw, 1, 2);
        
        Stage primaryStage = home.primaryStage;
        Scene primaryScene = new Scene(logoutPane, 400, 400);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Logout");
        primaryStage.show();
        
        //loginBtn.setOnAction(e -> {
            //Login1 login = new Login1(primaryStage);
            //primaryStage.close();
        //});
        
    }
}
