package bark;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.*;
import oracle.jdbc.pool.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Applicant fills out information, temporary entry created in database until
 * admin approves
 *
 */
public class Application2 extends Login1 {

    Login1 login;

    // FX Controls - Labels
    //Label title = new Label("Application"); ***Find JAVAFX Node/control that isnt Label for title
    Label description1 = new Label("Welcome! Thank you for your interest in BARK");
    Label description2 = new Label("Please contact us at 504-394-3001 with any questions.");
    Label fNameLbl = new Label("First Name");
    Label lNameLbl = new Label("Last Name");
    Label addressLbl = new Label("Address");
    Label dobLbl = new Label("Date of Birth");
    Label infoLbl = new Label("Tell us about yourself!");
    Label emailLbl = new Label("Email");
    Label phoneLbl = new Label("Phone");
    Label specialLbl = new Label("Specializations");
    Label expLbl = new Label("Experience");
    Label usernameLbl = new Label("Username:");
    Label passwordLbl = new Label("Password:");

    // FX Controls - Textfields, comboboxes, etc.
    TextField fNameTxt = new TextField();
    TextField lNameTxt = new TextField();
    TextField addressTxt = new TextField();
    TextField dobTxt = new TextField();
    TextArea infoTxt = new TextArea();
    TextField emailTxt = new TextField();
    TextField phoneTxt = new TextField();
    TextField usernameTxt = new TextField();
    TextField passwordTxt = new TextField();
    ComboBox<String> special = new ComboBox();
    TextField experience = new TextField();
    Button submitBtn = new Button("Submit");
    Button deny = new Button("Deny");

    ListView currentList = new ListView();
    TextField newSpecialTxt = new TextField();

    Image paw = new Image("file:paw.jpg");
    ImageView viewPaw = new ImageView(paw);

    GridPane pane1 = new GridPane();

    Application2(Login1 login) {
        ObservableList<String> specialization = FXCollections.observableArrayList();
        ArrayList<String> defaultList = new ArrayList<>();
        sendDBCommand("SELECT DISTINCT specializationID, specialization_Name FROM specialization");
        try {
            while (rs.next()) {
                String special = (rs.getString("specialization_Name"));
                defaultList.add(special);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignSpecialization.class.getName()).log(Level.SEVERE, null, ex);
        }
        specialization.addAll(defaultList);
        special = new ComboBox(FXCollections.observableArrayList(specialization));

        this.login = login;
        paneSettings(pane1);
        //pane1.add(title, 1,0);
        pane1.add(description1, 1, 0);
        pane1.add(description2, 1, 1);
        pane1.add(fNameLbl, 0, 3);
        pane1.add(fNameTxt, 1, 3);
        pane1.add(lNameLbl, 0, 4);
        pane1.add(lNameTxt, 1, 4);

        pane1.add(addressLbl, 0, 5);
        pane1.add(addressTxt, 1, 5);
        pane1.add(dobLbl, 0, 6);
        pane1.add(dobTxt, 1, 6);
        pane1.add(emailLbl, 0, 7);
        pane1.add(emailTxt, 1, 7);

        pane1.add(phoneLbl, 0, 8);
        pane1.add(phoneTxt, 1, 8);
        pane1.add(expLbl, 0, 9);
        pane1.add(experience, 1, 9);
        pane1.add(specialLbl, 0, 10);
        pane1.add(special, 1, 10);

        pane1.add(currentList, 1, 11);
        pane1.add(newSpecialTxt, 1, 12);

        pane1.add(infoLbl, 0, 13);
        pane1.add(infoTxt, 1, 13);

//        pane1.add(usernameLbl, 0, 1);
//        pane1.add(usernameTxt, 1, 1);
 //       pane1.add(passwordLbl, 0, 2);
//        pane1.add(passwordTxt, 1, 2);

        pane1.add(submitBtn, 1, 15);

        viewPaw.setFitHeight(50);
        viewPaw.setFitWidth(50);
        viewPaw.setX(100);
        viewPaw.setY(150);
        pane1.add(viewPaw, 0, 0);

        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(pane1, pane1.getMaxWidth(), pane1.getMaxHeight());
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("BARK Application");
        primaryStage.show();

        submitBtn.setOnAction(e -> {
            // Display success window
            // Update database with status 'Applicant' 
            // Change volunteerList to specify either 'Volunteer' or 'Admin'
            // Assign temp volID
            fNameTxt.getText();
            lNameTxt.getText();
            addressTxt.getText();
            infoTxt.getText();
            emailTxt.getText();
            phoneTxt.getText();

            String q = "SELECT * FROM VOLUNTEER";
            sendDBCommand(q);
            int largestVolId = 0;
            int largestScheduleId = 0;
            try {
                while (rs.next()) {
                    largestVolId = rs.getInt("volID"); // Look at first number, set it as largest
                    largestScheduleId = rs.getInt("schedule_ID"); // Look at first number, set it as largest
                    System.out.println("First " + largestVolId);
                    while (rs.next()) { // Loop through all numbers after it
                        int storedVolId = rs.getInt("volID"); // Store the next number
                        int storedScheduleId = rs.getInt("schedule_ID"); // Store the next number
                        System.out.println(storedVolId);
                        System.out.println(storedScheduleId);
                        if (storedVolId > largestVolId) { // If that number is greater than the last largest
                            largestVolId = storedVolId; // It becomes the largest
                        }
                        if (storedScheduleId > largestScheduleId) {
                            largestScheduleId = storedScheduleId;
                        }
                    }
                    largestVolId += 1;

                    System.out.println("Final largest volID:" + largestVolId);
                    System.out.println("Final largest schedule_ID:" + largestScheduleId);
                }

            } catch (SQLException ex) {
                Logger.getLogger(Application2.class.getName()).log(Level.SEVERE, null, ex);
            }
            String query = "INSERT INTO VOLUNTEER(volid, vol_FirstName, vol_LastName, vol_Address, vol_dateofbirth, vol_info, vol_Email, vol_Phone, cumulativeHours, status, username, password, schedule_id)"
                    + "VALUES ('" + largestVolId + "', '" + fNameTxt.getText() + "', '" + lNameTxt.getText() + "', '"
                    + addressTxt.getText() + "', '" + dobTxt.getText() + "', '" + infoTxt.getText() + "', '"
                    + emailTxt.getText() + "', '" + phoneTxt.getText() + "', " + "0, 'applicant', '" + usernameTxt.getText() + "', '"
                    + passwordTxt.getText() + "', '" + largestScheduleId + "')";
            System.out.println(query);
            sendDBCommand(query);
            
            CreateAccount ca = new CreateAccount(this);

        });

        deny.setOnAction(e -> {
            String query = "DELETE FROM ANIMAL WHERE animal_ID = ";
            sendDBCommand(query);
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

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
