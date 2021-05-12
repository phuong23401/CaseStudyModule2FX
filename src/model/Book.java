package model;

public class Book {
    private int id;
    private String name, kindOfBook;
    private int price, numOfBook;
    private String status;

    public Book(int id, String name, String kindOfBook, int price, int numOfBook, String status) {
        this.id = id;
        this.name = name;
        this.kindOfBook = kindOfBook;
        this.price = price;
        this.numOfBook = numOfBook;

        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKindOfBook() {
        return kindOfBook;
    }

    public void setKindOfBook(String kindOfBook) {
        this.kindOfBook = kindOfBook;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumOfBook() {
        return numOfBook;
    }

    public void setNumOfBook(int numOfBook) {
        this.numOfBook = numOfBook;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return "Id: " + id +
                ", Tên: " + name +
                ", Loại sách: " + kindOfBook +
                ", Giá: " + price +
                ", Số lượng: " + numOfBook +
                ", Tình trạng: " + status +
                "\n";
    }
}
