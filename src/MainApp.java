import backend.Scrapper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Scrapper cScrapper = new Scrapper("https://edition.cnn.com/2024/05/30/sport/simone-biles-xfinity-us-gymnastics-championships-spt-intl/index.html");
        String title  = cScrapper.getTitle();
        String author = cScrapper.getAuthor();
        // Create a Label
        Label label = new Label(title);
        Label label2 = new Label(author);

        // Create a layout and add components
        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(label2);

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