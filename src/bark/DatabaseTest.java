package bark;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 */
public class DatabaseTest extends Application {

    //ArrayList< > x = new ArrayList<>();
    
    Label lblDesc = new Label("Label:");
    TextField txtDesc = new TextField();
    Button btnCreate = new Button("Create Sprocket ->");
    
    GridPane createPane = new GridPane();
    
    Statement stmt;
    static Connection conn;
    ResultSet rs;

    @Override
    public void start(Stage primaryStage) throws Exception {

        createPane.setAlignment(Pos.CENTER);
        createPane.add(lblDesc, 0, 0);
        createPane.add(txtDesc, 1, 0);
        createPane.add(btnCreate, 1, 3);
        Scene primaryScene = new Scene(createPane, 400, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("TITLE");
        primaryStage.show();
        
        sendDBCommand("select * from Volunteer");

        try {
            // Read in first values
            while (rs.next()) {
                System.out.println(rs.getInt("volID"));
                System.out.println(rs.getString("vol_Name"));
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

    public static void main(String[] args) {
        launch(args);
    }

}
