package bark;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;
import tables.*;

/**
 *
 */
public class AnimalList extends Application {

    Home home;

    Statement stmt;
    static Connection conn;
    ResultSet rs;

    TableView<ListVolunteers> volTable = new TableView<>();
    ObservableList<ListVolunteers> tableData = FXCollections.observableArrayList();

    TableColumn id_col = new TableColumn("animalID");
    TableColumn name_col = new TableColumn("name");
    TableColumn species_col = new TableColumn("species");
    TableColumn age_col = new TableColumn("age");
    TableColumn history_col = new TableColumn("medicalHistory");
    TableColumn feeding_col = new TableColumn("feedingNeeds");
    TableColumn vethist_col = new TableColumn("vetHistory");
    TableColumn event_col = new TableColumn("eventID");
    TableColumn volID_col = new TableColumn("volID");

    GridPane tPane1 = new GridPane();

    public void start(Stage primaryStage) {
        volTable.setItems(tableData);
        tPane1.add(volTable, 0, 0);
        
        id_col.setCellValueFactory(new PropertyValueFactory<Volunteer, Integer>("animalID"));
        name_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("name"));
        species_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("species"));
        age_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("age"));
        history_col.setCellValueFactory(new PropertyValueFactory<Volunteer, Date>("medicalHistory"));
        feeding_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("feedingNeeds"));
        vethist_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("vetHistory"));
        event_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("eventID"));
        volID_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("volID"));

        
        volTable.getColumns().addAll(id_col, name_col, species_col, age_col, history_col, feeding_col, vethist_col, event_col, volID_col);
        
        sendDBCommand("SELECT animal_ID, name, species, age, medicalHistory, feedingNeeds, vetHistory, eventID, volID FROM Animal");
        System.out.println("RESULTSET: " + rs);
        try {
            ListVolunteers[] volunteerList = new ListVolunteers[25];
            for(int i = 0; i < 100; i++){
                while(rs.next()){
                    if (rs != null){
                        volunteerList[i] = new ListVolunteers(rs.getInt("volID"), rs.getString("vol_FirstName"), rs.getString("vol_LastName"), rs.getString("vol_Address"),rs.getDate("vol_DateOfBirth"), rs.getString("vol_Email"), rs.getString("vol_Phone"), rs.getDouble("cumulativeHours"), rs.getString("status"));
                        break;
                    }
                }
            }
            for(ListVolunteers x : volunteerList){
                tableData.add(x); 
            }
            
        } catch (SQLException e){
            System.out.println("SQL Exception! " + e);
        }

        Scene primaryScene = new Scene(tPane1, 600, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("List");
        primaryStage.show();
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
            System.out.println("RESULT SET: " + rs);

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
