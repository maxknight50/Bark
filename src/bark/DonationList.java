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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;
import tables.*;

/**
 *
 */
public class DonationList extends Application {

    Home home;

    Statement stmt;
    static Connection conn;
    ResultSet rs;

    TableView<Donation> volTable = new TableView<>();
    ObservableList<Donation> tableData = FXCollections.observableArrayList();

    TableColumn id_col = new TableColumn("Donation ID");
    TableColumn amountColumn = new TableColumn("Amount");
    TableColumn nameColumn = new TableColumn("Donor Name");
    TableColumn dateColumn = new TableColumn("Date");
    TableColumn volIdColumn = new TableColumn("VolunteerID");

    GridPane tPane1 = new GridPane();

    public void start(Stage primaryStage) {
        volTable.setItems(tableData);
        tPane1.add(volTable, 0, 0);
        
        id_col.setCellValueFactory(new PropertyValueFactory<Donation, Integer>("donation_ID"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Donation, String>("donationAmt"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Donation, String>("donationName"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Donation, Date>("donationDate"));
        volIdColumn.setCellValueFactory(new PropertyValueFactory<Donation, Integer>("volID"));

        volTable.getColumns().addAll(id_col, amountColumn, nameColumn, dateColumn, volIdColumn); 
        
        sendDBCommand("SELECT donation_ID, donationAmt, donationName, donationDate, volID FROM Donation"); 
        System.out.println("RESULTSET: " + rs);
        try {
            Donation[] donationList = new Donation[25];
            for(int i = 0; i < 100; i++){
                while(rs.next()){
                    if (rs != null){
                        donationList[i] = new Donation(rs.getInt("donation_ID"), rs.getString("donationAmt"), rs.getString("donationName"), rs.getDate("donationDate"), rs.getInt("volID"));
                        break;
                    }
                }
            }
            for(Donation x : donationList){
                tableData.add(x); 
            }
            
        } catch (SQLException e){
            System.out.println("SQL Exception! " + e);
        }

        Scene primaryScene = new Scene(tPane1, 600, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("List");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
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
