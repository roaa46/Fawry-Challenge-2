package models;

import interfaces.Emailable;
import interfaces.Purchasable;
import services.MailService;

import java.time.Year;

public class EBook extends Book implements Purchasable, Emailable {

    private String fileType;
    private MailService mailService;

    public EBook(String isbn, String title, Year yearPublished, double price, String fileType) {
        super(isbn, title, yearPublished, price);
        this.fileType = fileType;
        this.mailService = new MailService();
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public MailService getMailService() {
        return mailService;
    }

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }


    @Override
    public double buyBook(String email, String address, int quantity) {
        if (quantity == 0)
            throw new RuntimeException("Please select quantity greater than 0 for " + super.getTitle() + " book");
        if (quantity != 1)
            throw new RuntimeException("EBooks are delivered individually, choose quantity = 1 for " + super.getTitle() + " book");
        sendTo(email);
        return getPrice();
    }

    @Override
    public void sendTo(String email) {
        MailService.send(email);
    }
}
