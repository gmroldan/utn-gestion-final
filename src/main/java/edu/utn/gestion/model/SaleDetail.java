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
 * Created by martin on 08/12/15.
 */
@Entity
@Table(name = "sale_detail")
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double amount;

    /**
     * No args constructor. Returns an instance of SaleDetail.
     */
    public SaleDetail() {}

    /**
     * Returns an instance of SaleDetail.
     *
     * @param book
     * @param quantity Number of units for the given book.
     */
    public SaleDetail(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
        this.updateAmout();
    }

    /**
     * Updates the amount for a given sale detail.
     */
    private void updateAmout() {
        if (book != null) {
            this.amount = this.book.getPrice() * this.quantity;
        }
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
        this.updateAmout();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
