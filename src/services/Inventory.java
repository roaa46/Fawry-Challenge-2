package services;

import interfaces.Purchasable;
import models.Book;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Inventory {
    Set<Book> books;

    public Inventory() {
        this.books = new HashSet<>();
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> removeOutdatedBooks(int maxAgeInYears) {
        Year currentYear = Year.now();
        List<Book> removed = new ArrayList<>();
        books.removeIf(book -> {
            boolean isOld = book.isOutdated(currentYear, maxAgeInYears);
            if (isOld) removed.add(book);
            return isOld;
        });
        return removed;
    }


    public double buy(String isbn, int quantity, String email, String address) {
        Book book = getBookByIsbn(isbn);

        if (!(book instanceof Purchasable)) {
            throw new RuntimeException("Quantum book store: This book cannot be purchased.");
        }

        Purchasable purchasableBook = (Purchasable) book;
        return purchasableBook.buyBook(email, address, quantity);
    }

    public Book getBookByIsbn(String isbn) {
        if (books.isEmpty())
            throw new RuntimeException("There is no available books to buy");
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        throw new RuntimeException("Book with ISBN " + isbn + " not found");
    }

    public Set<Book> getAllBooks() {
        if (books.isEmpty())
            throw new RuntimeException("There is no available books to buy");
        return books;
    }

}
