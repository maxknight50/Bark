package bark;

import static bark.Login1.conn;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javafx.application.Application;
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
public class VolunteerList extends Login1 {

    Home home;

    Statement stmt;
    static Connection conn;
    ResultSet rs;

    TableView<ListVolunteers> volTable = new TableView<>();
    ObservableList<ListVolunteers> tableData = FXCollections.observableArrayList();

    TableColumn id_col = new TableColumn("ID");
    TableColumn fname_col = new TableColumn("First");
    TableColumn lname_col = new TableColumn("Last");
    TableColumn add_col = new TableColumn("Address");
    TableColumn dob_col = new TableColumn("DOB");
    TableColumn email_col = new TableColumn("Email");
    TableColumn phone_col = new TableColumn("Phone");
    TableColumn hours_col = new TableColumn("Hours");
    TableColumn status_col = new TableColumn("Status");

    GridPane tPane1 = new GridPane();

    public VolunteerList(Home home) {
        volTable.setItems(tableData);
        tPane1.add(volTable, 0, 0);
        
        id_col.setCellValueFactory(new PropertyValueFactory<Volunteer, Integer>("volunteerID"));
        fname_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("volFirst"));
        lname_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("volLast"));
        add_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("volAddress"));
        dob_col.setCellValueFactory(new PropertyValueFactory<Volunteer, Date>("dateOfBirth"));
        email_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("volEmail"));
        phone_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("volPhone"));
        hours_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("totalHours"));
        status_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("status"));

        
        volTable.getColumns().addAll(id_col, fname_col, lname_col, add_col, dob_col, email_col, phone_col, hours_col, status_col);
        
        sendDBCommand("SELECT volID, vol_FirstName, vol_LastName, vol_Address, vol_DateOfBirth, vol_Email, vol_Phone, cumulativeHours, status FROM Volunteer");
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
