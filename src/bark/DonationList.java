package bark;

import static bark.Login1.conn;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.io.*;
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
    Label totalDonations = new Label("Total Donated:");
    Label donationField = new Label();

    Label idField = new Label();
    TextField nameField = new TextField();
    TextField amountField = new TextField();
    TextField dateField = new TextField();
    Label message = new Label();

    Button backBtn = new Button("Back");
    Button add = new Button("Add");
    Button delete = new Button("Delete");
    Button modify = new Button("Modify");
    Button populate = new Button("<-- Select and Populate");
    Button report = new Button("Generate Report");
    Button showAll = new Button("Show All");

    ComboBox donationYears = new ComboBox();

    GridPane table = new GridPane();
    GridPane buttons = new GridPane();
    GridPane overall = new GridPane();

    public DonationList(Home home) {
        paneSettings(buttons);

        table.add(message, 0, 0);
        table.add(donTable, 0, 1);
        table.add(populate, 0, 2);
        table.add(donationYears, 0, 3);
        table.add(report, 0, 4);
        table.add(showAll, 0, 5);

        buttons.add(backBtn, 0, 0);
        buttons.add(donoID, 0, 1);
        buttons.add(idField, 1, 1);
        buttons.add(donorName, 0, 2);
        buttons.add(nameField, 1, 2);
        buttons.add(donoAmount, 0, 3);
        buttons.add(amountField, 1, 3);
        buttons.add(donoDate, 0, 4);
        buttons.add(dateField, 1, 4);
        buttons.add(totalDonations, 0, 5);
        buttons.add(donationField, 1, 5);
        buttons.add(add, 0, 6);
        buttons.add(delete, 1, 6);
        buttons.add(modify, 0, 7);

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

        sendDBCommand("SELECT donationDate from DONATION");
        String[] dates = new String[25];
        String[] years = new String[25];
        try {
            for (int i = 0; i < 100; i++) {
                while (rs.next()) {
                    if (rs != null) {
                        dates[i] = rs.getString("donationDate");
                        break;
                    }
                }
            }
            int counter = 1;
            for (int i = 0; i < dates.length; i++) {
                if (dates[i] != null) {
                    String[] array1 = dates[i].split("-");
                    for (String a : array1) {
                        if (counter % 3 == 0) {
                            years[i] = a;
                        }
                        counter++;
                    }
                }
            }
            String[] unique = Arrays.stream(years).distinct().toArray(String[]::new);
            for (String a : unique) {
                if (a != null) {
                    donationYears.getItems().add("20" + a);
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception! " + e);
        }

        sendDBCommand("SELECT donation_ID, donationAmt, donationName, donationDate, volID FROM Donation");

        try {
            for (int i = 0; i < 100; i++) {
                while (rs.next()) {
                    if (rs != null) {
                        donList.add(new Donation1(rs.getInt("donation_ID"), rs.getString("donationAmt"),
                                rs.getString("donationName"), rs.getString("donationDate"), rs.getInt("volID")));
                        break;
                    }
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

        int amount = 0;
        for (int i = 0; i < donList.size(); i++) {
            String[] donationArray = new String[20];
            donationArray[i] = donList.get(i).getDonationAmt().replace("$", "");
            donationArray[i] = donationArray[i].replace(",", "");
            for (int x = 0; x < donationArray.length; x++) {
                if (donationArray[x] != null) {
                    amount += Double.valueOf(donationArray[x]);
                }
            }
        }
        donationField.setText("$" + String.valueOf(amount) + ".00");
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
            message.setText("Entry removed successfully.");
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

        add.setOnAction(e -> {
            int largest = 0;
            try {
                String q = "SELECT * FROM DONATION";
                sendDBCommand(q);
                try {
                    while (rs.next()) {
                        largest = rs.getInt("donation_ID");
                        while (rs.next()) {
                            int store = rs.getInt("donation_ID");
                            if (store > largest) {
                                largest = store;
                            }
                        }
                    }
                } catch (Exception m) {
                    System.out.println("Exception finding the largest! " + m);
                }
                int newID = largest + 1;
                String newName = nameField.getText();
                String newAmount = amountField.getText();
                String newDate = dateField.getText();

                Donation1 newDonation = new Donation1(newID, newAmount, newName, newDate);
                donList.add(newDonation);

                String query = "INSERT INTO DONATION(donation_ID, donationAmt, donationName, donationDate) VALUES (" + newID + ", '" + newAmount + "', '" + newName + "', '" + newDate + "')";

                sendDBCommand(query);
                tableData.clear();
                for (Donation1 x : donList) {
                    tableData.add(x);
                }

            } catch (Exception ex) {
                System.out.println("Error! " + ex);
            }
        });

        report.setOnAction(e -> {
            tableData.clear();                                                  //Clear the table data
            donationField.setText("");
            Donation1[] applicableDonations = new Donation1[20];
            int amount1 = 0;
            String[] dates1 = new String[20];                                   //Array to hold the date values    
            for (int i = 0; i < donList.size(); i++) {                          //Loop through the donationList array 
                if (donList.get(i) != null) {
                    dates1[i] = donList.get(i).getDonationDate();               //Insert values from DonationList into new array  
                    int counter = 1;                                            //Counter to determine which index's represent years 
                    String[] array1 = dates1[i].split("-");                     //Split each date value into a new array 
                    for (String a : array1) {                                   //Iterate through each date value   
                        if (counter % 3 == 0) {                                 //Use counter division to determine which are years 
                            if (donationYears.getValue().equals("20" + a)) {    //If combobox value = array value, add to table 
                                tableData.add(donList.get(i));
                                applicableDonations[i] = donList.get(i);
                            }
                        }
                        counter++;                                              //Increment counter after each date value                                            
                    }
                }
                String[] stringDonations = new String[20];
                if (applicableDonations[i] != null) {
                    stringDonations[i] = applicableDonations[i].getDonationAmt().replace("$", "");
                    stringDonations[i] = stringDonations[i].replace(",", "");
                    amount1 += Double.valueOf(stringDonations[i]);
                }
                donationField.setText("$" + String.valueOf(amount1) + ".00");
            }
        });

        backBtn.setOnAction(e -> {
            primaryStage.close();
        });

        showAll.setOnAction(e -> {
            tableData.clear();
            for (Donation1 x : donList) {
                tableData.add(x);
            }
            int amount2 = 0;
            for (int i = 0; i < donList.size(); i++) {
                String[] donationArray = new String[20];
                donationArray[i] = donList.get(i).getDonationAmt().replace("$", "");
                donationArray[i] = donationArray[i].replace(",", "");
                for (int x = 0; x < donationArray.length; x++) {
                    if (donationArray[x] != null) {
                        amount2 += Double.valueOf(donationArray[x]);
                    }
                }
            }
            donationField.setText("$" + String.valueOf(amount2) + ".00");
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
