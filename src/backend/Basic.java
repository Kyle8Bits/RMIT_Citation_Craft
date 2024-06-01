package backend;

public class Basic {
    //basic
    String[] author;
    String name;
    String exist_date;
    String URL;
    String date_access = "today";

    public Basic(String[] author, String name, String exist_date, String URL){
        for(int i = 0; i < author.length; i++){
            this.author[i] = author[i];
        }

        this.exist_date =  exist_date;
        this.name = name;
        this.URL = URL;
    }
}
