
package bark;

import static bark.VolunteerStatus.conn;
import java.util.*;
import javafx.collections.*; // ObservableArrayLists
import javafx.geometry.Pos;
import javafx.scene.chart.*; // Charts and Tables
import javafx.scene.control.cell.*; // Tableview
import javafx.scene.control.PasswordField; 
import java.sql.*;
import oracle.jdbc.pool.*;
import java.util.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    
    String name; // Stores the identified user name for use in other classes

    //FX Labels
    Label userNameLbl = new Label("Username");
    Label passwordLbl = new Label("Password");
    Button loginButton = new Button("Log In");
    Label interestLbl = new Label("Interested in joining BARK?");
    Button applicationButton = new Button("Begin Application");
    Image paw = new Image("file:paw.jpg");
    ImageView viewPaw = new ImageView(paw);
    Label login;

    TextField userNameTxt = new TextField();
    PasswordField passwordTxt = new PasswordField();
    //FX GridPane
    GridPane loginPane = new GridPane();
    
    Stage primaryStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {

        paneSettings(loginPane);
        //Adds to pane
        loginPane.add(userNameLbl, 0, 1);
        loginPane.add(userNameTxt, 0, 2);
        loginPane.add(passwordLbl, 0, 3);
        loginPane.add(passwordTxt, 0, 4);
        loginPane.add(loginButton, 0, 5);
        loginPane.add(interestLbl, 0, 6);
        loginPane.add(applicationButton, 0, 7);
        viewPaw.setFitHeight(50);
        viewPaw.setFitWidth(50);
        viewPaw.setX(100);
        viewPaw.setY(150);
        loginPane.getChildren().add(viewPaw);

        Scene primaryScene = new Scene(loginPane, 600, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Log In / Sign Up");
        primaryStage.show();
        
        
        // Begin application button
        applicationButton.setOnAction(e -> {
            Application2 app = new Application2(this);
        });

        sendDBCommand("select * from Volunteer");
//        sendDBCommand("select * from Volunteer where username = 'JMarkinson'");
//        if (rs.next()) {
//            System.out.println(rs.getString("password"));
//        }

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
        boolean correct = false;

        loginButton.setOnAction(e -> {
            //boolean result = loginAttempt(username);
            //if (result) 

            // 1. Username must match username within database, search through and locate
            String userName = userNameTxt.getText();
            String password = passwordTxt.getText();
            sendDBCommand("select * from Volunteer");

            try {
                // Read in first values
                int test = 1;
                while (rs.next() && test == 1) {
                    if (userName.equals(rs.getString("username"))) { // If the username entered matches an entry in the database
                        System.out.println("Success! " + userName + " = " + rs.getString("username"));
                        sendDBCommand("select * from Volunteer where username = '" + userName + "'"); // Update query to specify found username
                        System.out.println(password);
                        
                        if (rs.next()) {
                            if (password.equals(rs.getString("password"))) { // If the password matches the found username, it is successful
                                System.out.println("Success! " + password + " = " + rs.getString("password"));
                                
                                ////////////////////////////////////////////////////////////////////
                                if (rs.getString("status").equalsIgnoreCase("admin")) {
                                    Home home = new Home(this); // Display the admin home screen
                                    primaryStage.close();
                                } else {
                                    Home home = new Home(this); // Display the regular volunteer home screen
                                    primaryStage.close();
                                }
                                test = 0; // Both username and password were correct, so stop while loop 
                                
                            } else {
                                login = new Label("Incorrect login. Please try again.");
                                loginPane.add(login, 0, 0);
                                primaryStage.show();
                            }
                        } else {
                            System.out.println("Not correct password");
                            login = new Label("Incorrect login. Please try again.");
                            loginPane.add(login, 0, 0);
                            primaryStage.show();
                        }
                    } else if (rs.isLast()) { // If the whole database is searched and no matching usernames are found
                        System.out.println("No matching username found");
                        login = new Label("Incorrect login. Please try again.");
                        loginPane.add(login, 0, 0);
                        primaryStage.show();
                    }
                }

            } catch (Exception ex) {
                System.out.println("Error: " + e.toString());
            }

            // 3. Display correct home page for admin vs. volunteer
            // if Volunteer.status = 'Administrator' where Volunteer.username
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
    
    public void getPrimaryStage(Stage loginStage) {
        loginStage = primaryStage;
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
