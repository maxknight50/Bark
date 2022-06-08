/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bark;

import java.util.*;
import javafx.collections.*; // ObservableArrayLists
import javafx.geometry.Pos;
import javafx.scene.chart.*; // Charts and Tables
import javafx.scene.control.cell.*; // Tableview

import java.sql.*;
import oracle.jdbc.pool.*;
import java.util.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
 *
 * 
 */

public class Login1 extends Application {

    //FX Labels
    Label userNameLbl = new Label("Username");
    Label passwordLbl = new Label("Password");
    Button loginButton = new Button("Log In");
    Label interestLbl = new Label("Interested in joining BARK?");
    Button applicationButton = new Button("Begin Application");

    TextField userNameTxt = new TextField();
    TextField passwordTxt = new TextField();

    //FX GridPane
    GridPane loginPane = new GridPane();

    @Override
    public void start(Stage primaryStage) {
        paneSettings(loginPane);
        //Adds to pane
        loginPane.add(userNameLbl, 0, 0);
        loginPane.add(userNameTxt, 0, 1);
        loginPane.add(passwordLbl, 0, 2);
        loginPane.add(passwordTxt, 0, 3);
        loginPane.add(loginButton, 0, 4);
        loginPane.add(interestLbl, 0, 6);
        loginPane.add(applicationButton, 0, 7);

        Scene primaryScene = new Scene(loginPane, 600, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Log In / Sign Up");
        primaryStage.show();

        // Begin application button
        applicationButton.setOnAction(e -> {
            Application app = new Application(this);
        });
        
        // Log in Button
        loginButton.setOnAction(e -> {
            //boolean result = loginAttempt(username);
            //if (result) 
                Home home = new Home(this);
        });

    }
    
    public boolean loginAttempt(String username) {
        return true;
    }

    public void paneSettings(GridPane pane) {
        loginPane = pane;

        // Add spacing
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);
        pane.setAlignment(Pos.CENTER);

    }

    public static void main(String[] args) {
        launch(args);
    }
}