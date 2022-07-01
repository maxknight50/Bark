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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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
    TableColumn active_col = new TableColumn("Active Status");

    // Left side
    Label volID = new Label("Volunteer ID");
    Label topLbl = new Label("Volunteer Status");
    Label fNameLbl = new Label("First Name");
    Label lNameLbl = new Label("Last Name");
    Label addressLbl = new Label("Address");
    Label dateOfBirth = new Label("Date of Birth");
    Label emailLbl = new Label("E-Mail");
    Label phoneLbl = new Label("Phone");
    Label cumHrsLbl = new Label("Cumulative Hours");
    Label statusLbl = new Label("Status");
    Label activeLbl = new Label("Active Status");

    Label idField = new Label();
    TextField fNameTxt = new TextField();
    TextField lNameTxt = new TextField();
    TextField addressTxt = new TextField();
    TextField birthField = new TextField(); 
    TextField emailTxt = new TextField();
    TextField phoneTxt = new TextField();
    TextField cumHrsTxt = new TextField();
    ChoiceBox<String> statusTxt = new ChoiceBox();
    ChoiceBox<String> activeTxt = new ChoiceBox();

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
        statusTxt.setItems(FXCollections.observableArrayList("Volunteer", "Volunteer in Training", "Admin"));
        activeTxt.setItems(FXCollections.observableArrayList("active", "inactive"));
        paneSettings(modVolunteer);
        modVolunteer.add(backBtn, 0, 0);
        modVolunteer.add(topLbl, 1, 1);
        modVolunteer.add(volID, 0, 12);
        modVolunteer.add(idField, 1, 12);
        modVolunteer.add(fNameLbl, 0, 3);
        modVolunteer.add(fNameTxt, 1, 3);
        modVolunteer.add(lNameLbl, 0, 4);
        modVolunteer.add(lNameTxt, 1, 4);
        modVolunteer.add(addressLbl, 0, 5);
        modVolunteer.add(addressTxt, 1, 5);
        modVolunteer.add(dateOfBirth, 0, 6); 
        modVolunteer.add(birthField, 1, 6); 
        modVolunteer.add(emailLbl, 0, 7);
        modVolunteer.add(emailTxt, 1, 7);
        modVolunteer.add(phoneLbl, 0, 8);
        modVolunteer.add(phoneTxt, 1, 8);
        modVolunteer.add(cumHrsLbl, 0, 9);
        modVolunteer.add(cumHrsTxt, 1, 9);
        modVolunteer.add(statusLbl, 0, 10);
        modVolunteer.add(statusTxt, 1, 10);
        modVolunteer.add(activeLbl, 0, 11);
        modVolunteer.add(activeTxt, 1, 11);

        volID.setVisible(false);
        idField.setVisible(false);
        modVolunteer.add(add, 0, 13);
        modVolunteer.add(modify, 1, 13);
        modVolunteer.add(delete, 0, 14);

        volTable.setItems(tableData);
        volunteerTable.add(message, 0, 0);
        volunteerTable.add(volTable, 0, 1);
        volunteerTable.add(populate, 0, 2);

        viewPaw.setFitHeight(50);
        viewPaw.setFitWidth(50);
        viewPaw.setX(100);
        viewPaw.setY(150);
        modVolunteer.add(viewPaw, 0, 1);

        id_col.setCellValueFactory(new PropertyValueFactory<Volunteer, Integer>("volunteerID"));
        fname_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("volFirst"));
        lname_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("volLast"));
        add_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("volAddress"));
        dob_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("dateOfBirth"));
        email_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("volEmail"));
        phone_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("volPhone"));
        hours_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("totalHours"));
        status_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("status"));
        active_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("hasAccess"));

        volTable.getColumns().addAll(id_col, fname_col, lname_col, add_col, dob_col, email_col, phone_col, hours_col, status_col);
        ArrayList<ListVolunteers> volunteerList = new ArrayList<>();

        sendDBCommand("SELECT volID, vol_FirstName, vol_LastName, vol_Address, vol_DateOfBirth, vol_Email, vol_Phone, cumulativeHours, "
                + "status, hasAccess FROM Volunteer");
        try {
            for (int i = 0; i < 100; i++) {
                while (rs.next()) {
                    if (rs != null) {
                        volunteerList.add(new ListVolunteers(rs.getInt("volID"), rs.getString("vol_FirstName"), rs.getString("vol_LastName"),
                                rs.getString("vol_Address"), rs.getString("vol_DateOfBirth"), rs.getString("vol_Email"),
                                rs.getString("vol_Phone"), rs.getDouble("cumulativeHours"), rs.getString("status"), rs.getString("hasAccess")));
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
        Scene primaryScene = new Scene(overall, 1150, 650);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Listed Volunteers");
        primaryStage.show();

        populate.setOnAction(e -> {
            idField.setText(volTable.getSelectionModel().getSelectedItem().getVolunteerID() + "");
            fNameTxt.setText(volTable.getSelectionModel().getSelectedItem().getVolFirst() + "");
            lNameTxt.setText(volTable.getSelectionModel().getSelectedItem().getVolLast() + "");
            addressTxt.setText(volTable.getSelectionModel().getSelectedItem().getVolAddress() + "");
            birthField.setText(volTable.getSelectionModel().getSelectedItem().getDateOfBirth() + ""); 
            emailTxt.setText(volTable.getSelectionModel().getSelectedItem().getVolEmail() + "");
            phoneTxt.setText(volTable.getSelectionModel().getSelectedItem().getVolPhone() + "");
            cumHrsTxt.setText(volTable.getSelectionModel().getSelectedItem().getTotalHours() + "");
            //statusTxt.setText(volTable.getSelectionModel().getSelectedItem().getStatus() + "");
            statusTxt.setValue(volTable.getSelectionModel().getSelectedItem().getStatus() + "");
            activeTxt.setValue(volTable.getSelectionModel().getSelectedItem().getAccess());
           // activeTxt.setText(volTable.getSelectionModel().getSelectedItem().getAccess() + "");
        });
        //Action Handler to create a volunteer
        add.setOnAction(e -> {
            //Auto Volunteer ID assignment
            int largest = 0;
            try {
                String q = "SELECT * FROM VOLUNTEER";
                sendDBCommand(q);
                try {
                    while (rs.next()) {
                        largest = rs.getInt("volID");
                        while (rs.next()) {
                            int store = rs.getInt("volID");
                            if (store > largest) {
                                largest = store;
                            }
                        }
                    }
                } catch (Exception m) {
                    System.out.println("Exception finding the largest! " + m);
                }
                int newID = largest + 1;
                String newFirst = fNameTxt.getText();
                String newLast = lNameTxt.getText();
                String newAddress = addressTxt.getText();
                String dateOfBirth = birthField.getText(); 
                String newEmail = emailTxt.getText();
                String newPhone = phoneTxt.getText();
                double newCumulative = Double.valueOf(cumHrsTxt.getText());
                String newStatus = statusTxt.getValue(); 
                String newAccess = activeTxt.getValue();
                //Add new volunteer into the arraylist 
                ListVolunteers newVolunteer = new ListVolunteers(newID, newFirst, newLast, newAddress, dateOfBirth, newEmail, newPhone, newCumulative, newStatus, newAccess);
                String query = "INSERT INTO VOLUNTEER(volID, vol_FirstName, vol_LastName, vol_Address, vol_Email, vol_Phone, cumulativeHours, status, hasAccess) VALUES (" + newID + ",'" + newFirst + "', '" + newLast + "', '" + newAddress + "', '" + newEmail + "', '" + newPhone + "', " + newCumulative + ", '" + newStatus + "', '" + newAccess +"')";
                sendDBCommand(query);
                tableData.clear();
                volunteerList.add(newVolunteer);
                for (ListVolunteers x : volunteerList) {
                    tableData.add(x);
                }
                message.setText("Entry added successfully.");

            } catch (Exception ex) {
                System.out.println("Error! " + ex);
                message.setText("Error. Please revise your entries.");
            }
        });
        //Event handler to modify a volunteer's information 
        modify.setOnAction(e -> {
            int newID = Integer.valueOf(idField.getText());
            String newFirst = fNameTxt.getText();
            String newLast = lNameTxt.getText();
            String newAddress = addressTxt.getText();
            String dateOfBirth = birthField.getText(); 
            String newEmail = emailTxt.getText();
            String newPhone = phoneTxt.getText();
            double newCumulative = Double.valueOf(cumHrsTxt.getText());
            String newStatus = statusTxt.getValue(); 
            String newAccess = activeTxt.getValue();

            String query = "UPDATE VOLUNTEER SET vol_FirstName = '" + newFirst + "', vol_LastName = '" + newLast + "', vol_Address = '" + newAddress + "', vol_DateOfBirth = '" + dateOfBirth + "', vol_Email = '" + newEmail
                    + "', vol_Phone = '" + newPhone + "', cumulativeHours = " + newCumulative + ", status = '" + newStatus + "', hasAccess = '" + newAccess + "' WHERE volID = " + newID + "";
            sendDBCommand(query);
            for (int i = 0; i < volunteerList.size(); i++) {
                if (volunteerList.get(i).getVolunteerID() == newID) {
                    volunteerList.get(i).setVolunteerID(newID);
                    volunteerList.get(i).setVolFirst(newFirst);
                    volunteerList.get(i).setVolLast(newLast);
                    volunteerList.get(i).setVolAddress(newAddress);
                    volunteerList.get(i).setDateOfBirth(dateOfBirth); 
                    volunteerList.get(i).setVolEmail(newEmail);
                    volunteerList.get(i).setVolPhone(newPhone);
                    volunteerList.get(i).setTotalHours(newCumulative);
                    volunteerList.get(i).setStatus(newStatus);
                    volunteerList.get(i).setAccess(newAccess);
                    break;
                }
            }
            message.setText("Modify entry successful!");
            tableData.clear();
            for (ListVolunteers x : volunteerList) {
                tableData.add(x);
            }
        });
        //Action handler to delete a volunteer from the database and the table 
        delete.setOnAction(e -> {
            String query = "DELETE FROM VOLUNTEER WHERE volID = " + volTable.getSelectionModel().getSelectedItem().getVolunteerID();
            sendDBCommand(query);
            message.setText("Entry removed successfully.");
            int x = volTable.getSelectionModel().getSelectedIndex();
            volunteerList.remove(x);
            tableData.clear();
            for (ListVolunteers z : volunteerList) {
                tableData.add(z);
            }
        });
        //Event handler to close the pane
        backBtn.setOnAction(e -> {
            primaryStage.close();
        });
    }

    public void sendDBCommand(String sqlQuery) {
        String URL = "jdbc:oracle:thin:@localhost:1521:XE";
        String userID = "javauser";
        String userPASS = "javapass";
        OracleDataSource ds;

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
