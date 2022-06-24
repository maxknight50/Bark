package bark;

import static bark.Login1.conn;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.sql.Date; 
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;
import tables.*;

/**
 *
 */
public class DonationList extends Login1 {

    Home home;

    Statement stmt;
    static Connection conn;
    ResultSet rs;

    TableView<Donation1> donTable = new TableView<>();
    ObservableList<Donation1> tableData = FXCollections.observableArrayList();

    TableColumn id_col = new TableColumn("Donation ID");
    TableColumn amountColumn = new TableColumn("Amount");
    TableColumn nameColumn = new TableColumn("Donor Name");
    TableColumn dateColumn = new TableColumn("Date");
    TableColumn volIdColumn = new TableColumn("VolunteerID");
    //MenuBar menuBar = new MenuBar();
    //Menu menuDonations = new Menu("Donations");
    //MenuItem enterDonations = new MenuItem("Enter Donations...");

    Label donoID = new Label("Donation ID");
    Label donorName = new Label("Donor Name");
    Label donoAmount = new Label("Donation Amount");
    Label donoDate = new Label("Donation Date");

    TextField idField = new TextField();
    TextField nameField = new TextField();
    TextField amountField = new TextField();
    TextField dateField = new TextField();
    Label message = new Label();

    Button backBtn = new Button("Back");
    Button add = new Button("Add");
    Button delete = new Button("Delete");
    Button modify = new Button("Modify");
    Button populate = new Button("<-- Select and Populate");

    GridPane table = new GridPane();
    GridPane buttons = new GridPane();
    GridPane overall = new GridPane();

    public DonationList(Home home) {
        paneSettings(buttons);

        table.add(message, 0, 0);
        table.add(donTable, 0, 1);
        table.add(populate, 0, 2);

        buttons.add(backBtn, 0, 0);
        buttons.add(donoID, 0, 1);
        buttons.add(idField, 1, 1);
        buttons.add(donorName, 0, 2);
        buttons.add(nameField, 1, 2);
        buttons.add(donoAmount, 0, 3);
        buttons.add(amountField, 1, 3);
        buttons.add(donoDate, 0, 4);
        buttons.add(dateField, 1, 4);
        buttons.add(add, 0, 5);
        buttons.add(delete, 1, 5);
        buttons.add(modify, 0, 6);

        viewPaw.setFitHeight(50);
        viewPaw.setFitWidth(50);
        viewPaw.setX(100);
        viewPaw.setY(150);
        table.add(viewPaw, 2, 9);

        donTable.setItems(tableData);

        id_col.setCellValueFactory(new PropertyValueFactory<Donation1, Integer>("donationID"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Donation1, String>("donationAmt"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Donation1, String>("donationName"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Donation1, Date>("donationDate"));

        donTable.getColumns().addAll(id_col, amountColumn, nameColumn, dateColumn, volIdColumn);
        ArrayList<Donation1> donList = new ArrayList<>();

        sendDBCommand("SELECT donation_ID, donationAmt, donationName, donationDate, volID FROM Donation");
        try {
            for (int i = 0; i < 100; i++) {
                while (rs.next()) {
                    if (rs != null) {
                        donList.add(new Donation1(rs.getInt("donation_ID"), rs.getString("donationAmt"),
                                rs.getString("donationName"), rs.getString("donationDate"), rs.getInt("volID")));
                        break;
                    }
                    System.out.println("ID: " + donList.get(i).getDonationID()); 
                }
            }
            for (Donation1 x : donList) {
                tableData.add(x);
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception! " + e);
        }
        //menuDonations.getItems().add(enterDonations);
        //menuBar.getMenus().addAll(menuDonations);
        //tPane1.add(menuBar, 0, 0);

        overall.add(buttons, 0, 0);
        overall.add(table, 1, 0);

        Scene primaryScene = new Scene(overall, 1000, 550);
        donTable.setMinWidth(600);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Donation List");
        primaryStage.show();

        delete.setOnAction(e -> {
            String query = "DELETE FROM DONATION WHERE donation_ID =  " + donTable.getSelectionModel().getSelectedItem().getDonationID();
            sendDBCommand(query);
            //message.setText("Entry removed successfully."); 
            int x = donTable.getSelectionModel().getSelectedIndex();
            donList.remove(x);
            tableData.clear();
            for (Donation1 a : donList) {
                tableData.add(a);
            }
        });

        populate.setOnAction(e -> {
            idField.setText(donTable.getSelectionModel().getSelectedItem().getDonationID() + ""); 
            nameField.setText(donTable.getSelectionModel().getSelectedItem().getDonationName());
            amountField.setText(donTable.getSelectionModel().getSelectedItem().getDonationAmt());
            dateField.setText(donTable.getSelectionModel().getSelectedItem().getDonationDate());
        });

        modify.setOnAction(e -> {
            int newID = Integer.valueOf(idField.getText()); 
            String newName = nameField.getText();
            String newAmount = amountField.getText();
            String newDate = dateField.getText();

            String query = "UPDATE DONATION SET donationName = '" + newName + "', donationamt = '" + newAmount + "', donationdate = '" + newDate + "' WHERE donation_ID = '" + newID + "'";
            sendDBCommand(query);
            for (int i = 0; i < donList.size(); i++) {
                if (donList.get(i).getDonationID() == newID) {
                    donList.get(i).setDonationID(newID); 
                    donList.get(i).setDonationAmt(newAmount);
                    donList.get(i).setDonationName(newName);
                    donList.get(i).setDonationDate(newDate); 
                    break;
                }
            }
            message.setText("Modify entry successful!");
            tableData.clear();
            for (Donation1 don : donList) {
                tableData.add(don);
            }
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
