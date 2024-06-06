package frontend;

import frontend.RMIT_Harvard;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller implements Initializable{
    int citeNumber = 0;

    TextFlow text = new TextFlow();

    ArrayList<Pane> list = new ArrayList<>();

    @FXML
    ContextMenu contextMenu = new ContextMenu();

    @FXML
    ChoiceBox<String> selectStyle;

    @FXML
    VBox formContainer = new VBox();

    @FXML
    ScrollPane formScrollPane = new ScrollPane();

    @FXML
    VBox vboxResult = new VBox();

    String[] style = {"RMIT Harvard", "IEEE"};
    String[] type = {
        "Books",
        "Journal articles",
        "Newspaper and magazine articles",
        "Conference papers",
        "Websites and webpage documents",
        "Social media",
        "Audiovisual material",
        "Artworks, images, tables and graphs",
        "Reports and data sets",
        "Standards and patents",
        "Theses and dissertations",
        "Other sources"
    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectStyle.getItems().addAll(style);
        createForm(null);
        
    }

    public void onPlusButton(){
        createForm(null);
        citeNumber++;
    }

    public void createForm(String typeValue){
        Pane citeContainer = new Pane();
        citeContainer.setMinSize(1080,200);
        ChoiceBox<String> selectType = new ChoiceBox<>();
        selectType.setId("selectBox" + citeNumber);
        selectType.getItems().addAll(type);
        selectType.setPrefSize(200, 35);

        citeContainer.getChildren().add(selectType);
        formContainer.getChildren().addFirst(citeContainer);


        TextField[] form = setPositionNews();

        double layoutX = 0;
        double layoutY = 50;
        for (int i = 1; i < 7; i++){
            if(layoutX + 100 > citeContainer.getMinWidth()){
                layoutY += 50;
                layoutX = 0;
            }

            form[i-1].setLayoutX(layoutX);
            form[i-1].setLayoutY(layoutY);
            layoutX = layoutX + form[i-1].getPrefWidth() + 50;

            citeContainer.getChildren().add(i, form[i-1]);
        }

        list.add(citeContainer);
    }

    public TextField[] setPositionNews(){
        TextField[] form = new TextField[6];

        TextField author = new TextField();
        author.setPromptText("Author");
        form[0] = author;
        form[0].setPrefSize(100,20);

        TextField datePublished = new TextField();
        datePublished.setPromptText("Date publisher(DD/MM/YYYY)");
        form[1] = datePublished;
        form[1].setPrefSize(200,20);

        TextField title = new TextField();
        title.setPromptText("Title");
        form[2] = title;
        form[2].setPrefSize(100,20);
        
        TextField paper = new TextField();
        paper.setPromptText("Name of paper or magazine");
        form[3] = paper;
        form[3].setPrefSize(400,20);

        TextField dateAccesed = new TextField();
        dateAccesed.setPromptText("Date accessed (Leave blank if today)");
        form[4] = dateAccesed;
        form[4].setPrefSize(400,20);

        TextField URL = new TextField();
        URL.setPromptText("Link to the sources");
        form[5] = URL;
        form[5].setPrefSize(300,20);

        for(int i = 0; i < 6; i++){
            form[i].setFont(Font.font(20));
        }
        
        return form;

    }

    public void onSubmit(){
        
        for (int i = 0; i <= citeNumber; i++){
            Pane resultPane = list.get(i);
            String[] information = new String[resultPane.getChildren().size()];

            for(int y = 1; y < resultPane.getChildren().size();y++){
                TextField res =  (TextField) resultPane.getChildren().get(y);

                String resIdx = res.getText();

                information[y-1] = resIdx;

            }

            RMIT_Harvard rmit_Harvard = new RMIT_Harvard(information);
            vboxResult.getChildren().add(rmit_Harvard.createReferenceTextFlow());
            setUpContextMenu(rmit_Harvard.createReferenceTextFlow());
        }
    }

    private void setUpContextMenu(TextFlow textFlow) {
        MenuItem copyItem = new MenuItem("Copy with Formatting");
        copyItem.setOnAction(event -> copyTextWithFormatting(textFlow));
        contextMenu.getItems().add(copyItem);

        textFlow.setOnContextMenuRequested(event -> 
            contextMenu.show(textFlow, event.getScreenX(), event.getScreenY()));
    }

    private void copyTextWithFormatting(TextFlow textFlow) {
        String htmlContent = convertToHTML(textFlow);
        ClipboardContent content = new ClipboardContent();
        content.putHtml(htmlContent);
        Clipboard.getSystemClipboard().setContent(content);
    }

    private String convertToHTML(TextFlow textFlow) {
        StringBuilder html = new StringBuilder("<html><body>");
        for (var child : textFlow.getChildren()) {
            if (child instanceof Text) {
                Text text = (Text) child;
                String style = text.getFont().getStyle().contains("Italic") ? "<i>" : "";
                html.append(style).append(text.getText()).append(style.isEmpty() ? "" : "</i>");
            }
        }
        html.append("</body></html>");
        return html.toString();
    }
}
