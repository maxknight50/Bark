
package bark;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import tables.Volunteer;

/**
 *
 */
public class VolunteerList {
    
    TableView<Volunteer> volTable = new TableView<>();
    ObservableList<Volunteer> tableData = FXCollections.observableArrayList();
    
    
}
