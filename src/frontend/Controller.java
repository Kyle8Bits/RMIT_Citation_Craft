package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

public class Controller {

    @FXML
    private ComboBox<String> comboBox;
    
    @FXML
    private VBox additionalFields;

    public void initialize() {
        // Initialize the ComboBox with some values
        comboBox.getItems().addAll("Option 1", "Option 2", "Option 3");
    }

    @FXML
    protected void handleComboBoxAction() {
        // Clear previous text fields
        additionalFields.getChildren().clear();
        
        // Depending on the selection, add text fields
        int numberOfFields = comboBox.getSelectionModel().getSelectedIndex() + 1;
        for (int i = 0; i < numberOfFields; i++) {
            additionalFields.getChildren().add(new TextField("Field " + (i + 1)));
        }
    }

    @FXML
    protected void handleAddMore() {
        ComboBox<String> newComboBox = new ComboBox<>();
        newComboBox.getItems().addAll("Option 1", "Option 2", "Option 3");
        
        TextField newTextField = new TextField();
        VBox newVBox = new VBox(newComboBox, newTextField);
        additionalFields.getChildren().add(newVBox);
    }
}
