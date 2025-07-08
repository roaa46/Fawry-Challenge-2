package models;

import interfaces.Purchasable;
import interfaces.Shippable;
import services.ShippingService;

import java.time.Year;

public class PaperBook extends Book implements Purchasable, Shippable {

    private int numInStock;
    private ShippingService shippingService;

    public PaperBook(String isbn, String title, Year yearPublished, double price, int numInStock) {
        super(isbn, title, yearPublished, price);
        this.numInStock = numInStock;
        this.shippingService = new ShippingService();
    }

    public int getNumInStock() {
        return numInStock;
    }

    public void setNumInStock(int numInStock) {
        this.numInStock = numInStock;
    }

    public ShippingService getShippingService() {
        return shippingService;
    }

    public void setShippingService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @Override
    public double buyBook(String email, String address, int quantity) {
        // email is ignored for PaperBook
        if (quantity <= 0)
            throw new RuntimeException("Please select quantity greater than 0 for " + super.getTitle() + " book");
        else if (numInStock == 0) {
            throw new RuntimeException(super.getTitle() + " is out of stock");
        } else if (quantity > numInStock)
            throw new RuntimeException("Maximum available quantity is " + numInStock + " for " + super.getTitle() + " book");
        else {
            numInStock -= quantity;
            shipTo(address);
            return super.getPrice() * quantity;
        }
    }

    @Override
    public void shipTo(String address) {
        ShippingService.send(address);
    }
}
