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
        buttons.add(donorName, 0, 1);
        buttons.add(nameField, 1, 1);
        buttons.add(donoAmount, 0, 2);
        buttons.add(amountField, 1, 2);
        buttons.add(donoDate, 0, 3);
        buttons.add(dateField, 1, 3);
        buttons.add(add, 0, 5);
        buttons.add(delete, 1, 5);
        buttons.add(modify, 0, 6);

        viewPaw.setFitHeight(50);
        viewPaw.setFitWidth(50);
        viewPaw.setX(100);
        viewPaw.setY(150);
        table.add(viewPaw, 2, 9);

        donTable.setItems(tableData);

        id_col.setCellValueFactory(new PropertyValueFactory<Donation1, Integer>("donation_ID"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Donation1, String>("donationAmt"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Donation1, String>("donationName"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Donation1, Date>("donationDate"));

        donTable.getColumns().addAll(id_col, amountColumn, nameColumn, dateColumn);

        sendDBCommand("SELECT donation_ID, donationAmt, donationName, donationDate, volID FROM Donation");
        try {
            while (rs.next()) {
                    tableData.add(new Donation1(rs.getInt("donation_ID"), rs.getString("donationAmt"), 
                            rs.getString("donationName"), rs.getDate("donationDate"), rs.getInt("volID")));
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
