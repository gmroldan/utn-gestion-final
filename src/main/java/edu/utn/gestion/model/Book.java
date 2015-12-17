package edu.utn.gestion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(nullable = false, length = 12)
    private String isbn;

    @Column(nullable = false, unique = true, length = 30)
    private String title;

    @Column(nullable = false, length = 150)
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int currentStock;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false, length = 30)
    private String author;

    @Column(nullable = false, length = 30)
    private String editorial;

    @Column(nullable = false)
    private int minimumStock;

    /**
     * No-args Constructor.
     */
    public Book() {
    }

    /**
     * Class constructor.
     *
     * @param title
     * @param description
     * @param isbn
     * @param price
     * @param currentStock
     * @param category
     * @param author
     * @param editorial
     * @param minimumStock
     */
    public Book(String isbn, String title, String description, double price
            , int currentStock, Category category, String author, String editorial
            , int minimumStock) {
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.price = price;
        this.currentStock = currentStock;
        this.category = category;
        this.author = author;
        this.editorial = editorial;
        this.minimumStock = minimumStock;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(int minimumStock) {
        this.minimumStock = minimumStock;
    }
}
