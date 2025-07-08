package models;


import java.time.Year;

public class DemoBook extends Book{

    public DemoBook(String isbn, String title, Year yearPublished, double price) {
        super(isbn, title, yearPublished, price);
    }

}
