package bark;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import java.lang.Object.*;
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

    Label idTxt = new Label();
    TextField nameTxt = new TextField();
    TextField speciesTxt = new TextField();
    TextField ageTxt = new TextField();
    TextField medicalTxt = new TextField();
    TextField feedingTxt = new TextField();
    TextField eventIdTxt = new TextField();
    TextField volIdTxt = new TextField();

    Label message = new Label();

    Button backBtn = new Button("Back");
    Button add = new Button("Add");
    Button delete = new Button("Delete");
    Button modify = new Button("Modify");
    Button populate = new Button("<-- Select and Populate");

    Image paw = new Image("file:paw.jpg");
    ImageView viewPaw = new ImageView(paw);

    ComboBox<String> vetHistoryCB = new ComboBox();

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
        modAnimal.add(vetHistoryCB, 1, 6);
        modAnimal.add(feedingLbl, 0, 7);
        modAnimal.add(feedingTxt, 1, 7);
        modAnimal.add(eventIdLbl, 0, 8);
        modAnimal.add(eventIdTxt, 1, 8);
        modAnimal.add(volIdLbl, 0, 9);
        modAnimal.add(volIdTxt, 1, 9);
        modAnimal.add(add, 0, 10);
        modAnimal.add(modify, 1, 10);
        modAnimal.add(delete, 0, 11);

        viewPaw.setFitHeight(50);
        viewPaw.setFitWidth(50);
        viewPaw.setX(100);
        viewPaw.setY(150);
        modAnimal.add(viewPaw, 0, 12);

        volTable.setItems(tableData);
        animalTable.add(message, 0, 0);
        animalTable.add(volTable, 0, 1);
        animalTable.add(populate, 0, 2);

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
        //Animal[] animalList = new Animal[25];
        ArrayList<Animal> animalList = new ArrayList<>();

        sendDBCommand("SELECT animal_ID, name, species, age, medicalHistory, feedingNeeds, vetHistory, eventID, volID FROM Animal");
        try {
            for (int i = 0; i < 100; i++) {
                while (rs.next()) {
                    if (rs != null) {
                        //animalList[i] = new Animal(rs.getInt("animal_ID"), rs.getString("name"), rs.getString("species"), rs.getInt("age"), rs.getString("medicalHistory"), rs.getString("feedingNeeds"), rs.getString("vetHistory"), rs.getInt("eventID"), rs.getInt("volID"));
                        animalList.add(new Animal(rs.getInt("animal_ID"), rs.getString("name"), rs.getString("species"), rs.getInt("age"), rs.getString("medicalHistory"), rs.getString("feedingNeeds"), rs.getString("vetHistory"), rs.getInt("eventID"), rs.getInt("volID")));

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

        populate.setOnAction(e -> {
//            System.out.println(volTable.getSelectionModel().getSelectedIndex());
//            System.out.println(volTable.getSelectionModel().getSelectedItem().getAge());
            idTxt.setText(volTable.getSelectionModel().getSelectedItem().getAnimalID() + "");
            nameTxt.setText(volTable.getSelectionModel().getSelectedItem().getName() + "");
            speciesTxt.setText(volTable.getSelectionModel().getSelectedItem().getSpecies() + "");
            ageTxt.setText(volTable.getSelectionModel().getSelectedItem().getAge() + "");
            medicalTxt.setText(volTable.getSelectionModel().getSelectedItem().getMedicalHistory() + "");
            feedingTxt.setText(volTable.getSelectionModel().getSelectedItem().getFeedingNeeds() + "");
            eventIdTxt.setText(volTable.getSelectionModel().getSelectedItem().getEventID() + "");
            volIdTxt.setText(volTable.getSelectionModel().getSelectedItem().getVolID() + "");

        });

        delete.setOnAction(e -> {
            System.out.println(volTable.getSelectionModel().getSelectedItem().getAnimalID());
            String query = "DELETE FROM ANIMAL WHERE animal_ID = " + volTable.getSelectionModel().getSelectedItem().getAnimalID();
            sendDBCommand(query);
            System.out.println(volTable.getSelectionModel().getSelectedIndex());
            message.setText("Entry removed successfully.");
            int x = volTable.getSelectionModel().getSelectedIndex();
            animalList.remove(x);
            tableData.clear();
            for (Animal y : animalList) {
                tableData.add(y);
            }
        });

        add.setOnAction(e -> {
            int largest = 0;
            String q = "SELECT * FROM ANIMAL";
            sendDBCommand(q);
            try {
                while (rs.next()) {
                    largest = rs.getInt("animal_ID");
                    System.out.println("First: " + largest);
                    while (rs.next()) {
                        int store = rs.getInt("animal_ID");
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
            try {
                int newID = largest + 1;
                String newName = nameTxt.getText();
                String newSpecies = speciesTxt.getText();
                int newAge = Integer.valueOf(ageTxt.getText());
                String newHistory = medicalTxt.getText();
                String newFeeding = feedingTxt.getText();

                //String newVetHistory = MISSINGFIELDFORTHIS.getText(); 
                String newVetHistory = "";

                int newEventID = Integer.valueOf(eventIdTxt.getText());
                int newVolID = Integer.valueOf(volIdTxt.getText());

                //Create new animal in the animalList array
                Animal newAnimal = new Animal(newID, newName, newSpecies, newAge,
                        newHistory, newFeeding, newVetHistory, newEventID, newVolID);
                System.out.println("NEW ANIMAL: " + newAnimal.toString()); 
                String query = "INSERT INTO ANIMAL(animal_ID, name,"
                        + "species, age, medicalHistory, feedingNeeds, eventID, volID)"
                        + "VALUES (" + newID + ", '" + newName + "', '" + newSpecies + "', " + newAge + ", '" + newHistory + "', '" + newFeeding + "', " + newEventID + ", " + newVolID + ")";

                sendDBCommand(query);
                message.setText("Entry added successfully.");
                tableData.clear();
                for (int i = 0; i < animalList.size() + 1; i++) {
                    if (animalList.get(i) == null) {
                        animalList.add(newAnimal);
                        break;
                    }
                } 
                System.out.println("ANIMAL LIST: " + animalList); 
                for (Animal x : animalList) {
                    tableData.add(x);
                }
            } catch (Exception ex) {
                System.out.println("Error! " + ex);
                message.setText("Error while adding entry. Please check your input and try again.");
            }
        });

        modify.setOnAction(e -> {
            int newID = Integer.valueOf(idTxt.getText());
            String newName = nameTxt.getText();
            String newSpecies = speciesTxt.getText();
            int newAge = Integer.valueOf(ageTxt.getText());
            String newHistory = medicalTxt.getText();
            String newFeeding = feedingTxt.getText();
            String newVetHistory = "";
            int newEventID = Integer.valueOf(eventIdTxt.getText());
            int newVolID = Integer.valueOf(volIdTxt.getText());

            String query = "UPDATE ANIMAL SET name = '" + newName + "', species = '" + newSpecies + "', age = " + newAge + ", medicalHistory = '" + newHistory
                    + "', feedingNeeds = '" + newFeeding + "', eventID = " + newEventID + ", volID = " + newVolID + " WHERE animal_ID = " + newID + "";
            sendDBCommand(query);
            for (int i = 0; i < animalList.size(); i++) {
                if (animalList.get(i).getAnimalID() == Integer.valueOf(idTxt.getText())) {
                    animalList.get(i).setAnimalID(newID);
                    animalList.get(i).setName(newName);
                    animalList.get(i).setSpecies(newSpecies);
                    animalList.get(i).setAge(newAge);
                    animalList.get(i).setMedicalHistory(newHistory);
                    animalList.get(i).setFeedingNeeds(newFeeding);
                    animalList.get(i).setVetHistory(newVetHistory);
                    animalList.get(i).setEventID(newEventID);
                    animalList.get(i).setVolID(newVolID);
                    break;
                }
            }
            message.setText("Modify entry successful!");
            tableData.clear();
            for (Animal x : animalList) {
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

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
