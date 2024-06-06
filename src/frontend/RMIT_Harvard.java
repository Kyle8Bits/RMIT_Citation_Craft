package frontend;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class RMIT_Harvard {
    private String[] information;
    private int num = 0;

    public RMIT_Harvard(String[] arry){
        this.information = arry;
        this.num++;
    }

    private String authorName(String fullName){
        if (fullName == null || fullName.isEmpty()) {
            return ""; // Return an empty string or handle this as needed
        }
    
        String[] parts = fullName.trim().split("\\s+");
        if (parts.length < 2) {
            return fullName; // Handle cases where there might not be a last name
        }
    
        String firstName = parts[0];
        String lastName = parts[parts.length - 1]; // Assuming the last part is the family name
        char initial = firstName.charAt(0);
    
        return lastName + " " + initial;
    }

    private static String formatDate(String dateStr) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateStr, inputFormatter);

        String day = date.getDayOfMonth() + getDaySuffix(date.getDayOfMonth());
        String month = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String year = Integer.toString(date.getYear());

        return day + " " + month + " " + year;
    }

    private static String getDaySuffix(int day) {
        if (day >= 11 && day <= 13) {
            return "th";
        }
        switch (day % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    public String refNew(){
        //Author's family name Initial (Day Month Year) 'Title of article: subtitle of article', Name of Newspaper or Magazine, accessed Day Month Year. URL
        Text italicText = new Text(information[3]);
        italicText.setFont(Font.font("SYSTEM", FontPosture.ITALIC, 25));

        String resulString = 
        "["+num+"]" + " " + authorName(information[0]) + " (" + formatDate(information[1])+ " )" + " \'" + information[2] + "\', " + italicText.getText() +", accessed " + formatDate(information[4]) + ". " + information[5];
        
        return resulString;
    }

    public TextFlow createReferenceTextFlow() {
        String st = "["+ num +"]";
        String author = authorName(information[0]);
        String date = formatDate(information[1]);
        String title = information[2];
        String newspaperName = information[3];
        String accessedDate = formatDate(information[4]);
        String url = information[5];
    
        Text textAuthor = new Text(st+ " " + author + " (" + date + ") '");
        Text textTitle = new Text(title);
        Text textNewspaper = new Text("', " + newspaperName);
        textNewspaper.setFont(Font.font("Arial", FontPosture.ITALIC, 12));
        Text textAccessed = new Text(", accessed " + accessedDate + ". ");
        Text textUrl = new Text(url);
    
        TextFlow textFlow = new TextFlow(textAuthor, textTitle, textNewspaper, textAccessed, textUrl);
        return textFlow;
    }
}
