package backend;

public class Type {
    Basic basic;
        /* String[] author;
        String name;
        String exist_date;
        String URL;
        String date_access = "today";*/ 

    public Type(){}

    class Book {    
        int edition;
        String publisher;
        String placePublish;

        public Book(int edition, String publisher, String placePublish){
            this.edition = edition;
            this.publisher = publisher;
            this.placePublish = placePublish;
        }
    }
    
    class JournalArticle {

    }
}