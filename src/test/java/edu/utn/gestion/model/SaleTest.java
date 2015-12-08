package edu.utn.gestion.model;

import edu.utn.gestion.util.BookFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by martin on 08/12/15.
 */
public class SaleTest {
    private Sale sale;

    @Before
    public void setUp() throws Exception {
        this.sale = new Sale();
    }

    @Test
    public void addSaleDetailTest() throws Exception {
        Book book1 = BookFactory.createBook();
        Book book2 = BookFactory.createBook();

        Double expectedTotalAmount = (book1.getPrice() * 3) + (book2.getPrice() * 2);
        Integer expectedSaleDetailsSize = 2;

        this.sale.addSaleDetail(book1, 1);
        this.sale.addSaleDetail(book2, 2);
        this.sale.addSaleDetail(book1, 2);

        Double totalAmount = this.sale.getTotalAmount();
        Integer saleDetailsSize = this.sale.getSaleDetails().size();

        assertEquals(expectedTotalAmount, totalAmount);
        assertEquals(expectedSaleDetailsSize, saleDetailsSize);
    }
}