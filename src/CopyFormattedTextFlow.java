import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class CopyFormattedTextFlow extends Application {
    @Override
    public void start(Stage primaryStage) {
        TextFlow textFlow = new TextFlow();
        Text text1 = new Text("Hello, ");
        text1.setFont(Font.font("Arial", FontPosture.ITALIC, 12));  // This text will be italic
        Text text2 = new Text("right-click to copy this text.");
        textFlow.getChildren().addAll(text1, text2);

        ContextMenu contextMenu = new ContextMenu();
        MenuItem copy = new MenuItem("Copy with Format");
        copy.setOnAction(e -> {
            ClipboardContent content = new ClipboardContent();
            // Creating HTML content
            String htmlContent = "<html><body><p><i>" + text1.getText() + "</i>" + text2.getText() + "</p></body></html>";
            content.putHtml(htmlContent);
            Clipboard.getSystemClipboard().setContent(content);
        });
        contextMenu.getItems().add(copy);

        textFlow.setOnContextMenuRequested(e -> contextMenu.show(textFlow, e.getScreenX(), e.getScreenY()));

        Scene scene = new Scene(new StackPane(textFlow), 300, 200);
        primaryStage.setTitle("Copy Formatted Text from TextFlow");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
