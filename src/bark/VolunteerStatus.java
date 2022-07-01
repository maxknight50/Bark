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
import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;
import tables.Volunteer;

public class VolunteerStatus extends Login1 {

    Login1 login = new Login1();
    Home home;

    Label topLbl = new Label("Volunteer Status");
    Label fNameLbl = new Label("First Name");
    Label lNameLbl = new Label("Last Name");
//    Label addressLbl = new Label("Address");
    Label emailLbl = new Label("E-Mail");
//    Label phoneLbl = new Label("Phone");
    Label cumHrsLbl = new Label("Cumulative Hours");
    Label statusLbl = new Label("Status");

    TextField fNameTxt = new TextField();
    TextField lNameTxt = new TextField();
//    TextField addressTxt = new TextField();
    TextField emailTxt = new TextField();
//    TextField phoneTxt = new TextField();
    TextField cumHrsTxt = new TextField();
    TextField availTxt = new TextField();

    TextField statusBox = new TextField();

    Button backBtn = new Button("Back");
    
    Image paw = new Image("file:paw.jpg");
    ImageView viewPaw = new ImageView(paw);

    GridPane pane = new GridPane();

    Statement stmt;
    static Connection conn;
    ResultSet rs;

    VolunteerStatus(Home home) {

        this.home = home;
//        String status[] = {"Volunteer in Training", "Volunteer", "Admin"};
//        statusBox = new ComboBox(FXCollections.observableArrayList(status));
//        
        paneSettings(pane);
        pane.add(backBtn, 0, 0);
        pane.add(topLbl, 1, 1);
        pane.add(fNameLbl, 0, 2);
        pane.add(fNameTxt, 1, 2);
        pane.add(lNameLbl, 0, 3);
        pane.add(lNameTxt, 1, 3);
//        pane.add(addressLbl, 0, 4);
//        pane.add(addressTxt, 1, 4);
        pane.add(emailLbl, 0, 4);
        pane.add(emailTxt, 1, 4);
//        pane.add(phoneLbl, 0, 6);
//        pane.add(phoneTxt, 1, 6);
        pane.add(cumHrsLbl, 0, 5);
        pane.add(cumHrsTxt, 1, 5);
        pane.add(statusLbl, 0, 6);
        pane.add(statusBox, 1, 6);
        
        viewPaw.setFitHeight(50);
        viewPaw.setFitWidth(50);
        viewPaw.setX(100);
        viewPaw.setY(150);
        pane.add(viewPaw, 3, 8);

        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(pane, 600, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Volunteer Status");
        primaryStage.show();

        backBtn.setOnAction(e -> {
            primaryStage.close();
        });
        System.out.println("ID:" + home.login.id);
        String tmp = "select * from Volunteer where volID = ";
        tmp += home.login.id;
        sendDBCommand(tmp);

        try {
            // Read in first values
            if (rs.next()) {
                System.out.println(rs.getInt("volID"));
                fNameTxt.setText(rs.getString("vol_firstname"));
                lNameTxt.setText(rs.getString("vol_lastname"));
//                addressTxt.setText(rs.getString("vol_Address"));  
                emailTxt.setText(rs.getString("vol_Email"));
//                phoneTxt.setText(rs.getString("vol_Phone"));
                cumHrsTxt.setText(rs.getString("cumulativeHours"));
                statusBox.setText(rs.getString("status"));

            }

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }

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
