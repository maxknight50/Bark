package bark;

import static bark.Login1.conn;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    // Table column titles
    TableColumn id_col = new TableColumn("ID");
    TableColumn fname_col = new TableColumn("First");
    TableColumn lname_col = new TableColumn("Last");
    TableColumn add_col = new TableColumn("Address");
    TableColumn dob_col = new TableColumn("DOB");
    TableColumn email_col = new TableColumn("Email");
    TableColumn phone_col = new TableColumn("Phone");
    TableColumn hours_col = new TableColumn("Hours");
    TableColumn status_col = new TableColumn("Status");

    // Left side
    Label volID = new Label("Volunteer ID");
    Label topLbl = new Label("Volunteer Status");
    Label fNameLbl = new Label("First Name");
    Label lNameLbl = new Label("Last Name");
    Label addressLbl = new Label("Address");
    Label emailLbl = new Label("E-Mail");
    Label phoneLbl = new Label("Phone");
    Label cumHrsLbl = new Label("Cumulative Hours");
    Label statusLbl = new Label("Status");

    TextField idField = new TextField(); 
    TextField fNameTxt = new TextField();
    TextField lNameTxt = new TextField();
    TextField addressTxt = new TextField();
    TextField emailTxt = new TextField();
    TextField phoneTxt = new TextField();
    TextField cumHrsTxt = new TextField();
    TextField statusBox = new TextField();
    
    Label message = new Label();

    Button backBtn = new Button("Back");
    Button add = new Button("Add");
    Button delete = new Button("Delete");
    Button modify = new Button("Modify");
    Button populate = new Button("<-- Select and Populate");


    Image paw = new Image("file:paw.jpg");
    ImageView viewPaw = new ImageView(paw);

    GridPane overall = new GridPane();
    GridPane modVolunteer = new GridPane();
    GridPane volunteerTable = new GridPane();

    public VolunteerList(Home home) {
        paneSettings(modVolunteer);
        modVolunteer.add(backBtn, 0, 0);
        modVolunteer.add(topLbl, 1, 1);
        modVolunteer.add(volID, 0, 2); 
        modVolunteer.add(idField, 1, 2); 
        modVolunteer.add(fNameLbl, 0, 3);
        modVolunteer.add(fNameTxt, 1, 3);
        modVolunteer.add(lNameLbl, 0, 4);
        modVolunteer.add(lNameTxt, 1, 4);
        modVolunteer.add(addressLbl, 0, 5);
        modVolunteer.add(addressTxt, 1, 5);
        modVolunteer.add(emailLbl, 0, 6);
        modVolunteer.add(emailTxt, 1, 6);
        modVolunteer.add(phoneLbl, 0, 7);
        modVolunteer.add(phoneTxt, 1, 7);
        modVolunteer.add(cumHrsLbl, 0, 8);
        modVolunteer.add(cumHrsTxt, 1, 8);
        modVolunteer.add(statusLbl, 0, 9);
        modVolunteer.add(statusBox, 1, 9);

        modVolunteer.add(add, 0, 10);
        modVolunteer.add(modify, 1, 10);
        modVolunteer.add(delete, 0, 11);

        volTable.setItems(tableData);
        volunteerTable.add(message, 0, 0); 
        volunteerTable.add(volTable, 0, 1);
        volunteerTable.add(populate, 0, 2); 

        viewPaw.setFitHeight(50);
        viewPaw.setFitWidth(50);
        viewPaw.setX(100);
        viewPaw.setY(150);
        modVolunteer.add(viewPaw, 0, 12);

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
        ArrayList<ListVolunteers> volunteerList = new ArrayList<>();

        sendDBCommand("SELECT volID, vol_FirstName, vol_LastName, vol_Address, vol_DateOfBirth, vol_Email, vol_Phone, cumulativeHours, status FROM Volunteer");
        try {
            for (int i = 0; i < 100; i++) {
                while (rs.next()) {
                    if (rs != null) {
                        volunteerList.add(new ListVolunteers(rs.getInt("volID"), rs.getString("vol_FirstName"), rs.getString("vol_LastName"), rs.getString("vol_Address"), rs.getDate("vol_DateOfBirth"), rs.getString("vol_Email"), rs.getString("vol_Phone"), rs.getDouble("cumulativeHours"), rs.getString("status")));
                        break;
                    }
                }
            }
            for (ListVolunteers x : volunteerList) {
                tableData.add(x);
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception! " + e);
        }

        overall.add(modVolunteer, 0, 0);
        overall.add(volunteerTable, 1, 0);
        Scene primaryScene = new Scene(overall, 1000, 550);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Listed Volunteers");
        primaryStage.show();
        
        populate.setOnAction(e -> {
            System.out.println("Populate button clicked");
            idField.setText(volTable.getSelectionModel().getSelectedItem().getVolunteerID() + ""); 
            fNameTxt.setText(volTable.getSelectionModel().getSelectedItem().getVolFirst() + ""); 
            lNameTxt.setText(volTable.getSelectionModel().getSelectedItem().getVolLast() + ""); 
            addressTxt.setText(volTable.getSelectionModel().getSelectedItem().getVolAddress() + ""); 
            emailTxt.setText(volTable.getSelectionModel().getSelectedItem().getVolEmail() + ""); 
            phoneTxt.setText(volTable.getSelectionModel().getSelectedItem().getVolPhone() + ""); 
            cumHrsTxt.setText(volTable.getSelectionModel().getSelectedItem().getTotalHours() + ""); 
            statusBox.setText(volTable.getSelectionModel().getSelectedItem().getStatus() + ""); 
        });

        add.setOnAction(e -> {
            int largest = 0;
            try {
                String q = "SELECT * FROM VOLUNTEER";
                sendDBCommand(q);
                try {
                    while (rs.next()) {
                        largest = rs.getInt("volID");
                        System.out.println("First: " + largest);
                        while (rs.next()) {
                            int store = rs.getInt("volID");
                            System.out.println(store);
                            if (store > largest) {
                                largest = store;
                            }
                        }

                        System.out.println("Final largest: " + largest);
                    }
                } catch (Exception m) {
                    System.out.println("Exception finding the largest! " + m);
                }
                int newID = largest + 1;
                String newFirst = fNameTxt.getText();
                String newLast = lNameTxt.getText();
                String newAddress = addressTxt.getText();
                String newEmail = emailTxt.getText();
                String newPhone = phoneTxt.getText();
                double newCumulative = Integer.valueOf(cumHrsTxt.getText());

                ListVolunteers newVolunteer = new ListVolunteers(newID, newFirst, newLast, newAddress, newEmail, newPhone, newCumulative);

                String query = "INSERT INTO VOLUNTEER(volID, vol_FirstName, vol_LastName, vol_Address, vol_Email, vol_Phone, cumulativeHours) VALUES (" + newID + ",'" + newFirst + "', '" + newLast + "', '" + newAddress + "', '" + newEmail + "', '" + newPhone + "', " + newCumulative + ")";

                sendDBCommand(query);
                tableData.clear();
                for (int i = 0; i < volunteerList.size(); i++) {
                    if (volunteerList.get(i) == null) {
                        volunteerList.add(newVolunteer);
                        break;
                    }
                }
                for (ListVolunteers x : volunteerList) {
                    tableData.add(x);
                }

            } catch (Exception ex) {
                System.out.println("Error! " + ex);
            }
        });

        modify.setOnAction(e -> {
            System.out.println("Modify button clicked");
            int newID = Integer.valueOf(idField.getText()); 
            String newFirst = fNameTxt.getText();
            String newLast = lNameTxt.getText();
            String newAddress = addressTxt.getText();
            String newEmail = emailTxt.getText();
            String newPhone = phoneTxt.getText();
            double newCumulative = Double.valueOf(cumHrsTxt.getText());
            
            String query = "UPDATE VOLUNTEER SET vol_FirstName = '" + newFirst + "', vol_LastName = '" + newLast + "', vol_Address = '" + newAddress + "', vol_Email = '" + newEmail
                    + "', vol_Phone = '" + newPhone + "', cumulativeHours = " + newCumulative + " WHERE volID = " + newID + "";
            sendDBCommand(query);
            for(int i = 0; i < volunteerList.size(); i++){
                if(volunteerList.get(i).getVolunteerID() == newID){
                    volunteerList.get(i).setVolunteerID(newID);
                    volunteerList.get(i).setVolFirst(newFirst);
                    volunteerList.get(i).setVolLast(newLast);
                    volunteerList.get(i).setVolAddress(newAddress);
                    volunteerList.get(i).setVolEmail(newEmail);
                    volunteerList.get(i).setVolPhone(newPhone);
                    volunteerList.get(i).setTotalHours(newCumulative);
                    break;
                }
            }
            message.setText("Modify entry successful!");
            tableData.clear(); 
            for(ListVolunteers x: volunteerList){
                tableData.add(x); 
            }

        });

        backBtn.setOnAction(e -> {
            primaryStage.close();
        });
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
