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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import oracle.jdbc.pool.OracleDataSource;
import tables.*;

/**
 * Displays the list of animals, both past and current
 */
public class AnimalList extends Login1 {

    Home home;

    Statement stmt;
    static Connection conn;
    ResultSet rs;

    TableView<Animal> currentAnimal = new TableView<>();
    TableView<Animal> pastAnimal = new TableView<>();
    ObservableList<Animal> currentTableData = FXCollections.observableArrayList();
    ObservableList<Animal> pastTableData = FXCollections.observableArrayList();

    // Current animal 
    TableColumn id_col = new TableColumn("animalID");
    TableColumn name_col = new TableColumn("name");
    TableColumn species_col = new TableColumn("species");
    TableColumn age_col = new TableColumn("age");
    TableColumn history_col = new TableColumn("medicalHistory");
    TableColumn feeding_col = new TableColumn("feedingNeeds");
    TableColumn vethist_col = new TableColumn("vetHistory");
    TableColumn adoptID_col = new TableColumn("adopt_ID");
    
    // Past animal
    TableColumn id_col2 = new TableColumn("animalID");
    TableColumn name_col2 = new TableColumn("name");
    TableColumn species_col2 = new TableColumn("species");
    TableColumn age_col2 = new TableColumn("age");
    TableColumn history_col2 = new TableColumn("medicalHistory");
    TableColumn feeding_col2 = new TableColumn("feedingNeeds");
    TableColumn vethist_col2 = new TableColumn("vetHistory");
    TableColumn adoptID_col2 = new TableColumn("adopt_ID");

    Label topLbl = new Label("Animal Information");
    Label idLbl = new Label("Animal ID");
    Label nameLbl = new Label("Name");
    Label speciesLbl = new Label("Species");
    Label ageLbl = new Label("Age");
    Label medicalLbl = new Label("Medical History");
    Label vetHistoryLbl = new Label("Vet History");
    Label feedingLbl = new Label("Feeding Needs");

    Label idTxt = new Label();
    TextField nameTxt = new TextField();
    TextField speciesTxt = new TextField();
    TextField ageTxt = new TextField();
    TextField medicalTxt = new TextField();
    ListView vetHistoryList = new ListView();
    TextField feedingTxt = new TextField();

    Label currentMessage = new Label();
    Label pastMessage = new Label();
    Label adopterName = new Label();
    TextField adopterTxt = new TextField();

    Button backBtn = new Button("Back");
    Button add = new Button("Add");
    Button delete = new Button("Delete");
    Button modify = new Button("Modify");
    Button currentPopulate = new Button("<-- Select and Populate");
    Button pastPopulate = new Button("<-- Select and Populate");
   // Button addMedical = new Button("Add");

    Image paw = new Image("file:paw.jpg");
    ImageView viewPaw = new ImageView(paw);

    TextArea medicalHistoryTxt = new TextArea();

    GridPane overall = new GridPane();
    GridPane modAnimal = new GridPane();
    GridPane curAnimalTable = new GridPane();
    GridPane pastAnimalTable = new GridPane();

    // Tabs for the menu
    Tab current = new Tab("Within BARK");
    Tab past = new Tab("Adopted Animals");
    TabPane tabPane = new TabPane();

