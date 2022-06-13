/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bark;

import static bark.VolunteerStatus.conn;
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

    Statement stmt;
    static Connection conn;
    ResultSet rs;

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
    public void start(Stage primaryStage) throws Exception {

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
            Application2 app = new Application2(this);
        });

        sendDBCommand("select * from Volunteer");

//        try {
//            // Read in first values
//            while (rs.next()) {
//                System.out.println(rs.getInt("volID"));
//                System.out.println(rs.getString("vol_Name"));
//            }
//
//        } catch (Exception e) {
//            System.out.println("Error: " + e.toString());
//        }
        // Log in Button
        loginButton.setOnAction(e -> {
            //boolean result = loginAttempt(username);
            //if (result) 

            // 1. Username must match username within database, search through and locate
            String userName = userNameTxt.getText();
            try {
                // Read in first values
                while (rs.next()) {
                    System.out.println(rs.getString("username"));
                    System.out.println(rs.getString("vol_Name"));

                }

            } catch (Exception ex) {
                System.out.println("Error: " + e.toString());
            }

            String password = passwordTxt.getText();

            // 2. Password must match password associated with username
            // Display error message if failed login
            System.out.println(userName + " " + password);

            // 3. Display correct home page for admin vs. volunteer
            // if Volunteer.status = 'Administrator' where Volunteer.username
            Home home = new Home(this);

            //arrayListName.add(
            //userNameTxt.setText("");
            //passwordTxt.clear();
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

    public void sendDBCommand(String sqlQuery) {
        String URL = "jdbc:oracle:thin:@localhost:1521:XE";
        String userID = "javauser";
        String userPASS = "javapass";
        OracleDataSource ds;

        // You can comment this line out when your program is finished
        System.out.println(sqlQuery);

        try {
            ds = new OracleDataSource();
            ds.setURL(URL);
            conn = ds.getConnection(userID, userPASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sqlQuery); // Sends the Query to the DB

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
