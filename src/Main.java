import models.Book;
import models.DemoBook;
import models.EBook;
import models.PaperBook;
import services.Inventory;

import java.time.Year;

public class Main {
    private static Inventory inventory = new Inventory();

    public static void main(String[] args) {
        System.out.println("\n************************************************");
        System.out.println("Invalid case: Try to buy while inventory is empty");
        System.out.println("************************************************");

        try {
            inventory.buy("1234567890000", 1, "roaa@example.com", "Cairo");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        // Add all books
        Book paperBook1 = new PaperBook("9780132350884", "Clean Code", Year.of(2008), 45, 5);
        Book paperBook2 = new PaperBook("9780201485677", "Refactoring: Improving the Design of Existing Code", Year.of(1999), 55, 2);
        Book eBook1 = new EBook("9780134494166", "Clean Architecture", Year.of(2017), 50, "PDF");
        Book eBook2 = new EBook("9780262033848", "Introduction to Algorithms", Year.of(2009), 49.99, "PDF");
        Book demoBook = new DemoBook("9781617298691", "Spring Start Here", Year.of(2021), 40);

        inventory.addBook(paperBook1);
        inventory.addBook(paperBook2);
        inventory.addBook(eBook1);
        inventory.addBook(eBook2);
        inventory.addBook(demoBook);

        System.out.println("\nAll books have been added");

        System.out.println("\nAvailable books:");
        for (Book book : inventory.getAllBooks()) {
            book.printDetails();
        }

        // Run all scenarios
        validShippableOnly();
        invalidDemoBookPurchase();
        invalidQuantityZero();
        invalidQuantityForEBook();
        invalidQuantityExceedsStock();
        removeOldBooks();
    }

    public static void validShippableOnly() {
        System.out.println("\n************************************************");
        System.out.println("Valid case: Buy paper book and ebook");
        System.out.println("************************************************");

        try {
            double paid1 = inventory.buy("9780132350884", 2, "reader@gmail.com", "Cairo");
            System.out.println("Paper book purchased. Total paid: $" + paid1);

            double paid2 = inventory.buy("9780134494166", 1, "roaa@example.com", "Menoufia");
            System.out.println("EBook purchased. Total paid: $" + paid2);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void invalidDemoBookPurchase() {
        System.out.println("\n************************************************");
        System.out.println("Invalid case: Try to buy a demo book");
        System.out.println("************************************************");

        try {
            inventory.buy("9781617298691", 1, "roaa@gmail.com", "Menoufia");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void invalidQuantityZero() {
        System.out.println("\n************************************************");
        System.out.println("Invalid case: Try to buy with quantity = 0");
        System.out.println("************************************************");

        try {
            inventory.buy("9780201485677", 0, "roaa@gmail.com", "Cairo");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void invalidQuantityForEBook() {
        System.out.println("\n************************************************");
        System.out.println("Invalid case: Try to buy ebook with quantity != 1");
        System.out.println("************************************************");

        try {
            inventory.buy("9780262033848", 5, "roaa@gmail.com", "Cairo");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void invalidQuantityExceedsStock() {
        System.out.println("\n************************************************");
        System.out.println("Invalid case: Try to buy more than available stock");
        System.out.println("************************************************");

        try {
            inventory.buy("9780201485677", 5, "roaa@gmail.com", "Cairo");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeOldBooks() {
        System.out.println("\n************************************************");
        System.out.println("Remove outdated books older than 10 years");
        System.out.println("************************************************");

        for (Book removed : inventory.removeOutdatedBooks(10)) {
            System.out.println("Removed -> " + removed.getTitle());
        }
    }

}