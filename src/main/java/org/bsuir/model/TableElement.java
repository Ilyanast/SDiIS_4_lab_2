package org.bsuir.model;

public class TableElement {
    private String bookName;
    private String authorInitials;
    private String publisherInitials;
    private int tomAmount;
    private int circulation;
    private int finalTomAmount;

    public TableElement(String bookName, String authorInitials, String publisherInitials, int tomAmount, int circulation, int finalTomAmount) {
        this.bookName = bookName;
        this.authorInitials = authorInitials;
        this.publisherInitials = publisherInitials;
        this.tomAmount = tomAmount;
        this.circulation = circulation;
        this.finalTomAmount = finalTomAmount;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorInitials() {
        return authorInitials;
    }

    public void setAuthorInitials(String authorInitials) {
        this.authorInitials = authorInitials;
    }

    public String getPublisherInitials() {
        return publisherInitials;
    }

    public void setPublisherInitials(String publisherInitials) {
        this.publisherInitials = publisherInitials;
    }

    public int getTomAmount() {
        return tomAmount;
    }

    public void setTomAmount(int tomAmount) {
        this.tomAmount = tomAmount;
    }

    public int getCirculation() {
        return circulation;
    }

    public void setCirculation(int circulation) {
        this.circulation = circulation;
    }

    public int getFinalTomAmount() {
        return finalTomAmount;
    }

    public void setFinalTomAmount(int finalTomAmount) {
        this.finalTomAmount = finalTomAmount;
    }
}