    public AnimalList(Home home) {
        paneSettings(modAnimal);
        HBox.setHgrow(curAnimalTable, Priority.ALWAYS);
        VBox.setVgrow(curAnimalTable, Priority.ALWAYS);

        curAnimalTable.add(tabPane, 0, 0);

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
        modAnimal.add(medicalHistoryTxt, 1, 6, 1, 2);
        //modAnimal.add(medicalTxt, 1, 9);
//        modAnimal.add(vetHistoryLbl, 0, 8);
//        modAnimal.add(vetHistoryList, 1, 8);
        modAnimal.add(feedingLbl, 0, 10);
        modAnimal.add(feedingTxt, 1, 10);
       // medicalTxt.setPromptText("Add additional history");
       // modAnimal.add(addMedical, 0, 9);
       // addMedical.setAlignment(Pos.BASELINE_RIGHT);
        modAnimal.add(add, 0, 11);
        modAnimal.add(modify, 1, 11);
        modAnimal.add(delete, 0, 12);
        

        viewPaw.setFitHeight(50);
        viewPaw.setFitWidth(50);
        viewPaw.setX(100);
        viewPaw.setY(150);
        modAnimal.add(viewPaw, 0, 13);

        // Set observable lists to tableviews
        currentAnimal.setItems(currentTableData);
        pastAnimal.setItems(pastTableData); 

        // Populate the grids with labels and tables
        curAnimalTable.add(currentMessage, 0, 0);
        curAnimalTable.add(currentAnimal, 0, 1);
        curAnimalTable.add(currentPopulate, 0, 2);
        pastAnimalTable.add(pastMessage, 0, 0);
        pastAnimalTable.add(pastAnimal, 0, 1);
        pastAnimalTable.add(pastPopulate, 0, 2);

        // Set the content to the observable list
        past.setContent(pastAnimalTable);
        current.setContent(curAnimalTable);
        tabPane.getTabs().addAll(current, past);

        // Set cell values for current
        id_col.setCellValueFactory(new PropertyValueFactory<Volunteer, Integer>("animalID"));
        name_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("name"));
        species_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("species"));
        age_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("age"));
        history_col.setCellValueFactory(new PropertyValueFactory<Volunteer, Date>("medicalHistory"));
        feeding_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("feedingNeeds"));
     //   vethist_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("vetHistory"));
        adoptID_col.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("adopt_ID"));

        // Set cell values for past
        id_col2.setCellValueFactory(new PropertyValueFactory<Volunteer, Integer>("animalID"));
        name_col2.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("name"));
        species_col2.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("species"));
        age_col2.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("age"));
        history_col2.setCellValueFactory(new PropertyValueFactory<Volunteer, Date>("medicalHistory"));
        feeding_col2.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("feedingNeeds"));
      //  vethist_col2.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("vetHistory"));
        adoptID_col2.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("adopt_ID"));

        currentAnimal.getColumns().addAll(id_col, name_col, species_col, age_col, history_col, feeding_col, vethist_col, adoptID_col);
        pastAnimal.getColumns().addAll(id_col2, name_col2, species_col2, age_col2, history_col2, feeding_col2, vethist_col2, adoptID_col2);

        ArrayList<Animal> curAnimalList = new ArrayList<>();
        ArrayList<Animal> pastAnimalList = new ArrayList<>();

        // Populate current animals table
        sendDBCommand("SELECT animal_ID, name, species, age, medicalHistory, feedingNeeds, vetHistory, adopt_ID FROM Animal WHERE adopt_ID IS NULL");
        try {
            while (rs.next()) {
                curAnimalList.add(new Animal(rs.getInt("animal_ID"), rs.getString("name"), rs.getString("species"), rs.getInt("age"), rs.getString("medicalHistory"), rs.getString("feedingNeeds"), rs.getString("vetHistory"), rs.getInt("adopt_ID")));
            }
            for (Animal x : curAnimalList) {
                currentTableData.add(x);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception! " + e);
        }

        // Populate past animals table
        sendDBCommand("SELECT animal_ID, name, species, age, medicalHistory, feedingNeeds, vetHistory, adopt_ID FROM Animal WHERE adopt_ID IS NOT NULL");
        try {
            while (rs.next()) {
                pastAnimalList.add(new Animal(rs.getInt("animal_ID"), rs.getString("name"), rs.getString("species"), rs.getInt("age"), rs.getString("medicalHistory"), rs.getString("feedingNeeds"), rs.getString("vetHistory"), rs.getInt("adopt_ID")));
            }
            for (Animal x : pastAnimalList) {
                pastTableData.add(x);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception! " + e);
        }

        overall.add(modAnimal, 0, 0);
        overall.add(tabPane, 1, 0);
        Scene primaryScene = new Scene(overall, modAnimal.getWidth(), 550);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("List");
        primaryStage.show();

        // Populate left pane
        currentPopulate.setOnAction(e -> {
            vetHistoryList.getItems().clear();
            idTxt.setText(currentAnimal.getSelectionModel().getSelectedItem().getAnimalID() + "");
            nameTxt.setText(currentAnimal.getSelectionModel().getSelectedItem().getName() + "");
            speciesTxt.setText(currentAnimal.getSelectionModel().getSelectedItem().getSpecies() + "");
            ageTxt.setText(currentAnimal.getSelectionModel().getSelectedItem().getAge() + "");
            medicalHistoryTxt.setText(currentAnimal.getSelectionModel().getSelectedItem().getMedicalHistory() + "");
            feedingTxt.setText(currentAnimal.getSelectionModel().getSelectedItem().getFeedingNeeds() + "");
            
            sendDBCommand("select animal.animal_ID, eventID, description, workDate from VETHISTORY "
                    + "INNER JOIN ANIMAL ON vethistory.animal_id = animal.animal_ID where animal.animal_id = " + currentAnimal.getSelectionModel().getSelectedItem().getAnimalID() + "");
            try {
                while(rs.next()) {
                    vetHistoryList.getItems().add(rs.getString("workDate") + " " + rs.getString("description"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(AnimalList.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // Populate left pane for past animals
        pastPopulate.setOnAction(e -> {
            idTxt.setText(pastAnimal.getSelectionModel().getSelectedItem().getAnimalID() + "");
            nameTxt.setText(pastAnimal.getSelectionModel().getSelectedItem().getName() + "");
            speciesTxt.setText(pastAnimal.getSelectionModel().getSelectedItem().getSpecies() + "");
            ageTxt.setText(pastAnimal.getSelectionModel().getSelectedItem().getAge() + "");
            medicalHistoryTxt.setText(pastAnimal.getSelectionModel().getSelectedItem().getMedicalHistory() + "");
            feedingTxt.setText(pastAnimal.getSelectionModel().getSelectedItem().getFeedingNeeds() + "");

        });

        // Delete entry
        delete.setOnAction(e -> {
            System.out.println(currentAnimal.getSelectionModel().getSelectedItem().getAnimalID());
            String query = "DELETE FROM ANIMAL WHERE animal_ID = " + currentAnimal.getSelectionModel().getSelectedItem().getAnimalID();
            sendDBCommand(query);
            System.out.println(currentAnimal.getSelectionModel().getSelectedIndex());
            currentMessage.setText("Entry removed successfully.");
            int x = currentAnimal.getSelectionModel().getSelectedIndex();
            curAnimalList.remove(x);
            currentTableData.clear();
            for (Animal y : curAnimalList) {
                currentTableData.add(y);
            }
        });

        // Add entry
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
                String newHistory = medicalHistoryTxt.getText();
                String newFeeding = feedingTxt.getText();

                //String newVetHistory = MISSINGFIELDFORTHIS.getText(); 
                String newVetHistory = "";
                int newAdoptID = largest + 1;

                //Create new animal in the animalList array
                Animal newAnimal = new Animal(newID, newName, newSpecies, newAge,
                        newHistory, newFeeding, newVetHistory, newAdoptID);
                System.out.println("NEW ANIMAL: " + newAnimal.toString());
                String query = "INSERT INTO ANIMAL(animal_ID, name,"
                        + "species, age, medicalHistory, feedingNeeds, eventID, volID)"
                        + "VALUES (" + newID + ", '" + newName + "', '" + newSpecies + "', " + newAge + ", '" + newHistory + "', '" + newFeeding + ")";

                sendDBCommand(query);
                currentMessage.setText("Entry added successfully.");
                currentTableData.clear();

                curAnimalList.add(newAnimal);
                System.out.println("ANIMAL LIST: " + curAnimalList);
                for (Animal x : curAnimalList) {
                    currentTableData.add(x);
                }
            } catch (Exception ex) {
                System.out.println("Error! " + ex);
                currentMessage.setText("Error while adding entry. Please check your input and try again.");
            }
        });

        // Modify entry
        modify.setOnAction(e -> {
            int newID = Integer.valueOf(idTxt.getText());
            String newName = nameTxt.getText();
            String newSpecies = speciesTxt.getText();
            int newAge = Integer.valueOf(ageTxt.getText());
            String newHistory = medicalHistoryTxt.getText();
            String newFeeding = feedingTxt.getText();
            String newVetHistory = "";

            String query = "UPDATE ANIMAL SET name = '" + newName + "', species = '" + newSpecies + "', age = " + newAge + ", medicalHistory = '" + newHistory + "', feedingNeeds = '" + newFeeding + " WHERE animal_ID = " + newID + "";
            sendDBCommand(query);
            for (int i = 0; i < curAnimalList.size(); i++) {
                if (curAnimalList.get(i).getAnimalID() == Integer.valueOf(idTxt.getText())) {
                    curAnimalList.get(i).setAnimalID(newID);
                    curAnimalList.get(i).setName(newName);
                    curAnimalList.get(i).setSpecies(newSpecies);
                    curAnimalList.get(i).setAge(newAge);
                    curAnimalList.get(i).setMedicalHistory(newHistory);
                    curAnimalList.get(i).setFeedingNeeds(newFeeding);
                    curAnimalList.get(i).setVetHistory(newVetHistory);
                    break;
                }
            }
            currentMessage.setText("Modify entry successful!");
            currentTableData.clear();
            for (Animal x : curAnimalList) {
                currentTableData.add(x);
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
