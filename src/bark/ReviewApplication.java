package bark;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * This is for admins to review all submitted applications and either accept or
 * deny them
 */
public class ReviewApplication extends Login1 {

    Login1 login;

    // FX Controls - Labels
    //Label title = new Label("Application"); ***Find JAVAFX Node/control that isnt Label for title
    Label fNameLbl = new Label("First Name");
    Label lNameLbl = new Label("Last Name");
    Label addressLbl = new Label("Address");
    Label infoLbl = new Label("Tell us about yourself!");
    Label emailLbl = new Label("Email");
    Label phoneLbl = new Label("Phone");
    Label expLbl = new Label("Experience");
    Label selectNameLbl = new Label("Select Applicant: ");

    // FX Controls - Textfields, comboboxes, etc.
    TextField fNameTxt = new TextField();
    TextField lNameTxt = new TextField();
    TextField addressTxt = new TextField();
    TextArea infoTxt = new TextArea();
    TextField emailTxt = new TextField();
    TextField phoneTxt = new TextField();
    TextField expTxt = new TextField();
    Button approveBtn = new Button("Approve");
    Button denyBtn = new Button("Deny");
    ComboBox<String> selectIDCb = new ComboBox();

    ObservableList<String> idList = FXCollections.observableArrayList();

    Image paw = new Image("file:paw.jpg");
    ImageView viewPaw = new ImageView(paw);

    GridPane pane1 = new GridPane();

    ReviewApplication(Login1 login) {

        this.login = login;
        paneSettings(pane1);
        //pane1.add(title, 1,0);
//        pane1.add(description1, 1, 1);
//        pane1.add(description2, 1, 2);
        pane1.add(selectIDCb, 1, 1);
        pane1.add(selectNameLbl, 0, 1);
        pane1.add(fNameLbl, 0, 2);
        pane1.add(fNameTxt, 1, 2);
        pane1.add(lNameLbl, 0, 3);
        pane1.add(lNameTxt, 1, 3);

        pane1.add(addressLbl, 0, 4);
        pane1.add(addressTxt, 1, 4);
        pane1.add(emailLbl, 0, 5);
        pane1.add(emailTxt, 1, 5);

        pane1.add(phoneLbl, 0, 6);
        pane1.add(phoneTxt, 1, 6);
        pane1.add(expLbl, 0, 7);
        pane1.add(expTxt, 1, 7);
        pane1.add(infoLbl, 0, 8);
        pane1.add(infoTxt, 1, 8);

        pane1.add(approveBtn, 1, 9);
        pane1.add(denyBtn, 1, 10);

        viewPaw.setFitHeight(50);
        viewPaw.setFitWidth(50);
        viewPaw.setX(100);
        viewPaw.setY(150);
        pane1.add(viewPaw, 3, 10);
        
        fNameTxt.setEditable(false);
        lNameTxt.setEditable(false);
        addressTxt.setEditable(false);
        emailTxt.setEditable(false);
        phoneTxt.setEditable(false);
        infoTxt.setEditable(false);

        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(pane1, pane1.getMaxWidth(), pane1.getMaxHeight());
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Review Application");
        primaryStage.show();

        // DB Connectivity
        String query = "select * from volunteer where status = 'applicant'";
        sendDBCommand(query);
        System.out.println(rs);
        //System.out.println(rs);
        try {
            while (rs.next()) {
                idList.add(rs.getString("volid"));
            }
        } catch (Exception e) {

        }
        selectIDCb.setItems(idList);

        selectIDCb.setOnAction(e -> {
            fillFields(selectIDCb.getSelectionModel().getSelectedItem());
        });
        approveBtn.setOnAction(e ->{
            String update = "update volunteer set status = 'volunteer in training' "
                    + "where volid = " + selectIDCb.getSelectionModel().getSelectedItem();
            sendDBCommand(update);
            idList.remove(selectIDCb.getSelectionModel().getSelectedIndex());
            fNameTxt.clear();
            lNameTxt.clear();
            addressTxt.clear();
            emailTxt.clear();
            phoneTxt.clear();
            infoTxt.clear();
        });
        denyBtn.setOnAction(e -> {
            String delete = "delete from volunteer where volid = " + selectIDCb.getSelectionModel().getSelectedItem();
            sendDBCommand(delete);
            idList.remove(selectIDCb.getSelectionModel().getSelectedIndex());
            fNameTxt.clear();
            lNameTxt.clear();
            addressTxt.clear();
            emailTxt.clear();
            phoneTxt.clear();
            infoTxt.clear();
        });
    }

    public void fillFields(String id) {
        id = selectIDCb.getSelectionModel().getSelectedItem();
        String query = "select * from volunteer where volid = " + id + "";
//        query += id + "'";
        sendDBCommand(query);
        try {
            while (rs.next()) {
                System.out.println("First name: " + rs.getString("vol_firstname"));
                fNameTxt.setText(rs.getString("vol_firstname"));
                lNameTxt.setText(rs.getString("vol_lastname"));
                addressTxt.setText(rs.getString("vol_address"));
                emailTxt.setText(rs.getString("vol_email"));
                phoneTxt.setText(rs.getString("vol_phone"));
                infoTxt.setText(rs.getString("vol_info"));
            }
        } catch (SQLException sql) {
            System.out.println(sql);
        }

    }

}
