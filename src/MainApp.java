import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a Label
        Label helloLabel = new Label("Hello, JavaFX!");

        // Create a Button
        Button clickMeButton = new Button("Click me!");

        // Add an action on button click
        clickMeButton.setOnAction(event -> {
            helloLabel.setText("Button clicked!");
        });

        // Create a layout and add components
        StackPane root = new StackPane();
        root.getChildren().addAll(helloLabel, clickMeButton);

        // Create a scene
        Scene scene = new Scene(root, 300, 250);

        // Set the scene to the stage and configure the stage
        primaryStage.setTitle("JavaFX Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Launch the application
        launch(args);
    }
}