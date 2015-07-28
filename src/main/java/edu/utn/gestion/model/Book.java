package edu.utn.gestion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true, length = 30)
    private String title;

    @Column(nullable = false, length = 150)
    private String description;

    @Column(nullable = false, length = 12)
    private String isbn;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int stock;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false, length = 30)
    private String author;

    @Column(nullable = false, length = 30)
    private String editorial;

    /**
     * No-args Constructor.
     */
    public Book() {
    }

    /**
     * Class constructor.
     *
     * @param id
     * @param title
     * @param description
     * @param isbn
     * @param price
     * @param stock
     * @param category
     * @param author
     * @param editorial
     */
    public Book(long id, String title, String description, String isbn,
            double price, int stock, Category category, String author, String editorial) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.author = author;
        this.editorial = editorial;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

}
