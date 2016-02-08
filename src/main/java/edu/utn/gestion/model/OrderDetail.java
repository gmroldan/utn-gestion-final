package edu.utn.gestion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by martin on 25/12/15.
 */
@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    private int quantity;

    /**
     * No args constructor. Creates a new instance of <code>OrderDetail</code>.
     */
    public OrderDetail() {}

    /**
     * Creates a new instance of <code>OrderDetail</code>
     *
     * @param book
     * @param quantity Number of units for the given book.
     */
    public OrderDetail(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    /**
     * Creates a new instance of <code>OrderDetail</code>
     *
     * @param book
     * @param quantity Number of units for the given book.
     */
    public OrderDetail(long id, Book book, int quantity) {
        this.id = id;
        this.book = book;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
