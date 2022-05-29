/*
Author: Jeremy D. Ezell - JMU
Purpose: JavaFX form for creating and interacting with an
  inventory of Sprockets.

*/
package bark;

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;
import javafx.collections.*; // ObservableArrayLists
import javafx.geometry.Pos;
import javafx.scene.chart.*; // Charts and Tables
import javafx.scene.control.cell.*; // Tableview

import java.sql.*;
import oracle.jdbc.pool.*;
import java.util.*;


public class SprocketFormFill extends Application {
    // Class level variables / data fields / "globals"
    // Storing data in memory:
    ArrayList<Sprocket> sprocketInventory = new ArrayList<>();
    
    // FX Controls
    Label lblDesc = new Label("Label:");
    Label lblWeight = new Label("Weight:");
    Label lblColor = new Label("Color:");
    TextField txtDesc = new TextField();
    TextField txtWeight = new TextField();
    TextField txtColor = new TextField();
    Button btnCreate = new Button("Create Sprocket ->");
    ListView<Sprocket> sprocketList = new ListView<>();
    
    GridPane createPane = new GridPane();
    
    
    // Our Database Connection method needs these 
    // objects. We declare them here and point them
    // to instance objects below.
    Connection dbConn;
    Statement commStmt;
    ResultSet dbResults;
    
    @Override
    public void start(Stage primaryStage) {
        createPane.setAlignment(Pos.CENTER);
        createPane.add(lblDesc, 0, 0);
        createPane.add(txtDesc, 1, 0);
        createPane.add(lblWeight, 0, 1);
        createPane.add(txtWeight, 1, 1);
        createPane.add(lblColor, 0, 2);
        createPane.add(txtColor, 1, 2);
        createPane.add(btnCreate, 1, 3);
        createPane.add(sprocketList, 2, 0, 1, 4);
        
        Scene primaryScene = new Scene(createPane,800,650);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Sprocket Factory Form");
        primaryStage.show();
        
        //Event handling
        // 3 Methods:
        // #1: Event handling inner class
        //      Create a CDF inside the FX app
        //      Create an instance object of that class
        //      Register that object iwth the Button
        // #2: Anonymous Event Handling class
        //      In-line Definition and Instantiation of anEvent Handling Class
        // #3: Lambda Expression
        //      Lambda --> Shortcutting Syntax into a sinple form
        
        
        btnCreate.setOnAction(e -> {
            System.out.println("Hello from JavaFX!");
            
            // 1. Clear out the ListView
            sprocketList.getItems().clear();
            
            // 2. Create the new sprocket based on the user's information
            Sprocket tempSprocket = new CeramicSprocket(
                    txtDesc.getText(), 
                    Double.valueOf(txtWeight.getText()), 
                    txtColor.getText());
            
            // 3. Add the sprocket to the ArrayList (Project: to the DB too)
            sprocketInventory.add(tempSprocket);
            
            // 4. Update the ListView to show the most recent data
            for(Sprocket s : sprocketInventory) {
                sprocketList.getItems().add(s);
            }
            
        });
    }

    public void sendDBCommand(String sqlQuery)
    {
        // Set up your connection strings
        // IF YOU ARE IN CIS330 NOW: use YOUR Oracle Username/Password
        String URL = "jdbc:oracle:thin:@localhost:1521:XE";
        String userID = "javauser"; // Change to YOUR Oracle username
        String userPASS = "javapass"; // Change to YOUR Oracle password
        OracleDataSource ds;
        
        // Clear Box Testing - Print each query to check SQL syntax
        //  sent to this method.
        // You can comment this line out when your program is finished
        System.out.println(sqlQuery);
        
        // Lets try to connect
        try
        {
            // instantiate a new data source object
            ds = new OracleDataSource();
            // Where is the database located? Web? Local?
            ds.setURL(URL);
            // Send the user/pass and get an open connection.
            dbConn = ds.getConnection(userID,userPASS);
            // When we get results
            //  -TYPE_SCROLL_SENSITIVE means if the database data changes we
            //   will see our resultset update in real time.
            //  -CONCUR_READ_ONLY means that we cannot accidentally change the
            //   data in our database by using the .update____() methods of
            //   the ResultSet class - TableView controls are impacted by
            //   this setting as well.
            commStmt = dbConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // We send the query to the DB. A ResultSet object is instantiated
            //  and we are returned a reference to it, that we point to from
            // dbResults.
            // Because we declared dbResults at the datafield level
            // we can see the results from anywhere in our Class.
            dbResults = commStmt.executeQuery(sqlQuery); // Sends the Query to the DB
            // The results are stored in a ResultSet structure object
            // pointed to by the reference variable dbResults
            // Because we declared this variable globally above, we can use
            // the results in any method in the class.
        }
        catch (SQLException e)
        {
            System.out.println(e.toString());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
