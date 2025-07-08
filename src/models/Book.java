package models;

import java.time.Year;

public abstract class Book {

    private String isbn;
    private String title;
    private Year yearPublished;
    private double price;

    public Book(String isbn, String title, Year yearPublished, double price) {
        this.isbn = isbn;
        this.title = title;
        this.yearPublished = yearPublished;
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Year getYearPublished() {
        return yearPublished;
    }

    public double getPrice() {
        return price;
    }

    public boolean isOutdated(Year currentYear, int maxAge) {
        return yearPublished.plusYears(maxAge).isBefore(currentYear);
    }

    public void printDetails() {
        System.out.println(
                "Title: " + title + ", " +
                "ISBN: " + isbn + ", " +
                "Year: " + yearPublished + ", " +
                "Price: $" + price);
    }
}
