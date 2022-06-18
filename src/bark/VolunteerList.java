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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    
    Label topLbl = new Label("Volunteer Status");
    Label fNameLbl = new Label("First Name");
    Label lNameLbl = new Label("Last Name");
    Label addressLbl = new Label("Address");
    Label emailLbl = new Label("E-Mail");
    Label phoneLbl = new Label("Phone");
    Label cumHrsLbl = new Label("Cumulative Hours");
    Label statusLbl = new Label("Status");
    Label availLbl = new Label("Availability");

    TextField fNameTxt = new TextField();
    TextField lNameTxt = new TextField();
    TextField addressTxt = new TextField();
    TextField emailTxt = new TextField();
    TextField phoneTxt = new TextField();
    TextField cumHrsTxt = new TextField();
    TextField availTxt = new TextField();

    TextField statusBox = new TextField();

    Button backBtn = new Button("Back");

    GridPane overall = new GridPane();
    GridPane modVolunteer = new GridPane(); 
    GridPane volunteerTable = new GridPane();

    public VolunteerList(Home home) {
                paneSettings(modVolunteer);
        modVolunteer.add(backBtn, 0, 0);
        modVolunteer.add(topLbl, 1, 1);
        modVolunteer.add(fNameLbl, 0, 2);
        modVolunteer.add(fNameTxt, 1, 2);
        modVolunteer.add(lNameLbl, 0, 3);
        modVolunteer.add(lNameTxt, 1, 3);
        modVolunteer.add(addressLbl, 0, 4);
        modVolunteer.add(addressTxt, 1, 4);
        modVolunteer.add(emailLbl, 0, 5);
        modVolunteer.add(emailTxt, 1, 5);
        modVolunteer.add(phoneLbl, 0, 6);
        modVolunteer.add(phoneTxt, 1, 6);
        modVolunteer.add(cumHrsLbl, 0, 7);
        modVolunteer.add(cumHrsTxt, 1, 7);
        modVolunteer.add(statusLbl, 0, 8);
        modVolunteer.add(statusBox, 1, 8);
        modVolunteer.add(availLbl, 0, 9);
        modVolunteer.add(availTxt, 1, 9);
        
        volTable.setItems(tableData);
        volunteerTable.add(volTable, 0, 0);
        
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
        
        overall.add(modVolunteer, 0, 0);
        overall.add(volunteerTable, 1, 0);
        Scene primaryScene = new Scene(overall, 1000, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Listed Volunteers");
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
