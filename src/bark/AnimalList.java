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
    Button add = new Button("Add");
    Button delete = new Button("Delete");
    Button modify = new Button("Modify");

    GridPane overall = new GridPane();
    GridPane modAnimal = new GridPane();
    GridPane animalTable = new GridPane();

    public AnimalList(Home home) {
        paneSettings(modAnimal);
        modAnimal.add(backBtn, 0, 0);
        modAnimal.add(topLbl, 1, 1);
        modAnimal.add(idLbl, 0, 2);
        modAnimal.add(idTxt, 1, 2);
        modAnimal.add(nameLbl, 0, 3);
        modAnimal.add(nameTxt, 1, 3);
        modAnimal.add(speciesLbl, 0, 4);
        modAnimal.add(speciesTxt, 1, 4);
        modAnimal.add(ageLbl, 0, 5);
        modAnimal.add(ageTxt, 1, 5);
        modAnimal.add(medicalLbl, 0, 6);
        modAnimal.add(medicalTxt, 1, 6);
        modAnimal.add(feedingLbl, 0, 7);
        modAnimal.add(feedingTxt, 1, 7);
        modAnimal.add(eventIdLbl, 0, 8);
        modAnimal.add(eventIdTxt, 1, 8);
        modAnimal.add(volIdLbl, 0, 9);
        modAnimal.add(volIdTxt, 1, 9);
        modAnimal.add(add, 0, 10);
        modAnimal.add(modify, 1, 10);
        modAnimal.add(delete, 0, 11);

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

        overall.add(modAnimal, 0, 0);
        overall.add(animalTable, 1, 0);
        Scene primaryScene = new Scene(overall, 1000, 550);
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
