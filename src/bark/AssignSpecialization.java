package bark;

//import static bark.VolunteerStatus.conn;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * Assign Specialization screen
 */
public class AssignSpecialization extends Login1 {

    Home home;

    // add title and back button
    Label volNameLbl = new Label("Volunteer Name");
    TextField volNameTxt = new TextField();
    Label specialMenuLbl = new Label("Specialization Menu");
    ComboBox<String> specialMenuCb;
    Label currentSpecialLbl = new Label("Current Specializations: ");
    ListView currentList = new ListView();
    TextField newSpecialTxt = new TextField();
    Button add = new Button("Add");
    Button delete = new Button("Delete");
    Button create = new Button("Create New Specialization");
    
    Image paw = new Image("file:paw.jpg");
    ImageView viewPaw = new ImageView(paw);

    GridPane specialPane = new GridPane();
    
    ObservableList<String> specialization = FXCollections.observableArrayList();
    
    Statement stmt;
    static Connection conn;
    ResultSet rs;

    AssignSpecialization(Home home) {
        this.home = home;
        String[] defaultList = {"Feeding", "Enclosure Care", "Adopter Relations", "Event Volunteer", "Training", "Fundraising"}; 
        specialization.addAll(defaultList);
        //String specialization[] = {"Feeding", "Enclosure Care", "Adopter Relations", "Event Volunteer", "Training", "Fundraising"};
        specialMenuCb = new ComboBox(FXCollections.observableArrayList(specialization));

        // add nodes to pane
        specialPane.add(volNameLbl, 0, 0);
        specialPane.add(volNameTxt, 0, 1);
        specialPane.add(specialMenuLbl, 0, 2);
        specialPane.add(specialMenuCb, 0, 3);
        specialPane.add(currentSpecialLbl, 0, 4);
        specialPane.add(currentList, 0, 5, 3, 2);
        specialPane.add(newSpecialTxt, 0, 7);
        specialPane.add(create, 0, 8);
        specialPane.add(add, 3, 3);
        specialPane.add(delete, 3, 6);
        currentList.setPrefWidth(150);
        currentList.setPrefHeight(200);
        currentList.setMinSize(200.0, Control.USE_PREF_SIZE);
        currentList.setMaxSize(200.0, Control.USE_PREF_SIZE);
        
        
        // DB connectivity
        sendDBCommand("select * from volunteer");
        String fullName = "";
        try {
            if (rs.next()) {
                fullName = rs.getString("vol_firstname") + " " + rs.getString("vol_lastname");
                volNameTxt.setText(fullName);
            }
        } catch (SQLException e) {
            System.out.println("Error- " + e.toString());
        }
        
        
        viewPaw.setFitHeight(50);
        viewPaw.setFitWidth(50);
        viewPaw.setX(100);
        viewPaw.setY(200);
        specialPane.add(viewPaw, 2, 8);
        
        paneSettings(specialPane);

        Stage primaryStage = new Stage();
        Scene primaryScene = new Scene(specialPane, 700, 600);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Specialization Assignment");
        primaryStage.show();
        
        // Lambda add
        add.setOnAction(e -> {
            currentList.getItems().add(specialMenuCb.getSelectionModel().getSelectedItem());
        });
        
        // Lambda delete
        delete.setOnAction(e -> {
            int indxSelected = currentList.getSelectionModel().getSelectedIndex();
            currentList.getItems().remove(indxSelected);
        });
        // Lambda create
        create.setOnAction(e -> {
            currentList.getItems().add(newSpecialTxt.getText());
        });
    }
    
    @Override
    public void paneSettings(GridPane pane) {
        pane = specialPane;
        // Add spacing
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);
        pane.setAlignment(Pos.CENTER);
    }
    
    @Override
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
