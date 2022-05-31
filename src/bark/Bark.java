//THIS IS A COMMENT #2

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
import static javafx.application.Application.launch;

/**
 *
 * 
 */

public class Bark extends Application {
    
        // FX Controls
    Label label = new Label("Hello");
    Button button1 = new Button("Button");

    GridPane pane1 = new GridPane();
    GridPane pane2 = new GridPane();

    // Objects for Database Connection method 
    Connection dbConn;
    Statement commStmt;
    ResultSet dbResults;
    
    public void start(Stage primaryStage) {
        pane1.setAlignment(Pos.CENTER);
        pane1.add(label, 0, 0);
        pane1.add(button1, 0, 1);

        Scene primaryScene = new Scene(pane1, 600, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("First Scene");
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
