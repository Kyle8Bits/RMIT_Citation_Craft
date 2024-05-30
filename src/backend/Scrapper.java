package backend;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Scrapper{
    private String url;

    public Scrapper(String url){
        this.url = url;
    }

    public String getTitle(){
            try{
                Document doc = Jsoup.connect(url).get();
                return doc.title();
            }
            catch (IOException ex){
                return "ErrorTitle";
            }
    }
    
    public String getAuthor(){
        try {
            // Connect to the website and get the HTML
            Document doc = Jsoup.connect(url).get();

            // Select the HTML element that contains the author's name
            // Replace "cssQuery" with the appropriate CSS Selector
            Element authorElement = doc.selectFirst(".byline__name");

            // Extract the text from the element, which is the author's name
            if (authorElement != null) {
                String authorName = authorElement.text();
                return authorName;
            } else {
                return "Author not found.";
            }

        } catch (Exception e) {
            return "ErrorAuthorName";
        }
    }
}
