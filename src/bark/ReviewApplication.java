
package bark;

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
 * This is for admins to review all submitted applications and either accept or deny them
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

    // FX Controls - Textfields, comboboxes, etc.
    TextField fNameTxt = new TextField();
    TextField lNameTxt = new TextField();
    TextField addressTxt = new TextField();
    TextArea infoTxt = new TextArea();
    TextField emailTxt = new TextField();
    TextField phoneTxt = new TextField();
    ComboBox<String> expCb = new ComboBox();
    Button submitBtn = new Button("Approve");
    Button denyBtn = new Button("Deny");
    
    Image paw = new Image("file:paw.jpg");
    ImageView viewPaw = new ImageView(paw);

    GridPane pane1 = new GridPane();

    ReviewApplication(Login1 login) {

        this.login = login;
        paneSettings(pane1);
        //pane1.add(title, 1,0);
//        pane1.add(description1, 1, 1);
//        pane1.add(description2, 1, 2);
        pane1.add(fNameLbl, 0, 1);
        pane1.add(fNameTxt, 1, 1);
        pane1.add(lNameLbl, 0, 2);
        pane1.add(lNameTxt, 1, 2);
        
        pane1.add(addressLbl, 0, 3); 
        pane1.add(addressTxt, 1, 3); 
        pane1.add(emailLbl, 0, 4); 
        pane1.add(emailTxt, 1, 4); 
        
        
        pane1.add(phoneLbl, 0, 5);
        pane1.add(phoneTxt, 1, 5);
        pane1.add(expLbl, 0, 6);
        pane1.add(expCb, 1, 6);
        pane1.add(infoLbl, 0, 7);
        pane1.add(infoTxt, 1, 7);
        
        pane1.add(submitBtn, 1, 8);
        pane1.add(denyBtn, 1, 9);
        
        viewPaw.setFitHeight(50);
        viewPaw.setFitWidth(50);
        viewPaw.setX(100);
        viewPaw.setY(150);
        pane1.add(viewPaw, 3, 9);

        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(pane1, pane1.getMaxWidth(), pane1.getMaxHeight());
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Review Application");
        primaryStage.show();
        
        // DB Connectivity
        String query = "select* from volunteer where status = 'applicant'";
        sendDBCommand(query);
        //System.out.println(rs);
        try {
            while (rs.next()) {
                fNameTxt.setText(rs.getString("vol_firstname"));
                lNameTxt.setText(rs.getString("vol_lastname"));
                addressTxt.setText(rs.getString("vol_address"));
                emailTxt.setText(rs.getString("vol_email"));  
                phoneTxt.setText(rs.getString("vol_phone"));  
                // experience..??
                infoTxt.setText(rs.getString("vol_info"));
            }
        } catch (Exception e) {
            
        }
        
        
    }
    

}

