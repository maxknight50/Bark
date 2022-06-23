package bark;

import static bark.Login1.conn;
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
    
    Label donorName = new Label("Donor Name");
    Label donoAmount = new Label("Donation Amount");
    Label donoDate = new Label("Donation Date");
    
    TextField nameField = new TextField(); 
    TextField amountField = new TextField(); 
    TextField dateField = new TextField(); 
    
    Button backBtn = new Button("Back");
    Button add = new Button("Add");
    Button delete = new Button("Delete");
    Button modify = new Button("Modify");
    Button populate = new Button("<-- Select and Populate");

    GridPane tPane1 = new GridPane();

    public DonationList(Home home) {
        donTable.setItems(tableData);
        tPane1.add(donTable, 0, 0);

        id_col.setCellValueFactory(new PropertyValueFactory<Donation1, Integer>("donation_ID"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Donation1, String>("donationAmt"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Donation1, String>("donationName"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Donation1, Date>("donationDate"));
        volIdColumn.setCellValueFactory(new PropertyValueFactory<Donation1, Integer>("volID"));

        donTable.getColumns().addAll(id_col, amountColumn, nameColumn, dateColumn, volIdColumn);

        sendDBCommand("SELECT donation_ID, donationAmt, donationName, donationDate, volID FROM Donation");
        try {
            Donation1[] dList = new Donation1[25];
            for (int i = 0; i < 100; i++) {
                while (rs.next()) {
                    if (rs != null) {
                        dList[i] = new Donation1(rs.getInt("donation_ID"), rs.getString("donationAmt"), rs.getString("donationName"), rs.getDate("donationDate"), rs.getInt("volID"));
                        break;
                    }
                }
            }
            for (Donation1 x : dList) {
                tableData.add(x);
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception! " + e);
        }
        //menuDonations.getItems().add(enterDonations);
        //menuBar.getMenus().addAll(menuDonations);
        //tPane1.add(menuBar, 0, 0);
        Scene primaryScene = new Scene(tPane1, 600, 450);
        donTable.setMinWidth(primaryScene.getWidth()); 
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
            System.out.println("RESULT SET: " + rs);

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
