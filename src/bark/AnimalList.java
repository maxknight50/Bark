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
public class AnimalList extends Login1 {

    Home home;

    Statement stmt;
    static Connection conn;
    ResultSet rs;

    TableView<Animal> volTable = new TableView<>();
    ObservableList<Animal> tableData = FXCollections.observableArrayList();

    TableColumn id_col = new TableColumn("animalID");
    TableColumn name_col = new TableColumn("name");
    TableColumn species_col = new TableColumn("species");
    TableColumn age_col = new TableColumn("age");
    TableColumn history_col = new TableColumn("medicalHistory");
    TableColumn feeding_col = new TableColumn("feedingNeeds");
    TableColumn vethist_col = new TableColumn("vetHistory");
    TableColumn event_col = new TableColumn("eventID");
    TableColumn volID_col = new TableColumn("volID");

    Label topLbl = new Label("Animal Information");
    Label idLbl = new Label("Animal ID");
    Label nameLbl = new Label("Name");
    Label speciesLbl = new Label("Species");
    Label ageLbl = new Label("Age");
    Label medicalLbl = new Label("Medical History");
    Label feedingLbl = new Label("Feeding Needs");
    Label eventIdLbl = new Label("Event ID");
    Label volIdLbl = new Label("Volunteer ID");

    TextField idTxt = new TextField();
    TextField nameTxt = new TextField();
    TextField speciesTxt = new TextField();
    TextField ageTxt = new TextField();
    TextField medicalTxt = new TextField();
    TextField feedingTxt = new TextField();
    TextField eventIdTxt = new TextField();
    TextField volIdTxt = new TextField();

    Button backBtn = new Button("Back");

    GridPane overall = new GridPane();
    GridPane animalAdd = new GridPane();
    GridPane animalTable = new GridPane();

    public AnimalList(Home home) {
        paneSettings(animalAdd);
        animalAdd.add(backBtn, 0, 0);
        animalAdd.add(topLbl, 1, 1);
        animalAdd.add(idLbl, 0, 2);
        animalAdd.add(idTxt, 1, 2);
        animalAdd.add(nameLbl, 0, 3);
        animalAdd.add(nameTxt, 1, 3);
        animalAdd.add(speciesLbl, 0, 4);
        animalAdd.add(speciesTxt, 1, 4);
        animalAdd.add(ageLbl, 0, 5);
        animalAdd.add(ageTxt, 1, 5);
        animalAdd.add(medicalLbl, 0, 6);
        animalAdd.add(medicalTxt, 1, 6);
        animalAdd.add(feedingLbl, 0, 7);
        animalAdd.add(feedingTxt, 1, 7);
        animalAdd.add(eventIdLbl, 0, 8);
        animalAdd.add(eventIdTxt, 1, 8);
        animalAdd.add(volIdLbl, 0, 9);
        animalAdd.add(volIdTxt, 1, 9);

        volTable.setItems(tableData);
        animalTable.add(volTable, 0, 0);

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
        try {
            Animal[] animalList = new Animal[25];
            for (int i = 0; i < 100; i++) {
                while (rs.next()) {
                    if (rs != null) {
                        animalList[i] = new Animal(rs.getInt("animal_ID"), rs.getString("name"), rs.getString("species"), rs.getInt("age"), rs.getString("medicalHistory"), rs.getString("feedingNeeds"), rs.getString("vetHistory"), rs.getInt("eventID"), rs.getInt("volID"));
                        break;
                    }
                }
            }
            for (Animal x : animalList) {
                tableData.add(x);
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception! " + e);
        }

        overall.add(animalAdd, 0, 0);
        overall.add(animalTable, 1, 0);
        Scene primaryScene = new Scene(overall, 1000, 450);
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

        } catch (SQLException e) {
            System.out.println(e.toString());
            //Let me commit!
        }
    }
}
