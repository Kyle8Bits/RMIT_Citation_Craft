package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Controller {

    @FXML
    private VBox mainContainer;

    @FXML
    public void initialize() {
        addComboAndTextField();
    }

    @FXML
    private void addComboAndTextField() {
        ComboBox<String> comboBox = new ComboBox<>();
        ObservableList<String> options = FXCollections.observableArrayList(
            "Option 1", "Option 2", "Option 3", "Option 4"
        );
        comboBox.setItems(options);
        
        TextField textField = new TextField();
        textField.setPromptText("Enter text");
        
        VBox additionalFields = new VBox(5.0);
        comboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            TextField newField = new TextField();
            newField.setPromptText("Additional for " + newVal);
            additionalFields.getChildren().add(newField);
        });
        
        mainContainer.getChildren().addAll(comboBox, textField, additionalFields);
    }
}
